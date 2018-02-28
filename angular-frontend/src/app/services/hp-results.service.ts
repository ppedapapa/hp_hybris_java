import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { environment } from "../../environments/environment";
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import 'rxjs/add/operator/filter';

import { HpConfigService } from "./hp-config.service";
import { QuestionsService } from "./questions.service";
import { HpResultsModalComponent } from "../page/hp-results/hp-results-modal/hp-results-modal.component";
// import {variable} from "@angular/compiler/src/output/output_ast";

@Injectable()
export class HealthPrintResultsService {
    private allHealthPrintResultsSource = new BehaviorSubject<object>([]);
    private healthPrintResultInfoSource = new BehaviorSubject<object>({});
    allHealthPrintResults = this.allHealthPrintResultsSource.asObservable();
    healthPrintResultInfo = this.healthPrintResultInfoSource.asObservable();
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type':  'application/json; charset=utf-8',
            'Access-Control-Allow-Credentials': 'true',
            'Access-Control-Allow-Origin': '*',
            'Cache-Control': 'no-cache'
        })
    };
    resultsData;
    questions;
    closeResult: string;
    endPointAllHealthPrintResults = '/services/hp/getAllHealthPrints';
    endPointContent =  '/assets/mockjson/content.json';
    endPointRecommendation = '/services/hp/recommendations';
    endPointProduct = 'https://www.shakleedev.com:9002/shakleeintegration/v2/shakleeUS/products?fields=DEFAULT';

    constructor(private http: HttpClient,
                private questionsService: QuestionsService,
                private hpConfigService: HpConfigService,
                private modalService: NgbModal) {}

    getAllHealthPrints() {
        let healthProfile = JSON.parse(this.questionsService.getHealthProfileInfo());
        let request = {};
        console.log('getAllHealthPrints', healthProfile, request);

        if(healthProfile !== null) {
            if (healthProfile.email !== undefined) {
                request = {email: healthProfile.email};
            } else if (healthProfile.healthProfileId !== undefined) {
                request = {healthProfileId: healthProfile['healthProfileId']}
            }
        }
        return this.http.post(this.endPointAllHealthPrintResults, request, this.httpOptions);
    }

    getRecommendation(healthPrints) {
        this.questions = healthPrints.questions;
        return this.http.post(this.endPointRecommendation, this.questions, this.httpOptions);
    }

    private bundlesSubject: BehaviorSubject<any> = new BehaviorSubject({});
    private bundles = this.bundlesSubject.asObservable();

    private skuSubject: BehaviorSubject<any> = new BehaviorSubject({});
    private skuInfo = this.skuSubject.asObservable();

    getBundles() {
       return this.bundles;
    }

    getSkuInfo() {
       return this.skuInfo;
    }

    /*getAnsweredSubject(): Observable<any>  {
      return this.answeredSubject.asObservable();
    }

    setAnsweredSubject(answered: any) {
        console.log('setAnsweredSubject', answered);
        this.answeredSubject.next(answered);
    }*/

    setResultsData(responseData) {
        this.resultsData = responseData;
        let bundles = responseData['recommendations']['bundles'];
        let getCleanSku = this.hpConfigService.getCleanSku();
        let productDetails;
        let tmpArray = [];

        //Get healthprint skulist
        const skuList = this.getSkuList(bundles);
        for (let sku in skuList) {
            tmpArray.push(sku);
        }

        //Get Kids sku list and getClean sku
        const kidsSkuList = this.getKidsSkus();
        const skuString = tmpArray.join() + ',' + kidsSkuList.join() + ',' + getCleanSku;

        // get productInfo using skustring as input
        this.getProductContent(skuString).subscribe(responseData => {
            productDetails = responseData['products'];
            let productInfo = {};
            let image, price;

            if (!productDetails) {
                return;
            }

            productDetails.forEach(function (val) {
                if (val.images) {
                    val.images.forEach(function (imgVal) {
                        if (imgVal.format === 'thumbnail' && imgVal.imageType === 'PRIMARY') {
                            image = imgVal.url;
                        }
                    });
                }
                if (val.prices) {
                    val.prices.forEach(function (priceVal) {
                        if (priceVal.priceTier === 'SN') {
                            price = priceVal.value;
                        }
                    });
                }
                productInfo[val.sku] = {
                    title: val.baseProductName ? val.baseProductName : 'Title',
                    healthPrintDescription: val.healthPrintDescription ? val.healthPrintDescription : 'healthPrintDescription',
                    description: val.longDescription,
                    shortDescription: val.description,
                    itemImage: environment.hybrisServerName + image,
                    price: price
                };
            });

            this.skuSubject.next(productInfo);

            bundles.forEach((value, key) => {
                value.skus.forEach(function (value2, key2) {
                    if (value2.category === 'membership') {
                        bundles[key].skus[key2].info = {
                            title: 'shaklee-membership-title',
                            healthPrintDescription: 'shaklee-membership-description',
                            description: '',
                            shortDescription: '',
                            itemImage: '//images.shaklee.com/healthprint/ico-leaf.png'
                        };
                    } else {
                        bundles[key].skus[key2].info = productInfo[value2.sku] ? productInfo[value2.sku] : {
                            title: 'temp Title',
                            healthPrintDescription: 'temp healthPrintDescription',
                            description: 'temp longDescription',
                            shortDescription: 'temp description',
                            itemImage: '//images.shaklee.com/healthprint/ico-leaf.png',
                            price: 'temp price'
                        };
                    }
                });
            });

            let kidSkus = [];
            kidsSkuList.forEach(function (value, key) {
                const skuInfo =  productInfo[value] ? productInfo[value] : {
                    title: 'temp Title',
                    healthPrintDescription: 'temp healthPrintDescription',
                    description: 'temp longDescription',
                    shortDescription: 'temp description',
                    itemImage: '//images.shaklee.com/healthprint/ico-leaf.png',
                    price: 'temp price'
                };
                kidSkus.push({sku: value, info: skuInfo});
            });
            const kidsBundle = {skus: kidSkus, bundle: 'KIDS'};
            bundles.push(kidsBundle);

            this.bundlesSubject.next(bundles);
        });
    }

    getKidsSkus(){
        let kidsSkus =[];
        let dietRestrictions = this.questions.dietary_restrictions;
console.log('this.questions', this.questions);
        if(dietRestrictions !== null && dietRestrictions.indexOf('KOSHER')){
            kidsSkus = this.hpConfigService.getKosherSkus();
        }else{
            kidsSkus = this.hpConfigService.getNonKosherSkus();
        }

        if(dietRestrictions !== null && dietRestrictions.indexOf('SOY')){
            let nonSoySku = this.hpConfigService.getNonSoySku();
            let soySku = this.hpConfigService.getSoySku();
            kidsSkus.forEach((value, key) => {
                if(value === soySku){
                    kidsSkus[key] = nonSoySku;
                }
            });
        }
        return kidsSkus;
    }

    getContent() {
        return this.http.get(this.endPointContent);
    }

    isKids() {
        return this.questions.age <= 12;
    }

    getProductContent(sku) {
        let codes= '&codes='+sku;
        return this.http.get(this.endPointProduct+codes);
    }
    setAllHealthPrintResult(allHealthPrintResults:object) {
        this.allHealthPrintResultsSource.next(allHealthPrintResults);
        this.setHealthPrintResultInfo(allHealthPrintResults[0]);
    }

    setHealthPrintResultInfo(healthPrintResultInfo:object) {
        this.healthPrintResultInfoSource.next(healthPrintResultInfo);
    }

    private getSkuList(bundles) {
        let skuList = {};
        if (!bundles) {
            return false;
        }

        bundles.forEach( function(value, key) {
            if (!value.skus) {
                return false;
            }

            let tmpSkuList = [];
            let appSku = false;
            let free_membership=false;

            value.skus.forEach( function(value2, key2) {
                let sku = value2.sku;
                if(value2.category==="membership"){
                    appSku = true;
                    if(value2.sn_price===0){
                        free_membership=true;
                    }
                }

                skuList[sku] = value2.sku;
                let dataSku = {sku: value2.sku, qty: 1, freq: 1, selections: value2.sub_sku, membership_sku:appSku,free_membership:free_membership};
                tmpSkuList.push(dataSku);

                if (tmpSkuList.length > 0) {
                    bundles[key].skuList = tmpSkuList;
                }
                if (value2.sub_sku) {
                    value2.sub_sku.forEach( function(value3, key3) {
                        skuList[value3] = value3;
                    });
                }

            });

        });

        return skuList;
    }

    startOverQuiz() {
        const gotoUrl =  '/healthprint';
        localStorage.setItem('hpRetakeQuiz','true');
        window.location.href = gotoUrl;
    }

    openModal(name, data) {
        console.log('modalName', name);
        const modalRef = this.modalService.open(HpResultsModalComponent);
        modalRef.componentInstance.modalName = name;
        modalRef.componentInstance.modalData = data;
    }

    static getDismissReason(reason: any): string {
        if (reason === ModalDismissReasons.ESC) {
            return 'by pressing ESC';
        } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
            return 'by clicking on a backdrop';
        } else {
            return  `with: ${reason}`;
        }
    }
}

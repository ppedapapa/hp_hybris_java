import { Component, Input, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";
import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-hp-bundle',
  templateUrl: './hp-bundle.component.html',
  styleUrls: ['./hp-bundle.component.scss']
})
export class HpBundleComponent implements OnInit {

  @Input() recommendedbundles;
  bundles;

  constructor(private healthPrintResultsService: HealthPrintResultsService) { }

  ngOnInit() {
    this.bundles = this.getBundles(this.recommendedbundles.bundles);
  }

  getBundles(bundles) {
    let productDetails;
    let tiers: string[] = ['TIER_1', 'TIER_2', 'TIER_3'];
    let tmpArray = [];

    //Get healthprint skulist
    const skuList = this.getSkuList(bundles);
    for( let sku in skuList) {
        tmpArray.push(sku);
    }
    const skuString = tmpArray.join();

    // get productInfo using skustring as input
    this.healthPrintResultsService.getProductContent(skuString).subscribe(responseData => {
        productDetails = responseData['products'];
        const productInfo = {};
        let image, price;

        if (!productDetails) {
            return;
        }

        productDetails.forEach(function (val) {
            if(val.images) {
                val.images.forEach(function (imgVal) {
                    if(imgVal.format === 'thumbnail' && imgVal.imageType === 'PRIMARY') {
                        image = imgVal.url;
                    }
                });
            }
            if(val.prices) {
                val.prices.forEach(function (priceVal) {
                    if(priceVal.priceTier === 'SN') {
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
        console.log('productInfo', productInfo);

        bundles.forEach(function (value, key) {
            value.skus.forEach( function(value2, key2) {
                if (value2.category === 'membership') {
                    bundles[key].skus[key2].info = {
                        title: 'shaklee-membership-title',
                        healthPrintDescription: 'shaklee-membership-description',
                        description: '',
                        shortDescription: '',
                        itemImage: '//images.shaklee.com/healthprint/ico-leaf.png'
                    };
                }
                else {
                    bundles[key].skus[key2].info = productInfo[value2.sku] ? productInfo[value2.sku] : {title: 'temp Title', healthPrintDescription: 'temp healthPrintDescription', description: 'temp longDescription', shortDescription: 'temp description', itemImage: '//images.shaklee.com/healthprint/ico-leaf.png', price: 'temp price'};
                }
            });
        });
    });
console.log('bundles', bundles);
    return bundles
      .filter((bundle) => tiers.indexOf(bundle.bundle) !== -1)
      .sort((a, b) => a.bundle < b.bundle ? -1 : 1);
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
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
// import {variable} from "@angular/compiler/src/output/output_ast";

@Injectable()
export class HealthPrintResultsService {
    private allHealthPrintResultsSource = new BehaviorSubject<object>([]);
    private healthPrintResultInfoSource = new BehaviorSubject<object>({});
    allHealthPrintResults = this.allHealthPrintResultsSource.asObservable();
    healthPrintResultInfo = this.healthPrintResultInfoSource.asObservable();

    endPointAllHealthPrintResults = '/assets/mockjson/getAllHealthPrints.json';
    endPointContent =  '/assets/mockjson/content.json';
    endPointRecommendation = '/assets/mockjson/recommendation.json';
    endPointProduct = 'https://www.shakleedev.com:9002/shakleeintegration/v2/shakleeUS/products?fields=DEFAULT';

    constructor(private http: HttpClient) {}

    getHealthPrintResults() {
        return this.http.get(this.endPointAllHealthPrintResults);
    }

    getRecommendation() {
       return this.http.get(this.endPointRecommendation);
    }
    getContent() {
        return this.http.get(this.endPointContent);
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
}

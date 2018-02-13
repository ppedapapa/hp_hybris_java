import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { QuestionsService } from "./questions.service";
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

    endPointAllHealthPrintResults = '/services/hp/getAllHealthPrints';
    endPointContent =  '/assets/mockjson/content.json';
    endPointRecommendation = '/services/hp/recommendations';
    endPointProduct = 'https://www.shakleedev.com:9002/shakleeintegration/v2/shakleeUS/products?fields=DEFAULT';

    constructor(private http: HttpClient,
                private questionsService:QuestionsService) {}

    getAllHealthPrints() {
        let healthProfile = JSON.parse(this.questionsService.getHealthProfileInfo());
        let request = {};

        if(healthProfile.email !== undefined) {
            request = {email: healthProfile.email};
            // request = {email: 'qwe@asd.asd'};
        } else if(healthProfile.healthProfileId !== undefined)  {
            request = {healthProfileId: healthProfile['healthProfileId']}
        }
console.log(healthProfile, request);
       return this.http.post(this.endPointAllHealthPrintResults, request, this.httpOptions);
    }

    getRecommendation(healthPrints) {
        let questions = healthPrints.questions;
        return this.http.post(this.endPointRecommendation, questions, this.httpOptions);
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

import {Injectable, OnDestroy} from "@angular/core";
import { Resolve, ActivatedRouteSnapshot } from "@angular/router";
import { Observable } from "rxjs/Observable";
import { Subscription } from 'rxjs/Subscription'
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { QuestionsService } from "./questions.service";

@Injectable()
export class HealthPrintBundlesResolver implements Resolve <any>, OnDestroy{
    answered;
    subscription: Subscription;
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type':  'application/json; charset=utf-8',
            'Access-Control-Allow-Credentials': 'true',
            'Access-Control-Allow-Origin': '*',
            'Cache-Control': 'no-cache'
        })
    };

    constructor(private http: HttpClient,
                private questionService: QuestionsService) {}

    resolve (route: ActivatedRouteSnapshot): Observable<any>{

        this. subscription = this.questionService.getAnsweredSubject().subscribe((value) => {
            this.answered = value;
        });

        console.log('subscribtion value', this.answered);

        return this.http.post('/services/hp/recommendations', this.answered, this.httpOptions)
            .map(responseData => {
            console.log('responseData', responseData);
            /* if (responseData['bundles'].length == 0) {
                this.router.navigate(['/questions']);
            } else {
                return responseData;
            } */
        });
    }

    ngOnDestroy(){
        this.subscription.unsubscribe();
    }
}
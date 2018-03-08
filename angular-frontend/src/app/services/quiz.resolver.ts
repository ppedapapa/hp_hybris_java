import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';

import { Quiz } from '../models';
import { QuestionsService } from "./questions.service";
import { HealthPrintResultsService } from "./hp-results.service";
import { AppService } from '../app.service';


@Injectable()
export class QuizResolver implements Resolve <any> {

    constructor(private questionService: QuestionsService,
                 private appService: AppService,
                private healthPrintResultsService: HealthPrintResultsService,
                private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Observable<any> {
        console.log('quiz resolver');
        let healthProfile = JSON.parse(this.questionService.getHealthProfileInfo());
        let hpRetakeQuiz = JSON.parse(localStorage.getItem('hpRetakeQuiz'));
        localStorage.removeItem('hpRetakeQuiz');
        
        let isUserLoggedin = this.appService.isUserLogin();
      
        if(healthProfile !== null && hpRetakeQuiz === null) {
            this.router.navigate(['/healthprint-results']);
        }
        else {
               return Observable.create((observer: Observer<Quiz[]>) => {
                 if (!hpRetakeQuiz && isUserLoggedin) {
                     this.healthPrintResultsService.getAllHealthPrints().subscribe(responseData => {
                         if (responseData['status'] == 0 && responseData['data'].length > 0) {
                            this.router.navigate(['/healthprint-results']);
                           return false;
                          }
                      });
                }
                this.questionService.getQuestions().subscribe(
                        (data) => {
                            observer.next(data);
                            observer.complete();
                        }
                    );
           });
        }
    }
}

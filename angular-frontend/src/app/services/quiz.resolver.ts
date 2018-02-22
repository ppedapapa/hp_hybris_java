import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';

import { Quiz } from '../models';
import { QuestionsService } from "./questions.service";


@Injectable()
export class QuizResolver implements Resolve <Quiz[]> {

    constructor(private questionService: QuestionsService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Quiz[]> {
        console.log('quiz resolver');
        let healthProfile = JSON.parse(this.questionService.getHealthProfileInfo());
        let hpRetakeQuiz = JSON.parse(localStorage.getItem('hpRetakeQuiz'));
        localStorage.removeItem('hpRetakeQuiz');
        console.log('healthProfile', healthProfile);
        console.log('hpRetakeQuiz', hpRetakeQuiz);
        if(healthProfile !== null && hpRetakeQuiz === null) {
            this.router.navigate(['/healthprint-results']);
        }else {
            return Observable.create((observer: Observer<Quiz[]>) => {
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

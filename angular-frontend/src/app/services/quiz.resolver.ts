import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';

import { Quiz } from '../models/index';
import {QuestionsService} from "./questions.service";


@Injectable()
export class QuizResolver implements Resolve <Quiz[]> {

    constructor(private questionService: QuestionsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Quiz[]> {
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

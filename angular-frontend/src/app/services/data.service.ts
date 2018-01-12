import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Quiz } from '../models/index';



@Injectable()
export class DataService {
    constructor(private http: HttpClient) {}

    // storeRecipes() {
    //    return this.http.put('https://ng-recipe-book-916df.firebaseio.com/recipes.json', this.recipeService.getRecipes());
    // }


    // getQuestionsUrl() {
    //     this.questionUrl = './assets/data/us/questions.json';
    //     return this.questionUrl;
    // }

    private quizSource = new BehaviorSubject<Quiz[]>([]);
    quiz = this.quizSource.asObservable();

    getQuestions() {
        return this.http.get<Quiz[]>('./assets/data/us/questions.json').map(res => res);
    }
}

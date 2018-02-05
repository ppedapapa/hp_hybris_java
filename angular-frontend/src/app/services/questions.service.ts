import 'rxjs/add/operator/map';

import {Injectable, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Quiz } from '../models/quiz';
import { HpConfigService } from './hp-config.service';
import { Question, Option } from '../models/index';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class QuestionsService {
  questionUrl = '';
  quiz: Quiz[];
  baseQuiz: Quiz[];
  questions: string;
  answered = this.hpconfigService.getAnsweredJsonObj();
  goals: string[] = [];

  constructor(private http: HttpClient,
              private hpconfigService: HpConfigService) { }

  get(url: string) {
    return this.http.get(url);
  }

  getQuestions() {
      return this.http.get<Quiz[]>('./assets/data/us/questions.json').map(res => res);
  }

  getPages() {
    return this.quiz['pages'];
  }

  setQuiz(quiz: Quiz[]) {
    console.log('set quiz', quiz);
    this.quiz = quiz;
    this.baseQuiz = quiz;
  }

  setMaleQuiz() {
    const tempQuiz = Object.assign({}, this.baseQuiz);
    const result = tempQuiz['pages'].filter(function (page) {
      if('restrict' in page) {
          const restricted = page.restrict;
          return restricted.indexOf('male') === -1;
      } else {
        return true;
      }
    });
    tempQuiz['pages'] = result;
    return tempQuiz;
  }

  setKidsQuiz() {
      const tempQuiz = Object.assign({}, this.baseQuiz);
      const result = tempQuiz['pages'].filter(function (page) {
          if('restrict' in page) {
              const restricted = page.restrict;
              console.log('restricted', restricted);
              return restricted.indexOf('kids') === -1;
          } else {
              return true;
          }
      });
      tempQuiz['pages'] = result;
      return tempQuiz;
  }

  getAnswered() {
    return this.answered;
  }

  setAnswered(answered) {
    this.answered = answered;
  }

  goTo(index: number) {
    const pageCount = this.hpconfigService.getPagerCount();
    if (index >= 0 && index < pageCount) {
      this.hpconfigService.setPagerIndex(index);
    }
  }

  setSelected(question: Question, option: Option) {
    console.log(this.answered);
    this.answered[question.name] = option.index;
    let index = option.index;

    if(question.name === 'gender') {
      if(index === 0) {
        this.quiz = this.setMaleQuiz();
      } else if(index === 1 && question.name === 'gender') {
        this.quiz = this.baseQuiz;
      }
    }
    this.hpconfigService.updatelength(this.quiz['pages'].length);
    console.log('update quiz', this.quiz);
    console.log('Base quiz', this.baseQuiz);

      // this.hpconfigService.setAutomove(1);
  }

  setInput(name, val) {
    console.log(this.answered);
    this.answered[name] = val;

    // this.hpconfigService.setAutomove(1);
  }

  setDropdown(name, val, type) {
    this.answered[type][name] = val;
    console.log(this.answered);
  }

  setGoalDropdown(name, val) {
    const index = this.goals.indexOf(val);
    if (index === -1) {
      this.goals.push(val);
    }
    this.answered['health_goals'] = this.goals;
    console.log(this.answered);
  }
}
import 'rxjs/add/operator/map';

import {Injectable, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Quiz } from '../models/quiz';
import { HpConfigService } from './hp-config.service';
import { DataService } from './data.service';
import { Question, Option } from '../models/index';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class QuestionsService {
  questionUrl = '';
  quiz: Quiz[];
  questions: string;
  answered = this.hpconfigService.getAnsweredJsonObj();
  goals: string[] = [];

  constructor(private http: HttpClient,
              private hpconfigService: HpConfigService,
              private dataService: DataService) { }

  get(url: string) {
    return this.http.get(url);
  }

  getPages() {
    return this.quiz['pages'];
  }

  setQuiz(quiz: Quiz[]) {
    console.log('set quiz', quiz);
    this.quiz = quiz;
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
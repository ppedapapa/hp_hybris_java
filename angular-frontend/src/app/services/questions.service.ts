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

    // question.options.forEach((x) => {
    //   if (x.index !== option.index) {
    //     x.selected = false;
    //   } else {
    //     x.selected = true;
    //   }
    // });

    // this.hpconfigService.setAutomove(1);
  }

  setInput(name, val) {
    console.log(this.answered);
    this.answered[name] = val;

    // question.answered = val;

    // this.hpconfigService.setAutomove(1);
  }

  setDropdown(name, val) {
      this.answered['height_inches'][name] = val;
      console.log(this.answered);
  }
}

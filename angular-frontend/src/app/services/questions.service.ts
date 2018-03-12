import 'rxjs/add/operator/map';

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HpConfigService } from './hp-config.service';
import { Quiz, Question, Option } from '../models';
import { Router } from "@angular/router";
import { GoogleAnalyticsService } from "./google-analytics.service";

@Injectable()
export class QuestionsService {
  questionUrl = '';
  quiz: Quiz[];
  baseQuiz: Quiz[];
  questions: string;
  answered = this.hpconfigService.getAnsweredJsonObj();
  goals = {goal0: undefined,goal1: undefined,goal2: undefined};
  heightInches = {foot: undefined, inches: undefined};
  isPWS;
  isShaklee180;
  pws_owner_id = null; // update this value based on req -- revisit
  goalDropdown = [];

  // private answeredSubject: BehaviorSubject<any> = new BehaviorSubject<any>(this.hpconfigService.getAnsweredJsonObj());

  constructor(private http: HttpClient,
              private hpconfigService: HpConfigService,
              private router: Router,
              private analyticsService: GoogleAnalyticsService) {}

  /*getAnsweredSubject(): Observable<any>  {
    return this.answeredSubject.asObservable();
  }

  setAnsweredSubject(answered: any) {
      console.log('setAnsweredSubject', answered);
      this.answeredSubject.next(answered);
  }*/

  getAnswered(): any {
      return this.answered;
  }

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

  setSelected(question: Question, option: Option) {
    this.answered[question.name] = option.index;
    let index = option.index;

    if(question.name === 'gender') {
      if(index === 0) {
        this.quiz = this.setMaleQuiz();
      } else if(index === 1 && question.name === 'gender') {
        this.quiz = this.baseQuiz;
      }
      this.hpconfigService.updatelength(this.quiz['pages'].length);
    }
    this.setAutomove();
  }

  setInput(name, val) {
    console.log('setInput answered', this.answered);
    this.answered[name] = val;
  }

  goTo(index: number) {
  console.log('index', index);
    const pageCount = this.hpconfigService.getPagerCount();
    if (index >= 0 && index < pageCount && this.validCurrentPage()) {
        this.hpconfigService.setPagerIndex(index);
        this.questionsAnalytics(index);
    }
  }

  questionsAnalytics(questionId){
    var label = 'User on question page-'+questionId+'-A';
    var question;
    switch(questionId){
        case 1: question = 'gender';break;
        case 2: question = 'fruit-vegetables-whole grains';break;
        case 3: question = 'dairy-fish-water';break;
        case 4: question = 'sugary drinks-unhealthy snacks';break;
        case 5: question = 'breakfast-organic foods';break;
        case 6: question = 'bleach ammonia';break;
        case 7: question = 'energy-stress-sleep';break;
        case 8: question = 'memory-often exercise-describe weekly exercise';break;
        case 9: question = 'age-weight-height';break;
        case 10: question = 'health goals';break;
        case 11: question = 'dietary restrictions';break;
        case 12: question = 'pregnant';break;
        case 13: question = 'spend each day';break;
        case 14: question = 'name-email';break;
        default: question = 'questionnaire';
    }
    this.analyticsService.emitEvent(label, question);
  }

  validCurrentPage() {
      let pageNum = this.hpconfigService.getPager();
      let pages = this.getPages();
      let currentPage = pages[pageNum.index];
      let currentAnsweredSet = [];

      currentPage.questions.forEach(item => {
          if(item.name == "age" || item.name == "weight" || item.name == "email-form") {
              item.options.forEach(val => {
                  currentAnsweredSet.push(this.answered[val.name]);
              });
          }
          else {
            currentAnsweredSet.push(this.answered[item.name]);
          }
      });
      return currentAnsweredSet.indexOf(undefined) === -1;
  }

  setAutomove() {
      let pageNum = this.hpconfigService.getPager();
      let pages = this.getPages();
      let currentPage = pages[pageNum.index];
      if(currentPage.autoMove !== false) {
          this.goTo(pageNum.index+1);
      }
  }

  setDropdown(name, val, type) {
    this.answered[type] = val;
    console.log('answered',this.answered);
  }

  setHealthProfileInfo(profile) {
      localStorage.setItem('healthProfile', JSON.stringify(profile) );
  };

  getHealthProfileInfo() {
      return localStorage.getItem('healthProfile' );
  };

  update(){
      let data = this.formatPostData();
      this.http.post('/services/hp/questions/update', data)
      .subscribe(responseData => {
          let profileInfo = {healthProfileId:responseData['healthProfileId'],email:data['email']};
          this.setHealthProfileInfo(profileInfo);
          this.router.navigate(['/healthprint-results']);
      });
      this.questionsAnalytics(this.hpconfigService.getPagerCount());
      this.analyticsService.emitEvent('complete', 'Complete-Quiz');
  }

  formatPostData() {
    let excludeNonQuestions = ["email","first_name","last_name","referrer_id","health_profile_id","opt_in","recaptcha_response","completed_time_stamp","share_with_distributors"];
    let kidExcludeList = this.hpconfigService.getExcludeKidQuestionList();
    let tempPostData = Object.assign({}, this.answered);

    //referrer_id logic
    if(this.isPWS || this.isShaklee180){
        tempPostData.referrer_id = this.pws_owner_id;
    }
    else{
        tempPostData.referrer_id = null;
    }

    if ( this.answered.dietary_restrictions !== "NONE" ) {
      delete tempPostData.dietary_restrictions;
    }

    let postData = {questions:{}};
    for(let key in tempPostData){
        if((excludeNonQuestions.indexOf(key) == -1)) {
            postData.questions[key] = ((kidExcludeList.indexOf(key) >= 0) && (parseInt(this.answered.age) < 13))?undefined:QuestionsService.stringToNumber(tempPostData[key]);

        }
        else if (excludeNonQuestions.indexOf(key) >=0) {
            postData[key] = ((kidExcludeList.indexOf(key) >= 0) && (parseInt(this.answered.age) < 13))?undefined:tempPostData[key];
        }
    }
    console.log('PostData', postData);
    // this.setAnsweredSubject(postData);

    return postData;
  }

  static stringToNumber(value) {
    if(/^(\-|\+)?([0-9]+|Infinity)$/.test(value)) {
        return parseInt(value);
    }
    return value;
  }
  setGoalDropdown(list){
      this.goalDropdown = list;
  }

}
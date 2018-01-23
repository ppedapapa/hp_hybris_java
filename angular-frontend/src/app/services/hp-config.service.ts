import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

import { QuestionConfig } from '../models/index';

@Injectable()
export class HpConfigService {
  pager = {
    index: 0,
    size: 1,
    count: 1
  };

  private pageLength = new Subject<number>();

  length = this.pageLength.asObservable();

  updatelength(length: number) {
      this.pageLength.next(length);
  }

  config: QuestionConfig = {
    'allowBack': true,
    'autoMove': false,  // if true, it will move to next question automatically when answered.
    'pageSize': 1,
    'showPager': true
  };

  constructor() { }

  getAnsweredJsonObj() {
    // var isMC = (appConst.site === "mc") ? true : false;
    const isMC =  false;
    const jsonObject = {
      country_code: undefined,
      language: 'en',
      email: undefined,
      referrer_id: undefined,
      user_id: undefined,
      first_name: undefined,
      last_name: undefined,
      age: undefined,
      gender: undefined,
      weight_lbs: undefined,
      height_inches: {foot: undefined, inches: undefined},
      pregnant: undefined,
      energy: undefined,
      memory: undefined,
      stress: undefined,
      sleep: undefined,
      exercise_frequency: undefined,
      exercise_intensity: undefined,
      toxins: undefined,
      fruits: undefined,
      vegetables: undefined,
      grains: undefined,
      dairy: undefined,
      healthy_fats: undefined,
      water: undefined,
      junk_food: undefined,
      sugar_drinks: undefined,
      breakfast: undefined,
      organic: undefined,
      spending: undefined,
      dietary_restrictions: {},
      health_goals: {},
      is_guest: (!isMC) ? true : false,
      noShareWithDistributors: false,
      share_with_distributors: true
    };
    return jsonObject;
  }

  getPager() {
    return this.pager;
  }

  getPagerCount() {
    return this.pager.count;
  }

  setPagerCount(index: number) {
    this.pager.count = index;
  }

  setPagerIndex(index: number) {
    this.pager.index = index;
  }

  getConfig() {
    return this.config;
  }

  setAutomove(index: number) {
    this.pager.index = index;
  }
}

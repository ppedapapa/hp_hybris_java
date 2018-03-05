import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class HpConfigService {
  pager = {
    index: 0,
    count: 1
  };

  private pageLength = new Subject<number>();
  isUS = true;
  length = this.pageLength.asObservable();

  updatelength(length: number) {
      this.pageLength.next(length);
  }

  constructor() { }

  getAnsweredJsonObj() {
    // var isMC = (appConst.site === "mc") ? true : false;
    const isMC =  false;
    const jsonObject = {
      country_code: 'US',
      language: 'en',
      opt_in: true,
      email: undefined,
      referrer_id: undefined,
      user_id: undefined,
      first_name: undefined,
      last_name: undefined,
      age: undefined,
      gender: undefined,
      weight_lbs: undefined,
      height_inches: undefined,
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
      dietary_restrictions: undefined,
      health_goals: undefined,
      is_guest: (!isMC) ? true : false,
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

  isMobile() {
      console.log('isMobile', /Mobi/.test(navigator.userAgent));
    return /Mobi/.test(navigator.userAgent);
  }

  getExcludeKidQuestionList() {
      return ["stress", "sleep", "energy", "pregnant", "memory", "stress", "sleep", "exercise_frequency", "exercise_intensity", "fruits", "vegetables", "grains", "dairy", "healthy_fats", "water", "junk_food", "sugar_drinks", "breakfast", "organic", "health_goals"];
  }

  getKosherSkus = function() {
      if (this.isUS) {
          return ['20001', '21261', '20096', '21216'];
      } else {
          return ['57475', '55400', '54420'];
      }
  }

  getNonKosherSkus = function() {
      if(this.isUS){
          return ['20002', '20076', '20096', '21261'];
      }else{
          return ['57475', '55400', '54420', '57860'];
      }
  }

  getNonSoySku = function() {
      if(this.isUS) {
          return '21275';
      }else{
          return '56278';
      }
  }

  getSoySku = function() {
      if(this.isUS) {
          return '21261';
      }else{
          return '56261';
      }
  }

  getCleanSku = function() {
      return '50456';//same sku for US and Canada
  }

  getDiet = function() {
    return ['healthy_fats', 'water', 'sugar_drinks', 'junk_food', 'breakfast', 'organic', 'dietary_restrictions'];
  }

  getDietChart = function() {
    return ['fruits', 'vegetables', 'grains', 'dairy'];
  }

  getLifeStyle = function() {
    return ['energy', 'stress', 'sleep', 'memory', 'exercise_frequency', 'exercise_intensity', 'toxins', 'pregnant', 'spending'];
  }
}

import { Injectable, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/platform-browser';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../environments/environment';

@Injectable()
export class AppService {
  
  appConst = {
    country: undefined,
    lang: undefined,
    langList:{"US":["en","es"],"CA":["en","fr"]},
    selectedLang:undefined
  };
  
  hpEntity;
  constructor(private http: HttpClient,
			  private cookieService: CookieService,
			  @Inject(DOCUMENT) private document: any) {
			  this.hpEntity = (this.cookieService.get('hpEntity'))?JSON.parse(atob(this.cookieService.get('hpEntity'))): {};
			  }

  isUserLogin() {
     return (this.hpEntity['userLogged'])?true:false;
  }

  getAppConst() {
      this.appConst.country = (this.hpEntity['country'])?this.hpEntity["country"]:"US";
      this.appConst.lang = (this.hpEntity['lang'])?this.hpEntity["lang"]:"en";
      this.appConst.selectedLang = this.appConst.country.toLowerCase()+'-'+ this.appConst.lang;
      return this.appConst;
  }
  
  resetLanguage(curLanguage) {
        this.hpEntity['lang']=curLanguage;
        const hpEntityCookie = JSON.stringify(this.hpEntity);
        const hpEntityCookieEncode = btoa(hpEntityCookie);
        this.document.cookie = 'hpEntity='  + hpEntityCookieEncode +';'+';path=/';
  }
}

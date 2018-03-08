import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class AppService {
  constructor(private http: HttpClient,
			  private cookieService: CookieService) {}

  appConst = {
    country: undefined,
    lang: undefined
  };
  
  isUserLogin() {
     return this.cookieService.get('userLogged')?true:false;
  }

  getAppConst() {
      this.appConst.country = "US";
      this.appConst.lang = "en";
      return this.appConst;
  }

}

import { Component, OnInit } from '@angular/core';
import { TranslateService } from "@ngx-translate/core";
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

import { AppService } from '../app.service';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  appConst;
  country;
  lang;
  langList;
  
  constructor(private translate: TranslateService,
              private appService: AppService,
              private cookieService: CookieService,
              private router: Router) { }
 
  ngOnInit() {
    this.appConst = this.appService.getAppConst();
    this.country = this.appConst['country'];
    this.lang = this.appConst['lang'];
    this.langList = this.appConst['langList'][this.country];
  }

  switchLanguage(event) {
      const lang = event.target.value;
      const curLang = this.country.toLowerCase()+'-'+lang;
      this.translate.use(curLang);
      this.appService.resetLanguage(lang);
  }

}

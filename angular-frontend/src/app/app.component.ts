import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { QuestionsService } from './services/questions.service';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private translate: TranslateService,
              private serverService: AppService) {
    translate.setDefaultLang('us-en');
  }

  ngOnInit() {
    // this.serverService.isUserLogin();
  }

}

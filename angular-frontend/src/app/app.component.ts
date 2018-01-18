import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { QuestionsService } from './services/questions.service';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [QuestionsService]
})
export class AppComponent implements OnInit {

  constructor(private translate: TranslateService,
              private serverService: AppService) {
    translate.setDefaultLang('us-en');
  }
  appGreetings = this.serverService.getGreetings();

  switchLanguage(language: string) {
    this.translate.use(language);
  }

  ngOnInit() {
    this.serverService.isUserLogin();
  }

}

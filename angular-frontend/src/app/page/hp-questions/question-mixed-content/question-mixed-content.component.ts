import { Component, Input, OnInit } from '@angular/core';
import { Question } from '../../../models/question';
import { QuestionsService } from '../../../services/questions.service';
import { HpConfigService } from '../../../services/hp-config.service';
import { GoogleAnalyticsService } from "../../../services/google-analytics.service";
import { AppService } from "../../../app.service";
import { TranslateService } from "@ngx-translate/core";

@Component({
  selector: 'app-question-mixed-content',
  templateUrl: './question-mixed-content.component.html',
  styleUrls: ['./question-mixed-content.component.scss'],
})
export class QuestionMixedContentComponent implements OnInit {

  @Input() questions: Question;
  @Input() pageIndex;

  // appConst = {country: 'us', language: 'en'};
  appConst = this.appService.getAppConst();

  /*langPageImageLink = this.appConst.country.toLowerCase() + this.appConst.lang;
  imagePath = 'https://images.shaklee.com/healthprint/landing/';
  image1 = 'hp-landing-lifestyle-01a-' + this.langPageImageLink + '.jpg';
  image2 = 'hp-landing-chart-01a-' + this.langPageImageLink + '.jpg';
  image3 = 'hp-landing-logos-01a-' + this.langPageImageLink + '.jpg'; */
  pager;
  answered = this.questionsService.getAnswered();
  lifeStyleImage;
  chartImage;
  logoImage;
  constructor(private questionsService: QuestionsService,
              private hpConfigService: HpConfigService,
              private appService: AppService,
              private translate: TranslateService,
              private analyticsService: GoogleAnalyticsService) {
      // this.translate.stream('landing.hp-landing-lifestyle').subscribe(res => this.lifeStyleImage = res);
      // this.translate.stream('landing.hp-landing-chart').subscribe(res => this.chartImage = res);
      // this.translate.stream('landing.hp-landing-logo').subscribe(res => this.logoImage = res);
  }

  ngOnInit() {
    this.pager = this.hpConfigService.getPager();
  }

  startQuiz(index: number) {
    const name = this.questions[0].name;
    if(this.answered[name] != undefined) {
      this.questionsService.goTo(index);
    }
    this.analyticsService.emitEvent('take', 'Start-Quiz');
  }
}

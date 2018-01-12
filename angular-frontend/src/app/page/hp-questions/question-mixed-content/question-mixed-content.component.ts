import { Component, Input, OnInit } from '@angular/core';
import { Question } from '../../../models/question';
import { QuestionsService } from '../../../services/questions.service';
import { HpConfigService } from '../../../services/hp-config.service';

@Component({
  selector: 'app-question-mixed-content',
  templateUrl: './question-mixed-content.component.html',
  styleUrls: ['./question-mixed-content.component.scss'],
})
export class QuestionMixedContentComponent implements OnInit {

  @Input() questions: Question;

  appConst = {country: 'us', language: 'en'};
  langPageImageLink = this.appConst.country + this.appConst.language;
  imagePath = 'https://images.shaklee.com/healthprint/landing/';
  image1 = 'hp-landing-lifestyle-01a-' + this.langPageImageLink + '.jpg';
  image2 = 'hp-landing-chart-01a-' + this.langPageImageLink + '.jpg';
  image3 = 'hp-landing-logos-01a-' + this.langPageImageLink + '.jpg';
  pager;

  constructor(private questionsService: QuestionsService,
              private hpConfigService: HpConfigService) { }

  ngOnInit() {
    this.pager = this.hpConfigService.getPager();
  }

  goTo(index: number) {
    this.questionsService.goTo(index);
  }
}

import { Component, Input, OnInit } from '@angular/core';
import { Question } from '../../../models/question';
import { QuestionsService } from '../../../services/questions.service';
import { HpConfigService } from '../../../services/hp-config.service';
import { GoogleAnalyticsService } from "../../../services/google-analytics.service";

@Component({
  selector: 'app-question-mixed-content',
  templateUrl: './question-mixed-content.component.html',
  styleUrls: ['./question-mixed-content.component.scss'],
})
export class QuestionMixedContentComponent implements OnInit {

  @Input() questions: Question;
  @Input() pageIndex;

  pager;
  answered = this.questionsService.getAnswered();
  constructor(private questionsService: QuestionsService,
              private hpConfigService: HpConfigService,
              private analyticsService: GoogleAnalyticsService) {}

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

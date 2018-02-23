import { Component, OnInit } from '@angular/core';

import { QuestionsService } from '../services/questions.service';
import { Quiz } from '../models';
import { HpConfigService } from '../services/hp-config.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-page',
  templateUrl: './page.component.html',
  styleUrls: ['./page.component.scss']
})
export class PageComponent implements OnInit {
  quiz: Quiz[];

  constructor(private questionsService: QuestionsService,
              private hpconfigService: HpConfigService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.quiz = this.route.snapshot.data['quizList'];
    this.questionsService.setQuiz(this.quiz);
    this.hpconfigService.setPagerCount(this.quiz['pages'].length);
  }

}

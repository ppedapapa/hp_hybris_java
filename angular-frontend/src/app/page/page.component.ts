import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Rx';

import { QuestionsService } from '../services/questions.service';
import { Quiz } from '../models/index';
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

  onSubmit() {
    const answers = [];
    this.quiz['pages'].forEach(
        x => {
          answers.push({ 'name': this.quiz['id'], 'questionId': x.id, 'answered': x.selected });
        }
    );

    // Post your data to the server here. answers contains the questionId and the users' answer.
    console.log(this.quiz['pages']);
    console.log(answers);
    // this.mode = 'result';
  }

}

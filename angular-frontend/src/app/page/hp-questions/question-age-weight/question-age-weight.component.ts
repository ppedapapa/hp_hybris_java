import { Component, OnInit, Input } from '@angular/core';
import { Question } from '../../../models/question';
import { QuestionsService } from '../../../services/questions.service';

@Component({
  selector: 'app-question-age-weight',
  templateUrl: './question-age-weight.component.html',
  styleUrls: ['./question-age-weight.component.scss']
})
export class QuestionAgeWeightComponent implements OnInit {
  @Input() questions;
  answered;

  height_feet =  [3, 4, 5, 6, 7];
  height_inches = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];

  constructor(private questionsService: QuestionsService) { }

  ngOnInit() {
    this.answered = this.questionsService.getAnswered();
  }

  inputChange(name, event) {
    const val = event.target.value;
    this.questionsService.setInput(name, val);
  }
}

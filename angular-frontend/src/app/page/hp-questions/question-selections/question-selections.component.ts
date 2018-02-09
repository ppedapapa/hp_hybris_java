import {Component, Input, OnInit} from '@angular/core';
import { Question, Option } from '../../../models/index';
import { QuestionsService } from '../../../services/questions.service';

@Component({
  selector: 'app-question-selections',
  templateUrl: './question-selections.component.html',
  styleUrls: ['./question-selections.component.scss'],
})
export class QuestionSelectionsComponent implements OnInit{

  @Input() pageIndex;
  @Input() questions;
  @Input() className;
  answered;

  constructor(private questionsService: QuestionsService) {
  }

  ngOnInit() {
    this.answered = this.questionsService.getAnswered();
  }

  onSelect(question: Question, option: Option) {
    this.questionsService.setSelected(question, option);
  }
}

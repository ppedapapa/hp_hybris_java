import { Component, OnInit, Input } from '@angular/core';
import {QuestionsService} from '../../../services/questions.service';

@Component({
  selector: 'app-question-dietary-restrictions',
  templateUrl: './question-dietary-restrictions.component.html',
  styleUrls: ['./question-dietary-restrictions.component.scss']
})
export class QuestionDietaryRestrictionsComponent implements OnInit {

  @Input() questions;

  constructor(private questionsService: QuestionsService) { }

  ngOnInit() {
  }

  setValue(val) {
    this.questionsService.setDropdown('dietary_restrictions', val);
  }
}

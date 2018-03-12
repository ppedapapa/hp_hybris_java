import { Component, OnInit, Input } from '@angular/core';
import { QuestionsService } from "../../../services/questions.service";

@Component({
  selector: 'app-question-goals',
  templateUrl: './question-goals.component.html',
  styleUrls: ['./question-goals.component.scss']
})
export class QuestionGoalsComponent implements OnInit {

  @Input() questions;
  @Input() pageIndex;
  dropdownCount;
  goalDropdown;

  constructor(private questionsService: QuestionsService) {
    this.dropdownCount = Array(3).fill(0).map((x, i) => i);
  }

  ngOnInit() {
      this.goalDropdown = Object.assign([], this.questions[0]['options']);
      this.questionsService.setGoalDropdown(this.goalDropdown);
  }

}

import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-question-goals',
  templateUrl: './question-goals.component.html',
  styleUrls: ['./question-goals.component.scss']
})
export class QuestionGoalsComponent implements OnInit {

  @Input() questions;

  constructor() { }

  ngOnInit() {
  }

}

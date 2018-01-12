import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.scss']
})
export class QuestionFormComponent implements OnInit {

  @Input() questions;

  constructor() { }

  ngOnInit() {
  }

}

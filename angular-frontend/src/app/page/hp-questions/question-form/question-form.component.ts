import {Component, OnInit, Input, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionFormComponent implements OnInit {

  @Input() questions;

  constructor() { }

  ngOnInit() {
  }

}

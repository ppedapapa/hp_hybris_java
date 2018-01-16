import { Component, OnInit, Input } from '@angular/core';
import {QuestionsService} from '../../../services/questions.service';

@Component({
  selector: 'app-question-dietary-restrictions',
  templateUrl: './question-dietary-restrictions.component.html',
  styleUrls: ['./question-dietary-restrictions.component.scss']
})
export class QuestionDietaryRestrictionsComponent implements OnInit {

  @Input() questions;
  checked: string[] = [];

  constructor(private questionsService: QuestionsService) { }

  ngOnInit() {
  }

  setValue(val, event) {
      const noneIndex = this.checked.indexOf('NONE');
      const index = this.checked.indexOf(val);

      if (event.target.checked) {
          if (val !== 'NONE') {
              if (noneIndex !== -1) {
                  this.checked.splice(noneIndex, 1);
              }
              if (index === -1) {
                  this.checked.push(val);
              }
          }
          if (val === 'NONE' && index === -1) {
              this.checked = [];
              this.checked.push(val);
          }
      } else if (index !== -1) {
          this.checked.splice(index, 1);
      }
    console.log(this.checked, index);
    this.questionsService.setInput('dietary_restrictions', this.checked);
  }
}

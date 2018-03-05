import { Component, OnInit, Input } from '@angular/core';
import { QuestionsService } from '../../../services/questions.service';
import { FormControl, FormGroup, Validators, FormGroupDirective, NgControl } from "@angular/forms";

@Component({
  selector: 'app-question-age-weight',
  templateUrl: './question-age-weight.component.html',
  styleUrls: ['./question-age-weight.component.scss']
})
export class QuestionAgeWeightComponent implements OnInit {
  @Input() questions;
  @Input() pageIndex;
  answered;

  height_feet =  [3, 4, 5, 6, 7];
  height_inches = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];

  constructor(private questionsService: QuestionsService) { }

  ngOnInit() {
      this.questions.forEach(item => {
          item.options.forEach(val => {
            if(val.name == "age") {
                val.pattern = "(([0-9][1-9])|([1-9][0-9])|[4-9])";
            }
            if(val.name == "weight_lbs") {
                val.pattern = "[1-9][0-9]{1,2}";
            }
          });
      });
      this.answered = this.questionsService.getAnswered();
  }

  inputChange(name, validate, event) {
    let val;
    if(validate.valid) {
      val = event.target.value;
    } else {
      val = undefined;
    }
    this.questionsService.setInput(name, val);
  }
}

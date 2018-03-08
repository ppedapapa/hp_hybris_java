import { Component, Input, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { QuestionsService } from '../../../services/questions.service';

@Component({
  selector: 'app-question-dropdown',
  templateUrl: './question-dropdown.component.html',
  styleUrls: ['./question-dropdown.component.scss']
})
export class QuestionDropdownComponent implements OnInit {

  @Input() dropdown;
  @Input() firstLabel;
  @Input() label;
  @Input() fromComponent;
  @Input() type;
  @Input() index;
  currentDropdown = {};
  selectedOption;
  answered = this.questionsService.getAnswered();
  goals =  this.questionsService.goals;
  heightInches = this.questionsService.heightInches;

  constructor(private translate: TranslateService,
              private questionsService: QuestionsService) { }

  ngOnInit() {
      this.translate.get('label.' + this.firstLabel).subscribe((res: string) => {
          this.selectedOption = res;
      });
      this.dropdown.forEach(item => {
          if (this.fromComponent === 'goal') {
              this.translate.get('label.' + this.label + item.toLowerCase()).subscribe((res: string) => {
                  this.currentDropdown[item] = res;
                  const selectedAnswer = this.goals[this.index];
                  if(selectedAnswer !== undefined) {
                      this.selectedOption = this.currentDropdown[selectedAnswer];
                  }
              });
          } else {
              this.translate.get('label.' + this.label).subscribe((res: string) => {
                  this.currentDropdown[item] = item + ' ' + res;
                  const selectedAnswer = this.heightInches[this.label];
                  if(selectedAnswer !== undefined) {
                      this.selectedOption = this.currentDropdown[selectedAnswer];
                  }
              });
          }
      });
  }

  setValue(label, val, type) {
    console.log(label, val, type);
    if (this.fromComponent === 'goal') {
        this.translate.get('label.' + label + val.toLowerCase()).subscribe((res: string) => {
            this.selectedOption = res;
        });
        const index = this.goals.indexOf(val);
        if (index === -1) {
            this.goals.push(val);
        }
        if(this.goals.length === 3) {
            this.questionsService.setDropdown(label, this.goals, type);
        }
    } else {
        this.translate.get('label.' + label).subscribe((res: string) => {
            this.selectedOption = val + ' ' + res;
            let height = this.questionsService.heightInches;
            height[label] = val;
            if(height.foot != undefined && height.inches != undefined) {
                const height_inches =  (parseInt(height.foot) * 12)+parseInt(height.inches);
                console.log('height_inches', height_inches);
                this.questionsService.setDropdown(label, height_inches, type);
            }
        });
    }
  }
}

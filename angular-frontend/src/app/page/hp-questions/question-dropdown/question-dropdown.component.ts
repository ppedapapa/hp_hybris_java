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
  goalDropdown;
  currentGoals = [];

  constructor(private translate: TranslateService,
              private questionsService: QuestionsService) { }

  ngOnInit() {
      this.translate.stream('label.' + this.firstLabel).subscribe((res: string) => {
          this.selectedOption = res;
      });
      if (this.fromComponent === 'goal') {
          this.goalDropdown = this.questionsService.goalDropdown;
          this.goalDropdown.forEach(item => {
              this.translate.stream('label.' + this.label + item.toLowerCase()).subscribe((res: string) => {
                  this.currentDropdown[item] = res;
                  const selectedAnswer = this.goals['goal'+this.index];
                  if(selectedAnswer !== undefined) {
                      this.selectedOption = this.currentDropdown[selectedAnswer];
                  }
              });
          });
      } else {
          this.dropdown.forEach(item => {
              this.translate.stream('label.' + this.label).subscribe((res: string) => {
                  this.currentDropdown[item] = item + ' ' + res;
                  const selectedAnswer = this.heightInches[this.label];
                  if(selectedAnswer !== undefined) {
                      this.selectedOption = this.currentDropdown[selectedAnswer];
                  }
              });
          });
      }

  }

  formatGoalList() {
      if (this.fromComponent === 'goal') {
          this.dropdown = [];
          this.goalDropdown.forEach(val => {
              if (this.goals['goal0'] != val && this.goals['goal1'] != val && this.goals['goal2'] != val) {
                  this.dropdown.push(val);
              }
          });
      }
  }

  setValue(label, val, type) {
    if (this.fromComponent === 'goal') {
        this.translate.stream('label.' + label + val.toLowerCase()).subscribe((res: string) => {
            this.selectedOption = res;
        });
        this.goals['goal'+this.index] = val;

        for(let key in this.goals) {
            if(this.goals[key] != undefined) {
                this.currentGoals.push(this.goals[key]);
            }
        }
        if(this.currentGoals.length === 3) {
            this.questionsService.setDropdown(label, this.currentGoals, type);
        }
    } else {
        this.translate.stream('label.' + label).subscribe((res: string) => {
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

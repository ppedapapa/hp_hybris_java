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
  currentDropdown = {};
  selectedOption;

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
              });
          } else {
              this.translate.get('label.' + this.label).subscribe((res: string) => {
                  this.currentDropdown[item] = item + ' ' + res;
              });
          }
          console.log(this.currentDropdown);
      });
  }

  setValue(label, val) {
      console.log(val);
        if (this.fromComponent === 'goal') {
            this.translate.get('label.' + label + val.toLowerCase()).subscribe((res: string) => {
                this.selectedOption = res;
            });
        } else {
            this.translate.get('label.' + label).subscribe((res: string) => {
                this.selectedOption = val + ' ' + res;
            });
        }
    this.questionsService.setDropdown(label, val);
  }
}

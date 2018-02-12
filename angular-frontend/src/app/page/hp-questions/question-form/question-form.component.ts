import { Component, OnInit, Input, ViewEncapsulation } from '@angular/core';
import { QuestionsService } from "../../../services/questions.service";
import { Router } from "@angular/router";
import { Validators, FormControl, FormGroup } from '@angular/forms'

@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class QuestionFormComponent implements OnInit {

    @Input() questions;
    @Input() pageIndex;
    answered = this.questionsService.getAnswered();
    form: FormGroup;
    firstLastName = "^[a-zA-Z\s\&/\.()-,']+$";
    emailPattern = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";

    constructor(private questionsService: QuestionsService,
              private router: Router) {}

    ngOnInit() {
        this.form = this.toFormGroup(this.questions);
        this.form.valueChanges.subscribe(
            val => {
                console.log('this.form.valid ===========', val, this.form);
                this.questions.forEach(question => {
                    question.options.forEach(option => {
                        console.log( this.answered[option.name], val[option.name]);
                        if(this.form.valid) {
                            this.answered[option.name] = val[option.name];
                        }
                    });
                });

        });
    }

    toFormGroup(questions ) {
        let group: any = {};
        let pattern;
        questions.forEach(question => {
            question.options.forEach(option => {
                if(option.name == "first_name" || option.name == 'last_name') {
                    pattern = this.firstLastName;
                } else {
                    pattern = this.emailPattern;
                }
                group[option.name] =  new FormControl(option.value || '', {validators: [Validators.required, Validators.pattern(pattern)], updateOn: 'change'});
            });
        });
        return new FormGroup(group);
    }

    get first_name() {
        return this.form.get('first_name');
    }

    get last_name() {
        return this.form.get('last_name');
    }

    get email() {
        return this.form.get('email');
    }

    validPage() {
        return this.questionsService.validCurrentPage();
    }

    getResults() {
      /*const answered = {
          country_code: "US",
          language: "en",
          age: 10,
          gender: "M",
          weight_lbs: 123,
          height_inches: 87,
          energy: 1,
          memory: 1,
          stress: 2,
          sleep: 1,
          exercise_frequency: 2,
          exercise_intensity: 2,
          toxins: 1,
          fruits: 1,
          vegetables: 1,
          grains: 1,
          dairy: 2,
          healthy_fats: 2,
          water: 2,
          junk_food: 2,
          sugar_drinks: 2,
          breakfast: 2,
          organic: 1,
          spending: 2,
          health_goals: [
              "WEIGHT",
              "PERFORMANCE",
              "AGING"
          ],
          is_guest: true
      };*/

      this.questionsService.setAnsweredSubject(this.answered);

      // this.router.navigate(['/healthprint-results', { data:answeredObj, skipLocationChange: true}]);
      this.router.navigate(['/healthprint-results']);
    }
}

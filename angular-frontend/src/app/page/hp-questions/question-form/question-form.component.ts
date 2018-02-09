import {Component, OnInit, Input, ViewEncapsulation} from '@angular/core';
import {QuestionsService} from "../../../services/questions.service";
import {Router} from "@angular/router";

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

  constructor(private questionsService: QuestionsService,
              private router: Router) { }

  ngOnInit() {

  }

  inputChange(name, event) {
      const val = event.target.value;
      this.questionsService.setInput(name, val);
      console.log(name, val);
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

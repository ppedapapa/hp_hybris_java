import { Component, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";

@Component({
  selector: 'app-hp-goals',
  templateUrl: './hp-goals.component.html',
  styleUrls: ['./hp-goals.component.scss']
})
export class HpGoalsComponent implements OnInit {
	private healthPrintResultInfo: any;
	
	constructor(private healthPrinResultsService: HealthPrintResultsService) { }

	ngOnInit() {
		this.healthPrinResultsService.healthPrintResultInfo.subscribe(
            healthPrintResultInfo => this.healthPrintResultInfo = healthPrintResultInfo);
            console.log(this.healthPrintResultInfo.questions.health_goals);
	}

	readGoalInfo(){
		this.healthPrinResultsService.openModal('goal', 'data');
	}

}

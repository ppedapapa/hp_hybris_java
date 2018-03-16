import { Component, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";
import { GoogleAnalyticsService } from "../../../services/google-analytics.service";

@Component({
  selector: 'app-hp-goals',
  templateUrl: './hp-goals.component.html',
  styleUrls: ['./hp-goals.component.scss']
})
export class HpGoalsComponent implements OnInit {
	private healthPrintResultInfo: any;
	
	constructor(private healthPrintResultsService: HealthPrintResultsService,
				private analyticsService: GoogleAnalyticsService) { }

	ngOnInit() {
		this.healthPrintResultsService.healthPrintResultInfo.subscribe(
            healthPrintResultInfo => this.healthPrintResultInfo = healthPrintResultInfo);
	}

	readGoalInfo(){
		let data = {};
		this.healthPrintResultsService.openModal('goal', data);
		this.analyticsService.emitEvent('continue on goal', 'Goal');
	}

}

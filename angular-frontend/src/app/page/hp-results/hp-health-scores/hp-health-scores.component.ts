import { Component, OnInit, Input, ViewEncapsulation } from '@angular/core';
import { Observable} from 'rxjs/Observable';

import { HealthPrintResultsService } from '../../../services/hp-results.service';

@Component({
    selector: 'app-hp-health-scores',
    templateUrl: './hp-health-scores.component.html',
    styleUrls: ['./hp-health-scores.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class HpHealthScoresComponent implements OnInit {

    private healthPrintResultInfo: any;
    private diet: any;
    private lifeStyle: any;
    // private contentInfo:Observable<Object[]>;
    private contentInfo:any = {};
    bmiWeightRange = {};
    recap = this.healthPrintResultsService.questions;

    constructor(private healthPrintResultsService: HealthPrintResultsService) { }

    ngOnInit() {
        let minWeight = this.getBMI(18.5, this.recap.height_inches);
        let maxWeight = this.getBMI(24.9, this.recap.height_inches);
        this.bmiWeightRange['minWeight'] = Math.floor(minWeight);
        this.bmiWeightRange['maxWeight'] = Math.floor(maxWeight);

        this.healthPrintResultsService.healthPrintResultInfo.subscribe(
            healthPrintResultInfo => this.healthPrintResultInfo = healthPrintResultInfo);
        this.healthPrintResultsService.getContent().subscribe(data => {
            this.contentInfo = data;
            //console.log(data);
        });
        this.diet = 'diet';
        this.lifeStyle = 'lifestyle';
        this.contentInfo={'bmi':{score:0}};
        console.log(this.contentInfo);
    }


    getBMI(bmi, height) {
        var heightInches = (parseInt(height.foot) * 12)+parseInt(height.inches);
        return (Math.pow(heightInches, 2) * bmi / 703);
    }
}

import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";



@Component({
  selector: 'app-hp-header',
  templateUrl: './hp-header.component.html',
  styleUrls: ['./hp-header.component.scss'],
  encapsulation: ViewEncapsulation.None
})

export class HpHeaderComponent implements OnInit {

    private allHealthPrintsResults:any;
    private healthPrintResultInfo:any;
    private healthProfileId:string;

   constructor(private healthPrintResultsService:HealthPrintResultsService) {}

    ngOnInit() {
        this.healthPrintResultsService.allHealthPrintResults.subscribe( allHealthPrintsResults => this.allHealthPrintsResults = allHealthPrintsResults );
        this.healthPrintResultsService.healthPrintResultInfo.subscribe(healthPrintResultInfo => this.healthPrintResultInfo = healthPrintResultInfo);
        this.healthProfileId = this.healthPrintResultInfo.health_profile_id;
   }

    public changeHealthPrintResultInfo(event):void {
       for (let index in this.allHealthPrintsResults) {
           let curHealthPrintResult = this.allHealthPrintsResults[index];

           if ( this.healthProfileId == curHealthPrintResult.health_profile_id ) {
               this.healthPrintResultsService.setHealthPrintResultInfo(curHealthPrintResult);
               this.healthPrintResultsService.getRecommendation(curHealthPrintResult).subscribe(responseData => {
                   let recommendationData = {recommendations:responseData};
                   this.healthPrintResultsService.setResultsData(recommendationData);
                })
           }
       }
    }

    startOverQuiz(){
        this.healthPrintResultsService.startOverQuiz();
    }

    public printResults(event) {
        event.stopPropagation();
        console.log("print result page");
        console.log(this.healthPrintResultInfo);
        return false;
    }


    public openEmailResultMore(event) {
        event.stopPropagation();
        console.log("open Email results more");
        console.log(this.healthPrintResultInfo);
        return false;
    }

}



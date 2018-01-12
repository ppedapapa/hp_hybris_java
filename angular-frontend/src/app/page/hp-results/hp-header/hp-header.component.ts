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
        this.healthPrintResultsService.allHealthPrintResults.subscribe(allHealthPrintsResults => this.allHealthPrintsResults = allHealthPrintsResults);
        this.healthPrintResultsService.healthPrintResultInfo.subscribe(healthPrintResultInfo => this.healthPrintResultInfo = healthPrintResultInfo);
        this.healthProfileId = this.healthPrintResultInfo.health_profile_id;

        //this.deviceInfo = this.deviceService.getDeviceInfo();
        //console.log(this.deviceInfo);
   }

    public changeHealthPrintResultInfo(event):void{
       for (let index in this.allHealthPrintsResults) {
           let curHealthPrintResult = this.allHealthPrintsResults[index];

           if(this.healthProfileId == curHealthPrintResult.health_profile_id) {
               this.healthPrintResultsService.setHealthPrintResultInfo(curHealthPrintResult);
           }
       }
    }

    public startOverQuiz(event) {
        event.stopPropagation();
       console.log("start over quiz");
        console.log(this.healthPrintResultInfo);
       return false;
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



import { Component, OnInit, Input, ViewEncapsulation } from '@angular/core';
import { HealthPrintResultsService } from '../../../services/hp-results.service';
import { Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-hp-health-scores',
  templateUrl: './hp-health-scores.component.html',
  styleUrls: ['./hp-health-scores.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpHealthScoresComponent implements OnInit {

  private healthPrintResultInfo: any;
  // private contentInfo:Observable<Object[]>;
  private contentInfo:any;

  constructor(private healthPrintResultsService: HealthPrintResultsService) { }

  ngOnInit() {
      this.healthPrintResultsService.healthPrintResultInfo.subscribe(
        healthPrintResultInfo => this.healthPrintResultInfo = healthPrintResultInfo);
      this.healthPrintResultsService.getContent().subscribe(data => {
          this.contentInfo = data;
          //console.log(data);
      });
      console.log(this.contentInfo);
  }
}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import { HealthPrintResultsService } from '../../services/hp-results.service';

@Component({
  selector: 'app-hp-results',
  templateUrl: './hp-results.component.html',
  styleUrls: ['./hp-results.component.scss']
})

export class HpResultsComponent implements OnInit {
    private resultsData: any;

    constructor(private route: ActivatedRoute, private healthPrintResultsService: HealthPrintResultsService) { }

  ngOnInit() {
      this.route.data.subscribe((data: {}) => {
          this.resultsData = this.route.snapshot.data['healthPrintResults'];
          this.healthPrintResultsService.setResultsData(this.resultsData);
          // this.resultsBundle = this.resultsData['recommendations'];
          console.log('this.resultsData ', this.resultsData );
          // this.healthPrintResultsService.setAllHealthPrintResult(this.resultsData);
          // this.healthPrintResultsService.setHealthPrintResultInfo(this.resultsData);
      });

      /* const skus = '22067,89384';
      this.healthPrintResultsService.getProductContent(skus).subscribe(responseData => {
          this.productInfo = responseData['products'];
         console.log(this.productInfo);
      });

      this.healthPrintResultsService.getRecommendation().subscribe(responseData => {
         this.recommendationInfo = {data: responseData['data'], bundles: responseData['bundles']};
         console.log("asdfs", this.recommendationInfo);
      }); */
  }
}

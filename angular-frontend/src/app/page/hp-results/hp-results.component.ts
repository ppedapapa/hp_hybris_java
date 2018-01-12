import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { HealthPrintResultsService } from "../../services/hp-results.service";

@Component({
  selector: 'app-hp-results',
  templateUrl: './hp-results.component.html',
  styleUrls: ['./hp-results.component.scss']
})

export class HpResultsComponent implements OnInit {

    private productInfo:any;
    constructor(private route:ActivatedRoute, private healthPrintResultsService:HealthPrintResultsService) { }

  ngOnInit() {
      this.route.data.subscribe((data:{}) => {
          this.healthPrintResultsService.setAllHealthPrintResult(data['healthPrintResults']);
          this.healthPrintResultsService.setHealthPrintResultInfo(data['healthPrintResults'][0]);
      });
      var skus = '22067,89384';
      this.healthPrintResultsService.getProductContent(skus).subscribe(responseData => {
          this.productInfo = responseData['products'];
         console.log(this.productInfo);
      });
  }
}

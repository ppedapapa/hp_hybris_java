import { Component, OnInit } from '@angular/core';
import {HealthPrintResultsService} from "../../../services/hp-results.service";

@Component({
  selector: 'app-hp-kids',
  templateUrl: './hp-kids.component.html',
  styleUrls: ['./hp-kids.component.scss']
})
export class HpKidsComponent implements OnInit {
  showKids = false;
  kidsBundle;
  kids = false;

  constructor(private resultsService: HealthPrintResultsService) { }

  ngOnInit() {
    this.kidsBundle = this.resultsService.getKidsSkus()
    console.log('kidsBundle', this.kidsBundle);
  }

  kidsBundleOn = function () {
      this.showKids = true;
  };

  kidsBundleOff = function () {
      this.showKids = false;
  };
}

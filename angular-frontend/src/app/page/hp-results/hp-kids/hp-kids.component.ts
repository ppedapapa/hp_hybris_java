import { Component, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";

@Component({
  selector: 'app-hp-kids',
  templateUrl: './hp-kids.component.html',
  styleUrls: ['./hp-kids.component.scss']
})
export class HpKidsComponent implements OnInit {
  showKids = false;
  bundles = [];
  kidsBundle = [];
  kids = false;

  constructor(private healthPrintResultsService: HealthPrintResultsService) { }

  ngOnInit() {
    const tiers: string[] = ["KIDS"];
    this.healthPrintResultsService.getBundles().subscribe(bundle => {
      for(let val in bundle) {
          if(tiers.indexOf(bundle[val]['bundle']) !== -1) {
              this.bundles.push(bundle[val]);
          }
      }
      this.kidsBundle = this.bundles[0];
    });
  }

  kidsBundleOn = function () {
      this.showKids = true;
  };

  kidsBundleOff = function () {
      this.showKids = false;
  };
}

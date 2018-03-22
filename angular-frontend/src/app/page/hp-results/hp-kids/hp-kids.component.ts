import { Component, OnInit } from '@angular/core';
import { HpConfigService } from "../../../services/hp-config.service";
import { HealthPrintResultsService } from "../../../services/hp-results.service";
import { GoogleAnalyticsService } from "../../../services/google-analytics.service";

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

  constructor(private healthPrintResultsService: HealthPrintResultsService,
              private analyticsService: GoogleAnalyticsService,
              private hpConfigService: HpConfigService ) { }

  ngOnInit() {
    const tiers: string[] = ["KIDS"];
  
      this.healthPrintResultsService.healthPrintResultInfo.subscribe( healthPrintResultInfo => {
          this.kids = (healthPrintResultInfo['questions']['age'] <= this.hpConfigService.getKosherSkus())?true:false;
      });
  
    this.healthPrintResultsService.getBundles().subscribe(bundle => {
      this.bundles = [];
      this.kidsBundle = [];
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
      this.analyticsService.emitEvent('view kids bundle', 'Kids');
  };

  kidsBundleOff = function () {
      this.showKids = false;
      this.analyticsService.emitEvent('close kids bundle', 'Kids');
  };
}

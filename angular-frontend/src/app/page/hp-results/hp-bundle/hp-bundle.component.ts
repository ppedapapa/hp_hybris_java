import { Component, Input, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";
import { environment } from "../../../../environments/environment";

@Component({
  selector: 'app-hp-bundle',
  templateUrl: './hp-bundle.component.html',
  styleUrls: ['./hp-bundle.component.scss']
})
export class HpBundleComponent implements OnInit {
  bundles = [];

  constructor(private healthPrintResultsService: HealthPrintResultsService) { }

  ngOnInit() {
    const tiers: string[] = ['TIER_1', 'TIER_2', 'TIER_3'];
    this.healthPrintResultsService.getBundles().subscribe(bundle => {
        this.bundles = [];
        for(let val in bundle) {
            if(tiers.indexOf(bundle[val]['bundle']) !== -1) {
                this.bundles.push(bundle[val]);
            }
        }
    });
  }
}

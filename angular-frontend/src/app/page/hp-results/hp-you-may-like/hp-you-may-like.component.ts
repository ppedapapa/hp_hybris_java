import { Component, Input, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";

@Component({
  selector: 'app-hp-you-may-like',
  templateUrl: './hp-you-may-like.component.html',
  styleUrls: ['./hp-you-may-like.component.scss']
})
export class HpYouMayLikeComponent implements OnInit {

    bundles;
    considerBundle;
    isMobile = false;
    kids = false;

    constructor(private healthPrintResultsService: HealthPrintResultsService) { }

    ngOnInit() {
        const tiers: string[] = ['CONSIDER'];
        this.bundles = this.healthPrintResultsService.getBundles(tiers);
        this.considerBundle = this.bundles[0];
        console.log('consider bundles', this.bundles);
    }
}

import { Component, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";
import { HpConfigService } from "../../../services/hp-config.service";
import {isEmpty} from "rxjs/operators";

@Component({
  selector: 'app-hp-get-clean',
  templateUrl: './hp-get-clean.component.html',
  styleUrls: ['./hp-get-clean.component.scss']
})
export class HpGetCleanComponent implements OnInit {
  kids = false;
  image;
  price;
  getCleanSkuInfo = [];
  getCleanSku = this.hpConfigService.getCleanSku();

  constructor(private healthPrintResultsService: HealthPrintResultsService,
              private hpConfigService: HpConfigService) { }

  ngOnInit() {
      this.healthPrintResultsService.getSkuInfo().subscribe(skuInfo => {
        if(Object.keys(skuInfo).length !== 0) {
          this.getCleanSkuInfo.push({sku: this.getCleanSku, info: skuInfo[this.getCleanSku]});
        }
      });
  }
}

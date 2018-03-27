import { Component, Input, OnInit } from '@angular/core';
import { HealthPrintResultsService } from "../../../services/hp-results.service";

@Component({
  selector: 'app-hp-bundle',
  templateUrl: './hp-bundle.component.html',
  styleUrls: ['./hp-bundle.component.scss']
})
export class HpBundleComponent implements OnInit {
  bundles = [];
  isCanFr = false;
  isMobile = false;
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
        this.bundles = this.updateBundlesPricing(this.bundles).sort((a, b) => a.bundle.localeCompare(b.bundle));
        console.log('this.bundles before', this.bundles);
    });
  }


  updateBundlesPricing(bundles) {
      bundles.forEach(function(value, key) {
        if (!value.skus) {
            return false;
        }

        let totalBundlePricing = 0;
        let subTotalBundlePricing = 0;
        let totalBundleDiscount = 0;
        let perDayPrice;

        value.skus.forEach(function(value2, key2) {
            if(value2.category !== 'membership')
            {
                subTotalBundlePricing = subTotalBundlePricing + parseFloat(value2.info.price);
                totalBundlePricing = totalBundlePricing + parseFloat(value2.info.price);
                totalBundleDiscount = totalBundleDiscount + (parseFloat(value2.info.srpPrice) - parseFloat(value2.info.price));
            }
            else {
                totalBundlePricing = subTotalBundlePricing + value2.sn_price;
            }
        });

        bundles[key]['bundlePrice'] = totalBundlePricing;
        bundles[key]['subTotalbundlePrice'] = subTotalBundlePricing;
        bundles[key]['totalDiscount'] = totalBundleDiscount;
        
        perDayPrice = value.bundlePrice/30;
        perDayPrice = Math.ceil(perDayPrice);
        bundles[key].perDayPrice = perDayPrice;
      });
      return bundles;
  }

  filterNoMembership(skus) {
      return skus.filter( sku => sku.category != 'membership').sort((x,y) =>
      {
          if (x.info.price < y.info.price) {
              return 1;
          }
          else if (x.info.price > y.info.price) {
              return -1;
          }
          return 0;
      });
  }

  filterMembership(skus) {
      return skus.filter( sku => sku.category == 'membership');
  }

}

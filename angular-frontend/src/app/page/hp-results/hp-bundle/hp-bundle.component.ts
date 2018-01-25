import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-hp-bundle',
  templateUrl: './hp-bundle.component.html',
  styleUrls: ['./hp-bundle.component.scss']
})
export class HpBundleComponent implements OnInit {

  @Input() recommendedbundles;
  bundles;

  constructor() { }

  ngOnInit() {
    let tiers: string[] = ['TIER_1', 'TIER_2', 'TIER_3'];

    this.bundles = this.recommendedbundles
      .filter((bundle) => tiers.indexOf(bundle.bundle) !== -1)
      .sort((a, b) => a.bundle < b.bundle ? -1 : 1);
  }

}

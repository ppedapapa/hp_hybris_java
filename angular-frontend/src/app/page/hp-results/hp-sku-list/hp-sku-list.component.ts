import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-hp-sku-list',
  templateUrl: './hp-sku-list.component.html',
  styleUrls: ['./hp-sku-list.component.scss']
})
export class HpSkuListComponent implements OnInit {
  @Input() sku;
  @Input() bundleType;
  shakesSkuList = ['21261','21264','21267','21270','56261','56264','56267','56270'];

  constructor() { }

  ngOnInit() {}

  shakeSkuList(sku) {
    if(this.shakesSkuList.indexOf(sku) > -1){
      return true;
    }else{
      return false;
    }
  }

}

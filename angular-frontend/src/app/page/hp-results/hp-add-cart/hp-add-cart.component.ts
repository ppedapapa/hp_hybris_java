import { Component, Input, OnInit, ViewEncapsulation } from '@angular/core';
import { Buffer } from 'buffer';
import { environment } from '../../../../environments/environment';

import { CookieService } from 'ngx-cookie-service';
import { CartService } from '../../../services/cart.service';
import {HealthPrintResultsService} from "../../../services/hp-results.service";

@Component({
  selector: 'app-hp-add-cart',
  templateUrl: './hp-add-cart.component.html',
  styleUrls: ['./hp-add-cart.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpAddCartComponent implements OnInit {

  @Input() bundle;
  @Input() sku;
  constructor( private cookieService: CookieService,
               private cartService: CartService,
               private healthPrinResultsService: HealthPrintResultsService,) { }

  ngOnInit() {
  }

  addToCart() {
    let sku = [];
    sku.push(this.sku.sku);
    this.cartService.addToCart(this.sku);
    console.log(sku);
    this.healthPrinResultsService.openModal('content');
    // this.cartService.addToCart(['20282', '21261']);
  }
  // addBundleToCart() {
  //     this.cartService.addBundleToCart(['20282', '21261'], 'COMPREHENSIVE', '80.15', '52355');
  // }

}

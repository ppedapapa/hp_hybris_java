import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Buffer } from 'buffer';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-hp-add-cart',
  templateUrl: './hp-add-cart.component.html',
  styleUrls: ['./hp-add-cart.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpAddCartComponent implements OnInit {

  constructor( private cookieService: CookieService ) { }

  ngOnInit() {
  }

  addToCart(sku, event, bundle) {
    const item = {};
    item[sku] = 1;
    const productsToAdd = {};
      productsToAdd[sku] = 1;
      productsToAdd['89384'] = 1;
    const healthprintCart = {};
    healthprintCart['recommendationBundle'] = bundle;
    healthprintCart['recommendationTotal'] = 10.21;
    healthprintCart['addAllToCart'] = false;
    healthprintCart['productsToAdd'] = productsToAdd;
    healthprintCart['freeProduct'] = '89384';

     const hpCartCookie = JSON.stringify(healthprintCart);
     const hpCartObjJsonB64 = new Buffer(hpCartCookie).toString('base64');
    this.cookieService.set('shakleeUS-healthprint-cart', hpCartObjJsonB64, 100, '/', '.shakleedev.com');
      window.location.href = 'https://www.shakleedev.com:9002/cart';
  }

}

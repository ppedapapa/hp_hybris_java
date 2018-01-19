import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { Buffer } from 'buffer';
import { CookieService } from 'ngx-cookie-service';
import { CartService } from '../../../services/cart.service';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-hp-add-cart',
  templateUrl: './hp-add-cart.component.html',
  styleUrls: ['./hp-add-cart.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpAddCartComponent implements OnInit {

  constructor( private cookieService: CookieService, private cartService: CartService ) { }

  ngOnInit() {
  }

  addToCart(sku, event, bundle) {
    this.cartService.addToCart(['20282', '21261']);
  }
  addBundleToCart() {
      this.cartService.addBundleToCart(['52355', '21261'], 'ADVANCED', '80.15', '20282');
  }

}

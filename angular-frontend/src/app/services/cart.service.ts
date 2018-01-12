
import { Injectable, Inject } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { DOCUMENT } from '@angular/platform-browser';
import { Buffer } from 'buffer';
import { environment } from '../../environments/environment';

@Injectable()
export class CartService {
    constructor( private cookieService: CookieService, @Inject(DOCUMENT) private document: any) { }

    addToCart(sku) {
        const healthprintCart = {};
        const skuWithQtyAddCartList = {};
        const curCookie = this.cookieService.get('shakleeUS-healthprint-cart');
        if (curCookie) {
            const curItemsInCart = JSON.parse(atob(curCookie));
            skuWithQtyAddCartList[sku] = (curItemsInCart['productsToAdd'][sku]) ? curItemsInCart['productsToAdd'][sku] + 1 : 1;
        } else {
            skuWithQtyAddCartList[sku] = 1;
        }

        healthprintCart['productsToAdd'] = skuWithQtyAddCartList;
        healthprintCart['addAllToCart'] = false;
        healthprintCart['freeProduct'] = '89384'; /**remove from hybris */
        console.log('healthprintCart', healthprintCart);

        const hpCartCookie = JSON.stringify(healthprintCart);
        const hpCartObjJsonB64 = new Buffer(hpCartCookie).toString('base64');
        this.cookieService.set('shakleeUS-healthprint-cart', hpCartObjJsonB64, 100, '/', environment.domainName);
        window.location.href = 'https://' + environment.hybrisServerName + '/cart';
    }

    addBundleToCart(skuList, bundle, recommendationTotal, freeProduct) {

        const skuWithQtyAddCartList = {};
        const healthprintCart = {};
        const curCookie = this.cookieService.get('shakleeUS-healthprint-cart');
        for (const sku of skuList) {
          if (curCookie) {
            const curItemsInCart = JSON.parse(atob(curCookie));
            skuWithQtyAddCartList[sku] = (curItemsInCart['productsToAdd'][sku]) ? curItemsInCart['productsToAdd'][sku] + 1 : 1;
          } else {
              skuWithQtyAddCartList[sku] = 1;
          }
        }

        healthprintCart['productsToAdd'] = skuWithQtyAddCartList;
        healthprintCart['recommendationBundle'] = bundle;
        healthprintCart['recommendationTotal'] = recommendationTotal;
        healthprintCart['addAllToCart'] = 'true';
        healthprintCart['freeProduct'] = freeProduct;
        const hpCartCookie = JSON.stringify(healthprintCart);
        const hpCartObjJsonB64 = new Buffer(hpCartCookie).toString('base64');
        this.cookieService.set('shakleeUS-healthprint-cart', hpCartObjJsonB64, 100, '/', environment.domainName);
        this.document.location.href = 'https://' + environment.hybrisServerName + '/cart';
    }
}

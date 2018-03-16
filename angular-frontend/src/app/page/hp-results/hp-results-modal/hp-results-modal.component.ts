import { Component, Input, ViewEncapsulation } from '@angular/core';
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { CartService } from '../../../services/cart.service';

@Component({
  selector: 'app-hp-results-modal',
  templateUrl: './hp-results-modal.component.html',
  styleUrls: ['./hp-results-modal.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpResultsModalComponent {

  @Input() modalName;
  @Input() data;

  constructor(public activeModal: NgbActiveModal, public cartService: CartService) {}
  redirectToCart() {
      this.cartService.redirectCart();
  }
}

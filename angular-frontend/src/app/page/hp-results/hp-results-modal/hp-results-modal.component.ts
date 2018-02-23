import {Component, Input} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-hp-results-modal',
  templateUrl: './hp-results-modal.component.html',
  styleUrls: ['./hp-results-modal.component.scss']
})
export class HpResultsModalComponent {

  @Input() name;

  constructor(public activeModal: NgbActiveModal) { }
}

import { Component, OnInit, Input, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-hp-health-score',
  templateUrl: './hp-health-score.component.html',
  styleUrls: ['./hp-health-score.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpHealthScoreComponent implements OnInit {

  @Input() healthPrintResultInfo: any;
  constructor() { }

  ngOnInit() {
  }

}

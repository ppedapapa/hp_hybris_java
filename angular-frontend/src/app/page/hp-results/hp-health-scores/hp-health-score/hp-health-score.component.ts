import { Component, OnInit, Input, ViewEncapsulation } from '@angular/core';
import { HealthPrintResultsService } from "../../../../services/hp-results.service";

@Component({
  selector: 'app-hp-health-score',
  templateUrl: './hp-health-score.component.html',
  styleUrls: ['./hp-health-score.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpHealthScoreComponent implements OnInit {

  @Input() scoreName;
  @Input() contentInfo;
  @Input() healthPrintResultInfo: any;

  constructor(private healthPrintResultsService: HealthPrintResultsService) { }

  ngOnInit() {
    console.log('scoreName', this.scoreName);
  }

  readMoreInfo(data) {
    const modalName = 'readMore';
    const modalData = 'modalData';
    this.healthPrintResultsService.openModal(modalName, modalData);
  }

}

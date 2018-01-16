import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {HpConfigService} from '../../services/hp-config.service';
import { QuestionsService } from '../../services/questions.service';

@Component({
  selector: 'app-hp-questions',
  templateUrl: './hp-questions.component.html',
  styleUrls: ['./hp-questions.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HpQuestionsComponent implements OnInit {

  pager;

  constructor(private questionsService: QuestionsService,
              private hpconfigService: HpConfigService) { }

  ngOnInit() {
    this.pager = this.hpconfigService.getPager();
  }

  get pages() {
    const pages = this.questionsService.getPages();
    return (pages) ? pages.slice(this.pager.index, this.pager.index + 1) : [];
  }
}

import { Component, OnInit } from '@angular/core';
import { QuestionsService } from '../../services/questions.service';
import { HpConfigService } from '../../services/hp-config.service';

@Component({
  selector: 'app-hp-nav',
  templateUrl: './hp-nav.component.html',
  styleUrls: ['./hp-nav.component.scss']
})
export class HpNavComponent implements OnInit {

  pager;
  config;
  pages;

  constructor(private questionsService: QuestionsService,
              private hpconfigService: HpConfigService) { }

  ngOnInit() {
    this.pager = this.hpconfigService.getPager();
    this.config = this.hpconfigService.getConfig();
    this.pages = this.questionsService.getPages();
  }

  goTo(index: number) {
    this.questionsService.goTo(index);
  }
}

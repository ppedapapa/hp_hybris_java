import {Component, OnDestroy, OnInit} from '@angular/core';
import { QuestionsService } from '../../services/questions.service';
import { HpConfigService } from '../../services/hp-config.service';
import { Subscription } from "rxjs/Subscription";

@Component({
  selector: 'app-hp-nav',
  templateUrl: './hp-nav.component.html',
  styleUrls: ['./hp-nav.component.scss']
})
export class HpNavComponent implements OnInit, OnDestroy {

  pagelengthSub: Subscription;
  pagelength: number = 0;
  arr = Array;
  pager;

  constructor(private questionsService: QuestionsService,
              private hpconfigService: HpConfigService) { }

  ngOnInit() {
    this.pager = this.hpconfigService.getPager();
    this.pagelengthSub = this.hpconfigService.length.subscribe(length => this.pagelength = length);
  }

  goTo(index: number) {
    this.questionsService.goTo(index);
  }

  ngOnDestroy() {
    this.pagelengthSub.unsubscribe();
  }
}

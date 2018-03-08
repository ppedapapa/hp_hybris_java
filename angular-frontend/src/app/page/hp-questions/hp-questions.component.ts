import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { trigger, query, animate, transition, style } from '@angular/animations';

import {HpConfigService} from '../../services/hp-config.service';
import { QuestionsService } from '../../services/questions.service';

@Component({
  selector: 'app-hp-questions',
  templateUrl: './hp-questions.component.html',
  styleUrls: ['./hp-questions.component.scss'],
  encapsulation: ViewEncapsulation.None,
  animations: [
      trigger('transition', [
          transition('* <=> *', [
              style({transform: 'translateX(100%)'}),
              animate('500ms ease',
                  style({
                      opacity: 1,
                      transform: 'translateX(0%)'
                  })
              )
          ])
      ])
  ]
})

export class HpQuestionsComponent implements OnInit {
  @Input() fadeInAnimation;
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

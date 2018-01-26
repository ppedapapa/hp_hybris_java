import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hp-goals',
  templateUrl: './hp-goals.component.html',
  styleUrls: ['./hp-goals.component.scss']
})
export class HpGoalsComponent implements OnInit {

  goals =  [
      { name:"PERFORMANCE",
        keys:["performance-key-1","performance-key-1","performance-key-1"]
      },
      { name:"AGING",
        keys:["aging-key-13","aging-key-55","aging-key-9"]
      },
      { name:"WEIGHT",
        keys:["weight-key-55","weight-key-59","weight-key-9"]
      }
    ];

  constructor() { }

  ngOnInit() {
  }

}

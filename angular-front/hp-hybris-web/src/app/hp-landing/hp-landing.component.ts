import { Component, OnInit } from '@angular/core';

import { AppService } from '../app.service';

@Component({
  selector: 'app-hp-landing',
  templateUrl: './hp-landing.component.html',
  styleUrls: ['./hp-landing.component.scss']
})
export class HpLandingComponent implements OnInit {
  appGreetings = this.serverService.getGreetings();
  appConst = {country: 'us', language: 'en'};
  langPageImageLink = this.appConst.country + this.appConst.language;
  imagePath = 'https://images.shaklee.com/healthprint/landing/';
  image1 = 'hp-landing-lifestyle-01a-' + this.langPageImageLink + '.jpg';
  image2 = 'hp-landing-chart-01a-' + this.langPageImageLink + '.jpg';
  image3 = 'hp-landing-logos-01a-' + this.langPageImageLink + '.jpg';

  constructor( private serverService: AppService) {
    console.log(this.appGreetings);
  }

  ngOnInit() {
  }

}

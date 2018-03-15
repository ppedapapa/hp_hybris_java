import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AppService } from '../app.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {
  isUserLogin: boolean  = false;
  constructor(  private appService: AppService) { }

  ngOnInit() {
      this.isUserLogin = this.appService.isUserLogin();
  }

}

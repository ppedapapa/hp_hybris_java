import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
// import as RouterEvent to avoid confusion with the DOM Event
import { Event as RouterEvent,     Router, NavigationStart, NavigationEnd, NavigationCancel, NavigationError } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  loading = true;
  constructor(private translate: TranslateService,
              private router: Router) {
    router.events.subscribe((event: RouterEvent) => {
        this.navigationInterceptor(event)
    });
    translate.setDefaultLang('us-en');
  }

  ngOnInit() {}

  // Shows and hides the loading spinner during RouterEvent changes
  navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
        this.loading = true
    }
    if (event instanceof NavigationEnd || event instanceof NavigationCancel || event instanceof NavigationError) {
        this.loading = false
    }
  }
}

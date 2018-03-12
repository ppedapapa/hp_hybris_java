import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
// import as RouterEvent to avoid confusion with the DOM Event
import { Event as RouterEvent,     Router, NavigationStart, NavigationEnd, NavigationCancel, NavigationError } from '@angular/router';

import { environment } from '../environments/environment';
import { GoogleAnalyticsService } from "./services/google-analytics.service";
import { AppService } from '../app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  loading = true;
  constructor(private googleAnalyticsService: GoogleAnalyticsService,
  			  private appService: AppService,
              private translate: TranslateService,
              private router: Router) {
    router.events.subscribe((event: RouterEvent) => {
        this.navigationInterceptor(event)
    });
    let appConst = appService.getAppConst();
    translate.setDefaultLang(appConst.selectedLang);
    this.appendGaTrackingCode();
  }

  ngOnInit() {}

  // Shows and hides the loading spinner during RouterEvent changes
  navigationInterceptor(event: RouterEvent): void {
    if (event instanceof NavigationStart) {
        this.loading = true;
    }
    if (event instanceof NavigationEnd){
        this.loading = false;
        // this.googleAnalyticsService.sendPageView(event.urlAfterRedirects);
    }

    if (event instanceof NavigationCancel || event instanceof NavigationError) {
        this.loading = false;
    }
  }

  private appendGaTrackingCode() {
      try {
          const script = document.createElement('script');
          script.innerHTML = `
              (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
              (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
              m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
              })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
        
              ga('create', '` + environment.googleAnalysAccount + `', 'auto');
              ga('require', 'ec');
            `;
          //sends page view by default
          document.head.appendChild(script);
      } catch (ex) {
          console.error('Error appending google analytics');
          console.error(ex);
      }
  }
}

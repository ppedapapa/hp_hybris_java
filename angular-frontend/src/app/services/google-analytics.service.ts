import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
declare var ga: Function;

@Injectable()
export class GoogleAnalyticsService {

    constructor(public router: Router) {
        this.router.events.subscribe(event => {
            try {
                if (typeof ga === 'function') {
                    if (event instanceof NavigationEnd) {
                        ga('set', 'page', event.urlAfterRedirects);
                        ga('send', 'pageview', {page: window.location.pathname, title: "Personalized Health Questionnaire", dimension3: window.location.search});
                        console.log('%%% Google Analytics page view event %%%');
                    }
                }
            } catch (e) {
                console.log(e);
            }
        });

    }

    public emitEvent(label, section) {
        if (typeof ga === 'function') {

            let eventLabel;
            let eventAction = label;
            let eventCategory = 'HealthPrint';
            let eventValue = 1;
            let isMember = 'guest';
            let isRank = '0';

            if(sessionStorage.getItem('trackingId')) {
                eventCategory = eventCategory + "-" + sessionStorage.getItem('trackingId');
            }
            if(section !== undefined){
                eventLabel = section;
            }else{
                eventLabel = label;
            }
            ga('send', 'event', eventCategory, eventAction, eventLabel, eventValue, { dimension1: isMember, dimension2: isRank, page: window.location.pathname});
        }
    }
}
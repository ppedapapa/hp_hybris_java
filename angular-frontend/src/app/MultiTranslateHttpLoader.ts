import {HttpClient} from '@angular/common/http';
import {TranslateLoader} from '@ngx-translate/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/forkJoin';

export class MultiTranslateHttpLoader implements TranslateLoader {

    constructor(private http: HttpClient,
                public resources: { prefix: string, suffix: string }[] = [{
                    prefix: '/assets/translation/',
                    suffix: '.json'
                }]) {}

    /**
     * Gets the translations from the server
     * @param lang
     * @returns {any}
     */
    public getTranslation(lang: string): any {

        return Observable.forkJoin(this.resources.map(config => {
            return this.http.get(`${config.prefix}${lang}${config.suffix}`);
        })).map(response => {
            return response.reduce((a, b) => {
                return Object.assign(a, b);
            });
        });
    }
}
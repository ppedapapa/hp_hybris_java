import { Injectable} from "@angular/core";
import {Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {HealthPrintResultsService} from "./hp-results.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HealthPrintBundlesResolver implements Resolve <any> {
        constructor(private healthPrintResultsService:HealthPrintResultsService,
                    private router: Router ) {}

        resolve (route: ActivatedRouteSnapshot,state:RouterStateSnapshot): Observable<any>{
            return this.healthPrintResultsService.getRecommendation().map(responseData => {
                console.log('responseData', responseData);
                if (responseData['bundles'].length == 0) {
                    this.router.navigate(['/questions']);
                } else {
                    return responseData;
                }
            });
        }
}
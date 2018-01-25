import { Injectable} from "@angular/core";
import {Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {HealthPrintResultsService} from "./hp-results.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HealthPrintResultsResolver implements Resolve <any> {
        constructor(private healthPrintResultsService:HealthPrintResultsService,
                    private router: Router ) {}

        resolve (route: ActivatedRouteSnapshot,state:RouterStateSnapshot): Observable<any>|Promise<any>|any{
            return this.healthPrintResultsService.getHealthPrintResults().map(responseData => {
                //check status and throw error need to work on.ss
                if (responseData['data'].length == 0) {
                    this.router.navigate(['/questions']);
                } else {
                    return responseData['data'];
                }
            });
        }

}
import { Injectable} from "@angular/core";
import { Resolve, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { HealthPrintResultsService } from "./hp-results.service";
import { mergeMap } from "rxjs/operators";

@Injectable()
export class HealthPrintResultsResolver implements Resolve <any> {

    constructor(private healthPrintResultsService:HealthPrintResultsService, private router: Router) {}

    resolve (route: ActivatedRouteSnapshot,state:RouterStateSnapshot){
        return this.healthPrintResultsService.getAllHealthPrints()
            .pipe(mergeMap(healthPrints => {
                console.log('healthPrints pipe', healthPrints);
                if(healthPrints['status'] == 0) {
                    return this.healthPrintResultsService.getRecommendation(healthPrints['data'][0])
                    .map(res => {
                        healthPrints['recommendations'] = res;
                        console.log('resolver response', healthPrints);
                        return healthPrints;
                    });
                } else {
                    this.router.navigate(['/healthprint']);
                }

            }))
    }

}
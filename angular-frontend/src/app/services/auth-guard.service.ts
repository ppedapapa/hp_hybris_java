import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {QuestionsService} from "./questions.service";
import {AuthService} from "./auth.service";

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {
    constructor(private authService: AuthService,
                private questionsService: QuestionsService,
                private router: Router) {}

    canActivate( route: ActivatedRouteSnapshot,
                 state: RouterStateSnapshot ): Observable<boolean> | Promise<boolean> | boolean {
        let healthProfile = JSON.parse(this.questionsService.getHealthProfileInfo());
        let isUserLoggedin = route.queryParams['userLogged'];
        this.authService.setLogin(isUserLoggedin);

        console.log('authGuard',isUserLoggedin, healthProfile);

        if(isUserLoggedin != 'true' && healthProfile === null ) {
console.log('guard to questions');
            this.router.navigate(['/healthprint']);
        } else {
console.log('guard to results');
            this.router.navigate(['/healthprint-results']);
        }
        return false;
    }

    canActivateChild( route: ActivatedRouteSnapshot,
                      state: RouterStateSnapshot ): Observable<boolean> | Promise<boolean> | boolean {
        return this.canActivate(route, state);
    }
}

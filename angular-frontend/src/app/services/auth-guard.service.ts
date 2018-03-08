import {ActivatedRouteSnapshot, CanActivate, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {QuestionsService} from "./questions.service";
import {AuthService} from "./auth.service";
import {AppService} from "../app.service";

@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {
    constructor(private authService: AuthService,
                private questionsService: QuestionsService,
                private router: Router,
                private appService: AppService) {}

    canActivate( route: ActivatedRouteSnapshot,
                 state: RouterStateSnapshot ): Observable<boolean> | Promise<boolean> | boolean {
        let healthProfile = JSON.parse(this.questionsService.getHealthProfileInfo());
        let isUserLoggedin = this.appService.isUserLogin();
 
        this.authService.setLogin(isUserLoggedin);

        if ( isUserLoggedin != true && healthProfile === null ) {
          
            this.router.navigate(['/healthprint']);
        } else {
           
            this.router.navigate(['/healthprint-results']);
        }
        return false;
    }

    canActivateChild( route: ActivatedRouteSnapshot,
                      state: RouterStateSnapshot ): Observable<boolean> | Promise<boolean> | boolean {
        return this.canActivate(route, state);
    }
}

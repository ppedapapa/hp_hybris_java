// import {AppService } from "../app.service";

export class AuthService {
    loggedIn = true;

    // constructor(private appService: AppService) {}

    isAuthenticated() {
        const promise = new Promise(
            (resolve, reject) => {
                setTimeout(() => {
                    resolve(this.loggedIn);
                });
            }
        );
        // const promise = this.appService.isUserLogin();
        return promise;
    }

    login() {
        this.loggedIn = true;
    }

    logout() {
        this.loggedIn = false;
    }
}

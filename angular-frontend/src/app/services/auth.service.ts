import { ActivatedRoute } from '@angular/router';
import {Injectable} from "@angular/core";

@Injectable()
export class AuthService {
    loggedIn;
    constructor() {}

    setLogin(login) {
        this.loggedIn = (login === 'true');
    }

    getLogin() {
        return this.loggedIn;
    }

    logout() {
        this.loggedIn = false;
    }
}

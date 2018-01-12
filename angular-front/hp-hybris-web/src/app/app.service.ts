import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/RX';

@Injectable()
export class AppService {
 constructor(private http: HttpClient) {}
 getGreetings() {
     return this.http.get('https://hp-dev-dot-shaklee-gps.appspot.com/greeting?name=Shaklee')
         .subscribe(
             data => {
                 console.log(data);
                 return data;
             }
         );
    }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AppService {

    constructor(private http: HttpClient) {}

    getGreetings() {

    const headers = new HttpHeaders();
    headers.append('Access-Control-Allow-Headers', 'Content-Type');
    headers.append('Access-Control-Allow-Methods', 'GET');
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Content-Type', 'application/json; charset=utf-8');

        return this.http.get('https://localhost:9002/shakleeintegration/v2/shakleeUS/products/89384?fields=DEFAULT',
{headers: headers})
     .subscribe(
         data => {
             console.log(data);
             return data;
         }
     );
    }
}

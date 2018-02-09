import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class AppService {

  constructor(private http: HttpClient) {}
  
  isUserLogin() {
      const headers = new HttpHeaders();
      headers.append('Content-Type', 'application/json; charset=utf-8');
      headers.append('Access-Control-Allow-Credentials', 'true');
      headers.append('Access-Control-Allow-Origin', '*');
      headers.append('Cache-Control', 'no-cache');
      return this.http.get('services/hp/testUserId', {headers: headers})
         .subscribe(
             data => {
                 console.log(data);
                 return data;
             }
         );
    }
}

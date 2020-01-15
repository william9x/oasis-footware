import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { User } from './user.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    isLoggedin = false;
    // API path
    apiURL = 'https://oasis-footware.herokuapp.com/api/';

    // API path
    logoutApiURL = 'https://oasis-footware.herokuapp.com/logout';

    // Http Options
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private httpClient: HttpClient) { }

    // Login
    public login(item, data): Observable<User> {
        this.isLoggedin = true;
        return this.httpClient.post<User>(
            this.apiURL + item,
            JSON.stringify(data),
            this.httpOptions
        );
    }

    // Logout
    public logout(): Observable<User> {
        this.isLoggedin = false;
        return this.httpClient.post<User>(
            this.logoutApiURL,
            this.httpOptions
        );
    }
}

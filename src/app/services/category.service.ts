import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from '../models/category.model';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    // API path
    apiURL = 'https://oasis-footware.herokuapp.com/api/';

    // Http Options
    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private httpClient: HttpClient) { }

    // Get All Category
    public getCategories(): Observable<Category> {
        return this.httpClient.get<Category>(
            this.apiURL + 'category',
            this.httpOptions
        );
    }

    // Get Item By Id
    public getItem(id): Observable<Category> {
        return this.httpClient.get<Category>(
            this.apiURL + 'category/' + id,
            this.httpOptions
        );
    }

}

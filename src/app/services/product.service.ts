import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Products } from '../models/product.model';

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    // API path
    apiURL = 'https://oasis-footware.herokuapp.com/api/';

    // Http Options
    httpHeader = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private httpClient: HttpClient) { }

    // Get All Products
    public getAllProducts(): Observable<Products> {
        return this.httpClient.get<Products>(
            this.apiURL + 'product',
            this.httpHeader
        );
    }

    // Get Item By Id
    public getProductDetail(id): Observable<Products> {
        return this.httpClient.get<Products>(
            this.apiURL + 'product/' + id,
            this.httpHeader
        );
    }

}

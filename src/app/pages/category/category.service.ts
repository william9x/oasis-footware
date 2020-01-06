import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Category } from './category.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  // API path
  apiURL = 'https://oasis-footware.herokuapp.com/api/';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private httpClient: HttpClient) {}

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

  // Delete Item By Id
  public deleteItem(id: any): Observable<any> {
    return this.httpClient.delete(
      this.apiURL + 'category' + `/${id}`,
      this.httpOptions
    );
  }

  // Update Item By Id
  public updateItem(id: any, item): Observable<any> {
    return this.httpClient.put(
      this.apiURL + 'category' + `/${id}`,
      JSON.stringify(item),
      this.httpOptions
    );
  }
}

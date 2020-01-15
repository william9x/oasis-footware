import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from '../category.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  // API path
  apiURL = 'https://oasis-footware.herokuapp.com/api/';

  constructor(private httpClient: HttpClient) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json'
    })
  };

  // Create a new item
  public createItem(item, data): Observable<Category> {
    return this.httpClient.post<Category>(
      this.apiURL + item,
      JSON.stringify(data),
      this.httpOptions
    );
  }


}

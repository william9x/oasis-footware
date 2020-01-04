import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Products } from './products.module';

@Injectable({
  providedIn: "root"
})

export class ApiService {
  // API path
  apiURL: string = "https://oasis-footware.herokuapp.com/api/";

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json"
    })
  };

  constructor(private httpClient: HttpClient) {}

  // Get All Products
  public getAllProducts(): Observable<Products> {
    return this.httpClient.get<Products>(
      this.apiURL + "product",
      this.httpOptions
    );
  }

  // // Get Item By Id
  // public getItem(id): Observable<Category> {
  //   return this.httpClient.get<Category>(
  //     this.apiURL + "category/" + id,
  //     this.httpOptions
  //   );
  // }

  // // Delete Item By Id
  // public deleteItem(id: any): Observable<any> {
  //   return this.httpClient.delete(
  //     this.apiURL + "category" + `/${id}`,
  //     this.httpOptions
  //   );
  // }

  // // Update Item By Id
  // public updateItem(id: any, item): Observable<any> {
  //   return this.httpClient.put(
  //     this.apiURL + "category" + `/${id}`,
  //     JSON.stringify(item),
  //     this.httpOptions
  //   );
  // }
}

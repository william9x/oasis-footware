import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { Products } from "./products.module";

@Injectable({
  providedIn: "root"
})
export class CreateProductService {
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

  // Get Item By Id
  public getProductDetail(id): Observable<any> {
    return this.httpClient.get<any>(
      this.apiURL + "product/" + id,
      this.httpOptions
    );
  }

  // Create a new product
  public createProduct(item, data): Observable<Products> {
    return this.httpClient.post<Products>(
      this.apiURL + item,
      JSON.stringify(data),
      this.httpOptions
    );
  }

  // Update Product Detail By Id
  public updateProductDetail(id: any, item): Observable<any> {
    return this.httpClient.put(
      this.apiURL + "product" + `/${id}`,
      JSON.stringify(item),
      this.httpOptions
    );
  }

  // Delete Item By Id
  public deleteProduct(id: any): Observable<any> {
    return this.httpClient.delete(
      this.apiURL + "product" + `/${id}`,
      this.httpOptions
    );
  }
}

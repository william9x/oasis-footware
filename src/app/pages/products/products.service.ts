import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Products } from './products.module';

@Injectable({
  providedIn: 'root'
})
export class CreateProductService {
  // API path
  apiURL = 'https://oasis-footware.herokuapp.com/api/';

  // Imgur API path
  imgurApiUrl = 'https://api.imgur.com/3/image';


  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      Authorization: 'Client-ID e16d35e275b6885',
      'Content-Type': 'multipart/form-data'
    })
  };

  httpHeader = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

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

  // Create a new product
  public createProduct(item, data): Observable<Products> {
    return this.httpClient.post<Products>(
      this.apiURL + item,
      JSON.stringify(data),
      this.httpHeader
    );
  }

  // Upload image
  public uploadImage(fileToUpload: File): Observable<any> {
    // const formData: FormData = new FormData();
    // formData.append('file', fileToUpload);
    // console.log('formData' , formData);
    return this.httpClient.post(
      this.imgurApiUrl,
      // formData,
      fileToUpload,
      this.httpOptions,
    );
  }

  // Update Product Detail By Id
  public updateProductDetail(id: any, item): Observable<Products> {
    return this.httpClient.put<Products>(
      this.apiURL + 'product' + `/${id}`,
      JSON.stringify(item),
      this.httpHeader
    );
  }

  // Delete Item By Id
  public deleteProduct(id: any): Observable<Products> {
    return this.httpClient.delete<Products>(
      this.apiURL + 'product' + `/${id}`,
      this.httpHeader
    );
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Invoice } from './invoice.module';

@Injectable({
    providedIn: 'root'
})
export class InvoiceService {
    // API path
    apiURL = 'https://oasis-footware.herokuapp.com/api/';


    httpHeader = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    }

    constructor(private httpClient: HttpClient) { }

    // Get All Invoices
    public getAllInvoices(): Observable<Invoice> {
        return this.httpClient.get<Invoice>(
            this.apiURL + 'invoice',
            this.httpHeader
        );
    }

    // Get Invoice By Id
    public getInvoiceDetail(id): Observable<Invoice> {
        return this.httpClient.get<Invoice>(
            this.apiURL + 'invoice/' + id,
            this.httpHeader
        );
    }

    // Create a new Invoice
    public createInvoice(item, data): Observable<Invoice> {
        return this.httpClient.post<Invoice>(
            this.apiURL + item,
            JSON.stringify(data),
            this.httpHeader
        );
    }


    // Update Invoice Detail By Id
    public updateInvoiceDetail(id: any, item): Observable<Invoice> {
        return this.httpClient.put<Invoice>(
            this.apiURL + 'invoice' + `/${id}`,
            JSON.stringify(item),
            this.httpHeader
        );
    }

    // Delete Invoice By Id
    public deleteInvoice(id: any): Observable<Invoice> {
        return this.httpClient.delete<Invoice>(
            this.apiURL + 'invoice' + `/${id}`,
            this.httpHeader
        );
    }
}

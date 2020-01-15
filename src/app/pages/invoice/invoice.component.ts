import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InvoiceService } from './invoice.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
  invoices;

  constructor(
    private router: Router,
    private invoiceService: InvoiceService,
    private SpinnerService: NgxSpinnerService) {
    this.invoices = [];
  }

  ngOnInit() {
    this.getAllInvoices();
  }

  toCreatePage() {
    this.router.navigate(['/invoice/create']);
  }

  getAllInvoices() {
    this.invoiceService.getAllInvoices().subscribe(
      res => {
        console.log(res.data);
        this.invoices = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }

  statusFilter(status: number) {
    switch (status) {
      case 1: return 'In progress'; break;
      case 2: return 'Transporting'; break;
      case 3: return 'Completed'; break;
      case 4: return 'Canceled'; break;
      case 5: return 'Returning'; break;
      case 6: return 'Returned'; break;
    }
  }

  deleteInvoice(id: any) {
    this.SpinnerService.show();
    this.invoiceService.deleteInvoice(id).subscribe(
      res => {
        console.log(res);
        this.getAllInvoices();
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

}

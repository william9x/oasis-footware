import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';


import { InvoiceService } from '../invoice.service';
import { Invoice } from '../invoice.module';

@Component({
  selector: 'app-invoice-update',
  templateUrl: './invoice-update.component.html',
  styleUrls: ['./invoice-update.component.css']
})
export class InvoiceUpdateComponent implements OnInit {
  id: number;
  data: Invoice;

  constructor(
    private router: Router,
    private invoiceService: InvoiceService,
    private SpinnerService: NgxSpinnerService,
    public activatedRoute: ActivatedRoute
  ) {
    this.data = new Invoice();
  }

  ngOnInit() {
    this.SpinnerService.show();
    this.id = this.activatedRoute.snapshot.params.id;
    // get invoice details using id
    this.invoiceService.getInvoiceDetail(this.id).subscribe(response => {
      console.log(response);
      this.data = response.data;
      this.SpinnerService.hide();
    });
  }


  updateInvoice() {
    this.SpinnerService.show();
    // Update invoice by taking uid and updated data object
    this.invoiceService.updateInvoiceDetail(this.id, this.data).subscribe(
      res => {
        console.log(res);
        this.SpinnerService.hide();
        this.router.navigate(['invoice']);
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

}

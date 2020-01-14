import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Products } from '../../models/product.model';
import { CustomerInvoice } from '../../models/customer.model';
import { CreateInvoiceService } from '../../services/invoice.service';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit {
  checkoutProducts: Products[];
  totalPrice = 0;
  invoice: CustomerInvoice;
  constructor(private productService: ProductService, private invoiceService: CreateInvoiceService) {
    this.invoice = new CustomerInvoice();
  }

  ngOnInit() {
    this.getLocalProduct();
  }

  getLocalProduct() {
    const products = this.productService.getLocalCartProducts();
    this.checkoutProducts = products;
    products.forEach((product) => {
      this.totalPrice += product.unitPrice;
    });

    console.log('checkoutPr', this.checkoutProducts);
  }



  createInvoice() {

    this.invoice.totalValue = this.totalPrice;
    this.invoice.productValue = 0;
    this.invoice.discountValue = 0;
    this.invoice.totalValue = this.totalPrice;
    this.invoice.status = 1;

    this.checkoutProducts.forEach((product) => {
      this.invoice.productIds.push(product.productUID);
    });


    console.log(this.invoice);

    // this.SpinnerService.show();
    this.invoiceService.createInvoice('invoice', this.invoice).subscribe(
      res => {
        // this.SpinnerService.hide();
        console.log(res);
        //this.getLocalProduct();
        // this.router.navigate(['/category']);
      },
      error => {
        console.log(error);
        // this.SpinnerService.hide();
      }
    );

  }
}

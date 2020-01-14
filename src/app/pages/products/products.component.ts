import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateProductService } from './products.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products;

  constructor(
    private router: Router,
    private apiService: CreateProductService,
    private SpinnerService: NgxSpinnerService
  ) {
    this.products = [];
  }

  ngOnInit() {
    this.getAllProducts();
  }

  toCreatePage() {
    this.router.navigate(['/product/create']);
  }

  getAllProducts() {
    this.apiService.getAllProducts().subscribe(
      res => {
        console.log(res.data);
        this.products = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }

  deleteProduct(id: any) {
    this.SpinnerService.show();
    this.apiService.deleteProduct(id).subscribe(
      res => {
        console.log(res);
        this.getAllProducts();
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

  genderFilter(gender: number) {
    if (gender === 1) {
      return 'Male';
    } else {
      return 'Female';
    }
  }
}

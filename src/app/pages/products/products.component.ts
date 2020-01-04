import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products;

  constructor(private router: Router, private apiService: ApiService) {
    this.products = [];
  }

  ngOnInit() {
    this.getAllProducts();
  }

  toCreatePage() {
    this.router.navigate(["/product/create"]);
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
    )
  }


}

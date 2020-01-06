import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../category/category.service';
import { CreateProductService } from '../products.service';
import { Router } from '@angular/router';
import { Products } from '../products.module';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  categories;
  data: Products;

  constructor(
    private apiService: ApiService,
    private createProductSerivce: CreateProductService,
    public router: Router,
    public SpinnerService: NgxSpinnerService) {
      this.data = new Products();
     }

  ngOnInit() {
    this.getAllCategories();
  }

  getAllCategories() {
    this.apiService.getCategories().subscribe(
      res => {
        console.log(res.data);
        this.categories = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }

  createProduct() {
    this.SpinnerService.show();
    this.createProductSerivce.createProduct('product', this.data).subscribe(
      res => {
        console.log(res.data);
        this.getAllCategories();
        this.SpinnerService.hide();
        this.router.navigate(['/product']);
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }


}

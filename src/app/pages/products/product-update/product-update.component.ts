import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CreateProductService } from '../products.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Products } from '../products.module';
import { ApiService } from '../../category/category.service';


@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.css']
})
export class ProductUpdateComponent implements OnInit {
  id: number;
  data: Products;
  categories;

  constructor(
    private router: Router,
    private createProductService: CreateProductService,
    private apiService: ApiService,
    private SpinnerService: NgxSpinnerService,
    public activatedRoute: ActivatedRoute) {
      this.data = new Products();
     }

  ngOnInit() {
    this.SpinnerService.show();
    this.id = this.activatedRoute.snapshot.params.id;
        // get product details using id
    this.createProductService.getProductDetail(this.id).subscribe(response => {
          console.log(response);
          this.data = response.data;
          this.SpinnerService.hide();
        });

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

  updateProduct() {
    this.SpinnerService.show();
    // Update item by taking id and updated data object
    this.createProductService.updateProductDetail(this.id, this.data).subscribe(
      res => {
        console.log(res);
        this.SpinnerService.hide();
        this.router.navigate(['product']);
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";

import { CategoryService } from '../../services/category.service';
import { ProductService } from '../../services/product.service';
import { Store } from '@ngrx/store';
import { setProducts } from '../../reducers/products.reducer';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  categories;
  products;
  category;

  constructor(private categoryService: CategoryService,
    private productSerice: ProductService,
    public activatedRoute: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private store: Store<any>
  ) { }

  ngOnInit() {
    this.getAllCategories();
    this.getAllProducts();
  }

  toProductDetailPage(id) {
    this.router.navigate(['/product', id]);
  }

  addProductsToCart(listProducts) {
    console.log(listProducts);
    this.store.dispatch(setProducts({ listProducts }));
  }

  getAllCategories() {
    this.spinner.show();
    this.categoryService.getCategories().subscribe(
      res => {
        console.log(res.data);
        this.categories = res.data;
        this.spinner.hide();
      },
      error => {
        console.log(error);
        this.spinner.hide();
      }
    );
  }


  getAllProducts() {
    this.spinner.show();
    this.productSerice.getAllProducts().subscribe(
      res => {
        console.log(res.data);
        console.log('images', res.data[0].images);
        this.products = res.data;
        this.spinner.hide();
      },
      error => {
        console.log(error);
        this.spinner.hide();
      }
    );
  }

}

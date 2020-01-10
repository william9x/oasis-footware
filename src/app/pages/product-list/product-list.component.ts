import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  categories;
  products;
  category;

  constructor(private categoryService: CategoryService, private productSerice: ProductService) { }

  ngOnInit() {
    this.getAllCategories();
    this.getAllProducts();
  }

  getAllCategories() {
    this.categoryService.getCategories().subscribe(
      res => {
        console.log(res.data);
        this.categories = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }


  getAllProducts() {
    this.productSerice.getAllProducts().subscribe(
      res => {
        console.log(res.data);
        console.log('images', res.data[0].images)
        this.products = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }

}

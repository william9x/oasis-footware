import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../../../services/category.service';

@Component({
  selector: 'app-product-filter-by-category',
  templateUrl: './product-filter-by-category.component.html',
  styleUrls: ['./product-filter-by-category.component.css']
})
export class ProductFilterByCategoryComponent implements OnInit {
  id: number;
  categories;
  category;

  constructor(
    public activatedRoute: ActivatedRoute,
    public categoryService: CategoryService,
  ) {
  }

  ngOnInit() {

    this.getAllCategories();

    this.id = this.activatedRoute.snapshot.params.id;
    this.categoryService.getItem(this.id).subscribe(res => {
      console.log('productWithCategoryId', res.data.products);
    });
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

  getProductByCategoryId(id) {
    this.categoryService.getItem(id).subscribe(
      res => {
        this.category = res.data;
        console.log('data', res.data);
      },
      error => {
        console.log(error);
      }
    );
  }

}

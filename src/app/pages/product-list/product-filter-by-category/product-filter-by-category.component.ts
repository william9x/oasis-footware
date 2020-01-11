import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
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
    private router: Router
  ) {
  }

  ngOnInit() {
    // this.id = this.activatedRoute.snapshot.params.id;
    // this.categoryService.getItem(this.id).subscribe(res => {
    //   console.log('productWithCategoryId', this.id);
    // });

    this.getAllCategories();
    // this.getProductByCategoryId(this.id);
    // console.log('categoryId', this.id);

    this.toAnotherPagesFilter();

  }

  toAnotherPagesFilter() {

    // this.getAllCategories();
    // this.getProductByCategoryId(this.id);
    // this.router.navigate(['/product/filterByCategory', this.id]);
    console.log('categoryId', this.categories);
  }

  getAllCategories() {
    this.categoryService.getCategories().subscribe(
      res => {
        console.log('All categories', res.data);
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
        console.log('data', res.data);
        this.category = res.data.products;
      },
      error => {
        console.log(error);
      }
    );
  }

}

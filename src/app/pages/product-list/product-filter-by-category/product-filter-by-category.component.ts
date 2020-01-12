import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { CategoryService } from '../../../services/category.service';
import { NgxSpinnerService } from 'ngx-spinner';

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
    private router: Router,
    public SpinnerService: NgxSpinnerService
  ) {
  }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params.id;
    this.categoryService.getItem(this.id).subscribe(
      res => {
        console.log('productWithCategoryId', this.id);
        this.category = res.data.products;
      }, error => {
        console.log(error);
      });

    this.getAllCategories();
    console.log('categoryId', this.id);
  }

  toProductDetailPage(id) {
    this.router.navigate(['/product', id]);
  }

  toAnotherPagesFilter(id) {
    this.getProductByCategoryId(id);
    this.router.navigate(['/product/filterByCategory', id]);
    console.log('categoryId', this.categories);
  }

  getAllCategories() {
    this.SpinnerService.show();
    this.categoryService.getCategories().subscribe(
      res => {
        console.log('All categories', res.data);
        this.categories = res.data;
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

  getProductByCategoryId(id) {
    this.SpinnerService.show();
    this.categoryService.getItem(id).subscribe(
      res => {
        console.log('data', res.data);
        this.category = res.data.products;
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

}

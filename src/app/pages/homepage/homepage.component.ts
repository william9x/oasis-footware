import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { ProductService } from '../../services/product.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  categories;
  products;

  slideConfig = {
    "slidesToShow": 4, "slidesToScroll": 1,
  };

  constructor(
    private categoryService: CategoryService,
    private SpinnerService: NgxSpinnerService,
    private productSerice: ProductService) {
    this.categories = [];
  }

  ngOnInit() {
    this.getAllCategories();
    this.getAllProducts();
    this.SpinnerService.show();
    setTimeout(() => {
      this.SpinnerService.hide();
    }, 8000);
  }

  slickInit(e) {
    console.log('slick initialized');
  }

  breakpoint(e) {
    console.log('breakpoint');
  }

  afterChange(e) {
    console.log('afterChange');
  }

  beforeChange(e) {
    console.log('beforeChange');
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

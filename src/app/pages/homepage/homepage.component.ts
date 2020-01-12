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
    this.SpinnerService.show();
    this.getAllCategories();
    this.getAllProducts();
    this.SpinnerService.hide();
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
    this.SpinnerService.show();
    this.categoryService.getCategories().subscribe(
      res => {
        console.log(res.data);
        this.categories = res.data;
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

  getAllProducts() {
    this.SpinnerService.show();
    this.productSerice.getAllProducts().subscribe(
      res => {
        console.log(res.data);
        console.log('images', res.data[0].images);
        this.products = res.data;
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

}

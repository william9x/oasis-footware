import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../../services/category.service';
import { ProductService } from '../../services/product.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  categories;
  products;
  category;

  slideConfig = {
    slidesToShow: 4, slidesToScroll: 1,
  };

  constructor(
    private categoryService: CategoryService,
    private SpinnerService: NgxSpinnerService,
    private productSerice: ProductService,
    private router: Router) {
    this.categories = [];
  }

  ngOnInit() {
    this.getAllCategories();
    this.getAllProducts();
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

  toAnotherPagesFilter(id) {
    this.getProductByCategoryId(id);
    this.router.navigate(['/product/filter-by-category', id]);
    console.log('categoryId', this.categories);
  }

  toProductDetailPage(id) {
    this.getProductByCategoryId(id);
    this.router.navigate(['/product', id]);
  }


  addProductToCart(product) {
    console.log('click button', product);
    this.productSerice.addToCart(product);
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

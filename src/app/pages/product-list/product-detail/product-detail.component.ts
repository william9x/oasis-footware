import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../../services/product.service';
import { NgxSpinnerService } from 'ngx-spinner';


@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  id: number;
  products;
  product;

  slideConfig = {
    "slidesToShow": 4, "slidesToScroll": 1,
  };


  constructor(private productSerice: ProductService,
    public activatedRoute: ActivatedRoute,
    private SpinnerService: NgxSpinnerService) { }

  ngOnInit() {
    this.id = this.activatedRoute.snapshot.params.id;
    this.getAllProducts();
    this.getProductById(this.id);
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

  addProductToCart(product) {
    console.log('click button', product)
    this.productSerice.addToCart(product);
  }

  getAllProducts() {
    this.SpinnerService.show();
    this.productSerice.getAllProducts().subscribe(
      res => {
        console.log(res.data);
        this.products = res.data;
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }


  getProductById(id) {
    this.SpinnerService.show();
    this.productSerice.getProductDetail(id).subscribe(
      res => {
        console.log('data', res.data);
        this.product = res.data;
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }

}

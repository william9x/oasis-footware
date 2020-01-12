import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../../services/product.service';


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


  constructor(private productSerice: ProductService, public activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.id = this.activatedRoute.snapshot.params.id;
    this.productSerice.getProductDetail(this.id).subscribe(
      res => {
        console.log('productID', this.id);
        this.product = res.data;
        console.log('product', res.data.images);
      }, error => {
        console.log(error);
      });

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

  getAllProducts() {
    this.productSerice.getAllProducts().subscribe(
      res => {
        console.log(res.data);
        this.products = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }


  getProductById(id) {
    this.productSerice.getProductDetail(id).subscribe(
      res => {
        console.log('data', res.data);
        this.product = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }

}

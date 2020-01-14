import { Injectable, Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Products } from '../../models/product.model';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartProducts: Products[];
  totalValue = 0;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getCartProduct();
    this.calculateSum();
  }

  calculateSum() {
    this.totalValue = 0;

    for (const product of this.cartProducts) {
      this.totalValue += product.unitPrice;
    }

    console.log('cartProduct', this.cartProducts)
  }

  removeCartProduct(product) {
    this.productService.removeLocalCartProduct(product);
    console.log(product);
    // Recalling
    this.getCartProduct();
    this.calculateSum();
  }

  getCartProduct() {
    this.cartProducts = this.productService.getLocalCartProducts();
    console.log(this.cartProducts);
    this.calculateSum();
  }

}

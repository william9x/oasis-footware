import { Injectable } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  storeSubscription: Subscription;
  products;

  constructor(
    private store: Store<any>
  ) {
  }

  ngOnInit() {
    this.storeSubscription = this.store.select('data').subscribe(listProducts => {
      console.log('productState', listProducts);
      this.products = listProducts.listProducts;
      console.log('products', this.products);
    });
  }

}

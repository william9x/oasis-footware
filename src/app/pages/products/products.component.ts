import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

export class Product {
  id: number;
  name: string;
  description: string;
  image: string;
  price: number;
  quantity: number;
  category: string;
}


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products : Product[] = [
    {
      id: 1,
      name: "Product item number 1",
      description: "Description for product item number 1",
      image: "https://cdn.shopify.com/s/files/1/1241/4530/products/MateriaBootBlackPerfil_800x.jpg?v=1570440626",
      price: 5950000,
      quantity: 2,
      category: "sport"
    },
    {
      id: 2,
      name: "Product item number 2",
      description: "Description for product item number 2",
      image: "https://cdn.shopify.com/s/files/1/1241/4530/products/MateriaBootBlackPerfil_800x.jpg?v=1570440626",
      price: 9100000,
      quantity: 1,
      category: "sport"
    }
  ];

  constructor(private router: Router) { }

  ngOnInit() {
  }

  toCreatePage() {
    this.router.navigate(['/product/create']);
  }

}

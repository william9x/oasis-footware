import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

export class Category {
  id: number;
  name: string;
  productQuantity: number;
}


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories : Category[] = [
    {
      id: 1,
      name: "Product item number 1",
      productQuantity: 2,
    },
    {
      id: 2,
      name: "Product item number 2",
      productQuantity: 7,
    }
  ];

  constructor(private router: Router) { }

  ngOnInit() {
  }

  toCreatePage() {
    this.router.navigate(['/category/create']);
  }

}

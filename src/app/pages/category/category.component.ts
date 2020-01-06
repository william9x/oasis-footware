import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './category.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categories;

  constructor(
    private router: Router,
    private apiService: ApiService,
    private SpinnerService: NgxSpinnerService
  ) {
    this.categories = [];
  }

  ngOnInit() {
    this.getAllCategories();
  }

  getAllCategories() {
    this.apiService.getCategories().subscribe(
      res => {
        console.log(res.data);
        this.categories = res.data;
      },
      error => {
        console.log(error);
      }
    );
  }

  toCreatePage() {
    this.router.navigate(['/category/create']);
  }

  deleteCategory(id: any) {
    this.SpinnerService.show();
    this.apiService.deleteItem(id).subscribe(
      res => {
        console.log(res);
        this.getAllCategories();
        this.SpinnerService.hide();
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }
}

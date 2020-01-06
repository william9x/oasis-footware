import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../category.model';
import { ApiService } from '../category.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-category-update',
  templateUrl: './category-update.component.html',
  styleUrls: ['./category-update.component.css']
})
export class CategoryUpdateComponent implements OnInit {
  id: number;
  data: Category;

  constructor(
    public activatedRoute: ActivatedRoute,
    public router: Router,
    public apiService: ApiService,
    public SpinnerService: NgxSpinnerService
  ) {
    this.data = new Category();
  }

  ngOnInit() {
    this.SpinnerService.show();
    this.id = this.activatedRoute.snapshot.params.id;
    // get item details using id
    this.apiService.getItem(this.id).subscribe(response => {
      console.log(response);
      this.data = response.data;
      this.SpinnerService.hide();
    });
  }

  update() {
    this.SpinnerService.show();
    // Update item by taking id and updated data object
    this.apiService.updateItem(this.id, this.data).subscribe(
      res => {
        console.log(res);
        this.SpinnerService.hide();
        this.router.navigate(['category']);
      },
      error => {
        console.log(error);
        this.SpinnerService.hide();
      }
    );
  }
}

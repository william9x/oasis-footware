import { Component, OnInit } from "@angular/core";
import { Category } from "../category/category.model";
import { ApiService } from "./create-category.service";
import { Router } from "@angular/router";
import { NgxSpinnerService } from "ngx-spinner";

@Component({
  selector: "app-create-category",
  templateUrl: "./create-category.component.html",
  styleUrls: ["./create-category.component.css"]
})
export class CreateCategoryComponent implements OnInit {
  data: Category;

  constructor(
    public apiService: ApiService,
    public router: Router,
    public SpinnerService: NgxSpinnerService
  ) {
    this.data = new Category();
  }

  ngOnInit() {}

  submitForm() {
    this.SpinnerService.show();
    this.apiService.createItem("category", this.data).subscribe(
      res => {
        this.SpinnerService.hide();
        this.router.navigate(['/category']);
      },
      error => {
        this.SpinnerService.hide();
      }
    );
  }
}

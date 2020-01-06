import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../category/category.service';
import { CreateProductService } from '../products.service';
import { Router } from '@angular/router';
import { Products } from '../products.module';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  categories;
  data: Products;
  fileToUpload: File = null;

  constructor(
    private apiService: ApiService,
    private createProductSerivce: CreateProductService,
    public router: Router,
    public SpinnerService: NgxSpinnerService) {
      this.data = new Products();
  }

  ngOnInit() {
    this.getAllCategories();
  }

  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log('handle input', this.fileToUpload);
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

  // createProduct() {
  //   this.SpinnerService.show();
  //   this.createProductSerivce.createProduct('upload', this.data).subscribe(
  //     res => {
  //       console.log(res.data);
  //       this.getAllCategories();
  //       this.SpinnerService.hide();
  //       this.router.navigate(['/product']);
  //     },
  //     error => {
  //       console.log(error);
  //       this.SpinnerService.hide();
  //     }
  //   );
  // }

  createProduct() {
    this.createProductSerivce.uploadImage(this.fileToUpload).subscribe(res => {
     console.log(res.data.link);
     this.SpinnerService.show();
     this.createProductSerivce.createProduct('product', this.data).subscribe(
          res => {
            console.log(res.data);
            this.getAllCategories();
            this.SpinnerService.hide();
            this.router.navigate(['/product']);
          },
          error => {
            console.log(error);
            this.SpinnerService.hide();
          }
        );
      }, error => {
        console.log(error);
        this.SpinnerService.hide();
      });
  }


}

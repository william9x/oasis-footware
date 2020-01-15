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
  previewUrl: any = null;
  uploadedFilePath: string = null;
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
    this.preview();
  }

  preview() {
    // Show preview 
    const mimeType = this.fileToUpload.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(this.fileToUpload);
    reader.onload = (event) => {
      this.previewUrl = reader.result;
    };
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

  createProduct() {
    this.createProductSerivce.uploadImage(this.fileToUpload).subscribe(res => {
      this.data.images[0].imageUrl = res.data.link;
      this.data.images[0].imageUID = res.data.id;

      console.log(this.data);

      this.SpinnerService.show();
      this.createProductSerivce.createProduct('product', this.data).subscribe(
        res => {
          console.log(res);
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

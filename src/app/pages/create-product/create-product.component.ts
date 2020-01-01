import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-create-product",
  templateUrl: "./create-product.component.html",
  styleUrls: ["./create-product.component.css"]
})
export class CreateProductComponent implements OnInit {
  fileData: File = null;
  previewUrl: any = null;
  uploadedFilePath: string = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {}

  preview() {
    // Show preview
    let mimeType = this.fileData.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    let reader = new FileReader();
    reader.readAsDataURL(this.fileData);
    reader.onload = _event => {
      this.previewUrl = reader.result;
    };
  }

  onSubmit() {
    const formData: any = new FormData();
    formData.append("file", this.fileData);
    this.http.post("https://api.imgur.com/3/upload", formData).subscribe(res => {
      console.log(res);
      this.uploadedFilePath = res.data.filePath;
      alert("SUCCESS !!");
    });
  }
}

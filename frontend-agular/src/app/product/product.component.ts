import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent {

  ProductArray: any[] = []

  name: string = "";
  description: string = "";
  price: Number = 0;
  productID = "";

  constructor(private http: HttpClient) {
    this.getAllProducts();
  }

  register() {
    let bodyData = {
      "name": this.name,
      "description": this.description,
      "price": this.price
    }

    this.http.post("http://localhost:8080/api/products/save", bodyData, { responseType: 'text' })
      .subscribe((resultData: any) => {
        console.log(resultData);
        alert("Product Created");
        this.getAllProducts();

        this.name = "";
        this.description = "";
        this.price = 0;
      })
  }

  getAllProducts() {
    this.http.get("http://localhost:8080/api/products")
      .subscribe((resultData: any) => {
        console.log(resultData);
        this.ProductArray = resultData;
      })
  }

  setUpdate(data: any) {
    this.name = data.name,
    this.description = data.description,
    this.price = data.price,
    this.productID = data._id
  }

  updateRecords() {
    let bodyData = {
      "name": this.name,
      "description": this.description,
      "price": this.price
    }

    this.http.put("http://localhost:8080/api/products/update" + "/" + this.productID, bodyData, {responseType: 'text'})
      .subscribe((resultData: any) => {
        console.log(resultData);
        alert("Product Updated")
        this.getAllProducts()
        this.name = ""
        this.description = ""
        this.price = 0
      })
  }

  save() {
    if (this.productID == '') {
      this.register()
    } else {
      this.updateRecords()
    }
  }

  setDelete(data: any) {
    this.http.delete("http://localhost:8080/api/products/delete" + "/" + data._id,{responseType: 'text'})
      .subscribe((resultData: any) => {
        console.log(resultData);
        alert("Product Deleted")
        this.getAllProducts()

        this.name = ""
        this.description = ""
        this.price = 0.0
      })
  }

}

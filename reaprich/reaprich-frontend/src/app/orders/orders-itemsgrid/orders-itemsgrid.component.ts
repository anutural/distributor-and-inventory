import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { InventoryService } from 'src/app/services/inventory.service';
import { IAllItemsListServerResponse, IItem } from 'src/app/data-type';

@Component({
  selector: 'app-orders-itemsgrid',
  standalone: true,
  imports: [CommonModule, MatGridListModule, MatCardModule,MatIconModule,MatButtonModule],
  templateUrl: './orders-itemsgrid.component.html',
  styleUrls: ['./orders-itemsgrid.component.css']
})
export class OrdersItemsgridComponent {

  public productList: IItem[];

  // productArray=[
  //   {
  //     id: 1,
  //     img: "../../../assets/images/sample1.png",
  //     name: "Sample Product 1",
  //     price: "13900",
  //     description: "This is some good product 1",
  //     quantity: 1
  //   },
  //   {
  //     id: 2,
  //     img: "../../../assets/images/sample2.png",
  //     name: "Sample Product 2",
  //     price: "15000",
  //     description: "This is some good product 2",
  //     quantity: 1
  //   },
  //   {
  //     id: 3,
  //     img: "../../../assets/images/sample1.png",
  //     name: "Sample Product 3",
  //     price: "13900",
  //     description: "This is some good product 3",
  //     quantity: 1
  //   },
  //   {
  //     id: 4,
  //     img: "../../../assets/images/sample2.png",
  //     name: "Sample Product 4",
  //     price: "15000",
  //     description: "This is some good product 4",
  //     quantity: 1
  //   },
  //   {
  //     id: 5,
  //     img: "../../../assets/images/sample1.png",
  //     name: "Sample Product 5",
  //     price: "13900",
  //     description: "This is some good product 5",
  //     quantity: 1
  //   },
  //   {
  //     id: 6,
  //     img: "../../../assets/images/sample2.png",
  //     name: "Sample Product 6",
  //     price: "15000",
  //     description: "This is some good product 6",
  //     quantity: 1
  //   },
  // ]

  /**
   *
   */
  constructor(private route: Router, private inventoryService : InventoryService) {
    
    
  }

  ngOnInit(){

    this.inventoryService.allItemsList().subscribe((result : any) => {
      if (result) {
        
        let servResp = result as IAllItemsListServerResponse;  
        
        console.warn("all item list: ", servResp.items);
        this.productList = servResp.items;
      }
    });
       
  }

  decrement(){

  }

  increment(){

  }

  addToCart(){
    alert("item added to the cart");
  }


  onCheckoutClick(){
    console.warn('clicked on checkout');
    this.route.navigate([`\orders-checkout`]);
  }
}

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatGridListModule} from '@angular/material/grid-list';
import { Router } from '@angular/router';


@Component({
  selector: 'app-inventory-home',
  standalone: true,
  imports: [MatGridListModule, CommonModule],
  templateUrl: './inventory-home.component.html',
  styleUrls: ['./inventory-home.component.css']
})
export class InventoryHomeComponent {

  constructor(private route : Router){

  }

  clickAddItemDetails(ev){
    this.route.navigate([`inventory-add-item-details`]);
  }

  clickAddItem(ev){
    this.route.navigate([`inventory-add-item`]);
  }

  clickWarehouse(ev){
    this.route.navigate([`warehouse-home`]);
  }

  clickOrders(ev){
    this.route.navigate([`orders-itemsgrid`]);
  }

}

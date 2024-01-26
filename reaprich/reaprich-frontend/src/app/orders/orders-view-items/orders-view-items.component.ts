import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { OrdersItemsgridComponent } from '../orders-itemsgrid/orders-itemsgrid.component';
import { OrdersItemstableComponent } from '../orders-itemstable/orders-itemstable.component';
import { MatButtonModule } from '@angular/material/button';
@Component({
  selector: 'app-orders-view-items',
  standalone: true,
  imports: [CommonModule, 
    MatIconModule, 
    MatCardModule, 
    MatButtonToggleModule, 
    OrdersItemsgridComponent, 
    OrdersItemstableComponent,
    MatButtonModule],
  templateUrl: './orders-view-items.component.html',
  styleUrls: ['./orders-view-items.component.css']
})
export class OrdersViewItemsComponent {


}

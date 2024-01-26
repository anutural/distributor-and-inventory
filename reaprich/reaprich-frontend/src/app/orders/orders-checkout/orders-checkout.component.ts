import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders-checkout',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders-checkout.component.html',
  styleUrls: ['./orders-checkout.component.css']
})
export class OrdersCheckoutComponent {

  constructor(private route : Router) {
    
  }


  backToShopClick(){    
    this.route.navigate([`\orders-itemsgrid`]);
  }

  placeOrder(){

  }

  onClickRemove(){
    alert("Yet to implement");
  }
}

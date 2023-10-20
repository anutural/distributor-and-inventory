import { Component } from '@angular/core';
import { Customer } from '../customer.model';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent {
  toAddCustomer : boolean = false;

  customers : Customer[] = [
    new Customer("Customer", "regular1","arpit1", "gupta1","gujarat", "no team developer"),
    new Customer("Customer", "regular2","arpit2", "gupta2","gujarat", "no team developer")
  ]; 

  onAddCustomer() {
    this.toAddCustomer = !this.toAddCustomer;
  }
}

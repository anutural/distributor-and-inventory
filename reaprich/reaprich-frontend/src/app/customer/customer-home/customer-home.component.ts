import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { ICustomer } from 'src/app/data-type';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css']
})
export class CustomerHomeComponent {

  customerList: undefined | ICustomer[];
  customerMessage: undefined | string;
  icon= faTrash;
  iconEdit = faEdit;

  constructor(private customerService: CustomerService, private route: Router){

  }

  ngOnInit(){
    this.list();
  }



  deleteCustomer(custId: number){
    
    this.customerService.deleteCustomer(custId).subscribe((result) => {
      if (result) {
        this.customerMessage = "Customer is deleted";
        this.list();
      }
      setTimeout(() => {
        this.customerMessage = undefined
      }, 3000);
    });
  }

  
  list() {
    this.customerService.customerList().subscribe((result) => {
      if (result) {
        console.warn(result);
        this.customerList = result;
      }
    })
  }

  addCustomer(){
    this.route.navigate([`\customer-add`]);
  }

}

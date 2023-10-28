import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ICustomer } from 'src/app/data-type';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent {
  addCustomerMessage : undefined | string;

  constructor(private customerService : CustomerService, private route: Router){
  }


  async addCustomerSubmit(data: ICustomer){
    this.customerService.addCustomer(data).subscribe((result)=>{
      console.warn(data);
      if (result)
      {
        this.addCustomerMessage = "Customer is successfully Added.!!"
      }
      setTimeout(() => {
        this.addCustomerMessage = undefined
      }, 3000);
    });
    
    await new Promise(f => setTimeout(f, 1000));

    this.route.navigate([`\customer-home`]);
  }
}

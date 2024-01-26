import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICustomer } from 'src/app/data-type';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent {
  customerData: ICustomer | undefined;
  customerMessage: string | undefined;

  constructor(private route: ActivatedRoute, private customerService: CustomerService){    
  }

  ngOnInit(): void {
    let customerId = this.route.snapshot.paramMap.get('id');
    console.warn(customerId);
    // customerId && this.customerService.getCustomer(customerId).subscribe((data) => {      
    //   //this.customerData = data;
    // })
  }

  submit(customer:ICustomer){    
    if(this.customerData)
    {
      customer.id = this.customerData.id
    }
    this.customerService.updateCustomer(customer).subscribe((result) => {
      if(result){                      
        this.customerData = result;
        this.customerMessage = "Customer has been updated!";
        
      }      
    });
    setTimeout(() => {
      this.customerMessage = undefined;
    }, 3000);
  }

}

import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ICustomer, IProviderInfo } from 'src/app/data-type';
import { CustomerService } from 'src/app/services/customer.service';
import { faCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import { MatDialog } from '@angular/material/dialog';
import { PopupAddAddressComponent } from 'src/app/popups/popup-add-address/popup-add-address.component';

@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent {
  addCustomerMessage : undefined | string;
  addCustomerForm!: FormGroup;
  providerInfo: IProviderInfo | undefined;
  customerAddressUUID : string | undefined;
  rightIconForModal= faCheck;
  wrongIconForModal= faXmark;  

  constructor(private customerService: CustomerService, private route: Router, private dialogRef: MatDialog) {

    this.addCustomerForm = new FormGroup({
      Actor_Type: new FormGroup({
        id: new FormControl({ value: 'Customer', disabled: true })
      }),
      Customer_Type: new FormGroup({
        id: new FormControl()
      }),      
      Customer_F_Name: new FormControl(),
      Customer_L_Name: new FormControl(),
      Customer_Contact_Number: new FormControl(),
      Customer_Add: new FormGroup({
        id: new FormControl()
      })
    });

  }

  ngOnInit() {
    let providerInfo = this.getFromLocalStorage('providerInfo');
    if (providerInfo) {
      this.providerInfo = JSON.parse(providerInfo);
    }
    this.customerAddressUUID = this.getFromLocalStorage('customerAddressUUID') as string;
  }


  async addCustomerSubmit(data: ICustomer){

  }



  async onAddCustomer(customerObject : ICustomer){
    if (localStorage.getItem("customerAddressUUID") === null) {
      alert('Please add Customer Address');
      return;
    }

    let customer = {
      id: "Customer",
    };

    customerObject.Actor_Type = customer;

    customerObject.Customer_Add = { id: this.customerAddressUUID };    

    console.warn('prepare object - ' , customerObject);
    (await this.customerService.addCustomer(customerObject)).subscribe((result: any) => {
      if (result) {
        alert("Customer Added Successfully!");
        this.removeLocalStorage();
        this.route.navigate([`\customer-home`]);
      }
    });
            
  }


  openPopUpForCustomerAddress(){
    var popupResponse = this.dialogRef.open(PopupAddAddressComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500,
      data: {
        district: this.providerInfo.addDistList,
        zone: this.providerInfo.addZoneList,
        state: this.providerInfo.addStateList,
        country: this.providerInfo.addCountryList,
        addUuidVal: 'customerAddressUUID'
      }
    });

    popupResponse.afterClosed().subscribe(item => {
      if (item) {
        if (item) {
          this.saveToLocalStorage('customerAddressUUID', item);
          this.customerAddressUUID = item;
        }
      }
    })
  }


  getFromLocalStorage(key: string) {
    return localStorage.getItem(key);
  }


  removeLocalStorage(){
    localStorage.removeItem('customerAddressUUID');
  }

  
  saveToLocalStorage(key: string, value: string) {
    localStorage.setItem(key, value);
  }



}

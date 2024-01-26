import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { IAddress, ICustomer, ICustomerResponse, ICustomerServerResponse, IProviderInfo } from 'src/app/data-type';
import { CustomerService } from 'src/app/services/customer.service';
import { faCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import { MatDialog } from '@angular/material/dialog';
import { PopupAddAddressComponent } from 'src/app/popups/popup-add-address/popup-add-address.component';
import { filter, first } from 'rxjs/operators';

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
  customerID : string | undefined;
  isUpdateCustomer : boolean = false;

  constructor(private customerService: CustomerService, private route: Router, private dialogRef: MatDialog, 
    private activatedRoute : ActivatedRoute,
    private formBuilder : FormBuilder) {

    this.addCustomerForm = new FormGroup({      
      actorType: new FormGroup({
        id: new FormControl({ value: 'Customer', disabled: true })
      }),
      customerType: new FormGroup({
        id: new FormControl()
      }),      
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      contactNumber: new FormControl('', Validators.required),
      address: new FormGroup({
        id: new FormControl()        
      })
    });

  }

  async ngOnInit() {
    let providerInfo = this.getFromLocalStorage('providerInfo');
    if (providerInfo) {
      this.providerInfo = JSON.parse(providerInfo);
    }
    this.customerAddressUUID = this.getFromLocalStorage('customerAddressUUID') as string;

    let customerId = this.activatedRoute.snapshot.paramMap.get('id');
    
    this.customerService.getCustomer(customerId).subscribe((result => {
      if (result) {
        let servResp = result as ICustomerServerResponse;
        let addResp = servResp.customer.address as IAddress;
        let custTypeResp = servResp.customer.customerType as IAddress;
        if (addResp){
          this.customerAddressUUID = addResp.id;
          this.saveToLocalStorage("customerAddressUUID", addResp.id);
          this.isUpdateCustomer = true;
        }
        if(addResp.id)
        {
          this.customerID = addResp.id;
        }
        console.warn("addresp:", addResp);
        this.addCustomerForm = this.formBuilder.group({
          firstName: servResp.customer.firstName,
          lastName: servResp.customer.lastName,
          contactNumber: servResp.customer.contactNumber,
          // address: this.formBuilder.group({
          //   id: addResp.id            
          // }),
          customerType: this.formBuilder.group({
            id: custTypeResp.id            
          })
        })
      }
    })
    );


    

    this.route.events
    .pipe(
      filter((e) => e instanceof NavigationEnd),
      first()
    )
    .subscribe(() => {
      this.removeLocalStorage();
    });
  }


  async addCustomerSubmit(data: ICustomer){

  }



  async onAddCustomer(customerObject : ICustomer){
    if (localStorage.getItem("customerAddressUUID") === null) {
      alert('Please add Customer Address');
      return;
    }

    if(this.customerID){
      customerObject.id = this.customerID;
      console.warn('customer id set for -', this.customerID)
    }

    let customer = {
      id: "Customer",
    };

    customerObject.actorType = customer;

    customerObject.address = { id: this.customerAddressUUID };    

    console.warn('customer object -', customerObject);
    
    if (!this.isUpdateCustomer) {
      (await this.customerService.addCustomer(customerObject)).subscribe((result: any) => {
        if (result) {
          alert("Customer Added Successfully!");
          this.removeLocalStorage();
          this.route.navigate([`\customer-home`]);
        }
      }, err => {
        alert("Unable to add customer at this time. Please connect to support.");   
        this.removeLocalStorage();     
        this.route.navigate([`\customer-home`]);
      });
    }
    else {
      (await this.customerService.editCustomer(customerObject)).subscribe((result: any) => {
        if (result) {
          alert("Customer Added Successfully!");
          this.removeLocalStorage();
          this.route.navigate([`\customer-home`]);
        }
      },err => {
        alert("Unable to update customer at this time. Please connect to support.");
        this.removeLocalStorage();
        this.route.navigate([`\customer-home`]);
      });
    }
            
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

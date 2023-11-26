import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { IAddress, IAddressServerResponse, ICountry, ICustomer, IDistrict, IServerResponsePut, IState, IZone } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';
import { CustomerService } from 'src/app/services/customer.service';


@Component({
  selector: 'app-popup-add-address',
  templateUrl: './popup-add-address.component.html',
  styleUrls: ['./popup-add-address.component.css']
})
export class PopupAddAddressComponent {
  addressForm!: FormGroup;
  
  constructor(public dialogRef: MatDialogRef<PopupAddAddressComponent>, 
    @Inject(MAT_DIALOG_DATA) public dataPassed, 
    private commonService: CommonService,
    private customerService: CustomerService,
    private readonly formBuilder: FormBuilder) {

    this.addressForm = new FormGroup({
      actorType: new FormGroup({
        id: new FormControl()
      }),
      name: new FormControl(),
      addressLine1: new FormControl(),
      addressLine2: new FormControl(),
      city: new FormControl(),
      taluka: new FormControl(),
      pinCode: new FormControl(),
      dist: new FormGroup({
        id: new FormControl()
      }),
      zone: new FormGroup({
        id: new FormControl()
      }),
      state: new FormGroup({
        id: new FormControl()
      }),
      country: new FormGroup({
        id: new FormControl()
      })
    });
  }

  ngOnInit() {
    
    let addUUID = this.getFromLocalStorage(this.dataPassed.addUuidVal)
    if(addUUID){
      this.customerService.getCustomerByUUID(addUUID).subscribe((result => {
        if(result) {
          let servResp = result as IAddressServerResponse;  
          console.warn('servResp.address.dist', servResp.address.dist)
          //this.addressForm = servResp.address
          console.warn('myres', servResp.address)

          let destrictResp = result.address.dist as IDistrict;
          let zoneResp = result.address.zone as IZone;
          let stateResp = result.address.state as IState;
          let countryResp = result.address.country as ICountry;
          

          this.addressForm = this.formBuilder.group({
            name: servResp.address.name,
            addressLine1: servResp.address.addressLine2,
            addressLine2: servResp.address.addressLine1,
            city: servResp.address.city,
            taluka: servResp.address.taluka,
            pinCode: servResp.address.pinCode,
                  
            dist : this.formBuilder.group({
                id: destrictResp.id
            }), 
            zone : this.formBuilder.group({
              id: zoneResp.id
            }),                        
            state : this.formBuilder.group({
              id: stateResp.id
            }), 
            country : this.formBuilder.group({
              id: countryResp.id
            }),                        


          });
        }
      }))
    }   
  }


  afterClosed() {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  closePopUp() {
    this.dialogRef.close();
  }


  async saveAddress(firmAddress: IAddress) {
    let outlet = {
      id: "Outlet",
    };

    firmAddress.actorType = outlet;
    
    (await this.commonService.postAddress(firmAddress)).subscribe((result: any) => {
      if (result) {

        let servResp = result as IServerResponsePut;
        this.dialogRef.close(servResp.id);
      }
      setTimeout(() => {
      }, 3000);
    })

  }

  
  getFromLocalStorage(key: string) {
    return localStorage.getItem(key);
  }


  removeLocalStorage(){
    // localStorage.removeItem('customerAddressUUID');
  }

  
  saveToLocalStorage(key: string, value: string) {
    localStorage.setItem(key, value);
  }


}


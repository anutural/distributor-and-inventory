import { Component, ElementRef, ViewChild } from '@angular/core';
import { IOutletAddress, IOutlet, IOutletType, IProviderInfo, IAddress, IServerResponsePut, IBankDetails, IKYCDetails, IActorType, IIDCommonKeyT } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';
import { OutletService } from 'src/app/services/outlet.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Router } from '@angular/router';
import { faTrash, faCheck, faSquareCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import { FormControl, FormGroup, FormsModule, NgForm } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupAddAddressComponent } from 'src/app/popups/popup-add-address/popup-add-address.component';
import { PopupbankdetailsComponent } from 'src/app/popups/popupbankdetails/popupbankdetails.component';
import { PopupOwnerKycComponent } from 'src/app/popups/popup-owner-kyc/popup-owner-kyc.component';



@Component({
  selector: 'app-outlet-add',
  templateUrl: './outlet-add.component.html',
  styleUrls: ['./outlet-add.component.css'],
})
export class OutletAddComponent {
  colorControl = new FormControl('primary' as ThemePalette);
  outletForm!: FormGroup;
  providerInfo: IProviderInfo | undefined;
  firmAddressUUID: string | undefined;
  bankDetailsUUID : string | undefined;
  ownerAddressUUID : string | undefined;
  kycDetailsUUID : string | undefined;

    //Icon for address added or not near button
  rightIconForModal= faCheck;
  wrongIconForModal= faXmark;

  constructor(private outletService: OutletService,
    private commonService: CommonService,
    private modalService: NgbModal,
    private route: Router,
    private dialogRef: MatDialog) {


    this.outletForm = new FormGroup({
      actorType: new FormGroup({
        id: new FormControl({ value: 'Outlet', disabled: true })
      }),
      outletType: new FormGroup({
        id: new FormControl()
      }),
      firmName: new FormControl(),
      firmContactNumber: new FormControl(),
      firmGSTNumber: new FormControl(),
      firmPAN: new FormControl(),
      firmAddress: new FormGroup({
        id: new FormControl()
      }),
      firmBankDetails: new FormGroup({
        id: new FormControl()
      }),
      ownerFirstName: new FormControl(),
      ownerLastName: new FormControl(),
      ownerContactNumber: new FormControl(),
      ownerPAN: new FormControl(),
      ownerKYC: new FormGroup({
        id: new FormControl()
      }),
      ownerAddress: new FormGroup({
        id: new FormControl()
      }),
      tdEmail: new FormControl(),
      email: new FormControl(),
      password: new FormControl(),
    });
  }

  ngOnInit() {
    let providerInfo = this.getFromLocalStorage('providerInfo');
    if (providerInfo) {
      this.providerInfo = JSON.parse(providerInfo);
    }

    //initialize the existing values of uuids
    this.firmAddressUUID = this.getFromLocalStorage('firmAddressUUID') as string;
    this.bankDetailsUUID = this.getFromLocalStorage('bankDetailsUUID') as string;
    this.kycDetailsUUID = this.getFromLocalStorage('kycDetailsUUID') as string;
    this.ownerAddressUUID = this.getFromLocalStorage('ownerAddressUUID') as string;
  }

  async onAddOutlet(outletFormObject: IOutlet) {

    if (localStorage.getItem("firmAddressUUID") === null) {
      alert('Please add firm address');
      return;
    }

    if (localStorage.getItem("bankDetailsUUID") === null) {
      alert('Please add bank details.');
      return;
    }
    if (localStorage.getItem("kycDetailsUUID") === null) {
      alert('Please add kyc details.');
      return;
    }
    if (localStorage.getItem("ownerAddressUUID") === null) {
      alert('Please add owner address.');
      return;
    }


    let outlet = {
      id: "Outlet",
    };

    outletFormObject.actorType = outlet;

    outletFormObject.firmAddress = { id: this.ownerAddressUUID };


    outletFormObject.firmBankDetails = { id: this.bankDetailsUUID };


    outletFormObject.ownerKYC = { id: this.kycDetailsUUID };


    outletFormObject.ownerAddress = { id: this.ownerAddressUUID };
    

    (await this.outletService.addOutlet(outletFormObject)).subscribe((result: any) => {
      if (result) {
        alert("Outlet Added Successfully!");
        this.removeLocalStorage();
        this.route.navigate([`\outlet-home`]);
      }
    });

  }

  openPopUpForFirmAddress() {
    var popupResponse = this.dialogRef.open(PopupAddAddressComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500,
      data: {
        district: this.providerInfo.addDistList,
        zone: this.providerInfo.addZoneList,
        state: this.providerInfo.addStateList,
        country: this.providerInfo.addCountryList,
        addUuidVal: 'firmAddressUUID'
      }
    });

    popupResponse.afterClosed().subscribe(item => {
      if (item){
      alert('Firm Address saved successfully');
      this.saveToLocalStorage('firmAddressUUID', item);
      this.firmAddressUUID = item;
      }
    })
  }

  saveToLocalStorage(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  getFromLocalStorage(key: string) {
    return localStorage.getItem(key);
  }

  openPopUpForBankDetails(){
    var popupResponse = this.dialogRef.open(PopupbankdetailsComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500
    });
    popupResponse.afterClosed().subscribe(item => {
      if (item){
      alert('Bank details saved successfully');
      this.saveToLocalStorage('bankDetailsUUID', item);
      this.bankDetailsUUID = item;
      }
    })
  }

  openPopUpForOwnerAddress(){
    var popupResponse = this.dialogRef.open(PopupAddAddressComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500,
      data: {
        district: this.providerInfo.addDistList,
        zone: this.providerInfo.addZoneList,
        state: this.providerInfo.addStateList,
        country: this.providerInfo.addCountryList,
        addUuidVal: 'ownerAddressUUID'
      }
    });

    popupResponse.afterClosed().subscribe(item => {
      if (item){
      alert('Owner Address saved successfully');
      this.saveToLocalStorage('ownerAddressUUID', item);
      this.ownerAddressUUID = item;
      }
    })
  }

  openPopUpForKycDetails(){
    var popupResponse = this.dialogRef.open(PopupOwnerKycComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500,
      data: {
        idType: this.providerInfo.kycIdTypes,
        addProofType: this.providerInfo.kycAddProofType
      }
    });

    popupResponse.afterClosed().subscribe(item => {
      if (item){
      alert('Owner KYC Details saved successfully');
      this.saveToLocalStorage('kycDetailsUUID', item);
      this.kycDetailsUUID = item;
      }
    })    
  }

  removeLocalStorage(){
    localStorage.removeItem('firmAddressUUID');
    localStorage.removeItem('bankDetailsUUID');
    localStorage.removeItem('kycDetailsUUID');
    localStorage.removeItem('ownerAddressUUID');
  }

}





// export class OutletAddComponent {    
//   closeResult : string | undefined;
//   actorType = "Outlet";
//   addOutletMessage: string | undefined;  
//   providerInfo : IProviderInfo | undefined;
//   // outlet firm address
  
//   firmAddressModuleError : string | undefined;

//   //Icon for address added or not near button
//   rightIconForModal= faCheck;
//   wrongIconForModal= faXmark;
  

//   firmAddressUUID : string | undefined;
//   bankDetailsUUID : string | undefined;
//   kycDetailsUUID : string | undefined;
//   ownerAddressUUID : string | undefined;



//   constructor(private outletService : OutletService, 
//     private commonService : CommonService,    
//     private modalService : NgbModal,
//     private route: Router){

//   }

//   ngOnInit(){

//     let cartData = this.getFromLocalStorage('providerInfo');
//     if (cartData) {
//       this.providerInfo = JSON.parse(cartData);
//     }
    
//     //I need to set the right symbol if the modal uuid is available.
//     this.firmAddressUUID = this.getFromLocalStorage('firmAddressUUID') as string;
//     this.bankDetailsUUID = this.getFromLocalStorage('bankDetailsUUID') as string;
//     this.kycDetailsUUID = this.getFromLocalStorage('kycDetailsUUID') as string;
//     this.ownerAddressUUID = this.getFromLocalStorage('ownerAddressUUID') as string;
    
//     var temp = 1;
//   }

//   async addOutletSubmit(outletData: IOutlet){

//     if (localStorage.getItem("firmAddressUUID") === null) {
//       alert('Please add firm address');
//       return;
//     }

//     if (localStorage.getItem("bankDetailsUUID") === null) {
//       alert('Please add bank details.');
//       return;
//     }
//     if (localStorage.getItem("kycDetailsUUID") === null) {
//       alert('Please add kyc details.');
//       return;
//     }
//     if (localStorage.getItem("ownerAddressUUID") === null) {
//       alert('Please add owner address.');
//       return;
//     }

      
//     outletData.firmAddress = { id: this.ownerAddressUUID };

    
//     outletData.firmBankDetails = { id: this.bankDetailsUUID };

    
//     outletData.ownerKYC = { id: this.kycDetailsUUID };

    
//     outletData.ownerAddress = { id: this.ownerAddressUUID };

//     console.warn("outlet data to send" + outletData);

//     (await this.outletService.addOutlet(outletData)).subscribe((result : any) => {
//       if (result){              
//         alert("Outlet Added Successfully!");
//         this.removeLocalStorage();
//         this.route.navigate([`\outlet-home`]);
//       }
//     });

//     //set the values which were fetched by button click - like address, kyc etc

    
//   }

//   removeLocalStorage(){
//     localStorage.removeItem('firmAddressUUID');
//     localStorage.removeItem('bankDetailsUUID');
//     localStorage.removeItem('kycDetailsUUID');
//     localStorage.removeItem('ownerAddressUUID');
//   }


//   open(content: any) {
//     this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
//       this.closeResult = `Closed with: ${result}`;
//     }, (reason) => {
//       this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
//     });
//   }
  
//   private getDismissReason(reason: any): string {
//     if (reason === ModalDismissReasons.ESC) {
//       return 'by pressing ESC';
//     } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
//       return 'by clicking on a backdrop';
//     } else {
//       return `with: ${reason}`;
//     }
//   }

//   async submitFirmAddress(firmAddress: IAddress){
//     //fix the actor type as Outlet here
//     firmAddress.actorType.id = "Outlet";
//     //save the address in address api and close the modal
//    // get the address uuid and save it in local variable.

//    (await this.commonService.postAddress(firmAddress)).subscribe((result : any) => {
//     if (result){      
      
//       let servResp = result as IServerResponsePut;      
//       this.saveToLocalStorage('firmAddressUUID',servResp.id);
//       this.firmAddressUUID = servResp.id;
//       alert("Firm Adress Saved Successfully!");
      
//     }
//     setTimeout(() => {
      
//     }, 3000);
//   })   
  
//   }


// //Bank Details API
//   async submitBankDetails(bankDetails: IBankDetails){
//     //fix the actor type as Outlet here
//     //save the address in address api and close the modal
//    // get the address uuid and save it in local variable.

//    bankDetails.actorType.id = "Outlet";
//    console.warn('bank details: ', bankDetails);

   

//     (await this.commonService.postBankDetails(bankDetails)).subscribe((result : any) => {
//       if (result){
   
//         let servResp = result as IServerResponsePut;      
//         this.saveToLocalStorage('bankDetailsUUID',servResp.id);
//         this.bankDetailsUUID = servResp.id;        
//         alert("Bank Details Saved Successfully!");
//       }
//       setTimeout(() => {
        
//       }, 3000);
//     })
//   }

//   async submitKYCDetails(kycDetails: IKYCDetails){
//     //fix the actor type as Outlet here
//     console.warn('kyc details called :', kycDetails);
  

//     //save the address in address api and close the modal
//    // get the address uuid and save it in local variable.

//     (await this.commonService.postKYCDetails(kycDetails)).subscribe((result : any) => {
//       if (result){                  
//         let servResp = result as IServerResponsePut;      
//         this.saveToLocalStorage('kycDetailsUUID',servResp.id);
//         this.kycDetailsUUID = servResp.id;    
           
//         alert("KYC Details Saved Successfully!");
//       }
//       setTimeout(() => {
        
//       }, 3000);
//     });

//   }  


//   async submitOwnerAddress(firmAddress: IAddress){
//     //fix the actor type as Outlet here

//     //save the address in address api and close the modal
//    // get the address uuid and save it in local variable.

//    (await this.commonService.postAddress(firmAddress)).subscribe((result : any) => {
//     if (result){                 
//       let servResp = result as IServerResponsePut;      
//       this.saveToLocalStorage('ownerAddressUUID',servResp.id);
//       this.ownerAddressUUID = servResp.id;          
//       alert("Owner Address Saved Successfully!");
//     }
//     setTimeout(() => {      
//     }, 3000);
//   })   
  
//   }

//   saveToLocalStorage(key: string, value : string){
//     localStorage.setItem(key,value);
//   }

//   getFromLocalStorage(key: string) {
//     return localStorage.getItem(key);
//   }
  
// }

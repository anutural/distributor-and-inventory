import { Component, ElementRef, ViewChild } from '@angular/core';
import { IOutletAddress, IOutlet, IOutletType, IProviderInfo, IAddress, IServerResponsePut, IBankDetails, IKYCDetails, IActorType, IIDCommonKeyT } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';
import { OutletService } from 'src/app/services/outlet.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { NavigationEnd, Router } from '@angular/router';
import { faTrash, faCheck, faSquareCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import { FormControl, FormGroup, FormsModule, NgForm } from '@angular/forms';
import { ThemePalette } from '@angular/material/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupAddAddressComponent } from 'src/app/popups/popup-add-address/popup-add-address.component';
import { PopupbankdetailsComponent } from 'src/app/popups/popupbankdetails/popupbankdetails.component';
import { PopupOwnerKycComponent } from 'src/app/popups/popup-owner-kyc/popup-owner-kyc.component';
import { filter, first } from 'rxjs';



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

    this.route.events
    .pipe(
      filter((e) => e instanceof NavigationEnd),
      first()
    )
    .subscribe(() => {
      this.removeLocalStorage();
    });
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
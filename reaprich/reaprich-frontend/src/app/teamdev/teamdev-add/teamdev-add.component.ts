import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { NavigationEnd, Route, Router } from '@angular/router';
import { faCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import { filter, first } from 'rxjs';
import { IProviderInfo, ITeamDev } from 'src/app/data-type';
import { PopupAddAddressComponent } from 'src/app/popups/popup-add-address/popup-add-address.component';
import { PopupOwnerKycComponent } from 'src/app/popups/popup-owner-kyc/popup-owner-kyc.component';
import { PopupbankdetailsComponent } from 'src/app/popups/popupbankdetails/popupbankdetails.component';
import { TeamdevService } from 'src/app/services/teamdev.service';

@Component({
  selector: 'app-teamdev-add',
  templateUrl: './teamdev-add.component.html',
  styleUrls: ['./teamdev-add.component.css']
})
export class TeamdevAddComponent {
  addTeamdevForm!: FormGroup;
  providerInfo: IProviderInfo | undefined;
  teamdevAddressUUID : string | undefined;
  rightIconForModal= faCheck;
  wrongIconForModal= faXmark;  
  teamdevBankDetailsUUID : string | undefined;  
  teamdevKycDetailsUUID : string | undefined;

  constructor(private dialogRef: MatDialog, private router : Router, private teamDevService : TeamdevService){
    this.addTeamdevForm = new FormGroup({      
      actorType: new FormGroup({
        id: new FormControl({ value: 'TD', disabled: true })
      }),

      firstName: new FormControl(),
      lastName: new FormControl(),
      contactNumber: new FormControl(),
      PAN: new FormControl(),
      KYC: new FormGroup({
        id: new FormControl()
      }),      
      address: new FormGroup({
        id: new FormControl()
      }),
      bankDetails: new FormGroup({
        id: new FormControl()
      }),
      email: new FormControl(),
      password: new FormControl(),
    });

  }

  ngOnInit() {
    let providerInfo = this.getFromLocalStorage('providerInfo');
    if (providerInfo) {
      this.providerInfo = JSON.parse(providerInfo);
    }
    this.teamdevAddressUUID = this.getFromLocalStorage('teamdevAddressUUID') as string;
    this.teamdevBankDetailsUUID = this.getFromLocalStorage('teamdevBankDetailsUUID') as string;
    this.teamdevKycDetailsUUID = this.getFromLocalStorage('teamdevKycDetailsUUID') as string;

    this.router.events
      .pipe(
        filter((e) => e instanceof NavigationEnd),
        first()
      )
      .subscribe(() => {
        this.removeLocalStorage();
      });
  }


  async onAddTeamdev(teamDevObj : ITeamDev){


    

    if (localStorage.getItem("teamdevAddressUUID") === null) {
      alert('Please add address');
      return;
    }

    if (localStorage.getItem("teamdevBankDetailsUUID") === null) {
      alert('Please add bank details.');
      return;
    }
    if (localStorage.getItem("teamdevKycDetailsUUID") === null) {
      alert('Please add kyc details.');
      return;
    }

    let td = {
      id: "TD",
    };

    teamDevObj.actorType = td;

    teamDevObj.address = { id: this.teamdevAddressUUID };


    teamDevObj.bankDetails = { id: this.teamdevBankDetailsUUID };


    teamDevObj.KYC = { id: this.teamdevKycDetailsUUID };

    console.warn("teamdevobj:", teamDevObj);
    

    (await this.teamDevService.addTeamdev(teamDevObj)).subscribe((result: any) => {
      if (result) {
        alert("TeamDev Added Successfully!");
        this.removeLocalStorage();
        this.router.navigate([`\teamdev-home`]);
      }
    });
  }

  openPopUpForTeamdevAddress(){
    var popupResponse = this.dialogRef.open(PopupAddAddressComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500,
      data: {
        district: this.providerInfo.addDistList,
        zone: this.providerInfo.addZoneList,
        state: this.providerInfo.addStateList,
        country: this.providerInfo.addCountryList,
        addUuidVal: 'teamdevAddressUUID'
      }
    });

    popupResponse.afterClosed().subscribe(item => {
      if (item) {
        if (item) {
          this.saveToLocalStorage('teamdevAddressUUID', item);
          this.teamdevAddressUUID = item;
        }
      }
    })
  }

  openPopUpForTeamdevKYC(){
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
      this.saveToLocalStorage('teamdevKycDetailsUUID', item);
      this.teamdevKycDetailsUUID = item;
      }
    })
  }


  openPopUpForTeamdevBankDetails(){
    var popupResponse = this.dialogRef.open(PopupbankdetailsComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500
    });
    popupResponse.afterClosed().subscribe(item => {
      if (item){
      alert('Bank details saved successfully');
      this.saveToLocalStorage('teamdevBankDetailsUUID', item);
      this.teamdevBankDetailsUUID = item;
      }
    })
  }

  getFromLocalStorage(key: string) {
    return localStorage.getItem(key);
  }


  removeLocalStorage(){
    localStorage.removeItem('teamdevAddressUUID');
    localStorage.removeItem('teamdevBankDetailsUUID');
    localStorage.removeItem('teamdevKycDetailsUUID');
  }

  
  saveToLocalStorage(key: string, value: string) {
    localStorage.setItem(key, value);
  }
}

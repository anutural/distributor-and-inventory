import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { faCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import { IProviderInfo, ITeamDev } from 'src/app/data-type';

@Component({
  selector: 'app-teamdev-add',
  templateUrl: './teamdev-add.component.html',
  styleUrls: ['./teamdev-add.component.css']
})
export class TeamdevAddComponent {
  addTeamdevForm!: FormGroup;
  providerInfo: IProviderInfo | undefined;
  customerAddressUUID : string | undefined;
  rightIconForModal= faCheck;
  wrongIconForModal= faXmark;  

  constructor(){
    this.addTeamdevForm = new FormGroup({      
      Actor_Type: new FormGroup({
        id: new FormControl({ value: 'TD', disabled: true })
      }),

      TD_F_Name: new FormControl(),
      TD_L_Name: new FormControl(),
      TD_Contact_No: new FormControl(),
      TD_PAN: new FormControl(),
      TD_KYC: new FormGroup({
        id: new FormControl()
      }),      
      TD_Add: new FormGroup({
        id: new FormControl()
      }),
      TD_Bank: new FormGroup({
        id: new FormControl()
      }),
      Email: new FormControl(),
      Password: new FormControl(),
    });
    // id: string,//PK
    // Actor_Type: {id : string | undefined}, //FK
    // TD_F_Name: string,
    // TD_L_Name: string,
    // TD_Contact_No: number,
    // TD_PAN: string,
    // TD_KYC : number, // FK
    // TD_Add: number,// FK
    // TD_Bank: number, //FK
    // Email : string,
    // Password: string,
    // Status: string
  }

  onAddTeamdev(teamDevObj : ITeamDev){

  }

  openPopUpForTeamdevAddress(){

  }
}

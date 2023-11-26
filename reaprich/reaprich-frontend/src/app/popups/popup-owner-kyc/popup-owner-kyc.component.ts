import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { IAddress, IKYCDetails, IServerResponsePut } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';


@Component({
  selector: 'app-popup-owner-kyc',
  templateUrl: './popup-owner-kyc.component.html',
  styleUrls: ['./popup-owner-kyc.component.css']
})
export class PopupOwnerKycComponent {
  kycDetailForm!: FormGroup;
  closeMessage = 'closed using directive';
  constructor(public dialogRef: MatDialogRef<PopupOwnerKycComponent>, @Inject(MAT_DIALOG_DATA) public dataPassed, private commonService: CommonService) {
    this.kycDetailForm = new FormGroup({
      actorType: new FormGroup({
        id: new FormControl()
      }),
      name: new FormControl(),
      idType: new FormGroup({
        id: new FormControl()
      }),
      idNumber: new FormControl(),
      addProofType:  new FormGroup({
        id: new FormControl()
      }),
      addProofNumber: new FormControl(),
      documentLinks: new FormControl()      
    });
  }

  ngOnInit() {
  }

  afterClosed() {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  closePopUp() {
    this.dialogRef.close();
  }


  async saveBankKycDetails(kycDetailsObject: IKYCDetails) {
    let outlet = {
      id: "Outlet",
    };

    kycDetailsObject.actorType = outlet;
    
    (await this.commonService.postKYCDetails(kycDetailsObject)).subscribe((result: any) => {
      if (result) {

        let servResp = result as IServerResponsePut;
        this.dialogRef.close(servResp.id);
      }
      setTimeout(() => {
      }, 3000);
    })

  }



}


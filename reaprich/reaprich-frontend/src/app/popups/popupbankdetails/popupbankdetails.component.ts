import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { IBankDetails, IServerResponsePut } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';

@Component({
  selector: 'app-popupbankdetails',
  templateUrl: './popupbankdetails.component.html',
  styleUrls: ['./popupbankdetails.component.css']
})
export class PopupbankdetailsComponent {
  bankDetailsForm!: FormGroup;

  constructor(public dialogRef: MatDialogRef<PopupbankdetailsComponent>, private commonService: CommonService) {
    this.bankDetailsForm = new FormGroup({
      actorType: new FormGroup({
        id: new FormControl()
      }),
      name: new FormControl(),
      acNumber: new FormControl(),
      acType: new FormControl(),
      bankName: new FormControl(),
      branchName: new FormControl(),
      ifscCode: new FormControl()
    });
  }

  async saveBankDetails(bankDetailsObject : IBankDetails)
  {
    let outlet = {
      id: "Outlet",
    };

    bankDetailsObject.actorType = outlet;
    
    (await this.commonService.postBankDetails(bankDetailsObject)).subscribe((result: any) => {
      if (result) {

        let servResp = result as IServerResponsePut;
        console.warn('serve resp bank de ', result.id)
        this.dialogRef.close(servResp.id);
      }
      setTimeout(() => {
      }, 3000);
    })

  }

  closePopUp()
  {
    this.dialogRef.close();
  }
}

import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { IAddress, IAllItemsListServerResponse, IItem, IItemInfo, IItemWarehouse, IKYCDetails, IServerResponsePut } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';
import { InventoryService } from 'src/app/services/inventory.service';


@Component({
  selector: 'app-popup-add-item-warehous',
  templateUrl: './popup-add-item-warehous.component.html',
  styleUrls: ['./popup-add-item-warehous.component.css']
})
export class PopupAddItemWarehousComponent {
  kycDetailForm!: FormGroup;  
  itemList: IItem[] = [];

  closeMessage = 'closed using directive';
  constructor(public dialogRef: MatDialogRef<PopupAddItemWarehousComponent>, @Inject(MAT_DIALOG_DATA) public dataPassed, private inventoryService: InventoryService,
  private route: Router) {
    this.kycDetailForm = new FormGroup({
      item: new FormGroup({
        id: new FormControl()
      }),
      outlet: new FormGroup({
        id: new FormControl()
      }),
      batchNumber: new FormControl(),
      mfgDate: new FormControl(),
      expDate: new FormControl(),
      state: new FormControl(),
      quantity: new FormControl()      
    });

    this.inventoryService.allItemsList().subscribe((result : any) => {
      if (result) {
        let servResp = result as IAllItemsListServerResponse;          
        this.itemList = servResp.items;        
      }
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


  async addWarehouseInventoryItems(itemWarehouseObject: IItemWarehouse) {
    let outlet = {
      id: this.dataPassed.outletId,
    };

    itemWarehouseObject.outlet = outlet;    

    let myReqObject = {
      addWarehouseInventoryItemsRequests : [itemWarehouseObject]
    };      

    (await this.inventoryService.addItemIntoWarehouse(myReqObject)).subscribe((result: any) => {
      if (result) {
        alert('Item added in warehouse.');
        let servResp = result as IServerResponsePut;
        this.dialogRef.close(servResp.id);        
      }
      setTimeout(() => {
      }, 3000);
    });
  }

  
  getFromLocalStorage(key: string) {
    return localStorage.getItem(key);
  }


  removeLocalStorage(){
    //localStorage.removeItem('customerAddressUUID');
  }

  
  saveToLocalStorage(key: string, value: string) {
    localStorage.setItem(key, value);
  }


}

import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { InventoryService } from 'src/app/services/inventory.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { IItem, IItemInfo, IProviderInfo } from 'src/app/data-type';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-inventory-add-item',
  standalone: true,
  imports: [CommonModule,MatFormFieldModule,FormsModule,MatSelectModule, ReactiveFormsModule,MatInputModule,MatTableModule,MatButtonModule],


  templateUrl: './inventory-add-item.component.html',
  styleUrls: ['./inventory-add-item.component.css']
})
export class InventoryAddItemComponent {

  addItemForm!: FormGroup;
  itemProviderInfo: IItemInfo | undefined;

  constructor(private inventoryService: InventoryService, private route: Router, private dialogRef: MatDialog, 
    private activatedRoute : ActivatedRoute,
    private formBuilder : FormBuilder) {

    this.addItemForm = new FormGroup({     
      name: new FormControl('', Validators.required), 
      packingType: new FormGroup({
        id: new FormControl()
      }),
      category: new FormGroup({
        id: new FormControl()
      }),
      subcategory: new FormGroup({
        id: new FormControl()
      }),
      itemPrices: new FormGroup({
        gl: new FormControl(),
        sl: new FormControl(),
        pl: new FormControl()
      }),      
      reatilPrice: new FormControl('', Validators.required),
      pictureLink: new FormControl('', Validators.required),
      thumbnailLink : new FormControl('', Validators.required),
      gstprice: new FormControl('', Validators.required)
    });

  }

  ngOnInit(){
    let itemProviderInfo = this.getFromLocalStorage('itemProviderInfo');
    if (itemProviderInfo) {
      this.itemProviderInfo = JSON.parse(itemProviderInfo);
    }
  }



  async onAddItem(itemObject : IItem){
    console.warn('item object -', itemObject);


    if (itemObject.name == "" || itemObject.retailPrice == "" || itemObject.pictureLink == ""
    || itemObject.thumbnailLink == "" || itemObject.gstprice == ""){    
      alert("Please enter valid value");
      return;
    }

   (await this.inventoryService.addItem(itemObject)).subscribe((result: any) => {    
    let servResp = result as IProviderInfo;
    console.warn("res1 body " , result.body);
    if (result) {
      //localStorage.setItem('itemProviderInfo', JSON.stringify(result.body))
      this.itemProviderInfo = result.body;
      alert("Item Added Successfully!");        
    }
  }, (err) => {
    //this.errorMessage = err.error.error;
    alert(err.error.error);   
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

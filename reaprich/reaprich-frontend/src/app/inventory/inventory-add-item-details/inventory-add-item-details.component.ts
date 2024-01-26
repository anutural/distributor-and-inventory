import {Component} from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup, FormControl} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { IItemCategory, IItemInfo, IItemSubCategory, IPackingType, IProviderInfo } from 'src/app/data-type';
import { InventoryService } from 'src/app/services/inventory.service';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';

/**
 * @title Stepper vertical
 */
@Component({
  selector: 'app-inventory-add-item-details',
  templateUrl: 'inventory-add-item-details.component.html',
  styleUrls: ['inventory-add-item-details.component.css'],
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    CommonModule,
    MatOptionModule,
    MatSelectModule
  ],
})
export class InventoryAddItemDetailsComponent {
  firstFormGroup = this._formBuilder.group({
    id : ['', Validators.required],
    category: ['', Validators.required],
  });
  secondFormGroup = this._formBuilder.group({
    id: ['', Validators.required],
    subcategory: ['', Validators.required],
    category : new FormGroup({
      id: new FormControl()
    })
  });
  
  thirdFormGroup = this._formBuilder.group({
    id : ['', Validators.required],
    packingType: ['', Validators.required],
    quantityInGrams : ['', Validators.required],
    container: ['', Validators.required]
  });
  isLinear = false;
  itemProviderInfo: IItemInfo | undefined;
  
  constructor(private _formBuilder: FormBuilder, private inventoryService : InventoryService) {
  }

  ngOnInit(){
    let itemProviderInfo = this.getFromLocalStorage('itemProviderInfo');
    if (itemProviderInfo) {
      this.itemProviderInfo = JSON.parse(itemProviderInfo);
    }
  }

  async onAddCategory(categoryObject: IItemCategory){
    console.warn("categoryObject" , categoryObject);
    
    if (categoryObject.id == "" || categoryObject.category == ""){    
      alert("Please enter valid value");
      return;
    }

   (await this.inventoryService.addCategory(categoryObject)).subscribe((result: any) => {
    console.warn("I am here");
    let servResp = result.body as IItemInfo;
    console.warn("res1 ", servResp);
    if (result) {
      localStorage.setItem('itemProviderInfo', JSON.stringify(servResp))
      this.itemProviderInfo = result.body;
      alert("Category Added Successfully!");        
    }
  }, (err) => {
    //this.errorMessage = err.error.error;
    alert(err.error.error);   
  });
  }

  async onAddSubCategory(subCategoryObject: IItemSubCategory){
    console.warn("sub categoryObject" , subCategoryObject);
    
    if (subCategoryObject.id == "" || subCategoryObject.subcategory == ""){    
      alert("Please enter valid value");
      return;
    }

   (await this.inventoryService.addSubCategory(subCategoryObject)).subscribe((result: any) => {    
    let servResp = result as IProviderInfo;
    console.warn("res1 body " , result.body);
    if (result) {
      localStorage.setItem('itemProviderInfo', JSON.stringify(result.body))
      this.itemProviderInfo = result.body;
      alert("Sub Category Added Successfully!");        
    }
  }, (err) => {
    //this.errorMessage = err.error.error;
    alert(err.error.error);   
  });
  }

  async onAddPackingType(packingType: IPackingType){
    console.warn("sub categoryObject" , packingType);
    
    if (packingType.id == "" || packingType.packingType == "" || packingType.quantityInGrams == "" || packingType.container == ""){    
      alert("Please enter valid value");
      return;
    }

   (await this.inventoryService.addPackingType(packingType)).subscribe((result: any) => {    
    let servResp = result as IProviderInfo;
    console.warn("res1 body " , result.body);
    if (result) {
      localStorage.setItem('itemProviderInfo', JSON.stringify(result.body))
      this.itemProviderInfo = result.body;
      alert("Packing Type Added Successfully!");        
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


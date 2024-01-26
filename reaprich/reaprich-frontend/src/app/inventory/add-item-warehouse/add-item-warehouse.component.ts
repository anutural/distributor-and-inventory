import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginator, MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { InventoryService } from 'src/app/services/inventory.service';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { IInventoryItems, IItem, IItemWarehouse, IItemWarehouseServerResponse, IOutletServerResponse } from 'src/app/data-type';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialog } from '@angular/material/dialog';
import { PopupAddItemWarehousComponent } from 'src/app/popups/popup-add-item-warehous/popup-add-item-warehous.component';

@Component({
  selector: 'app-add-item-warehouse',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatTableModule,MatInputModule,     
    MatSortModule, 
    MatPaginatorModule,
    MatButtonModule],
  templateUrl: './add-item-warehouse.component.html',
  styleUrls: ['./add-item-warehouse.component.css']
})
export class AddItemWarehouseComponent {

  outletId : string | undefined;
  outletName : string | undefined;

  outletMessage : string | undefined;
  outletItemList: IInventoryItems[] = [];
  

  dataSource: MatTableDataSource<IInventoryItems>;
  columnsToDisplay = ['outletType', 'firmName', 'ownerFirstName', 'Action'];

  @ViewChild(MatPaginator) paginator: MatPaginator  = new MatPaginator(new MatPaginatorIntl(), ChangeDetectorRef.prototype);
  @ViewChild(MatSort) sort: MatSort = new MatSort();

  constructor(private inventoryService : InventoryService, private route: Router, private activatedRoute : ActivatedRoute,
    private dialogRef: MatDialog) {
      this.outletId = this.activatedRoute.snapshot.paramMap.get('id');
      this.outletName = this.activatedRoute.snapshot.paramMap.get('name');  

    this.inventoryService.getAllItemListOfOutlet(this.outletId).subscribe((result : any) => {
      console.warn('item list' , result);
      if (result) {
        let servResp = result.body as IItemWarehouseServerResponse;  
        console.warn('item list' , servResp.inventoryItems);
        this.outletItemList = servResp.inventoryItems
        this.dataSource = new MatTableDataSource(this.outletItemList);
      }
    });

    this.dataSource = new MatTableDataSource(this.outletItemList);

                
    Array.from(this.outletItemList).forEach(item => {
      console.warn('here', item);
      if (item.itemId) {
        console.warn('here', item.itemId);    
  }});

  }



  ngOnInit() {
  
    setTimeout(() => this.dataSource.paginator = this.paginator, 1000);    
    setTimeout(() => this.dataSource.sort = this.sort, 1000);

    console.warn('ngoninit called');
  }

  applyFilter(event: Event) {
    console.warn('event', event)
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteOutlet(custId: string){

  }

  updateItemInWarehouse(outletId : number){
    //console.warn("I am clicked re", outletId);
    this.route.navigate([`add-item-warehouse`, outletId]);
  }

  openPopUpForAddItemInWarehouse(){
    var popupResponse = this.dialogRef.open(PopupAddItemWarehousComponent, {
      enterAnimationDuration: 500,
      exitAnimationDuration: 500,
      data: {
          outletId: this.outletId,
          outletName: this.outletName,
        // state: this.providerInfo.addStateList,
        // country: this.providerInfo.addCountryList,
        // addUuidVal: 'customerAddressUUID'
      }    
    });

    popupResponse.afterClosed().subscribe(item => {
        window.location.reload();      
    })
    
  }

}

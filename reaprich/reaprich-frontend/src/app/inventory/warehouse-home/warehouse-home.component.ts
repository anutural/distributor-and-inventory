import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IOutlet, IOutletServerResponse } from 'src/app/data-type';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { OutletService } from 'src/app/services/outlet.service';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-warehouse-home',
  standalone: true,
  imports: [CommonModule, MatFormFieldModule, MatTableModule,MatInputModule,     
            MatSortModule, 
            MatPaginatorModule,
            MatButtonModule],
  templateUrl: './warehouse-home.component.html',
  styleUrls: ['./warehouse-home.component.css']
})
export class WarehouseHomeComponent {

  outletMessage : string | undefined;
  outletList: IOutlet[] = [];
  icon= faTrash;
  iconEdit = faEdit;

  dataSource: MatTableDataSource<IOutlet>;
  columnsToDisplay = ['outletType', 'firmName', 'ownerFirstName', 'Action'];

  @ViewChild(MatPaginator) paginator: MatPaginator  = new MatPaginator(new MatPaginatorIntl(), ChangeDetectorRef.prototype);
  @ViewChild(MatSort) sort: MatSort = new MatSort();

  constructor(private outletService : OutletService, private route: Router) {
    this.outletService.allOutletList().subscribe((result : any) => {
      if (result) {
        let servResp = result as IOutletServerResponse;  
        this.outletList = servResp.outlets
        this.dataSource = new MatTableDataSource(this.outletList);
      }
    });

    this.dataSource = new MatTableDataSource(this.outletList);
  }



  ngOnInit() {
    setTimeout(() => this.dataSource.paginator = this.paginator, 1000);    
    setTimeout(() => this.dataSource.sort = this.sort, 1000);   
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

  clickAddItemInWarehouse(outletId : number, outletName : string){
    //console.warn("I am clicked re", outletId);
    this.route.navigate([`add-item-warehouse`, outletId, outletName]);
  }
}

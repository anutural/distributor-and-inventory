import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { IOutlet, IOutletServerResponse } from 'src/app/data-type';
import { OutletService } from 'src/app/services/outlet.service';

import { Observable } from 'rxjs';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
 

@Component({
  selector: 'app-outlet-home',
  templateUrl: './outlet-home.component.html',
  styleUrls: ['./outlet-home.component.css']
})
export class OutletHomeComponent {

  outletMessage : string | undefined;
  outletList: IOutlet[] = [];
  icon= faTrash;
  iconEdit = faEdit;

  dataSource: MatTableDataSource<IOutlet>;
  columnsToDisplay = ['outletType', 'firmName', 'firmContactNumber', 'ownerFirstName', 'ownerLastName', 'ownerContactNumber', 'tdEmail', 'email', 'Action'];

  @ViewChild(MatPaginator) paginator: MatPaginator  = new MatPaginator(new MatPaginatorIntl(), ChangeDetectorRef.prototype);
  @ViewChild(MatSort) sort: MatSort = new MatSort();

  constructor(private outletService : OutletService) {
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

}

import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { ITeamDev, ITeamDevServerResponse } from 'src/app/data-type';
import { TeamdevService } from 'src/app/services/teamdev.service';

@Component({
  selector: 'app-teamdev-home',
  templateUrl: './teamdev-home.component.html',
  styleUrls: ['./teamdev-home.component.css']
})
export class TeamdevHomeComponent {
  tdMessage : string | undefined;
  tdList: ITeamDev[] = [];
  icon= faTrash;
  iconEdit = faEdit;
  dataSource: MatTableDataSource<ITeamDev>;

  @ViewChild(MatPaginator) paginator: MatPaginator  = new MatPaginator(new MatPaginatorIntl(), ChangeDetectorRef.prototype);
  @ViewChild(MatSort) sort: MatSort = new MatSort();

  columnsToDisplay = ['firstName', 'lastName', 'contactNumber', 'PAN', 'email'];  

  constructor(private teamdevService: TeamdevService) {
    this.teamdevService.allTeamDevList().subscribe((result: any) => {
      console.warn('teamdev - res', result)
      if (result) {
        let servResp = result as ITeamDevServerResponse;
        if (servResp.teamdevs.length > 0) {
          this.tdList = servResp.teamdevs;
          this.dataSource = new MatTableDataSource(this.tdList);
        }
      }
    }, (err) => {
      console.warn("erro in td get -", err);
    });

    this.dataSource = new MatTableDataSource(this.tdList);
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
  
  deleteTD(custId: string){

  }
}

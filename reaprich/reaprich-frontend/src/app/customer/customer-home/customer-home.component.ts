import { ChangeDetectorRef, Component, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import { ICustomer } from 'src/app/data-type';
import { CustomerService } from 'src/app/services/customer.service';




@Component({
  selector: 'app-customer-home',
  templateUrl: './customer-home.component.html',
  styleUrls: ['./customer-home.component.css'],  
})


export class CustomerHomeComponent {

  customerList: ICustomer[] = [];
  customerMessage: undefined | string;
  icon= faTrash;
  iconEdit = faEdit;
  dataSource: MatTableDataSource<ICustomer>;
  columnsToDisplay = ['Customer_Type', 'Customer_F_Name', 'Customer_L_Name', 'Customer_Contact_Number', 'TD_Add', 'Action'];
  
  @ViewChild(MatPaginator) paginator: MatPaginator  = new MatPaginator(new MatPaginatorIntl(), ChangeDetectorRef.prototype);
  @ViewChild(MatSort) sort: MatSort = new MatSort();

  constructor(private customerService: CustomerService, private route: Router){
    this.customerService.customerList().subscribe((result) => {
      if (result) {        
        this.customerList = result;        
        this.dataSource = new MatTableDataSource(this.customerList);
      }
    });
    this.dataSource = new MatTableDataSource(this.customerList); // this needs to be fixed
  }

  ngOnInit(){
    setTimeout(() => this.dataSource.paginator = this.paginator, 1000);    
    setTimeout(() => this.dataSource.sort = this.sort, 1000);    
  }



  deleteCustomer(custId: number){
    console.warn('custId::', custId)
    this.customerService.deleteCustomer(custId).subscribe((result) => {
      if (result) {
        this.customerMessage = "Customer is deleted";
        
      }
      setTimeout(() => {
        this.customerMessage = undefined
      }, 3000);
    });
  }

  addCustomer(){
    this.route.navigate([`\customer-add`]);
  }

  
  applyFilter(event: Event) {
    console.warn('event', event)
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}



// export class CustomerHomeComponent {

//   customerList: undefined | ICustomer[];
//   customerMessage: undefined | string;
//   icon= faTrash;
//   iconEdit = faEdit;

//   constructor(private customerService: CustomerService, private route: Router){

//   }

//   ngOnInit(){
//     this.list();
//   }



//   deleteCustomer(custId: number){
//     this.customerService.deleteCustomer(custId).subscribe((result) => {
//       if (result) {
//         this.customerMessage = "Customer is deleted";
//         this.list();
//       }
//       setTimeout(() => {
//         this.customerMessage = undefined
//       }, 3000);
//     });
//   }

  
//   list() {
//     this.customerService.customerList().subscribe((result) => {
//       if (result) {
//         console.warn("i am here" + result);
//         this.customerList = result;
//       }
//     })
//   }

//   addCustomer(){
//     this.route.navigate([`\customer-add`]);
//   }

// }




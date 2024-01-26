import { Component } from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import { Router } from '@angular/router';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-login-home',
  templateUrl: './user-login-home.component.html',
  styleUrls: ['./user-login-home.component.css'],
  standalone: true,
  imports: [MatGridListModule, CommonModule],
})
export class UserLoginHomeComponent {

  showAdminControl : boolean;
  showModuleSelection : boolean = true;

  constructor(private route : Router){

  }



  clickTeamDev(ev){    
    this.route.navigate([`teamdev-home`]);
  }

  clickCustomer(ev){
    this.route.navigate([`customer-home`]);
  }

  clickOutlet(ev){
    this.route.navigate([`outlet-home`]);
  }

  clickLocation(ev){
    this.route.navigate([`admin-add-location`]);
  }

  clickInventory(ev){
    this.route.navigate([`inventory-home`]);
  }

  clickAdmin(ev){
    this.showAdminControl = true;
    this.showModuleSelection = false;    
  }
}

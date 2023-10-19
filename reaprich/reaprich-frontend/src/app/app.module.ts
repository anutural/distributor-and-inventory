import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { OutletListComponent } from './outlet/outlet-list/outlet-list.component';
import { TeamdevListComponent } from './teamdev/teamdev-list/teamdev-list.component';
import { CustomerComponent } from './customer/customer.component';
import { OutletComponent } from './outlet/outlet.component';
import { TeamdevComponent } from './teamdev/teamdev.component';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes= [
  { path: '', component: CustomerComponent},
  { path: 'customers', component: CustomerComponent},
  { path: 'outlets', component: OutletComponent},
  { path: 'teamdevs', component: TeamdevComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    CustomerListComponent,
    OutletListComponent,
    TeamdevListComponent,
    CustomerComponent,
    OutletComponent,
    TeamdevComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

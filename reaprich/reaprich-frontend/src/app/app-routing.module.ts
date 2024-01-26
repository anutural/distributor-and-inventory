import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ActorAuthComponent } from './actor-auth/actor-auth.component';
import { CustomerHomeComponent } from './customer/customer-home/customer-home.component';
import { CustomerEditComponent } from './customer/customer-edit/customer-edit.component';
import { CustomerAddComponent } from './customer/customer-add/customer-add.component';
import { UserLoginHomeComponent } from './user-login-home/user-login-home.component';
import { AuthGuard } from './auth.guard';
import { OutletHomeComponent } from './outlet/outlet-home/outlet-home.component';
import { TeamdevHomeComponent } from './teamdev/teamdev-home/teamdev-home.component';
import { OutletAddComponent } from './outlet/outlet-add/outlet-add.component';
import { TeamdevAddComponent } from './teamdev/teamdev-add/teamdev-add.component';
import { AddLocationComponent } from './admin/add-location/add-location.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { InventoryHomeComponent } from './inventory/inventory-home/inventory-home.component';
import { InventoryAddItemDetailsComponent } from './inventory/inventory-add-item-details/inventory-add-item-details.component';
import { InventoryAddItemComponent } from './inventory/inventory-add-item/inventory-add-item.component';
import { WarehouseHomeComponent } from './inventory/warehouse-home/warehouse-home.component';
import { AddItemWarehouseComponent } from './inventory/add-item-warehouse/add-item-warehouse.component';
import { OrdersHomeComponent } from './orders/orders-home/orders-home.component';
import { OrdersViewItemsComponent } from './orders/orders-view-items/orders-view-items.component';
import { OrdersCheckoutComponent } from './orders/orders-checkout/orders-checkout.component';
import { OrdersItemsgridComponent } from './orders/orders-itemsgrid/orders-itemsgrid.component';


const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path:'actor-auth',
    component : ActorAuthComponent
  },
  {
    path:'customer-home',
    component : CustomerHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path:'customer-edit/:id',
    component : CustomerAddComponent,
    canActivate : [AuthGuard]
  },
  {
    path:'customer-add',
    component : CustomerAddComponent,
    canActivate : [AuthGuard]
  },
  {
    path:'user-login-home',
    component : UserLoginHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path:'outlet-home',
    component : OutletHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path:'teamdev-home',
    component : TeamdevHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'outlet-add',
    component: OutletAddComponent,
  },
  {
    path:'teamdev-add',
    component : TeamdevAddComponent,
    canActivate : [AuthGuard]
  },
  {
    path:'admin-add-location',
    component : AddLocationComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'contact-us',
    component: ContactUsComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'inventory-home',
    component: InventoryHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'inventory-add-item-details',
    component: InventoryAddItemDetailsComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'inventory-add-item',
    component: InventoryAddItemComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'warehouse-home',
    component: WarehouseHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'add-item-warehouse/:id/:name',
    component: AddItemWarehouseComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'orders-home',
    component: OrdersHomeComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'orders-view-items',
    component: OrdersViewItemsComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'orders-checkout',
    component: OrdersCheckoutComponent,
    canActivate : [AuthGuard]
  },
  {
    path: 'orders-itemsgrid',
    component: OrdersItemsgridComponent,
    canActivate : [AuthGuard]
  },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

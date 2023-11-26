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
    component : CustomerEditComponent,
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
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ActorAuthComponent } from './actor-auth/actor-auth.component';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { CustomerHomeComponent } from './customer/customer-home/customer-home.component';
import { CustomerEditComponent } from './customer/customer-edit/customer-edit.component';
import { TeamdevHomeComponent } from './teamdev/teamdev-home/teamdev-home.component';
import { TeamdevEditComponent } from './teamdev/teamdev-edit/teamdev-edit.component';
import { OutletHomeComponent } from './outlet/outlet-home/outlet-home.component';
import { OutletEditComponent } from './outlet/outlet-edit/outlet-edit.component';
import { HttpClientModule } from '@angular/common/http';
import { CustomerAddComponent } from './customer/customer-add/customer-add.component';
import { ProductHomeComponent } from './product/product-home/product-home.component';
import { UserLoginHomeComponent } from './user-login-home/user-login-home.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    ActorAuthComponent,
    FooterComponent,
    HomeComponent,
    CustomerHomeComponent,
    CustomerEditComponent,
    TeamdevHomeComponent,
    TeamdevEditComponent,
    OutletHomeComponent,
    OutletEditComponent,
    CustomerAddComponent,
    ProductHomeComponent,
    UserLoginHomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgbModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

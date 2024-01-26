import { APP_INITIALIZER, NgModule } from '@angular/core';
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
import { OutletAddComponent } from './outlet/outlet-add/outlet-add.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatPaginator, MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {ReactiveFormsModule} from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule} from '@angular/material/dialog';
import { PopupbankdetailsComponent } from './popups/popupbankdetails/popupbankdetails.component';
import { PopupAddAddressComponent } from './popups/popup-add-address/popup-add-address.component';
import { PopupOwnerKycComponent } from './popups/popup-owner-kyc/popup-owner-kyc.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { MatToolbarModule  } from '@angular/material/toolbar';
import { MatIconModule  } from '@angular/material/icon';
import { TeamdevAddComponent } from './teamdev/teamdev-add/teamdev-add.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { PopupAddItemWarehousComponent } from './popups/popup-add-item-warehous/popup-add-item-warehous.component';
import { OrdersHomeComponent } from './orders/orders-home/orders-home.component';



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
    OutletAddComponent,
    PopupbankdetailsComponent,
    PopupAddAddressComponent,
    PopupOwnerKycComponent,
    TeamdevAddComponent,
    ContactUsComponent,
    PopupAddItemWarehousComponent,    
    OrdersHomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    MatFormFieldModule, 
    MatInputModule, 
    MatTableModule, 
    MatSortModule, 
    MatPaginatorModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatDialogModule,
    MatSidenavModule,
    MatListModule,
    MatToolbarModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

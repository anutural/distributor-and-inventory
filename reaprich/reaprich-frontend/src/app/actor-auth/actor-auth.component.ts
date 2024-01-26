import { Component } from '@angular/core';
import { ILogin } from '../data-type';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-actor-auth',
  templateUrl: './actor-auth.component.html',
  styleUrls: ['./actor-auth.component.css']  
})
export class ActorAuthComponent {
  authError:string  = "";
  showLogin = true;
  email = new FormControl('', [Validators.required, Validators.email]);
  userLoginForm!: FormGroup;
  hide = true;
  
  constructor(private userService : UserService, private route : Router, private commonService : CommonService){    
    this.userLoginForm = new FormGroup({
      email: new FormControl(),
      password: new FormControl()
    });
  }

  login(data: ILogin) {
    console.warn("Login Data: ", data);
    this.userService.userLogin(data);

    //listen to emitting event
    this.userService.invalidUserAuth.subscribe((result) => {      
      if (result) {
        this.authError = "Please enter valid user details";
      }
    });


    this.userService.isUserLoggedIn.subscribe((result) => {      
      if (result) {
        this.commonService.getProviderInfo();
      }
    });

  }

  openLogin(){
    this.showLogin = true;
  }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

}

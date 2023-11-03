import { Component } from '@angular/core';
import { ILogin, ISignUp } from '../data-type';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-actor-auth',
  templateUrl: './actor-auth.component.html',
  styleUrls: ['./actor-auth.component.css']
})
export class ActorAuthComponent {
  authError:string  = "";
  showLogin = false;

  constructor(private userService : UserService, private route : Router){}

  login(data: ILogin) {
    console.warn("Login Data: ", data);
    this.userService.userLogin(data);

    //listen to emitting event
    this.userService.invalidUserAuth.subscribe((result) => {
      if (result) {
        this.authError = "Please enter valid user details";
      }

    });
  }

  signUp(data: ISignUp):void{    
    this.userService.userSignUp(data);
  }

  openLogin(){
    this.showLogin = true;
  }

  openSignUp(){
    this.showLogin = false;
  }

}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  menuType: string = 'default';
  isUserLoggedIn : boolean = false;
  

  constructor(private route : Router, private customerService : CustomerService, private userService: UserService){
    this.userService.isUserLoggedIn.subscribe((result) => {      
      if (result) {
        this.isUserLoggedIn = true;
      }
      else
      {
        this.isUserLoggedIn = false;        
      }
    });
  }


  ngOnInit(): void {    
    this.route.events.subscribe((val:any) => {
      if(val.url){
        
        if(localStorage.getItem('usersreaprich'))
        {
          this.menuType = 'admin';  
          this.isUserLoggedIn = true;        
        }
        else{
          this.menuType = 'default';
        }
      }
    });
  }

  logout(){
    this.isUserLoggedIn = false;  
    localStorage.removeItem('usersreaprich');  
    localStorage.removeItem('providerInfo');
    this.route.navigate(['/']);    
  }

  onHomeClick(){        
    if(localStorage.getItem('usersreaprich'))
    {
      this.route.navigate(['/user-login-home']);
    }else{
      this.route.navigate(['/']);      
    }
    
  }

}

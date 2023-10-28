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
  

  constructor(private route : Router, private customerService : CustomerService, private userService: UserService){}


  ngOnInit(): void {    
    this.route.events.subscribe((val:any) => {
      if(val.url){
        
        if(localStorage.getItem('usersreaprich'))
        {
          this.menuType = 'admin';          
        }
        else{
          this.menuType = 'default';
        }
      }
    });
  }

  logout(){
    localStorage.removeItem('usersreaprich');  
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

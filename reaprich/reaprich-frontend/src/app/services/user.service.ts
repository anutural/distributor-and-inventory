import { EventEmitter, Injectable } from '@angular/core';
import { ILogin, ISignUp } from '../data-type';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  isUserLoggedIn = new BehaviorSubject<boolean>(false);
  invalidUserAuth = new EventEmitter<Boolean>(false);

  constructor(private http: HttpClient, private router: Router) { }

  userLogin(data: ILogin){
    this.http.get<ISignUp[]>(`http://localhost:3000/usersreaprich?email=${data.email}&password=${data.password}`,
    {observe:'response'})
    .subscribe((result) => {
      if(result && result.body?.length){
        this.isUserLoggedIn.next(true);
        localStorage.setItem('usersreaprich', JSON.stringify(result.body[0]));        
        this.router.navigate(['/user-login-home']);        
      }
      else{
        this.invalidUserAuth.emit(true);
      }
    })
    ;
  }

  userSignUp(data: ISignUp){
    this.http.post("http://localhost:3000/usersreaprich", data, {observe: 'response'})
    .subscribe((result) => {
      console.warn(result);
      if (result) {
        this.isUserLoggedIn.next(true);
        localStorage.setItem('usersreaprich', JSON.stringify(result.body));
        this.router.navigate(['/']);
      }
    })
  }


}

import { EventEmitter, Injectable } from '@angular/core';
import { IAddress, ILogin, ISignUp } from '../data-type';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
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
    this.http.post(`http://localhost:8080/v1/auth/authenticate`,data,
    {observe:'response'})
    .subscribe((result) => {
      console.warn('login res' + result);
      if(result && result.body){
        this.isUserLoggedIn.next(true);        
        localStorage.setItem('usersreaprich', JSON.stringify(result.body));        
        this.router.navigate(['/user-login-home']);        
      }
      else{
        this.invalidUserAuth.emit(true);
      }
    })
    ;
  }

  userSignUp(data: ISignUp){
    this.http.post("http://localhost:8080/usersreaprich", data, {observe: 'response'})
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

import { EventEmitter, Injectable } from '@angular/core';
import { IAddress, ILogin, ISignUp } from '../data-type';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  isUserLoggedIn = new BehaviorSubject<boolean>(false);
  invalidUserAuth = new EventEmitter<Boolean>(false);
  m_hostURL = environment.hostURL;
  constructor(private http: HttpClient, private router: Router) { }

  userLogin(data: ILogin){
    this.http.post(this.m_hostURL + 'auth/authenticate',data,
    {observe:'response'})
    .subscribe((result) => {
      console.warn('login res' , result);
      if(result && result.body){
        localStorage.setItem('usersreaprich', JSON.stringify(result.body));   
        this.isUserLoggedIn.next(true);             
        this.router.navigate(['/user-login-home']);        
      }
      else{
        this.invalidUserAuth.emit(true);
      }
    }, (err) => {
      this.invalidUserAuth.emit(true);
    })
    ;
  }

  userSignUp(data: ISignUp){
    this.http.post(this.m_hostURL + 'usersreaprich', data, {observe: 'response'})
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

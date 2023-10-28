import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { UserService } from './services/user.service';

export const AuthGuard: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const userService = inject(UserService);
  const router = inject(Router);

  //if user manually enters value for seller-home in browser, and if its already logged in as a local storage found, then return true.
  if(localStorage.getItem('usersreaprich')){    
    return true;
  }else{
    router.navigate(['/']);
  }

  return userService.isUserLoggedIn;
};
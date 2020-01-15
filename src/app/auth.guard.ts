import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild } from '@angular/router';
import { Injectable } from '@angular/core';
import { LoginService } from './pages/login/login.service';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})

export class AuthGuard implements CanActivate {

    constructor(private loginservice: LoginService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):
        boolean {
        if (this.loginservice.isLoggedin) {
            // if we return true user is allowed to access that route
            return true;
        } else {
            // if we return false user is not allowed to access
            this.router.navigate([''])
            return false;
        }
    }

    canActivateChild(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): boolean {
        // invoking the canActivate by passing route and state
        return this.canActivate(route, state);
    }

}
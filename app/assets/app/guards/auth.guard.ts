import { Injectable } from "@angular/core";
import { Router, ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from "@angular/router";

import { RedirectService } from "../services/redirect-service/redirect.service"

@Injectable()
export class AuthGuard implements CanActivate {

    state: RouterStateSnapshot;

constructor(private router: Router, private redirectService: RedirectService) {

}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        if (sessionStorage.getItem("currentUser")) {
            return true;
        }

        this.redirectService.setRedirectUrl(state.url);
        this.router.navigate(["/login"]);

        return false;
    }
}
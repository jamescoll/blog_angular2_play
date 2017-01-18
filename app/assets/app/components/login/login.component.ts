import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";

import { LoginService } from "../../services/login-service/login.service"
import { LoggingService } from "../../services/logging-service/logging.service"
import { LogLevel } from "../../models/loglevel"
import { RedirectService } from "../../services/redirect-service/redirect.service"



@Component({
  template: `
    <div class="container-fluid">
    <div class="row">
        <div class="col-xs-6 col-md-4"></div>
        <div class="col-xs-6 col-md-4">
    <h2>Sign in </h2>
    <form *ngIf="active" (ngSubmit)="onSubmit()" #loginForm="ngForm">
        <div class="form-group">
            <label for="username">Email</label>
            <input type="text" class="form-control" id="username" required
                   [(ngModel)]="model.username" name="username" #username="ngModel"
            >
            <div [hidden]="username.valid || username.pristine"
                 class="alert alert-danger">
                Email is required
            </div>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" required
                   [(ngModel)]="model.password" name="password" #password="ngModel">
        </div>
        <div [hidden]="password.valid || password.pristine"
             class="alert alert-danger">
            Password is required
        </div>
        <div class="text-center">
        <p><a routerLink="/user">No Account? Sign up</a></p>
        <p><a routerLink="/placeholder">Forgot Password</a></p>
            <button type="submit" class="btn btn-default" [disabled]="!loginForm.form.valid">Sign in</button></div>
    </form></div>
        <div class="col-xs-6 col-md-4"></div>
    </div>
</div>
  `
})

export class LoginComponent implements OnInit {

  model: Login;
  submitted = false;
  result: any;
  active = true;
  redirectUrl: any;

   constructor(private loginService: LoginService, private router: Router, private loggingService: LoggingService, private route: ActivatedRoute, private redirectService: RedirectService) {
        this.loggingService.sendLogMessage(LogLevel.TRACE, "Client in Login Component");

  }

  ngOnInit() {
    this.redirectUrl = this.redirectService.getRedirectUrl();
  }


  onSubmit() {
    this.submitted = true;
    this.loginService.login(JSON.stringify(this.model)).subscribe(
      result => {this.processLogin(result);}
    );
  }

  processLogin(result: any){
        if(result.token){
          if(result.success === "true") {
            sessionStorage.setItem("currentUser", JSON.stringify({ userInfo: this.model, token: result.token }));

            let url = this.redirectService.getRedirectUrl();
            if(url) {
            this.router.navigateByUrl(url);
            } else {
                    // todo process roles
           }

          } else {
            // todo navigate to a change password page
            sessionStorage.setItem("currentUser", JSON.stringify({ userInfo: this.model, token: result.token }));
          }

        } else {
          this.reset()
        }

      }

 

  reset() {
     // todo is there a better/conditional way to do this
    this.model.username = "";
    this.model.password = "";
    this.active = false;
    setTimeout(() => this.active = true, 0);

  }

}

export interface Login {
    username: string;
    password: string;
}

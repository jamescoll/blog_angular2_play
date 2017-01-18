import { NgModule }      from "@angular/core"
import { BrowserModule } from "@angular/platform-browser"
import { FormsModule }   from "@angular/forms"
import { HttpModule } from "@angular/http"

import DefaultAppComponent from "./app"
import { AppRoutingModule } from "./app-routing.module"

import { HomepageComponent } from "./components/homepage/homepage.component"
import { PostComponent } from "./components/post/post.component"
import { LoginComponent } from "./components/login/login.component"

import { LoggingService } from "./services/logging-service/logging.service"
import { PostService } from "./services/post-service/post.service"
import { CommentsService } from "./services/comments-service/comments.service"
import { RedirectService } from "./services/redirect-service/redirect.service"
import { LoginService } from "./services/login-service/login.service"

import { AuthGuard } from "./guards/auth.guard";

@NgModule({

  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  declarations: [
    DefaultAppComponent,
    HomepageComponent,
    PostComponent,
    LoginComponent
  ],
  bootstrap: [DefaultAppComponent],
  providers: [
    LoggingService,
    PostService,
    CommentsService,
    LoginService,
    RedirectService,
    AuthGuard
  ]
})

export class DefaultAppModule { }

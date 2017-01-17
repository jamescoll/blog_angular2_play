import { NgModule }      from "@angular/core"
import { BrowserModule } from "@angular/platform-browser"
import { FormsModule }   from "@angular/forms"

import { HttpModule } from "@angular/http"

import DefaultAppComponent from "./app"


import { AppRoutingModule } from "./app-routing.module"
import { HomepageComponent } from "./components/homepage/homepage.component"
import { PostComponent } from "./components/post/post.component"
import { LoggingService } from "./services/logging-service/logging.service"
import { PostService } from "./services/post-service/post.service"
import { CommentsService } from "./services/comments-service/comments.service"

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
    PostComponent
  ],
  bootstrap: [DefaultAppComponent],
  providers: [
    LoggingService,
    PostService,
    CommentsService
  ]
})

export class DefaultAppModule { }

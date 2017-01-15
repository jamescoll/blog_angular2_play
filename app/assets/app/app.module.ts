import { NgModule }      from "@angular/core"
import { BrowserModule } from "@angular/platform-browser"
import { FormsModule }   from "@angular/forms"
import { HttpModule } from "@angular/http"

import { DefaultAppComponent } from "./app"


import { AppRoutingModule } from "./app-routing.module"
import { HomeComponent } from "./components/home/home.component"
import { PostComponent } from "./components/post/post.component"
import { PostService } from "./services/post-service/post.service"
import { CommentsService } from "./services/comments-service/comments.service"

@NgModule({
  declarations: [
    DefaultAppComponent,
    PostComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  bootstrap: [DefaultAppComponent],
  providers: [
    PostService,
    CommentsService
  ]
})
export class DefaultAppModule { }

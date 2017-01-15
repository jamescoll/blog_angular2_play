import { NgModule }      from "@angular/core"
import { BrowserModule } from "@angular/platform-browser"
import { FormsModule }   from "@angular/forms"
import { HttpModule } from "@angular/http"

import { DefaultAppComponent } from "./app"


import { routing,
appRoutingProviders } from "./app-routing.module"
import { HomepageComponent } from "./components/homepage/homepage.component"
import { TestComponent } from "./components/test/test.component"
import { NotFoundComponent } from "./components/notfound/not-found.component"
import { HomeComponent } from "./components/home/home.component"
import { PostComponent } from "./components/post/post.component"
import { JsonTestService } from "./services/json-test/json-test.service"
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
    routing
  ],
  providers: [
    appRoutingProviders
  ],
  bootstrap: [DefaultAppComponent]
})
export class DefaultAppModule { }

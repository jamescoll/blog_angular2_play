import { NgModule }     from "@angular/core";
import { RouterModule } from "@angular/router";

import { HomepageComponent } from "./components/homepage/homepage.component"
import { PostComponent } from "./components/post/post.component"
import { LoginComponent } from "./components/login/login.component"

@NgModule({
  imports: [
    RouterModule.forRoot([
      {
        path: "",
        component: HomepageComponent
      },
      {
        path: "post/:id",
        component: PostComponent
      },
      {
        path: "login",
        component: LoginComponent
      }
    ]
    )
  ],
  exports: [
    RouterModule
  ],
  providers: [

  ]
})
export class AppRoutingModule {}

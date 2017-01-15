import { NgModule }     from "@angular/core";
import { RouterModule } from "@angular/router";
import { DefaultAppComponent } from "./app";
import { HomeComponent } from "./components/home/home.component"
import { PostComponent } from "./components/post/post.component"

@NgModule({
  imports: [
    RouterModule.forRoot([
        { path: '', component: HomeComponent },
        {
        path: 'post/:id', component: PostComponent
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

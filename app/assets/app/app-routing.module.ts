
import { Routes, RouterModule } from "@angular/router";
import { DefaultAppComponent } from "./app";
import { HomeComponent } from "./components/home/home.component"
import { PostComponent } from "./components/post/post.component"


const appRoutes: Routes = [
{ path: '', component: HomeComponent },
{
path: 'post/:id', component: PostComponent
}
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
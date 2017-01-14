import { NgModule }      from "@angular/core"
import { BrowserModule } from "@angular/platform-browser"
import { FormsModule }   from "@angular/forms"

import { HttpModule } from "@angular/http"

import DefaultAppComponent from "./app"


import { AppRoutingModule } from "./app-routing.module"
import { HomepageComponent } from "./components/homepage/homepage.component"
import { TestComponent } from "./components/test/test.component"
import { NotFoundComponent } from "./components/notfound/not-found.component"
import { JsonTestService } from "./services/json-test/json-test.service"

@NgModule({
    imports: [
      BrowserModule
      , FormsModule
      , HttpModule
      , AppRoutingModule
    ],
    declarations: [
      DefaultAppComponent,
      HomepageComponent,
      TestComponent,
      NotFoundComponent
    ],
    bootstrap: [DefaultAppComponent],
    providers: [
      JsonTestService
    ]
})
export class DefaultAppModule { }

import { Component } from "@angular/core";

@Component({
  styles: [`
    .center {

        margin-left: 10%; /* margin is -0.5 * dimension */
        margin-top: 10%;
    }​
    `],
  template:  `
  <div class="center">
    <h2>404 Page not found</h2>
    <p> Oops! The Page you requested was not found!</p>
    <p><a routerLink="/">Go back to the homepage</a></p>
    </div>
  `
})
export class NotFoundComponent { }

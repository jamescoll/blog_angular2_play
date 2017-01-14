import { Component } from "@angular/core";

@Component({
  styles: [`
    .center {

        margin-left: 10%; /* margin is -0.5 * dimension */
        margin-top: 10%;
    }​
    `],
    template: `
    <div class="center">
    <h2>TestComponent: This is some other page</h2>
    <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,
    totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.
     Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
     qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur,
     adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
     Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
     consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel
     illum qui dolorem eum fugiat quo voluptas nulla pariatur?</p>
    <p><a routerLink="/">Link to homepage</a></p>
    <p><a routerLink="/doesntExist">Link to non existing page</a></p>
    </div>
  `
})
export class TestComponent { }

import {Component} from "@angular/core"


@Component({
    selector: "blog-app",
    templateUrl: "assets/app/app.html",
    styleUrls: ['./app.css']
})

export class DefaultAppComponent {
    title = 'Angular 2 - Blog example';
    lead = 'This is a simple blog example created with Angular 2 (Angular-cli)';

    constructor() {

    }
}

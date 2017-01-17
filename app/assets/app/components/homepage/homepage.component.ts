import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs/Observable"

import { LoggingService } from "../../services/logging-service/logging.service"
import {PostService} from "../../services/post-service/post.service"

import { LogLevel } from "../../models/loglevel"

@Component({
    
    template: `
    <div class="col-md-6 col-md-pull-3">
    <h2>Posts ({{ posts?.length }})</h2>
    <div *ngFor="let post of posts">
        <div class="card ">
            <div class="card-block">
                <h4 class="card-title">{{ post.title }}</h4>
                <p class="card-text">{{ post.body }}</p>
                <a [routerLink]="['/post', post.id]" class="card-link">Read more</a>
            </div>
        </div>
    </div>
</div>
  `
})
export class HomepageComponent implements OnInit {
   
    posts:Array<any> = [];

    constructor(private p: PostService, private loggingService: LoggingService) {

        this.loggingService.sendLogMessage(LogLevel.TRACE, "Client in HomepageComponent");

        this.p.getAll().subscribe(
            result => this.posts = result,
            error => console.error('Error: ')
        );
    }

    ngOnInit() {
    }

}

import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs/Observable"

import {JsonTestService} from "../../services/json-test/json-test.service"

@Component({
    styles: [`
      .center {

          margin-left: 10%; /* margin is -0.5 * dimension */
          margin-top: 10%;
      }​
      `],
    template: `
    <div class="center">
    <h2 >Homepage Component</h2>
    <p>This page sends and receives Json in the background</p>
    <p>The last login was: {{jsonResponse.name}} {{jsonResponse.staff_member}} {{jsonResponse.login_date}}    </p>
    <p><a routerLink="/test">Link to test page</a></p>
    </div>
  `
})
export class HomepageComponent implements OnInit {
    private jsonResponse = {};

    constructor(private jsonService: JsonTestService) { }

    ngOnInit() { this.getJson(); this.sendJson(); }

    getJson() {

        this.jsonService.getJson().subscribe(
            result => this.jsonResponse = result,
            error => console.log(error)
        )
    }

    sendJson() {

        this.jsonService.sendJson().subscribe()

    }

}

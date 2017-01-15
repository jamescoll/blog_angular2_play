import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post-service/post.service';

@Component({
   
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
   
})
export class HomeComponent implements OnInit {

    posts:Array<any> = [];

    constructor(private p: PostService) {
        this.p.getAll().subscribe(
            result => this.posts = result,
            error => console.error('Error: ')
        );
    }

    ngOnInit() {
    }

}
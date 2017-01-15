import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { PostService } from '../../services/post-service/post.service';
import { CommentsService } from '../../services/comments-service/comments.service';

@Component({
  template:  `
  <div class="row">
    <div class="col-md-12">
        <h1>{{ posts?.title }}</h1>
        <div>
            {{ posts?.body }}
        </div>
        <hr>
        <div class="row">
            <div class="col-md-6 col-md-push-6">
                <h3>Comments ({{ comments.length }})</h3>
                <div *ngFor="let comment of comments">
                    <strong>{{ comment.email }}</strong> said: <br>
                    <h4>{{ comment.name }}</h4>
                    <p>{{ comment.body }}</p>
                    <hr>
                </div>
            </div>


        </div>
    </div>
</div>
  `
})
export class PostComponent implements OnInit {


    comments: Array<any> = [];
    posts:any;

    constructor(
        private route: ActivatedRoute,
        private p: PostService,
        private c: CommentsService
    ) { }

    ngOnInit() {


        this.route.params.forEach((params: Params) => {
            let id = +params['id'];


            // Post
            this.p.getPost(id).subscribe(
                r => this.posts = r,
                error => console.error('Error: ' + error)
            );

            // Comments
            this.c.getCommentsByPostId(id).subscribe(
                r => this.comments = r,
                error => console.error('Error: ' + error)
            );

        });

    }

}


export interface Post {
    id: number;
    title: string;
    body: string;
}

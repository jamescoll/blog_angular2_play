import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { LoggingService } from "../../services/logging-service/logging.service"
import { LogLevel } from "../../models/loglevel"
import 'rxjs/add/operator/map';

@Injectable()
export class CommentsService {

endpoint = "http://localhost:9000/s_comments";

constructor(private http: Http, private loggingService: LoggingService) {
    this.loggingService.sendLogMessage(LogLevel.TRACE, "Client in CommentsService");
 }

    getAll() {
        return this.http.get(this.endpoint)
            .map(response => response.json());
    }

    getCommentsByPostId(id) {
        return this.http.get(this.endpoint + '?postId=' + id)
            .map(response => response.json());
    }
}
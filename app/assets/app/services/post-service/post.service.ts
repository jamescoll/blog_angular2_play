import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { LoggingService } from "../../services/logging-service/logging.service"
import { LogLevel } from "../../models/loglevel"
import 'rxjs/add/operator/map';

@Injectable()
export class PostService {

endpoint = "http://localhost:9000/s_posts";


constructor(private http: Http, private loggingService: LoggingService) { 
    this.loggingService.sendLogMessage(LogLevel.TRACE, "Client in CommentsService");
}

  getAll() {
      return this.http.get(this.endpoint)
          .map(response => response.json());
  }

  // todo there is no play method which matches this
  // fix this
 /* getPost(id) {
      return this.http.get(this.endpoint + '/' +  id)
          .map(response => response.json());
  }*/
}
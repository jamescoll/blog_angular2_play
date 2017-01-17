import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { LogLevel } from "../../models/loglevel"
import 'rxjs/add/operator/map';


@Injectable()
export class LoggingService {

endpoint = "http://localhost:9000/s_logging";

constructor(private http: Http) { 
    this.sendLogMessage(LogLevel.TRACE, "Client in LoggingService");
}

     sendLogMessage( loglevel: LogLevel, message: string ) {
        let headers = new Headers({ "Content-Type": "application/json" });
        let options = new RequestOptions({ headers: headers });
        let t = new Date().getTime();
        let body = JSON.stringify({ "t": t, "l": loglevel, "r": "BlogClient", "m": message});
        console.log(body);
        return this.http.post(this.endpoint, body, options).subscribe();
    }
}
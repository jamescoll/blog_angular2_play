import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { LoggingService } from "../../services/logging-service/logging.service"
import { LogLevel } from "../../models/loglevel"
import 'rxjs/add/operator/map';


@Injectable()
export class LoginService {

    public token: string;

    endpoint = "http://localhost:9000/s_login";

    constructor(private http: Http, private loggingService: LoggingService) { 
        this.loggingService.sendLogMessage(LogLevel.TRACE, "Client in LoginService");
    }

    login ( userInfo: string ) {
        let headers = new Headers({ "Content-Type": "application/json" });
        let options = new RequestOptions({ headers: headers });
        let body = userInfo;
        return this.http.post(this.endpoint, body, options).map((res: Response) => res.json());
    }

    logout(): void {
        this.token = null;
        localStorage.removeItem("userToken");
    }
}
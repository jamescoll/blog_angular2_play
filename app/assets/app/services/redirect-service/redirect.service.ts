import { Injectable } from "@angular/core"
import { Http, Response, Headers, RequestOptions } from "@angular/http"
import { Observable } from "rxjs/Observable"
import { Observer } from "rxjs/Observer"
import { LoggingService } from "../../services/logging-service/logging.service"
import { LogLevel } from "../../models/loglevel"
import "rxjs/add/operator/map"
import "rxjs/add/operator/catch"

@Injectable()
export class RedirectService {

    private redirectURL: string;

    constructor(private loggingService: LoggingService){
        this.loggingService.sendLogMessage(LogLevel.TRACE, "Client in RedirectService");
    }

    setRedirectUrl(redirectURL:any) {
        this.redirectURL = redirectURL;

    }

    getRedirectUrl(): string {
        return this.redirectURL;
    }

}

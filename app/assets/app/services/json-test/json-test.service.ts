import { Injectable } from "@angular/core"
import { Http, Response, Headers, RequestOptions } from "@angular/http"
import { Observable } from "rxjs/Observable"
import "rxjs/add/operator/map"
import "rxjs/add/operator/catch"

@Injectable()
export class JsonTestService {
    constructor(private http: Http) {

    }

    getJson(): Observable<Object> {
        return this.http.get("/json")
            .map(this.extractData)
            .catch(this.handleError);
    }

    sendJson() {
        let example = { name: "John Jones", done: "false", date: new Date().getTime().toString() }
        let headers = new Headers({ "Content-Type": "application/json" });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(example);
        return this.http.post("/sendJson", body, options).map((res: Response) => res.json());
    }

    private extractData(res: Response) {
        let body = res.json();
        return body || {};
    }

    private handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || "";
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ""} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

}

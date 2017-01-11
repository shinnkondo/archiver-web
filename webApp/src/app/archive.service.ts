
import { Injectable } from '@angular/core';
import { Inject } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable }     from 'rxjs/Observable';
import { Subject }     from 'rxjs/Subject';
import { $WebSocket } from 'angular2-websocket/angular2-websocket'

import 'rxjs/add/operator/map';

@Injectable()
export class ArchiveService {
    private archivalURL = '/archivable'
    private socketURL = 'ws://' + window.location.host + '/socket/archivable'
    private ws: $WebSocket

    constructor(@Inject(Http) private http: Http) {}

    postArchiveJob(url: string): Observable<string> {
        return this.http.post(this.archivalURL, {url: url}).map(this.extractData)
    }

    getArchiveJobs(): Observable<ArchiveJobStatus[]> {
        return this.http.get(this.archivalURL).map(this.extractData)
    }

    connect(): Subject<string> {
        if (!this.ws) {
            this.ws = new $WebSocket(this.socketURL);
            this.ws.send("Hello")
        }
        return this.ws.getDataStream()
    }


    private extractData(res: Response) {
        let body = res.json();
        if (body instanceof Array) {
            return body
        } else {
            return body.data
        }
    }
}

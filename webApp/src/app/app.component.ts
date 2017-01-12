import { Component, OnInit } from '@angular/core';
import { ArchiveService} from './archive.service'

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
    name = 'Angular'
    statusList: ArchiveJobStatus[]
    errorMessage: string
    data = {}
    
    constructor(private archiveService: ArchiveService) {}

    ngOnInit() {
        this.archiveService.connect().subscribe((message) => {
            this.list()
        })
        this.list()
    }

    download(url: string) {
        this.archiveService.postArchiveJob(url).subscribe((id: string) => {
            this.list()
        },(error) => {
            this.errorMessage = error
        })
    }

    list() {
        this.archiveService.getArchiveJobs().subscribe((statusList: ArchiveJobStatus[]) => {
            this.statusList = statusList
        }, (error) => {
            this.errorMessage = error
        })
    }
 }
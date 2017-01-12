import { Component, OnInit } from '@angular/core';
import { ArchiveService} from './archive.service'

@Component({
  selector: 'archivable-form',
  templateUrl: './archivable-form.component.html',
  styleUrls: ['./archivable-form.component.scss']
})
export class ArchivableFormComponent implements OnInit{
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
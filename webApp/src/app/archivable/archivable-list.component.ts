import { Component, OnInit } from '@angular/core';
import { ArchiveService} from './archive.service'

@Component({
  selector: 'archivable-list',
  templateUrl: './archivable-list.component.html',
  styleUrls: ['./archivable-list.component.scss']
})
export class ArchivableListComponent implements OnInit {
    constructor(private archiveService: ArchiveService) {}

    statusList: ArchiveJobStatus[]
    errorMessage: string

    ngOnInit() {
        this.archiveService.connect().subscribe((message) => {
            this.list()
       })
       this.list()
    }

    list() {
        this.archiveService.getArchiveJobs().subscribe((statusList: ArchiveJobStatus[]) => {
            this.statusList = statusList.reverse() // Newest first
        }, (error) => {
            this.errorMessage = error
        })
    }

}
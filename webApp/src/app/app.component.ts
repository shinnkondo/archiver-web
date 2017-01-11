import { Component } from '@angular/core';
import { ArchiveService} from './archive.service'

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html'
})
export class AppComponent {
    name = 'Angular'
    statusList: ArchiveJobStatus[]
    
    constructor(private archiveService: ArchiveService) {}

    download(url: string) {
        this.archiveService.postArchiveJob(url).subscribe()
    }

    list() {
        this.archiveService.getArchiveJobs().subscribe((statusList: ArchiveJobStatus[]) => {
            this.statusList = statusList
        })
    }
 }

import { Component } from '@angular/core';
import { ArchiveService} from './archive.service'

@Component({
  selector: 'archivable-form',
  templateUrl: './archivable-form.component.html',
  styleUrls: ['./archivable-form.component.scss']
})
export class ArchivableFormComponent {
    name = 'Angular'
    statusList: ArchiveJobStatus[]
    errorMessage: string
    data = {}
    
    constructor(private archiveService: ArchiveService) {}

    download(url: string) {
        this.archiveService.postArchiveJob(url).subscribe((id: string) => {},
        (error) => {
            this.errorMessage = error
        })
    }


 }
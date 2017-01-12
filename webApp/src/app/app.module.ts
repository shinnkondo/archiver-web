import { NgModule } from '@angular/core'
import { BrowserModule } from '@angular/platform-browser'
import { FormsModule } from '@angular/forms'
import { HttpModule, JsonpModule } from '@angular/http'

import { AppComponent } from './app.component'

import { ArchivableFormComponent } from './archivable/archivable-form.component'
import { ArchiveService} from './archivable/archive.service'

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
  ],
  declarations: [
    ArchivableFormComponent, AppComponent
  ],
  providers: [ArchiveService],
  bootstrap: [AppComponent]
})

export default class AppModule { }
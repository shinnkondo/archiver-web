require('./global.async.scss')
import 'zone.js'
import 'reflect-metadata'
import './app/app.module'
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import AppModule from './app/app.module';

export default function bootstrap() {
    platformBrowserDynamic().bootstrapModule(AppModule);
}
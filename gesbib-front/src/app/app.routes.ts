import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { GoogleApiComponent } from './google-api/google-api.component';

export const routes: Routes = [
    {
        path: '',
        component: HomeComponent
    },
    {
        path: 'google-api',
        component: GoogleApiComponent
    }
];

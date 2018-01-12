import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

import { HpLandingComponent } from './hp-landing/hp-landing.component';
import { HpQuestionsComponent } from './hp-questions/hp-questions.component';
import { HpResultsComponent } from './hp-results/hp-results.component';

const appRoutes: Routes = [
    { path: '', component: HpLandingComponent },
    /* { path: '',
        redirectTo: '/hp-start.html',
        pathMatch: 'full'
    },*/
    { path: 'questions', component: HpQuestionsComponent },
    { path: 'results', component: HpResultsComponent } ];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

import { PageComponent } from './page/page.component';
import { HpResultsComponent } from './page/hp-results/hp-results.component';

import { QuizResolver } from './services/quiz.resolver';
import { AuthGuard } from './services/auth-guard.service';
import { HealthPrintResultsResolveService} from './services/hp-results-resolve.service';

const appRoutes: Routes = [
    {
        path: '',
        component: PageComponent,
        resolve: {quizList: QuizResolver}
    },
    {
        path: 'healthprint',
        component: PageComponent,
        resolve: {quizList: QuizResolver}
    },
    {
        canActivate: [AuthGuard],
        path: 'healthprint-results',
        component: HpResultsComponent,
        resolve: {
            healthPrintResults: HealthPrintResultsResolveService
        }
    } ,
    {
        canActivateChild: [AuthGuard],
        path: 'healthprint-results/:hpID',
        component: HpResultsComponent,
        resolve: {
            healthPrintResults: HealthPrintResultsResolveService
        }
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    providers: [QuizResolver],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
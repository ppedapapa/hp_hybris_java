import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

import { PageComponent } from './page/page.component';
import { HpResultsComponent } from './page/hp-results/hp-results.component';

import { QuizResolver } from './services/quiz.resolver';
import { AuthGuard } from './services/auth-guard.service';
import { HealthPrintResultsResolver } from './services/hp-results-resolver';
import { HealthPrintBundlesResolver } from "./services/hp-bundles-resolver";

const appRoutes: Routes = [
    {
        canActivate: [AuthGuard],
        path: '',
        component: PageComponent
    },
    {
        path: 'healthprint',
        component: PageComponent,
        resolve: {quizList: QuizResolver}
    },
    {
        // canActivate: [AuthGuard],
        path: 'healthprint-results',
        component: HpResultsComponent,
        resolve: {
            healthPrintResults: HealthPrintResultsResolver
            // healthPrintBundles: HealthPrintBundlesResolver
        }
    },
    // {
    //     canActivateChild: [AuthGuard],
    //     path: 'healthprint-results/:hpID',
    //     component: HpResultsComponent,
    //     resolve: {
    //         healthPrintResults: HealthPrintResultsResolver
    //     }
    // }
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    providers: [
        QuizResolver,
        HealthPrintBundlesResolver,
        HealthPrintResultsResolver
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
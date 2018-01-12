import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule, NgOption} from '@ng-select/ng-select';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing.module';
import { AppService } from './app.service';
import { QuestionsService } from './services/questions.service';
import { HpConfigService } from './services/hp-config.service';
import { DataService } from './services/data.service';
import { HealthPrintResultsService } from './services/hp-results.service';
import { HealthPrintResultsResolveService} from './services/hp-results-resolve.service';
import { CartService } from './services/cart.service';

import { AppComponent } from './app.component';
import { PageComponent } from './page/page.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HpHeaderComponent } from './page/hp-results/hp-header/hp-header.component';
import { HpHealthScoresComponent } from './page/hp-results/hp-health-scores/hp-health-scores.component';
import { HpHealthScoreComponent } from './page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component';
import { HpQuestionsComponent } from './page/hp-questions/hp-questions.component';
import { HpResultsComponent } from './page/hp-results/hp-results.component';
import { HpNavComponent } from './page/hp-nav/hp-nav.component';
import { QuestionSelectionsComponent } from './page/hp-questions/question-selections/question-selections.component';
import { QuestionFormComponent } from './page/hp-questions/question-form/question-form.component';
import { QuestionDropdownComponent } from './page/hp-questions/question-dropdown/question-dropdown.component';
import { QuestionMixedContentComponent } from './page/hp-questions/question-mixed-content/question-mixed-content.component';
import { QuestionAgeWeightComponent } from './page/hp-questions/question-age-weight/question-age-weight.component';
import { QuestionGoalsComponent } from './page/hp-questions/question-goals/question-goals.component';
import { QuestionDietaryRestrictionsComponent } from './page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component';
import { HpAddCartComponent } from './page/hp-results/hp-add-cart/hp-add-cart.component';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/translation/hp/', '.json');
}

@NgModule({
    declarations: [
        AppComponent,
        HpQuestionsComponent,
        HpResultsComponent,
        PageComponent,
        HeaderComponent,
        FooterComponent,
        HpNavComponent,
        QuestionSelectionsComponent,
        QuestionFormComponent,
        QuestionDropdownComponent,
        QuestionMixedContentComponent,
        QuestionAgeWeightComponent,
        QuestionGoalsComponent,
        QuestionDietaryRestrictionsComponent,
        HpHeaderComponent,
        HpHealthScoresComponent,
        HpHealthScoreComponent,
        HpAddCartComponent
    ],
    imports: [
        NgbModule.forRoot(),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: (createTranslateLoader),
                deps: [HttpClient]
            }
        }),
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        NgSelectModule
    ],
    providers: [
        AppService,
        CookieService,
        QuestionsService,
        HpConfigService,
        DataService,
        HealthPrintResultsService,
        HealthPrintResultsResolveService,
        CartService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }

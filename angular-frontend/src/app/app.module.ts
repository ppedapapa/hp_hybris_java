import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule, NgOption} from '@ng-select/ng-select';
import { CookieService } from 'ngx-cookie-service';
import { ChartModule } from 'angular-highcharts';

import { MultiTranslateHttpLoader } from './MultiTranslateHttpLoader';

import { AppRoutingModule } from './app-routing.module';
import { AppService } from './app.service';
import { QuestionsService } from './services/questions.service';
import { HpConfigService } from './services/hp-config.service';
import { HealthPrintResultsService } from './services/hp-results.service';
import { CartService } from './services/cart.service';
import { AuthService } from './services/auth.service';
import { AuthGuard } from './services/auth-guard.service';
import { GoogleAnalyticsService } from './services/google-analytics.service';

import { ShortStringPipe } from './pipe/shortString';
import { FormatPricePipe } from './pipe/formatPrice';

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
import { HpGoalsComponent } from './page/hp-results/hp-goals/hp-goals.component';
import { HpBundleComponent } from './page/hp-results/hp-bundle/hp-bundle.component';
import { HpYouMayLikeComponent } from './page/hp-results/hp-you-may-like/hp-you-may-like.component';
import { HpSkuListComponent } from './page/hp-results/hp-sku-list/hp-sku-list.component';
import { HpKidsComponent } from './page/hp-results/hp-kids/hp-kids.component';
import { HpGetCleanComponent } from './page/hp-results/hp-get-clean/hp-get-clean.component';
import { HpRecapComponent } from './page/hp-results/hp-recap/hp-recap.component';
import { HpResultsModalComponent } from './page/hp-results/hp-results-modal/hp-results-modal.component';
import { HpDietRestrictionsComponent } from './page/hp-results/hp-diet-restrictions/hp-diet-restrictions.component';

// AoT requires an exported function for factories
/* export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/translation/', '.json');
} */

export function translateLoader(http: HttpClient) {

    return new MultiTranslateHttpLoader(http, [
        {prefix: './assets/translation/app/', suffix: '.json'},
        {prefix: './assets/translation/hp/', suffix: '.json'},
        {prefix: './assets/translation/hp/content/', suffix: '.json'},
    ]);
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
        HpAddCartComponent,
        HpGoalsComponent,
        HpBundleComponent,
        ShortStringPipe,
        FormatPricePipe,
        HpYouMayLikeComponent,
        HpSkuListComponent,
        HpKidsComponent,
        HpGetCleanComponent,
        HpRecapComponent,
        HpResultsModalComponent,
        HpDietRestrictionsComponent
    ],
    entryComponents: [
        HpResultsModalComponent
    ],
    imports: [
        NgbModule.forRoot(),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: (translateLoader),
                deps: [HttpClient]
            }
        }),
        BrowserAnimationsModule,
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        NgSelectModule,
        ChartModule
    ],
    providers: [
        AppService,
        CookieService,
        QuestionsService,
        HpConfigService,
        HealthPrintResultsService,
        CartService,
        AuthService,
        AuthGuard,
        GoogleAnalyticsService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }

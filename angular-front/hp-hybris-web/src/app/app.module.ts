import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';


import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { HpLandingComponent } from './hp-landing/hp-landing.component';
import { HpQuestionsComponent } from './hp-questions/hp-questions.component';
import { HpResultsComponent } from './hp-results/hp-results.component';
import { AppRoutingModule } from './app-routing.module';
import { AppService } from './app.service';
import { QuestionComponent } from './hp-questions/question/question.component';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/translation/hp/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HpLandingComponent,
    HpQuestionsComponent,
    HpResultsComponent,
    QuestionComponent
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
    AppRoutingModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }

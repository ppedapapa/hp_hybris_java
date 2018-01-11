webpackJsonp(["main"],{

/***/ "../../../../../src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app-routing.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppRoutingModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__page_page_component__ = __webpack_require__("../../../../../src/app/page/page.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__page_hp_results_hp_results_component__ = __webpack_require__("../../../../../src/app/page/hp-results/hp-results.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_quiz_resolver__ = __webpack_require__("../../../../../src/app/services/quiz.resolver.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_hp_results_resolve_service__ = __webpack_require__("../../../../../src/app/services/hp-results-resolve.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






var appRoutes = [
    {
        path: '',
        component: __WEBPACK_IMPORTED_MODULE_2__page_page_component__["a" /* PageComponent */],
        resolve: { quizList: __WEBPACK_IMPORTED_MODULE_4__services_quiz_resolver__["a" /* QuizResolver */] }
    },
    /* { path: '',
        redirectTo: '/hp-start.html',
        pathMatch: 'full'
    },
    {
        path: 'questions',
        component: HpQuestionsComponent
    },*/
    {
        path: 'results',
        component: __WEBPACK_IMPORTED_MODULE_3__page_hp_results_hp_results_component__["a" /* HpResultsComponent */],
        resolve: {
            healthPrintResults: __WEBPACK_IMPORTED_MODULE_5__services_hp_results_resolve_service__["a" /* HealthPrintResultsResolveService */]
        }
    }
];
var AppRoutingModule = (function () {
    function AppRoutingModule() {
    }
    AppRoutingModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["J" /* NgModule */])({
            imports: [
                __WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* RouterModule */].forRoot(appRoutes)
            ],
            providers: [__WEBPACK_IMPORTED_MODULE_4__services_quiz_resolver__["a" /* QuizResolver */]],
            exports: [__WEBPACK_IMPORTED_MODULE_1__angular_router__["c" /* RouterModule */]]
        })
    ], AppRoutingModule);
    return AppRoutingModule;
}());



/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"container hp\">\n  <!--div class=\"row\">\n    <div class=\"col-9\">\n      <nav class=\"navbar navbar-light bg-faded\">\n        <ul class=\"nav nav-pills\">\n          <li class=\"nav-item\"><a routerLinkActive=\"active\" [routerLinkActiveOptions]=\"{exact: true}\" class=\"nav-link\" routerLink=\"/\">Landing</a></li>\n          <li class=\"nav-item\"><a routerLinkActive=\"active\" class=\"nav-link\" routerLink=\"/results\">Results</a></li>\n        </ul>\n      </nav>\n    </div>\n    <div class=\"col\">\n      <div ngbDropdown class=\"d-inline-block\">\n        <button class=\"btn btn-outline-primary\" id=\"dropdownBasic1\" ngbDropdownToggle>Select Language</button>\n        <div ngbDropdownMenu aria-labelledby=\"dropdownBasic1\">\n          <button class=\"dropdown-item\" (click)=\"switchLanguage('us-en')\">English</button>\n          <button class=\"dropdown-item\" (click)=\"switchLanguage('jp-ja')\">Japanese</button>\n        </div>\n      </div>\n    </div>\n  </div-->\n  <router-outlet></router-outlet>\n</div>"

/***/ }),

/***/ "../../../../../src/app/app.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__ngx_translate_core__ = __webpack_require__("../../../../@ngx-translate/core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__app_service__ = __webpack_require__("../../../../../src/app/app.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var AppComponent = (function () {
    function AppComponent(translate, serverService) {
        this.translate = translate;
        this.serverService = serverService;
        this.appGreetings = this.serverService.getGreetings();
        translate.setDefaultLang('us-en');
    }
    AppComponent.prototype.switchLanguage = function (language) {
        this.translate.use(language);
    };
    AppComponent.prototype.ngOnInit = function () {
    };
    AppComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__("../../../../../src/app/app.component.html"),
            styles: [__webpack_require__("../../../../../src/app/app.component.scss")],
            providers: [__WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */]]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__ngx_translate_core__["c" /* TranslateService */],
            __WEBPACK_IMPORTED_MODULE_3__app_service__["a" /* AppService */]])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export createTranslateLoader */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_forms__ = __webpack_require__("../../../forms/esm5/forms.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__ngx_translate_core__ = __webpack_require__("../../../../@ngx-translate/core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__ngx_translate_http_loader__ = __webpack_require__("../../../../@ngx-translate/http-loader/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__ng_bootstrap_ng_bootstrap__ = __webpack_require__("../../../../@ng-bootstrap/ng-bootstrap/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__ng_select_ng_select__ = __webpack_require__("../../../../@ng-select/ng-select/@ng-select/ng-select.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8_ngx_cookie_service__ = __webpack_require__("../../../../ngx-cookie-service/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__app_routing_module__ = __webpack_require__("../../../../../src/app/app-routing.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__app_service__ = __webpack_require__("../../../../../src/app/app.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__services_hp_config_service__ = __webpack_require__("../../../../../src/app/services/hp-config.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__services_data_service__ = __webpack_require__("../../../../../src/app/services/data.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__services_hp_results_service__ = __webpack_require__("../../../../../src/app/services/hp-results.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__services_hp_results_resolve_service__ = __webpack_require__("../../../../../src/app/services/hp-results-resolve.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__page_page_component__ = __webpack_require__("../../../../../src/app/page/page.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__header_header_component__ = __webpack_require__("../../../../../src/app/header/header.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__footer_footer_component__ = __webpack_require__("../../../../../src/app/footer/footer.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__page_hp_results_hp_header_hp_header_component__ = __webpack_require__("../../../../../src/app/page/hp-results/hp-header/hp-header.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_21__page_hp_results_hp_health_scores_hp_health_scores_component__ = __webpack_require__("../../../../../src/app/page/hp-results/hp-health-scores/hp-health-scores.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_22__page_hp_results_hp_health_scores_hp_health_score_hp_health_score_component__ = __webpack_require__("../../../../../src/app/page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_23__page_hp_questions_hp_questions_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/hp-questions.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_24__page_hp_results_hp_results_component__ = __webpack_require__("../../../../../src/app/page/hp-results/hp-results.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_25__page_hp_nav_hp_nav_component__ = __webpack_require__("../../../../../src/app/page/hp-nav/hp-nav.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_26__page_hp_questions_question_selections_question_selections_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-selections/question-selections.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_27__page_hp_questions_question_form_question_form_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-form/question-form.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_28__page_hp_questions_question_dropdown_question_dropdown_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-dropdown/question-dropdown.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_29__page_hp_questions_question_mixed_content_question_mixed_content_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-mixed-content/question-mixed-content.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_30__page_hp_questions_question_age_weight_question_age_weight_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-age-weight/question-age-weight.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_31__page_hp_questions_question_goals_question_goals_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-goals/question-goals.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_32__page_hp_questions_question_dietary_restrictions_question_dietary_restrictions_component__ = __webpack_require__("../../../../../src/app/page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_33__page_hp_results_hp_add_cart_hp_add_cart_component__ = __webpack_require__("../../../../../src/app/page/hp-results/hp-add-cart/hp-add-cart.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};


































// AoT requires an exported function for factories
function createTranslateLoader(http) {
    return new __WEBPACK_IMPORTED_MODULE_5__ngx_translate_http_loader__["a" /* TranslateHttpLoader */](http, './assets/translation/hp/', '.json');
}
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_2__angular_core__["J" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_16__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_23__page_hp_questions_hp_questions_component__["a" /* HpQuestionsComponent */],
                __WEBPACK_IMPORTED_MODULE_24__page_hp_results_hp_results_component__["a" /* HpResultsComponent */],
                __WEBPACK_IMPORTED_MODULE_17__page_page_component__["a" /* PageComponent */],
                __WEBPACK_IMPORTED_MODULE_18__header_header_component__["a" /* HeaderComponent */],
                __WEBPACK_IMPORTED_MODULE_19__footer_footer_component__["a" /* FooterComponent */],
                __WEBPACK_IMPORTED_MODULE_25__page_hp_nav_hp_nav_component__["a" /* HpNavComponent */],
                __WEBPACK_IMPORTED_MODULE_26__page_hp_questions_question_selections_question_selections_component__["a" /* QuestionSelectionsComponent */],
                __WEBPACK_IMPORTED_MODULE_27__page_hp_questions_question_form_question_form_component__["a" /* QuestionFormComponent */],
                __WEBPACK_IMPORTED_MODULE_28__page_hp_questions_question_dropdown_question_dropdown_component__["a" /* QuestionDropdownComponent */],
                __WEBPACK_IMPORTED_MODULE_29__page_hp_questions_question_mixed_content_question_mixed_content_component__["a" /* QuestionMixedContentComponent */],
                __WEBPACK_IMPORTED_MODULE_30__page_hp_questions_question_age_weight_question_age_weight_component__["a" /* QuestionAgeWeightComponent */],
                __WEBPACK_IMPORTED_MODULE_31__page_hp_questions_question_goals_question_goals_component__["a" /* QuestionGoalsComponent */],
                __WEBPACK_IMPORTED_MODULE_32__page_hp_questions_question_dietary_restrictions_question_dietary_restrictions_component__["a" /* QuestionDietaryRestrictionsComponent */],
                __WEBPACK_IMPORTED_MODULE_20__page_hp_results_hp_header_hp_header_component__["a" /* HpHeaderComponent */],
                __WEBPACK_IMPORTED_MODULE_21__page_hp_results_hp_health_scores_hp_health_scores_component__["a" /* HpHealthScoresComponent */],
                __WEBPACK_IMPORTED_MODULE_22__page_hp_results_hp_health_scores_hp_health_score_hp_health_score_component__["a" /* HpHealthScoreComponent */],
                __WEBPACK_IMPORTED_MODULE_33__page_hp_results_hp_add_cart_hp_add_cart_component__["a" /* HpAddCartComponent */]
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_6__ng_bootstrap_ng_bootstrap__["a" /* NgbModule */].forRoot(),
                __WEBPACK_IMPORTED_MODULE_4__ngx_translate_core__["b" /* TranslateModule */].forRoot({
                    loader: {
                        provide: __WEBPACK_IMPORTED_MODULE_4__ngx_translate_core__["a" /* TranslateLoader */],
                        useFactory: (createTranslateLoader),
                        deps: [__WEBPACK_IMPORTED_MODULE_3__angular_common_http__["a" /* HttpClient */]]
                    }
                }),
                __WEBPACK_IMPORTED_MODULE_3__angular_common_http__["b" /* HttpClientModule */],
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_9__app_routing_module__["a" /* AppRoutingModule */],
                __WEBPACK_IMPORTED_MODULE_1__angular_forms__["a" /* FormsModule */],
                __WEBPACK_IMPORTED_MODULE_7__ng_select_ng_select__["a" /* NgSelectModule */]
            ],
            providers: [
                __WEBPACK_IMPORTED_MODULE_10__app_service__["a" /* AppService */],
                __WEBPACK_IMPORTED_MODULE_8_ngx_cookie_service__["a" /* CookieService */],
                __WEBPACK_IMPORTED_MODULE_11__services_questions_service__["a" /* QuestionsService */],
                __WEBPACK_IMPORTED_MODULE_12__services_hp_config_service__["a" /* HpConfigService */],
                __WEBPACK_IMPORTED_MODULE_13__services_data_service__["a" /* DataService */],
                __WEBPACK_IMPORTED_MODULE_14__services_hp_results_service__["a" /* HealthPrintResultsService */],
                __WEBPACK_IMPORTED_MODULE_15__services_hp_results_resolve_service__["a" /* HealthPrintResultsResolveService */]
            ],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_16__app_component__["a" /* AppComponent */]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "../../../../../src/app/app.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var AppService = (function () {
    function AppService(http) {
        this.http = http;
    }
    AppService.prototype.getGreetings = function () {
        var headers = new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["c" /* HttpHeaders */]();
        headers.append('Access-Control-Allow-Headers', 'Content-Type');
        headers.append('Access-Control-Allow-Methods', 'GET');
        headers.append('Access-Control-Allow-Origin', '*');
        headers.append('Content-Type', 'application/json; charset=utf-8');
        return this.http.get('/public/hp/testUserId', { headers: headers })
            .subscribe(function (data) {
            console.log(data);
            return data;
        });
    };
    AppService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], AppService);
    return AppService;
}());



/***/ }),

/***/ "../../../../../src/app/footer/footer.component.html":
/***/ (function(module, exports) {

module.exports = "<p>\n  footer works!\n</p>\n"

/***/ }),

/***/ "../../../../../src/app/footer/footer.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/footer/footer.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return FooterComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var FooterComponent = (function () {
    function FooterComponent() {
    }
    FooterComponent.prototype.ngOnInit = function () {
    };
    FooterComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-footer',
            template: __webpack_require__("../../../../../src/app/footer/footer.component.html"),
            styles: [__webpack_require__("../../../../../src/app/footer/footer.component.scss")]
        }),
        __metadata("design:paramtypes", [])
    ], FooterComponent);
    return FooterComponent;
}());



/***/ }),

/***/ "../../../../../src/app/header/header.component.html":
/***/ (function(module, exports) {

module.exports = "<p>\n  header works!\n</p>\n"

/***/ }),

/***/ "../../../../../src/app/header/header.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/header/header.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HeaderComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HeaderComponent = (function () {
    function HeaderComponent() {
    }
    HeaderComponent.prototype.ngOnInit = function () {
    };
    HeaderComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-header',
            template: __webpack_require__("../../../../../src/app/header/header.component.html"),
            styles: [__webpack_require__("../../../../../src/app/header/header.component.scss")]
        }),
        __metadata("design:paramtypes", [])
    ], HeaderComponent);
    return HeaderComponent;
}());



/***/ }),

/***/ "../../../../../src/app/models/question.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Question; });
var Question = (function () {
    function Question(id, image, name, label, questionTypeId, options) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.label = label;
        this.questionTypeId = questionTypeId;
        this.options = options;
    }
    return Question;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-nav/hp-nav.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"row buttons hq-nav-container\" *ngIf=\"pager.index !== 0\">\n  <div class=\"col-xs-5 col-sm-3 col-md-3 col-lg-3\">\n    <button class=\"btn btn-primary hq-nav-btn continue-btn\" *ngIf=\"config.allowBack\" (click)=\"goTo(pager.index -1);\" [ngClass]=\"{'continue-answered-btn':pager.index >= 1 && pager.index < pager.count}\">\n      <i class=\"fa fa-chevron-circle-left\"></i>\n      <span>{{'questions.back' |translate}}</span>\n    </button>\n  </div>\n  <div class=\"col-xs-2 col-sm-6 col-md-6 col-lg-6\">\n    <ol class=\"hq-indicators\">\n      <li *ngFor=\"let q of pages; let i = index;\" [ngClass]=\"{'active': pager.index === i}\">\n        <span class=\"sr-only\"></span>\n      </li>\n    </ol>\n  </div>\n  <div class=\"col-xs-5 col-sm-3 col-md-3 col-lg-3\">\n    <button *ngIf=\"pager.index + 1 !== pager.count\" class=\"btn btn-primary hq-nav-btn continue-btn\" [ngClass]=\"{'continue-answered-btn':pager.index < pager.count}\"  (click)=\"goTo(pager.index + 1);\">\n      <span>{{'questions.continue' |translate}}</span>\n      <i class=\"fa fa-chevron-circle-right\"></i>\n    </button>\n    <button *ngIf=\"pager.index + 1 === pager.count\"  class=\"btn btn-primary hq-nav-btn continue-btn\" [ngClass]=\"{'continue-answered-btn':pager.index + 1 === pager.count}\" (click)=\"onSubmit();\">\n      <span>{{'questions.get-results' |translate}}</span>\n      <i class=\"fa fa-chevron-circle-right\"></i>\n    </button>\n  </div>\n  <div class=\"col-12 nav-disclaimer\">{{'questions.disclaimer'|translate}}</div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-nav/hp-nav.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".hq-nav-container {\n  margin: 20px 0;\n  padding: 0; }\n  .hq-nav-container .hq-nav-btn {\n    padding: 10px 15px;\n    background: #f5f5f5;\n    width: 100%;\n    color: #ccc;\n    font-size: 12.8px;\n    line-height: 2em;\n    min-height: 3em;\n    text-align: center;\n    border-radius: 2px;\n    white-space: normal !important;\n    word-wrap: break-word;\n    border-color: #ccc;\n    pointer-events: none; }\n    .hq-nav-container .hq-nav-btn i {\n      border: 1px solid #6dab3c;\n      border-radius: 50%;\n      margin-right: 20px;\n      padding: 3px;\n      display: inline-block; }\n  .hq-nav-container .continue-btn i {\n    border: 1px solid #ccc;\n    margin-left: 20px; }\n  .hq-nav-container .continue-answered-btn {\n    background-color: #679f39;\n    color: #fff;\n    pointer-events: auto; }\n    .hq-nav-container .continue-answered-btn i {\n      border: 1px solid #FFF; }\n  .hq-nav-container .lang-progress-indicator {\n    width: 43%; }\n  .hq-nav-container .lang-next-question-nav {\n    width: 32%; }\n  .hq-nav-container .hq-indicators {\n    width: 100%;\n    position: absolute;\n    bottom: 10px;\n    z-index: 15;\n    padding-left: 0;\n    list-style: none;\n    text-align: center;\n    margin: 0; }\n    .hq-nav-container .hq-indicators li {\n      background: #CCC;\n      margin: 1px 5px;\n      cursor: default;\n      display: inline-block;\n      width: 10px;\n      height: 10px;\n      text-indent: -999px;\n      border: 1px solid #f2f2f2;\n      border-radius: 10px; }\n    .hq-nav-container .hq-indicators .active {\n      background-color: #679f39;\n      border-color: #CCC;\n      margin: 1px 5px;\n      width: 12px;\n      height: 12px; }\n    .hq-nav-container .hq-indicators .sr-only {\n      position: absolute;\n      width: 1px;\n      height: 1px;\n      margin: -1px;\n      padding: 0;\n      overflow: hidden;\n      clip: rect(0, 0, 0, 0);\n      border: 0; }\n  .hq-nav-container.guest-results-btn {\n    padding: 0 15px 0 35px;\n    margin: 10px 0 0; }\n    .hq-nav-container.guest-results-btn .hq-nav-btn {\n      line-height: 4em;\n      padding: 12px 15px; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-nav/hp-nav.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpNavComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_hp_config_service__ = __webpack_require__("../../../../../src/app/services/hp-config.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var HpNavComponent = (function () {
    function HpNavComponent(questionsService, hpconfigService) {
        this.questionsService = questionsService;
        this.hpconfigService = hpconfigService;
    }
    HpNavComponent.prototype.ngOnInit = function () {
        this.pager = this.hpconfigService.getPager();
        this.config = this.hpconfigService.getConfig();
        this.pages = this.questionsService.getPages();
    };
    HpNavComponent.prototype.goTo = function (index) {
        this.questionsService.goTo(index);
    };
    HpNavComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-nav',
            template: __webpack_require__("../../../../../src/app/page/hp-nav/hp-nav.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-nav/hp-nav.component.scss")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_questions_service__["a" /* QuestionsService */],
            __WEBPACK_IMPORTED_MODULE_2__services_hp_config_service__["a" /* HpConfigService */]])
    ], HpNavComponent);
    return HpNavComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/hp-questions.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"questions\">\n  <div class=\"row hp-banner\" *ngIf=\"pager.index !== 0\">\n    <div class=\"col-2\">\n      <img src=\"https://images.shaklee.com/healthprint/Healthprint-Logo.png\" class=\"img-fluid\">\n    </div>\n    <div class=\"col-8\">\n      <h1>Your Personalized Health Builder</h1>\n    </div>\n  </div>\n\n  <div class=\"question\" *ngFor=\"let page of pages;\">\n    <app-question-mixed-content [questions]=\"page.questions\" *ngIf=\"page.component === 'question-mixed-content'\" class=\"row\"></app-question-mixed-content>\n    <app-question-selections [questions]=\"page.questions\" *ngIf=\"page.component === 'question-selections'\" class=\"row justify-content-center\"></app-question-selections>\n    <app-question-age-weight [questions]=\"page.questions\" *ngIf=\"page.component === 'question-age-weight'\" class=\"row\"></app-question-age-weight>\n    <app-question-goals [questions]=\"page.questions\" *ngIf=\"page.component === 'question-goals'\" class=\"row justify-content-center\"></app-question-goals>\n    <app-question-dietary-restrictions [questions]=\"page.questions\" *ngIf=\"page.component === 'question-dietary-restrictions'\" class=\"row justify-content-center\"></app-question-dietary-restrictions>\n    <app-question-form [questions]=\"page.questions\" *ngIf=\"page.component === 'question-form'\" class=\"row justify-content-center\"></app-question-form>\n  </div>\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/hp-questions.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".hp {\n  color: #1f1f1f;\n  font-family: \"Open Sans\", Helvetica, Arial, Verdana, sans-serif;\n  font-size: 15.5px;\n  line-height: 1.6em; }\n  .hp .questions {\n    margin: 30px 0; }\n    .hp .questions .hp-banner {\n      overflow: auto;\n      border: 2px solid #dbdbdb;\n      padding: 30px;\n      text-align: left;\n      font: 44px/90px \"Open Sans\", Helvetica, Arial, Verdana, sans-serif;\n      color: #5c993d;\n      font-weight: 300; }\n      .hp .questions .hp-banner .img-banner-placeholder {\n        text-align: right; }\n        .hp .questions .hp-banner .img-banner-placeholder img {\n          width: 100px;\n          height: auto; }\n      .hp .questions .hp-banner span {\n        font-weight: 300; }\n    .hp .questions .question {\n      margin: 40px 0; }\n      .hp .questions .question .question-text {\n        text-align: center;\n        height: 3.5em;\n        font-weight: 300;\n        color: #333;\n        font-size: 22px;\n        font-family: \"Open Sans\", Helvetica, Arial, Verdana, sans-serif; }\n    .hp .questions .continue-answered-btn {\n      background-color: #679f39;\n      color: #fff;\n      pointer-events: auto; }\n    .hp .questions .hq-nav-btn {\n      padding: 10px 15px;\n      background: #f5f5f5;\n      width: 100%;\n      color: #ccc;\n      font-size: 12.8px;\n      line-height: 2em;\n      min-height: 3em;\n      text-align: center;\n      border-radius: 2px;\n      white-space: normal !important;\n      word-wrap: break-word;\n      border-color: #ccc;\n      pointer-events: none;\n      text-transform: uppercase; }\n  .hp .nav-disclaimer {\n    font-size: 15px;\n    line-height: 1em;\n    padding: 3px 2em;\n    margin: 20px 0; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/hp-questions.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpQuestionsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_hp_config_service__ = __webpack_require__("../../../../../src/app/services/hp-config.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var HpQuestionsComponent = (function () {
    function HpQuestionsComponent(questionsService, hpconfigService) {
        this.questionsService = questionsService;
        this.hpconfigService = hpconfigService;
    }
    HpQuestionsComponent.prototype.ngOnInit = function () {
        this.pager = this.hpconfigService.getPager();
    };
    Object.defineProperty(HpQuestionsComponent.prototype, "pages", {
        get: function () {
            var pages = this.questionsService.getPages();
            return (pages) ? pages.slice(this.pager.index, this.pager.index + 1) : [];
        },
        enumerable: true,
        configurable: true
    });
    HpQuestionsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-questions',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/hp-questions.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/hp-questions.component.scss")],
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* ViewEncapsulation */].None
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */],
            __WEBPACK_IMPORTED_MODULE_1__services_hp_config_service__["a" /* HpConfigService */]])
    ], HpQuestionsComponent);
    return HpQuestionsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-age-weight/question-age-weight.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"question-content col-5\" *ngFor=\"let question of questions;\">\n  <div class=\"text-center\">\n    <img class=\"img-fluid question-img\" src=\"{{question.image}}\">\n    <p class=\"question-text\"><span [innerHTML]=\"question.label\"></span></p>\n  </div>\n  <div class=\"options\" *ngFor=\"let option of question.options\">\n    <input type=\"text\" placeholder=\"{{ ('questions.' +option.name+ '-placeholder') | translate}}\" value=\"{{answered[option.name]}}\" class=\"form-control\" *ngIf=\"option.name !== 'height_inches'\" (change)=\"inputChange(option.name, $event)\"/>\n    <div *ngIf=\"option.name === 'height_inches'\" class=\"row\">\n      <app-question-dropdown [dropdown]=\"height_feet\" firstLabel=\"height-ft\" label=\"foot\" type=\"height_inches\" class=\"col-6\"></app-question-dropdown>\n      <app-question-dropdown [dropdown]=\"height_inches\" firstLabel=\"height-in\" label=\"inches\" type=\"height_inches\" class=\"col-6\"></app-question-dropdown>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-age-weight/question-age-weight.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".question-content {\n  border: none;\n  border-right: 1px solid #cccccc; }\n  .question-content:last-child {\n    border: none; }\n  .question-content .question-img {\n    width: 120px;\n    height: auto; }\n  .question-content .question-text {\n    text-align: center;\n    height: 3.5em;\n    font-weight: 400;\n    font-size: 20px;\n    color: #1f1f1f;\n    margin: 20px 10px 15px;\n    font-family: \"Open Sans\", Helvetica, Arial, Verdana, sans-serif; }\n  .question-content input {\n    width: 100%;\n    border: 1px solid #aaa;\n    padding: 15px;\n    line-height: 25px;\n    text-align: center;\n    font-size: 15px;\n    margin: 5px 0 0;\n    border-radius: 2px; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-age-weight/question-age-weight.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionAgeWeightComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var QuestionAgeWeightComponent = (function () {
    function QuestionAgeWeightComponent(questionsService) {
        this.questionsService = questionsService;
        this.height_feet = [3, 4, 5, 6, 7];
        this.height_inches = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];
    }
    QuestionAgeWeightComponent.prototype.ngOnInit = function () {
        this.answered = this.questionsService.getAnswered();
    };
    QuestionAgeWeightComponent.prototype.inputChange = function (name, event) {
        var val = event.target.value;
        this.questionsService.setInput(name, val);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionAgeWeightComponent.prototype, "questions", void 0);
    QuestionAgeWeightComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-age-weight',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-age-weight/question-age-weight.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-age-weight/question-age-weight.component.scss")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_questions_service__["a" /* QuestionsService */]])
    ], QuestionAgeWeightComponent);
    return QuestionAgeWeightComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"question-content col-8\" *ngFor=\"let question of questions;\">\n  <div class=\"text-center\">\n    <img class=\"img-fluid question-img\" src=\"{{question.image}}\">\n    <p class=\"question-text\"><span [innerHTML]=\"question.label\"></span></p>\n  </div>\n  <div class=\"row\">\n    <div class=\"col-12 col-md-6 col-lg-6\" *ngFor=\"let option of question.options\">\n      <div class=\"checkbox-selection-option\">\n        <input type=\"checkbox\" [id]=\"option\" (click)=\"setValue(option, $event)\" [checked]=\"checked.includes(option)\"/>\n        <label [for]=\"option\"></label>\n        <span>{{'label.dietary-restrictions-'+option|lowercase|translate}}</span>\n      </div>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".checkbox-selection-option {\n  border: 1px solid #aaa;\n  margin: 10px 0;\n  padding: 18px 10px;\n  border-radius: 2px; }\n  .checkbox-selection-option input[type=\"checkbox\"] {\n    display: none; }\n  .checkbox-selection-option input[type=checkbox] + label {\n    float: left;\n    position: relative;\n    background: #fff;\n    height: 22px;\n    width: 22px;\n    display: inline-block;\n    border-radius: 1px;\n    transition: box-shadow .4s, border .4s;\n    border: solid 1px #5d9732;\n    cursor: pointer;\n    margin: 0 10px 0 0; }\n  .checkbox-selection-option input[type=checkbox] + label:hover, .checkbox-selection-option input[type=checkbox]:checked + label {\n    border: solid 2px #5d9732;\n    box-shadow: 0 0 1px #5d9732; }\n  .checkbox-selection-option input[type=checkbox]:checked + label:after {\n    content: '\\2713';\n    height: 1em;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 0;\n    right: 0;\n    margin: auto;\n    color: #5d9732;\n    line-height: 1;\n    font-size: 15px;\n    text-align: center; }\n  .checkbox-selection-option:hover {\n    background-color: #5d9732;\n    color: #fff; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionDietaryRestrictionsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var QuestionDietaryRestrictionsComponent = (function () {
    function QuestionDietaryRestrictionsComponent(questionsService) {
        this.questionsService = questionsService;
        this.checked = [];
    }
    QuestionDietaryRestrictionsComponent.prototype.ngOnInit = function () {
    };
    QuestionDietaryRestrictionsComponent.prototype.setValue = function (val, event) {
        var noneIndex = this.checked.indexOf('NONE');
        var index = this.checked.indexOf(val);
        if (event.target.checked) {
            if (val !== 'NONE') {
                if (noneIndex !== -1) {
                    this.checked.splice(noneIndex, 1);
                }
                if (index === -1) {
                    this.checked.push(val);
                }
            }
            if (val === 'NONE' && index === -1) {
                this.checked = [];
                this.checked.push(val);
            }
        }
        else if (index !== -1) {
            this.checked.splice(index, 1);
        }
        console.log(this.checked, index);
        this.questionsService.setInput('dietary_restrictions', this.checked);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionDietaryRestrictionsComponent.prototype, "questions", void 0);
    QuestionDietaryRestrictionsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-dietary-restrictions',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-dietary-restrictions/question-dietary-restrictions.component.scss")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_questions_service__["a" /* QuestionsService */]])
    ], QuestionDietaryRestrictionsComponent);
    return QuestionDietaryRestrictionsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-dropdown/question-dropdown.component.html":
/***/ (function(module, exports) {

module.exports = "<div ngbDropdown class=\"d-inline-block btn-group btn-group-select-option\">\n  <button class=\"btn btn-outline-primary btn-select-option\" id=\"dropdownBasic1\" ngbDropdownToggle [innerHTML]=\"selectedOption\"></button>\n  <div ngbDropdownMenu aria-labelledby=\"dropdownBasic1\">\n    <button class=\"dropdown-item\" *ngFor=\"let option of dropdown\" (click)=\"setValue(label, option, type)\" [innerHTML]=\"currentDropdown[option]\"></button>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-dropdown/question-dropdown.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".btn-group-select-option {\n  width: 100%; }\n\n.btn-group, .btn-group-vertical {\n  position: relative;\n  display: inline-block;\n  vertical-align: middle; }\n\n.btn-group-select-option .btn-select-option {\n  color: #333;\n  border: 1px solid #aaa;\n  border-radius: 2px;\n  font-size: 14px;\n  line-height: 1.4em;\n  margin: 15px 0 0;\n  min-height: 4.1em;\n  width: 100%;\n  background: #fff;\n  padding: 1em 2em 1em .75em;\n  text-align: left;\n  text-transform: none; }\n\n.btn-group > .btn:first-child {\n  margin-left: 0; }\n\n.btn-group > .btn, .btn-group-vertical > .btn {\n  position: relative;\n  float: left; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-dropdown/question-dropdown.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionDropdownComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__ngx_translate_core__ = __webpack_require__("../../../../@ngx-translate/core/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var QuestionDropdownComponent = (function () {
    function QuestionDropdownComponent(translate, questionsService) {
        this.translate = translate;
        this.questionsService = questionsService;
        this.currentDropdown = {};
    }
    QuestionDropdownComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.translate.get('label.' + this.firstLabel).subscribe(function (res) {
            _this.selectedOption = res;
        });
        this.dropdown.forEach(function (item) {
            if (_this.fromComponent === 'goal') {
                _this.translate.get('label.' + _this.label + item.toLowerCase()).subscribe(function (res) {
                    _this.currentDropdown[item] = res;
                });
            }
            else {
                _this.translate.get('label.' + _this.label).subscribe(function (res) {
                    _this.currentDropdown[item] = item + ' ' + res;
                });
            }
        });
    };
    QuestionDropdownComponent.prototype.setValue = function (label, val, type) {
        var _this = this;
        console.log(label, val, type);
        if (this.fromComponent === 'goal') {
            this.translate.get('label.' + label + val.toLowerCase()).subscribe(function (res) {
                _this.selectedOption = res;
                _this.questionsService.setGoalDropdown(label, val);
            });
        }
        else {
            this.translate.get('label.' + label).subscribe(function (res) {
                _this.selectedOption = val + ' ' + res;
                _this.questionsService.setDropdown(label, val, type);
            });
        }
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionDropdownComponent.prototype, "dropdown", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionDropdownComponent.prototype, "firstLabel", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionDropdownComponent.prototype, "label", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionDropdownComponent.prototype, "fromComponent", void 0);
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionDropdownComponent.prototype, "type", void 0);
    QuestionDropdownComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-dropdown',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-dropdown/question-dropdown.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-dropdown/question-dropdown.component.scss")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__ngx_translate_core__["c" /* TranslateService */],
            __WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */]])
    ], QuestionDropdownComponent);
    return QuestionDropdownComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-form/question-form.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"question-content col-8 offset-2\" *ngFor=\"let question of questions;\">\n    <h4 class=\"text-center\">Thank You for completing your Shaklee HealthPrint!</h4>\n    <p class=\"text-center\" [innerHTML]=\"question.label\"></p>\n    <div class=\"row\">\n      <div class=\"col-12 options\" *ngFor=\"let option of question.fields\" [ngClass]=\"{'col-md-6 col-lg-6':option === 'first-name'||option === 'last-name'}\">\n          <input *ngIf=\"option === 'first-name'\" type=\"text\" placeholder=\"{{ ('label.'+option) | translate}}\" value=\"\" class=\"form-control\" (change)=\"inputChange(option, $event)\"/>\n          <input *ngIf=\"option === 'last-name'\" type=\"text\" placeholder=\"{{ ('label.'+option) | translate}}\" value=\"\" class=\"form-control\" (change)=\"inputChange(option, $event)\"/>\n          <input *ngIf=\"option === 'email'\" type=\"text\" placeholder=\"{{ ('label.'+option) | translate}}\" value=\"\" class=\"form-control\" (change)=\"inputChange(option, $event)\"/>\n      </div>\n    </div>\n    <div class=\"row btn-container\">\n        <div class=\"col-xs-12 col-sm-12 col-md-6 col-lg-6 guest-results-btn\">\n          <button class=\"btn btn-primary hq-nav-btn continue-btn continue-answered-btn\" ng-click=\"nextQuestion()\">\n            <span>Get Results</span>\n            <i class=\"fa fa-chevron-circle-right\"></i>\n          </button>\n        </div>\n    </div>\n    <div class=\"disclaimer\">\n        <p class=\"get-results-info\" [innerHtml]=\"'questions.email-disclaimer-3'|translate\"></p>\n        <button class=\"btn btn-primary hq-nav-btn continue-btn continue-answered-btn ng-binding\" type=\"submit\" (click)=\"trackGuestEvent()\">\n            {{'questions.member-login-view-results'|translate}}\n        </button>\n    </div>\n</div>\n<div class=\"col-2\"></div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-form/question-form.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".question h4 {\n  color: #1f1f1f !important;\n  font-family: \"Open Sans\",Helvetica,Arial,Verdana,sans-serif;\n  letter-spacing: -0.025em;\n  line-height: 30px; }\n\n.question input.form-control {\n  width: 100%;\n  border: 1px solid #aaa;\n  padding: 15px;\n  line-height: 25px;\n  text-align: center;\n  font-size: 15px;\n  margin: 5px 0 0;\n  border-radius: 2px; }\n\n.question .guest-results-btn {\n  padding: 0 15px 0 35px;\n  margin: 10px 0 0;\n  text-align: right; }\n\n.question .btn-container {\n  -webkit-box-pack: end;\n      -ms-flex-pack: end;\n          justify-content: flex-end; }\n\n.question .guest-results-btn .continue-answered-btn, .question .disclaimer .continue-answered-btn {\n  background-color: #679f39;\n  color: #fff;\n  pointer-events: auto; }\n\n.question .guest-results-btn .hq-nav-btn {\n  line-height: 4em;\n  padding: 12px 15px; }\n\n.question .disclaimer {\n  font-size: 12px;\n  line-height: 1em;\n  padding: 3px 2em; }\n  .question .disclaimer p {\n    text-align: center;\n    line-height: 1.4em;\n    color: #666; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-form/question-form.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionFormComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var QuestionFormComponent = (function () {
    function QuestionFormComponent() {
    }
    QuestionFormComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionFormComponent.prototype, "questions", void 0);
    QuestionFormComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-form',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-form/question-form.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-form/question-form.component.scss")],
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* ViewEncapsulation */].None
        }),
        __metadata("design:paramtypes", [])
    ], QuestionFormComponent);
    return QuestionFormComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-goals/question-goals.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"question-content col-12  col-md-6 col-lg-6\" *ngFor=\"let question of questions;\">\n  <div class=\"text-center\">\n    <img class=\"img-fluid question-img\" src=\"{{question.image}}\">\n    <p class=\"question-text\"><span [innerHTML]=\"question.label\"></span></p>\n  </div>\n\n  <div class=\"row\" *ngFor=\"let i of dropdownCount\">\n    <div class=\"col-2 text-right\">\n      <span class=\"health-goals\">{{i+1}}</span>\n    </div>\n    <div class=\"col-10\">\n      <app-question-dropdown [dropdown]=\"question.goals\" firstLabel=\"select-option-goal\" label=\"health-goals-\" fromComponent=\"goal\"></app-question-dropdown>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-goals/question-goals.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".health-goals {\n  background: #6dab3c;\n  border-radius: 50%;\n  color: #fff;\n  display: inline-block;\n  margin: 20px 0;\n  text-align: center;\n  width: 30px;\n  font-weight: 700;\n  height: 30px;\n  line-height: 30px; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-goals/question-goals.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionGoalsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var QuestionGoalsComponent = (function () {
    function QuestionGoalsComponent() {
        this.dropdownCount = Array(3).fill(0).map(function (x, i) { return i; });
    }
    QuestionGoalsComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionGoalsComponent.prototype, "questions", void 0);
    QuestionGoalsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-goals',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-goals/question-goals.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-goals/question-goals.component.scss")]
        }),
        __metadata("design:paramtypes", [])
    ], QuestionGoalsComponent);
    return QuestionGoalsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-mixed-content/question-mixed-content.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"row landing\">\n  <div class=\"col-12\">\n    <h1 class=\"hp-landing-title\" [innerHTML]=\"'landing.text0' | translate\"></h1>\n  </div>\n  <div class=\"col-xs-12 col-sm-7 col-md-7 col-lg-7\">\n    <img [src]=\"imagePath+image1\" class=\"img-fluid img-landing \" />\n    <img [src]=\"imagePath+image2\" class=\"img-fluid img-landing\" />\n  </div>\n  <div class=\"col-xs-12 col-sm-5 col-md-5 col-lg-5\">\n    <div class=\"landing-question\">\n      <div class=\"landing-header\">\n        <h4 [innerHTML]=\"'landing.text6' | translate\"></h4>\n        <h2 [innerHTML]=\"'landing.text8' | translate\"></h2>\n      </div>\n      <div class=\"question\">\n        <app-question-selections [questions]=\"questions\" class=\"options\"></app-question-selections>\n        <a class=\"btn btn-block start-btn\" translate (click)=\"goTo(pager.index + 1);\">landing.text7</a>\n      </div>\n    </div>\n    <img [src]=\"imagePath+image3\" class=\"img-fluid img-landing-logo\" />\n  </div>\n</div>\n<div class=\"row landing\">\n  <div class=\"shaklee-logo-section\">\n    <img [src]=\"'landing.shaklee-guaranteed-image'|translate\" class=\"img-fluid guarantee-logo\"/>\n    <h3 [innerHTML]=\"'landing.text1' | translate\"></h3>\n  </div>\n  <div class=\"row shaklee-guarantee-section\">\n    <div class=\"col-4 col-xs-12\" [innerHTML]=\"'landing.text2' | translate\"></div>\n    <div class=\"col-4 col-xs-12\" [innerHTML]=\"'landing.text3' | translate\"></div>\n    <div class=\"col-4 col-xs-12\" [innerHTML]=\"'landing.text4' | translate\"></div>\n  </div>\n  <div class=\"trademarks\" [innerHTML]=\"'landing.text5' | translate\"></div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-mixed-content/question-mixed-content.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".landing .hp-landing-title {\n  color: #00b4cb;\n  font-size: 40px;\n  font-weight: 100;\n  line-height: 1em;\n  text-align: center;\n  margin: 0 0 30px; }\n\n.landing .shaklee-logo-section {\n  text-align: center; }\n  .landing .shaklee-logo-section .guarantee-logo {\n    margin: 20px auto;\n    width: 15%; }\n  .landing .shaklee-logo-section h3 {\n    font-size: 22px;\n    font-weight: 500;\n    margin-bottom: 15px; }\n\n.landing .start-btn {\n  margin: 20px 16px;\n  border-radius: 0;\n  padding: 15px;\n  background-color: #00b4cb;\n  border-color: #00b3cb;\n  color: #FFFFFF;\n  font-weight: 400;\n  text-transform: uppercase;\n  font-size: 16px;\n  line-height: 1em;\n  cursor: pointer;\n  width: 92%; }\n\n.landing .shaklee-guarantee-section {\n  border-bottom: 1px solid #cccccc;\n  margin: 20px 0;\n  padding: 0 0 35px;\n  text-align: center; }\n  .landing .shaklee-guarantee-section p {\n    margin: 10px 0;\n    font-size: 15px; }\n  .landing .shaklee-guarantee-section h2 {\n    font-size: 24px;\n    font-weight: 500;\n    text-align: center; }\n\n.landing .trademarks {\n  margin: 10px 0;\n  font-size: 12.5px;\n  text-align: center; }\n\n.landing .img-landing {\n  margin: 0 0 25px; }\n\n.landing .img-landing-logo {\n  margin: 0 0 25px;\n  padding: 40px 50px;\n  border: 1px solid #cccccc; }\n\n.landing .landing-question {\n  background-color: #dedede;\n  border: 1px solid #cccccc;\n  padding: 15px 20px;\n  min-height: 490px;\n  margin-bottom: 25px; }\n  .landing .landing-question .landing-header h4 {\n    color: #b2025b;\n    font-size: 20px;\n    font-weight: bold;\n    margin: 5px;\n    text-align: center;\n    text-transform: uppercase; }\n  .landing .landing-question .landing-header h2 {\n    color: #00b4cb;\n    font-size: 24px;\n    font-weight: 400;\n    line-height: 1.1em;\n    margin: 10px 5px 20px;\n    text-align: center;\n    text-transform: uppercase; }\n  .landing .landing-question .question {\n    border: 1px solid #cccccc;\n    background: #FFFFFF; }\n    .landing .landing-question .question ::ng-deep .question-content {\n      margin: 0;\n      max-width: 100%; }\n    .landing .landing-question .question .question-img {\n      width: 120px;\n      height: auto; }\n    .landing .landing-question .question .question-text {\n      text-align: center;\n      height: 3.5em;\n      font-weight: 400;\n      font-size: 20px;\n      color: #1f1f1f;\n      margin: 20px 10px 15px;\n      font-family: \"Open Sans\", Helvetica, Arial, Verdana, sans-serif; }\n    .landing .landing-question .question .hq-btn {\n      color: #333333;\n      border: 1px solid #aaaaaa;\n      border-radius: 2px;\n      background: #FFFFFF;\n      font-size: 14px;\n      line-height: 1.35em;\n      margin: 15px 0;\n      padding: 10px 18px;\n      text-transform: none;\n      white-space: normal;\n      min-height: 4.25em;\n      font-weight: bold; }\n    .landing .landing-question .question .hq-gender {\n      font-size: 24px;\n      height: 24px;\n      line-height: 24px;\n      vertical-align: middle;\n      margin-right: 15px; }\n    .landing .landing-question .question button.hq-btn {\n      background-color: #FFFFFF;\n      color: #333;\n      border: 1px solid #aaa;\n      border-radius: 2px;\n      font-size: 14px;\n      line-height: 1.35em;\n      margin: 15px 0;\n      padding: 10px 18px;\n      text-transform: none;\n      white-space: normal;\n      font-weight: normal;\n      min-height: 4.25em;\n      width: 100%; }\n    .landing .landing-question .question button.hq-btn:hover, .landing .landing-question .question button.hq-selected-item {\n      background-color: #5d9732;\n      color: #FFF; }\n    .landing .landing-question .question .question-img {\n      width: 120px;\n      height: auto; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-mixed-content/question-mixed-content.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionMixedContentComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__models_question__ = __webpack_require__("../../../../../src/app/models/question.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_hp_config_service__ = __webpack_require__("../../../../../src/app/services/hp-config.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var QuestionMixedContentComponent = (function () {
    function QuestionMixedContentComponent(questionsService, hpConfigService) {
        this.questionsService = questionsService;
        this.hpConfigService = hpConfigService;
        this.appConst = { country: 'us', language: 'en' };
        this.langPageImageLink = this.appConst.country + this.appConst.language;
        this.imagePath = 'https://images.shaklee.com/healthprint/landing/';
        this.image1 = 'hp-landing-lifestyle-01a-' + this.langPageImageLink + '.jpg';
        this.image2 = 'hp-landing-chart-01a-' + this.langPageImageLink + '.jpg';
        this.image3 = 'hp-landing-logos-01a-' + this.langPageImageLink + '.jpg';
    }
    QuestionMixedContentComponent.prototype.ngOnInit = function () {
        this.pager = this.hpConfigService.getPager();
    };
    QuestionMixedContentComponent.prototype.goTo = function (index) {
        this.questionsService.goTo(index);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", __WEBPACK_IMPORTED_MODULE_1__models_question__["a" /* Question */])
    ], QuestionMixedContentComponent.prototype, "questions", void 0);
    QuestionMixedContentComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-mixed-content',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-mixed-content/question-mixed-content.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-mixed-content/question-mixed-content.component.scss")],
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__services_questions_service__["a" /* QuestionsService */],
            __WEBPACK_IMPORTED_MODULE_3__services_hp_config_service__["a" /* HpConfigService */]])
    ], QuestionMixedContentComponent);
    return QuestionMixedContentComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-selections/question-selections.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"question-content\" *ngFor=\"let question of questions;\" [ngClass]=\"{'col-4':questions.length === 3, 'col-5':questions.length === 2, 'col-6':questions.length === 1}\">\n  <div class=\"text-center\">\n    <img class=\"img-fluid question-img\" src=\"{{question.image}}\">\n    <p class=\"question-text\"><span [innerHTML]=\"question.label\"></span></p>\n  </div>\n  <div class=\"options\" *ngFor=\"let option of question.options\">\n    <button class=\"btn btn-block hq-btn\" type=\"button\" id=\"{{option.index}}\" (click)=\"onSelect(question, option);\" [ngClass]=\"{'hq-selected-item': answered[question.name] === option.index}\">\n      <i *ngIf=\"option.class\" class=\"fa hq-gender {{option.class}}\" aria-hidden=\"true\"></i>\n      <span>{{option.label}}</span>\n    </button>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-selections/question-selections.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".question-content {\n  border: none;\n  border-right: 1px solid #cccccc;\n  margin: 40px 0; }\n  .question-content:last-child {\n    border: none; }\n  .question-content .question-img {\n    width: 120px;\n    height: auto; }\n  .question-content .question-text {\n    text-align: center;\n    height: 3.5em;\n    font-weight: 400;\n    font-size: 20px;\n    color: #1f1f1f;\n    margin: 20px 10px 15px;\n    font-family: \"Open Sans\", Helvetica, Arial, Verdana, sans-serif; }\n  .question-content .hq-btn {\n    color: #333333;\n    border: 1px solid #aaaaaa;\n    border-radius: 2px;\n    background: #FFFFFF;\n    font-size: 14px;\n    line-height: 1.35em;\n    margin: 15px 0;\n    padding: 10px 18px;\n    text-transform: none;\n    white-space: normal;\n    min-height: 4.25em;\n    font-weight: bold; }\n  .question-content .hq-gender {\n    font-size: 24px;\n    height: 24px;\n    line-height: 24px;\n    vertical-align: middle;\n    margin-right: 15px; }\n  .question-content button.hq-btn {\n    background-color: #FFFFFF;\n    color: #333;\n    border: 1px solid #aaa;\n    border-radius: 2px;\n    font-size: 14px;\n    line-height: 1.35em;\n    margin: 15px 0;\n    padding: 10px 18px;\n    text-transform: none;\n    white-space: normal;\n    font-weight: normal;\n    min-height: 4.25em;\n    width: 100%; }\n  .question-content button.hq-btn:hover, .question-content button.hq-selected-item {\n    background-color: #5d9732;\n    color: #FFF; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-questions/question-selections/question-selections.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionSelectionsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var QuestionSelectionsComponent = (function () {
    function QuestionSelectionsComponent(questionsService) {
        this.questionsService = questionsService;
    }
    QuestionSelectionsComponent.prototype.ngOnInit = function () {
        this.answered = this.questionsService.getAnswered();
    };
    QuestionSelectionsComponent.prototype.onSelect = function (question, option) {
        console.log('ques', question);
        this.questionsService.setSelected(question, option);
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], QuestionSelectionsComponent.prototype, "questions", void 0);
    QuestionSelectionsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-question-selections',
            template: __webpack_require__("../../../../../src/app/page/hp-questions/question-selections/question-selections.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-questions/question-selections/question-selections.component.scss")],
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_questions_service__["a" /* QuestionsService */]])
    ], QuestionSelectionsComponent);
    return QuestionSelectionsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-add-cart/hp-add-cart.component.html":
/***/ (function(module, exports) {

module.exports = "<a (click)=\"addToCart('20282', $event, 'Advanced');\" class=\"btn add-item\">ADD ITEM</a>\n"

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-add-cart/hp-add-cart.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".add-item {\n  border: 1px solid #666;\n  border-radius: 2px;\n  color: #FFFFFF !important;\n  background-color: #666;\n  cursor: pointer; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-add-cart/hp-add-cart.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpAddCartComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_buffer__ = __webpack_require__("../../../../buffer/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_buffer___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_1_buffer__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ngx_cookie_service__ = __webpack_require__("../../../../ngx-cookie-service/index.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var HpAddCartComponent = (function () {
    function HpAddCartComponent(cookieService) {
        this.cookieService = cookieService;
    }
    HpAddCartComponent.prototype.ngOnInit = function () {
    };
    HpAddCartComponent.prototype.addToCart = function (sku, event, bundle) {
        var item = {};
        item[sku] = 1;
        var productsToAdd = {};
        productsToAdd[sku] = 1;
        productsToAdd['89384'] = 1;
        var healthprintCart = {};
        healthprintCart['recommendationBundle'] = bundle;
        healthprintCart['recommendationTotal'] = 10.21;
        healthprintCart['addAllToCart'] = false;
        healthprintCart['productsToAdd'] = productsToAdd;
        healthprintCart['freeProduct'] = '89384';
        var hpCartCookie = JSON.stringify(healthprintCart);
        var hpCartObjJsonB64 = new __WEBPACK_IMPORTED_MODULE_1_buffer__["Buffer"](hpCartCookie).toString('base64');
        this.cookieService.set('shakleeUS-healthprint-cart', hpCartObjJsonB64, 100, '/', '.shakleedev.com');
        window.location.href = 'https://www.shakleedev.com:9002/cart';
    };
    HpAddCartComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-add-cart',
            template: __webpack_require__("../../../../../src/app/page/hp-results/hp-add-cart/hp-add-cart.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-results/hp-add-cart/hp-add-cart.component.scss")],
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* ViewEncapsulation */].None
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2_ngx_cookie_service__["a" /* CookieService */]])
    ], HpAddCartComponent);
    return HpAddCartComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-header/hp-header.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"row health-results-above-banner hp-results-banner\">\n  <div class=\"col-xs-12 col-sm-4 col-md-4 col-lg-4\">\n    <img class=\"logo-img\" src=\"//images.shaklee.com/healthprint/Healthprint-Logo.png\">\n    <span class=\"logo-text ng-binding\" ng-bind-html=\"'personalize-health.results.shaklee_healthprint'|translate\">Shaklee <b>HEALTHPRINT</b><sup>TM</sup></span>\n  </div>\n  <div class=\"col-xs-12 col-sm-4 col-md-4 col-lg-4 hp-results-selection\">\n    <ng-select [items]=\"allHealthPrintsResults\"\n               bindLabel=\"first_name\"\n               bindValue=\"health_profile_id\"\n               placeholder=\"Select healthprint\"\n               name=\"healthPrintResults\"\n               [clearable]=\"false\"\n               [(ngModel)]=\"healthProfileId\"\n               (change)=\"changeHealthPrintResultInfo($event)\">\n    </ng-select>\n  </div>\n\n  <div class=\"col-xs-12 col-sm-4 col-md-4 col-lg-4 text-right\">\n      <div class=\"resultTopLinks\">\n        <a href=\"#\" (click)=\"startOverQuiz($event)\">RETAKE YOUR ASSESSMENT</a>\n        <a href=\"#\" (click)=\"printResults($event)\"><i class=\"fa fa-print fa-2x\" aria-hidden=\"true\"></i></a>\n        <a href=\"#\" (click)=\"openEmailResultMore($event)\"><i class=\"fa fa-envelope-o fa-2x\" aria-hidden=\"true\"></i></a>\n      </div>\n   </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-header/hp-header.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".hp-results-banner {\n  border-bottom: 2px solid #dbdbdb;\n  padding: 10px 0; }\n  .hp-results-banner .logo-img {\n    width: 20%; }\n  .hp-results-banner .hp-results-selection .searchable {\n    margin: 20px auto 0; }\n  .hp-results-banner .resultTopLinks {\n    margin-top: 20px; }\n    .hp-results-banner .resultTopLinks a {\n      color: #999;\n      font-size: 12px; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-header/hp-header.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpHeaderComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_hp_results_service__ = __webpack_require__("../../../../../src/app/services/hp-results.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var HpHeaderComponent = (function () {
    function HpHeaderComponent(healthPrintResultsService) {
        this.healthPrintResultsService = healthPrintResultsService;
    }
    HpHeaderComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.healthPrintResultsService.allHealthPrintResults.subscribe(function (allHealthPrintsResults) { return _this.allHealthPrintsResults = allHealthPrintsResults; });
        this.healthPrintResultsService.healthPrintResultInfo.subscribe(function (healthPrintResultInfo) { return _this.healthPrintResultInfo = healthPrintResultInfo; });
        this.healthProfileId = this.healthPrintResultInfo.health_profile_id;
        //this.deviceInfo = this.deviceService.getDeviceInfo();
        //console.log(this.deviceInfo);
    };
    HpHeaderComponent.prototype.changeHealthPrintResultInfo = function (event) {
        for (var index in this.allHealthPrintsResults) {
            var curHealthPrintResult = this.allHealthPrintsResults[index];
            if (this.healthProfileId == curHealthPrintResult.health_profile_id) {
                this.healthPrintResultsService.setHealthPrintResultInfo(curHealthPrintResult);
            }
        }
    };
    HpHeaderComponent.prototype.startOverQuiz = function (event) {
        event.stopPropagation();
        console.log("start over quiz");
        console.log(this.healthPrintResultInfo);
        return false;
    };
    HpHeaderComponent.prototype.printResults = function (event) {
        event.stopPropagation();
        console.log("print result page");
        console.log(this.healthPrintResultInfo);
        return false;
    };
    HpHeaderComponent.prototype.openEmailResultMore = function (event) {
        event.stopPropagation();
        console.log("open Email results more");
        console.log(this.healthPrintResultInfo);
        return false;
    };
    HpHeaderComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-header',
            template: __webpack_require__("../../../../../src/app/page/hp-results/hp-header/hp-header.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-results/hp-header/hp-header.component.scss")],
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* ViewEncapsulation */].None
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_hp_results_service__["a" /* HealthPrintResultsService */]])
    ], HpHeaderComponent);
    return HpHeaderComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"section-score\">\n  <h3 class=\"title\">Your Healthprint diet score of <b>43</b> indicates you’re ready to make a change.</h3>\n  <div class=\"row score-bar\">\n    <div class=\"col-xs-4 col-sm-6 col-md-6 col-lg-6\">\n      <img class=\"img-responsive\" src=\"//images.shaklee.com/healthprint/score-diet-blue.png\">\n    </div>\n    <div class=\"col-xs-8 col-sm-6 col-md-6 col-lg-6\">\n      <h3 class=\"img-label ng-binding\" ng-bind-html=\"'personalize-health.results.diet-score'|translate\">DIET SCORE</h3>\n    </div>\n  </div>\n  <div class=\"row score-bar \">\n    <div class=\"col-xs-12 col-sm-12 col-md-12 col-lg-12\">\n      <div class=\"legend\"><span class=\"score-mover\" ng-style=\"{width: contRecomInfo.diet.score+'%'}\" style=\"width: 43%;\"><em class=\"ng-binding\">43</em></span></div>\n      <div class=\"bar\">\n        <div class=\"scale red\"> </div>\n        <div class=\"scale orange\"> </div>\n        <div class=\"scale yellow\"> </div>\n        <div class=\"scale green\"> </div>\n      </div>\n      <div class=\"line\"> <span class=\"left\">0</span> <span class=\"right\">100</span> </div>\n    </div>\n  </div>\n  <div class=\"score-info\">\n    <p class=\"info\"><span ng-bind-html=\"'personalize-health.results.content-recom.intro-'+contRecomInfo.diet.key|translate\" class=\"ng-binding\">Completing your Shaklee Healthprint is a great first step on the path to a healthier you.  Based on your responses, here are some ways you can improve your diet</span>...</p>\n  </div>\n  <div class=\"read-more-box\">\n    <span class=\"learn-more ng-binding\" ng-bind-html=\"'personalize-health.label.read-more'|translate\" ng-click=\"readMoreInfo(contRecomInfo.diet,'diet')\">Read More</span>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpHealthScoreComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HpHealthScoreComponent = (function () {
    function HpHealthScoreComponent() {
    }
    HpHealthScoreComponent.prototype.ngOnInit = function () {
    };
    __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["E" /* Input */])(),
        __metadata("design:type", Object)
    ], HpHealthScoreComponent.prototype, "healthPrintResultInfo", void 0);
    HpHealthScoreComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-health-score',
            template: __webpack_require__("../../../../../src/app/page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-results/hp-health-scores/hp-health-score/hp-health-score.component.scss")],
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* ViewEncapsulation */].None
        }),
        __metadata("design:paramtypes", [])
    ], HpHealthScoreComponent);
    return HpHealthScoreComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-health-scores/hp-health-scores.component.html":
/***/ (function(module, exports) {

module.exports = "<div class =\"hp-health-scores\">\n  <h1 class=\"section-title\">\n    YOUR PERSONALIZED HEALTH SCORES\n  </h1>\n\n  <div class=\"row\">\n        <div class=\"col-xs-12 col-sm-4 col-md-4 col-lg-4 \">\n          <app-hp-health-score [healthPrintResultInfo]=\"healthPrintResultInfo\"></app-hp-health-score>\n        </div>\n        <div class=\"col-xs-12 col-sm-4 col-md-4 col-lg-4\">\n          <app-hp-health-score [healthPrintResultInfo]=\"healthPrintResultInfo\"></app-hp-health-score>\n        </div>\n        <div class=\"col-xs-12 col-sm-4 col-md-4 col-lg-4\">\n           <div class=\"bmi-scale\">\n             <h3 class=\"title\">Your Body Mass Index (BMI) is <b>32.7</b></h3>\n             <span class=\"bmi\">32.7 <br> <b class=\"bmi-text\">BMI</b></span>\n               <p class=\"info\">\n                 <span ng-bind-html=\"'personalize-health.results.content-recom.'+contRecomInfo.bmi.key|translate:bmiWeightRange\" class=\"ng-binding\">You meet the clinical definition for obese. Ideally you should weigh between <span>104</span>  (BMI 18.5) and <span>140</span> (BMI 24.99)</span>\n               </p><div class=\"read-more-box\">\n               <a class=\"learn-more ng-binding\" ng-href=\"http://go.shaklee.com/need-know-bmi/\" ng-click=\"trackEventOnResults('learn more - BMI', 'BMI');\" ng-bind-html=\"'personalize-health.label.learn-more'|translate\" target=\"_blank\" href=\"http://go.shaklee.com/need-know-bmi/\">Learn More</a>\n           </div>\n        </div>\n        </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-health-scores/hp-health-scores.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".hp-health-scores {\n  margin: 15px; }\n  .hp-health-scores .learn-more {\n    font-size: 10px;\n    text-transform: uppercase;\n    color: #5d9732;\n    font-weight: 700;\n    text-shadow: none !important;\n    cursor: pointer;\n    margin-top: 0;\n    display: block; }\n  .hp-health-scores .section-title {\n    color: #06b5cb;\n    font-weight: 700;\n    text-transform: uppercase;\n    font-size: 22px;\n    text-align: center;\n    margin: 15px auto; }\n    .hp-health-scores .section-title b {\n      font-size: 120%;\n      color: #5d9732; }\n  .hp-health-scores .section-score .title {\n    font-size: 15px;\n    font-weight: 700;\n    line-height: 22px;\n    padding: 10px 0;\n    height: 4.25em;\n    margin: 10px 0; }\n    .hp-health-scores .section-score .title b {\n      font-size: 120%;\n      color: #5d9732; }\n  .hp-health-scores .section-score .score-bar img {\n    margin: 15px 0 0 40px;\n    width: 70%; }\n  .hp-health-scores .section-score .score-bar .img-label {\n    font-size: 16px;\n    font-weight: 600;\n    text-align: left;\n    margin: 60px 0 0 -17px; }\n  .hp-health-scores .section-score .score-bar .legend {\n    text-align: left;\n    margin: 5px 0 0; }\n    .hp-health-scores .section-score .score-bar .legend .score-mover {\n      display: inline-block;\n      text-align: right;\n      font-weight: bold;\n      position: relative; }\n      .hp-health-scores .section-score .score-bar .legend .score-mover em {\n        background: #fff;\n        border-radius: 50%;\n        color: #5c993d;\n        text-align: center;\n        top: -0.45em;\n        position: absolute;\n        right: -0.5em;\n        box-shadow: 0 0 10px 0 #999;\n        font-weight: 400;\n        font-size: 1.3em;\n        font-style: normal;\n        height: 2em;\n        line-height: 2em;\n        width: 2em; }\n  .hp-health-scores .section-score .score-bar .bar {\n    clear: both; }\n    .hp-health-scores .section-score .score-bar .bar .scale {\n      height: 15px;\n      margin: 0;\n      padding: 0;\n      display: inline-block;\n      float: left; }\n    .hp-health-scores .section-score .score-bar .bar .red {\n      background: #e63939;\n      width: 50%;\n      border-top-left-radius: 7px;\n      border-bottom-left-radius: 7px; }\n    .hp-health-scores .section-score .score-bar .bar .orange {\n      background: #f2b63d;\n      width: 20%; }\n    .hp-health-scores .section-score .score-bar .bar .yellow {\n      background: #faea3e;\n      width: 20%; }\n    .hp-health-scores .section-score .score-bar .bar .green {\n      background: #5c993d;\n      width: 10%;\n      border-top-right-radius: 7px;\n      border-bottom-right-radius: 7px; }\n  .hp-health-scores .section-score .score-bar .line {\n    clear: both;\n    height: 11px;\n    font-size: 11px;\n    font-weight: bold; }\n    .hp-health-scores .section-score .score-bar .line .left {\n      float: left;\n      width: 50%;\n      text-align: left;\n      display: inline-block; }\n    .hp-health-scores .section-score .score-bar .line .right {\n      width: 50%;\n      text-align: right;\n      display: inline-block; }\n  .hp-health-scores .section-score .info {\n    margin: 25px 0 0;\n    padding: 0;\n    line-height: 19px;\n    font-size: 12px; }\n  .hp-health-scores .bmi-scale {\n    margin: 15px; }\n    .hp-health-scores .bmi-scale .title {\n      font-size: 15px;\n      font-weight: 700;\n      line-height: 22px;\n      padding: 10px 0;\n      height: 4.25em;\n      margin: 10px 0; }\n      .hp-health-scores .bmi-scale .title b {\n        font-size: 120%;\n        color: #5d9732; }\n    .hp-health-scores .bmi-scale .info {\n      margin: 60px 0 0;\n      padding: 0;\n      line-height: 19px;\n      font-size: 12px; }\n    .hp-health-scores .bmi-scale .bmi {\n      background: url(//images.shaklee.com/healthprint/scale.png) no-repeat scroll center center;\n      display: block;\n      font-size: 23px;\n      font-weight: bold;\n      height: 140px;\n      line-height: 18px;\n      padding: 70px 0;\n      text-align: center;\n      width: 100%;\n      background-size: 100%;\n      max-width: 95px;\n      margin: 10px auto 0; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-health-scores/hp-health-scores.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpHealthScoresComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_hp_results_service__ = __webpack_require__("../../../../../src/app/services/hp-results.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var HpHealthScoresComponent = (function () {
    function HpHealthScoresComponent(healthPrintResultsService) {
        this.healthPrintResultsService = healthPrintResultsService;
    }
    HpHealthScoresComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.healthPrintResultsService.healthPrintResultInfo.subscribe(function (healthPrintResultInfo) { return _this.healthPrintResultInfo = healthPrintResultInfo; });
        this.healthPrintResultsService.getContent().subscribe(function (data) {
            _this.contentInfo = data;
            //console.log(data);
        });
        console.log(this.contentInfo);
    };
    HpHealthScoresComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-health-scores',
            template: __webpack_require__("../../../../../src/app/page/hp-results/hp-health-scores/hp-health-scores.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-results/hp-health-scores/hp-health-scores.component.scss")],
            encapsulation: __WEBPACK_IMPORTED_MODULE_0__angular_core__["_13" /* ViewEncapsulation */].None
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_hp_results_service__["a" /* HealthPrintResultsService */]])
    ], HpHealthScoresComponent);
    return HpHealthScoresComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-results.component.html":
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\n  <div class=\"col-xs-12 col-sm-12 col-md-12 col-sm-12\">\n    <app-hp-header></app-hp-header>\n    <app-hp-health-scores></app-hp-health-scores>\n    <div class=\"row\">\n      <div class=\"col-12\">\n        <h1 class=\"section-title\"><b>Your Personalized Recommendations</b><br>based on your results and top health goals</h1>\n      </div>\n      <div class=\"col-2 image\">\n        <img src=\"https://assets.shaklee.com/publish/content/dam/shakleemedia/products/new_products_us/healthy_foundation/b20282.jpg\" class=\"img-fluid\">\n        <div class=\"img-res\">\n          <img src=\"//images.shaklee.com/healthprint/gluten-free.png\" class=\"img-fluid\"> <span>Gluten Free</span><br>\n          <img src=\"//images.shaklee.com/healthprint/lactose-free.png\" class=\"img-fluid\"> <span>Lactose Free</span>\n        </div>\n      </div>\n      <div class=\"col-8 description\">\n        <b>Vitalizer™</b>\n        <p>Vital, advanced nutrition in a convenient, multi-nutrient strip. 100% or more of the Daily Value of all essential vitamins to provide a foundation of nutritional support plus:</p>\n        <ul>\n          <li><b>Caroto-E-Omega: </b>500mg DHA/EPA ultra-pure Omega-3 fatty acids, plus vitamin E to support a healthy heart and joint comfort*</li>\n          <li><b>B+C Complex: </b>for increased energy and added immune support*</li>\n          <li><b>Optiflora: </b>probiotic to support digestive health*</li>\n          <li>Based on the <b>Landmark Study</b>, one of the largest studies of loterm supplementation conducted by UC Berkele</li>\n        </ul>\n      </div>\n      <div class=\"col-2 buttons\">\n        <p class=\"price\">$79.25</p>\n        <app-hp-add-cart></app-hp-add-cart>\n      </div>\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-results.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, ".section-title {\n  color: #000;\n  text-transform: uppercase;\n  font-size: 22px;\n  text-align: center;\n  margin: 15px 20px; }\n\n.img-res img {\n  width: 15%; }\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/hp-results/hp-results.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpResultsComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_hp_results_service__ = __webpack_require__("../../../../../src/app/services/hp-results.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var HpResultsComponent = (function () {
    function HpResultsComponent(route, healthPrintResultsService) {
        this.route = route;
        this.healthPrintResultsService = healthPrintResultsService;
    }
    HpResultsComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.data.subscribe(function (data) {
            _this.healthPrintResultsService.setAllHealthPrintResult(data['healthPrintResults']);
            _this.healthPrintResultsService.setHealthPrintResultInfo(data['healthPrintResults'][0]);
        });
        var skus = '22067,89384';
        this.healthPrintResultsService.getProductContent(skus).subscribe(function (responseData) {
            _this.productInfo = responseData['products'];
            console.log(_this.productInfo);
        });
    };
    HpResultsComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-hp-results',
            template: __webpack_require__("../../../../../src/app/page/hp-results/hp-results.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/hp-results/hp-results.component.scss")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_router__["a" /* ActivatedRoute */], __WEBPACK_IMPORTED_MODULE_2__services_hp_results_service__["a" /* HealthPrintResultsService */]])
    ], HpResultsComponent);
    return HpResultsComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page/page.component.html":
/***/ (function(module, exports) {

module.exports = "<app-header></app-header>\n<app-hp-questions></app-hp-questions>\n<app-hp-nav></app-hp-nav>\n<app-footer></app-footer>"

/***/ }),

/***/ "../../../../../src/app/page/page.component.scss":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page/page.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PageComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_questions_service__ = __webpack_require__("../../../../../src/app/services/questions.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__services_hp_config_service__ = __webpack_require__("../../../../../src/app/services/hp-config.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var PageComponent = (function () {
    function PageComponent(questionsService, hpconfigService, route) {
        this.questionsService = questionsService;
        this.hpconfigService = hpconfigService;
        this.route = route;
    }
    PageComponent.prototype.ngOnInit = function () {
        this.quiz = this.route.snapshot.data['quizList'];
        this.questionsService.setQuiz(this.quiz);
        this.hpconfigService.setPagerCount(this.quiz['pages'].length);
    };
    PageComponent.prototype.onSubmit = function () {
        var _this = this;
        var answers = [];
        this.quiz['pages'].forEach(function (x) {
            answers.push({ 'name': _this.quiz['id'], 'questionId': x.id, 'answered': x.selected });
        });
        // Post your data to the server here. answers contains the questionId and the users' answer.
        console.log(this.quiz['pages']);
        console.log(answers);
        // this.mode = 'result';
    };
    PageComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-page',
            template: __webpack_require__("../../../../../src/app/page/page.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page/page.component.scss")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_questions_service__["a" /* QuestionsService */],
            __WEBPACK_IMPORTED_MODULE_2__services_hp_config_service__["a" /* HpConfigService */],
            __WEBPACK_IMPORTED_MODULE_3__angular_router__["a" /* ActivatedRoute */]])
    ], PageComponent);
    return PageComponent;
}());



/***/ }),

/***/ "../../../../../src/app/services/data.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_BehaviorSubject__ = __webpack_require__("../../../../rxjs/_esm5/BehaviorSubject.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};




var DataService = (function () {
    function DataService(http) {
        this.http = http;
        // storeRecipes() {
        //    return this.http.put('https://ng-recipe-book-916df.firebaseio.com/recipes.json', this.recipeService.getRecipes());
        // }
        // getQuestionsUrl() {
        //     this.questionUrl = './assets/data/us/questions.json';
        //     return this.questionUrl;
        // }
        this.quizSource = new __WEBPACK_IMPORTED_MODULE_1_rxjs_BehaviorSubject__["a" /* BehaviorSubject */]([]);
        this.quiz = this.quizSource.asObservable();
    }
    DataService.prototype.getQuestions = function () {
        return this.http.get('./assets/data/us/questions.json').map(function (res) { return res; });
    };
    DataService.prototype.getLogin = function () {
        console.log("asdfsf");
        /**
        return this.http.get<Quiz[]>('./assets/data/us/questions.json').map(res => res {
            console.log(resp);
        });
         */
    };
    DataService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_2__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__angular_common_http__["a" /* HttpClient */]])
    ], DataService);
    return DataService;
}());



/***/ }),

/***/ "../../../../../src/app/services/hp-config.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HpConfigService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var HpConfigService = (function () {
    function HpConfigService() {
        this.pager = {
            index: 0,
            size: 1,
            count: 1
        };
        this.config = {
            'allowBack': true,
            'autoMove': false,
            'pageSize': 1,
            'showPager': true
        };
    }
    HpConfigService.prototype.getAnsweredJsonObj = function () {
        // var isMC = (appConst.site === "mc") ? true : false;
        var isMC = false;
        var jsonObject = {
            country_code: undefined,
            language: 'en',
            email: undefined,
            referrer_id: undefined,
            user_id: undefined,
            first_name: undefined,
            last_name: undefined,
            age: undefined,
            gender: undefined,
            weight_lbs: undefined,
            height_inches: { foot: undefined, inches: undefined },
            pregnant: undefined,
            energy: undefined,
            memory: undefined,
            stress: undefined,
            sleep: undefined,
            exercise_frequency: undefined,
            exercise_intensity: undefined,
            toxins: undefined,
            fruits: undefined,
            vegetables: undefined,
            grains: undefined,
            dairy: undefined,
            healthy_fats: undefined,
            water: undefined,
            junk_food: undefined,
            sugar_drinks: undefined,
            breakfast: undefined,
            organic: undefined,
            spending: undefined,
            dietary_restrictions: {},
            // health_goals: {goal0: undefined, goal1: undefined, goal2: undefined},
            health_goals: [],
            is_guest: (!isMC) ? true : false,
            noShareWithDistributors: false,
            share_with_distributors: true
        };
        return jsonObject;
    };
    HpConfigService.prototype.getPager = function () {
        return this.pager;
    };
    HpConfigService.prototype.getPagerCount = function () {
        return this.pager.count;
    };
    HpConfigService.prototype.setPagerCount = function (index) {
        this.pager.count = index;
    };
    HpConfigService.prototype.setPagerIndex = function (index) {
        this.pager.index = index;
    };
    HpConfigService.prototype.getConfig = function () {
        return this.config;
    };
    HpConfigService.prototype.setAutomove = function (index) {
        this.pager.index = index;
    };
    HpConfigService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [])
    ], HpConfigService);
    return HpConfigService;
}());



/***/ }),

/***/ "../../../../../src/app/services/hp-results-resolve.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HealthPrintResultsResolveService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__hp_results_service__ = __webpack_require__("../../../../../src/app/services/hp-results.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var HealthPrintResultsResolveService = (function () {
    function HealthPrintResultsResolveService(healthPrintResultsService, router) {
        this.healthPrintResultsService = healthPrintResultsService;
        this.router = router;
    }
    HealthPrintResultsResolveService.prototype.resolve = function (route, state) {
        var _this = this;
        return this.healthPrintResultsService.getHealthPrintResults().map(function (responseData) {
            //check status and throw error need to work on.ss
            if (responseData['data'].length == 0) {
                _this.router.navigate(['/questions']);
            }
            else {
                return responseData['data'];
            }
        });
    };
    HealthPrintResultsResolveService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__hp_results_service__["a" /* HealthPrintResultsService */], __WEBPACK_IMPORTED_MODULE_1__angular_router__["b" /* Router */]])
    ], HealthPrintResultsResolveService);
    return HealthPrintResultsResolveService;
}());



/***/ }),

/***/ "../../../../../src/app/services/hp-results.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return HealthPrintResultsService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__ = __webpack_require__("../../../../rxjs/_esm5/BehaviorSubject.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



// import {variable} from "@angular/compiler/src/output/output_ast";
var HealthPrintResultsService = (function () {
    function HealthPrintResultsService(http) {
        this.http = http;
        this.allHealthPrintResultsSource = new __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__["a" /* BehaviorSubject */]([]);
        this.healthPrintResultInfoSource = new __WEBPACK_IMPORTED_MODULE_2_rxjs_BehaviorSubject__["a" /* BehaviorSubject */]({});
        this.allHealthPrintResults = this.allHealthPrintResultsSource.asObservable();
        this.healthPrintResultInfo = this.healthPrintResultInfoSource.asObservable();
        this.endPointAllHealthPrintResults = '/assets/mockjson/getAllHealthPrints.json';
        this.endPointContent = '/assets/mockjson/content.json';
        this.endPointProduct = 'https://35.188.18.139:9002//shakleeintegration/v2/shakleeUS/products?fields=DEFAULT';
    }
    HealthPrintResultsService.prototype.getHealthPrintResults = function () {
        return this.http.get(this.endPointAllHealthPrintResults);
    };
    HealthPrintResultsService.prototype.getContent = function () {
        return this.http.get(this.endPointContent);
    };
    HealthPrintResultsService.prototype.getProductContent = function (sku) {
        var codes = '&codes=' + sku;
        return this.http.get(this.endPointProduct + codes);
    };
    HealthPrintResultsService.prototype.setAllHealthPrintResult = function (allHealthPrintResults) {
        this.allHealthPrintResultsSource.next(allHealthPrintResults);
        this.setHealthPrintResultInfo(allHealthPrintResults[0]);
    };
    HealthPrintResultsService.prototype.setHealthPrintResultInfo = function (healthPrintResultInfo) {
        this.healthPrintResultInfoSource.next(healthPrintResultInfo);
    };
    HealthPrintResultsService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], HealthPrintResultsService);
    return HealthPrintResultsService;
}());



/***/ }),

/***/ "../../../../../src/app/services/questions.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuestionsService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__hp_config_service__ = __webpack_require__("../../../../../src/app/services/hp-config.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__data_service__ = __webpack_require__("../../../../../src/app/services/data.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var QuestionsService = (function () {
    function QuestionsService(http, hpconfigService, dataService) {
        this.http = http;
        this.hpconfigService = hpconfigService;
        this.dataService = dataService;
        this.questionUrl = '';
        this.answered = this.hpconfigService.getAnsweredJsonObj();
        this.goals = [];
    }
    QuestionsService.prototype.get = function (url) {
        return this.http.get(url);
    };
    QuestionsService.prototype.getPages = function () {
        return this.quiz['pages'];
    };
    QuestionsService.prototype.setQuiz = function (quiz) {
        console.log('set quiz', quiz);
        this.quiz = quiz;
    };
    QuestionsService.prototype.getAnswered = function () {
        return this.answered;
    };
    QuestionsService.prototype.setAnswered = function (answered) {
        this.answered = answered;
    };
    QuestionsService.prototype.goTo = function (index) {
        var pageCount = this.hpconfigService.getPagerCount();
        if (index >= 0 && index < pageCount) {
            this.hpconfigService.setPagerIndex(index);
        }
    };
    QuestionsService.prototype.setSelected = function (question, option) {
        console.log(this.answered);
        this.answered[question.name] = option.index;
        // this.hpconfigService.setAutomove(1);
    };
    QuestionsService.prototype.setInput = function (name, val) {
        console.log(this.answered);
        this.answered[name] = val;
        // this.hpconfigService.setAutomove(1);
    };
    QuestionsService.prototype.setDropdown = function (name, val, type) {
        this.answered[type][name] = val;
        console.log(this.answered);
    };
    QuestionsService.prototype.setGoalDropdown = function (name, val) {
        var index = this.goals.indexOf(val);
        if (index === -1) {
            this.goals.push(val);
        }
        this.answered['health_goals'] = this.goals;
        console.log(this.answered);
    };
    QuestionsService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__angular_common_http__["a" /* HttpClient */],
            __WEBPACK_IMPORTED_MODULE_3__hp_config_service__["a" /* HpConfigService */],
            __WEBPACK_IMPORTED_MODULE_4__data_service__["a" /* DataService */]])
    ], QuestionsService);
    return QuestionsService;
}());



/***/ }),

/***/ "../../../../../src/app/services/quiz.resolver.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return QuizResolver; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__ = __webpack_require__("../../../../rxjs/_esm5/Observable.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__data_service__ = __webpack_require__("../../../../../src/app/services/data.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var QuizResolver = (function () {
    function QuizResolver(dataService) {
        this.dataService = dataService;
    }
    QuizResolver.prototype.resolve = function (route, state) {
        var _this = this;
        return __WEBPACK_IMPORTED_MODULE_1_rxjs_Observable__["a" /* Observable */].create(function (observer) {
            _this.dataService.getQuestions().subscribe(function (data) {
                observer.next(data);
                observer.complete();
            });
        });
    };
    QuizResolver = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["B" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_2__data_service__["a" /* DataService */]])
    ], QuizResolver);
    return QuizResolver;
}());



/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
var environment = {
    production: false
};


/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/esm5/platform-browser-dynamic.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_16" /* enableProdMode */])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map
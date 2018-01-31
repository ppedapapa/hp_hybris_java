import { Question } from './question';

export class Page {
    public component: string;
    public restrict: [string];
    public questions: Question[];

    constructor(component: string, restrict: [string], questions: Question[]) {
        this.component = component;
        this.restrict = restrict;
        this.questions = questions;
    }
}

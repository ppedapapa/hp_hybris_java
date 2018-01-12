import { Question } from './question';

export class Page {
    public questions: Question[];

    constructor(questions: Question[]) {
        this.questions = questions;
    }
}

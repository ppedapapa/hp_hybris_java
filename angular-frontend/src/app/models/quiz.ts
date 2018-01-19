import { QuestionConfig } from './question-config';
import { Question } from './question';
import { Page } from './page';

export class Quiz {
    public id: number;
    public label: string;
    public description: string;
    public config: QuestionConfig;
    public questions: Question[];
    public pages: Page[];

    constructor(id: number, label: string, description: string, config: QuestionConfig, questions: Question[], pages: Page[]) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.config = config;
        this.questions = questions;
        this.pages = pages;
    }
}

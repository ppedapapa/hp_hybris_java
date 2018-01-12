import { QuestionConfig } from './question-config';
import { Question } from './question';
import { Page } from './page';

export class Quiz {
    public id: number;
    public name: string;
    public description: string;
    public config: QuestionConfig;
    public questions: Question[];
    public pages: Page[];

    constructor(id: number, name: string, description: string, config: QuestionConfig, questions: Question[], pages: Page[]) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.config = config;
        this.questions = questions;
        this.pages = pages;
    }
}

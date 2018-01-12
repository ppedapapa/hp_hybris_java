import { Option } from './option';

export class Question {
    public id: number;
    public image: string;
    public name: string;
    public label: string;
    public questionTypeId: number;
    public options: Option[];
    public answered: boolean;

    constructor(id: number, image: string, name: string,  label: string, questionTypeId: number, options: Option[]) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.label = label;
        this.questionTypeId = questionTypeId;
        this.options = options;
    }
}

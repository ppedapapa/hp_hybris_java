import { Option } from './option';

export class Question {
    public name: string;
    public label: string;
    public image: string;
    public options: Option[];

    constructor(name: string,  label: string, image: string, options: Option[]) {
        this.name = name;
        this.label = label;
        this.image = image;
        this.options = options;
    }
}

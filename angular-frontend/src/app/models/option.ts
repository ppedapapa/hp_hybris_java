export class Option {
    public index: number;
    public name: string;
    public selected: boolean;

    constructor(index: number, name: string) {
        this.index = index;
        this.name = name;
    }
}

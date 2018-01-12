export class QuestionConfig {
    public allowBack: boolean;
    public autoMove: boolean;  // if boolean; it will move to next question automatically when answered.
    public pageSize: number;
    public showPager: boolean;

    constructor(allowBack: boolean, autoMove: boolean, pageSize: number, showPager: boolean) {
        this.allowBack = allowBack;
        this.autoMove = autoMove;
        this.pageSize = pageSize;
        this.showPager = showPager;
    }
}

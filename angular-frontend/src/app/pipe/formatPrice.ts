import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'formatPrice'
})
export class FormatPricePipe implements PipeTransform {
    transform(str: string): string {
        console.log(str);
        let language = 'us';
        return (language === 'fr')?(str.replace(",", " ")).replace(".", ",")+" $":"$"+str;
    }
}
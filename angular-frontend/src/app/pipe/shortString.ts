import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'shortString'
})
export class ShortStringPipe implements PipeTransform {
    transform(str: string): string {
        return (str.length > 175)?str.substring(0,175):str;
    }
}
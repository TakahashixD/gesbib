import { IPage } from '../interface/page.interface';

export class Page implements IPage{
    constructor(
        public content: any[],
        public page: any
    ){
        this.content = content;
        this.page = page;
    }    
}
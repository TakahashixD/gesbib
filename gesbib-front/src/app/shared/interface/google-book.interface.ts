export interface IGoogleBook{
    title: string;
    authors: string[];
    isbn: Isbn[];
    categories: string[];
    publishedDate: Date;
}

export class Isbn{
    constructor(
        public type: string,
        public identifier: string,
    ){
    }    
}
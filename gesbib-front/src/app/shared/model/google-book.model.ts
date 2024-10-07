import { IGoogleBook } from '../interface/google-book.interface';

export class GoogleBook implements IGoogleBook{
    constructor(
        public title: string,
        public author: string[],
        public isbn: any[],
        public categories: string[],
        public publishedDate: Date,
    ){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.categories = categories;
    }    
}
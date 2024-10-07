import { IGoogleBook, Isbn } from '../interface/google-book.interface';

export class GoogleBook implements IGoogleBook{
    constructor(
        public title: string,
        public authors: string[],
        public isbn: Isbn[],
        public categories: string[],
        public publishedDate: Date,
    ){
        this.title = title;
        this.authors = authors;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.categories = categories;
    }    
}
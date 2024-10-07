import { IBook } from '../interface/book.interface';

export class Book implements IBook{
    constructor(
        public title: string,
        public author: string,
        public isbn: string,
        public publishDate: Date,
        public category: string,
        public id?: number,
    ){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.category = category;
        this.id = id;
    }    
}
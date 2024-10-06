import { IBook } from '../interface/book.interface';

export class Book implements IBook{
    constructor(
        public id: number,
        public title: string,
        public author: string,
        public isbn: string,
        public publishedDate: Date,
        public category: string,
    ){
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.category = category;
    }    
}
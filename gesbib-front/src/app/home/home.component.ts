import { Component, inject, OnInit } from '@angular/core';
import { BookService } from '../shared/service/book.service';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { IBook } from '../shared/interface/book.interface';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatTableModule, MatPaginator, DatePipe],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
    bookService = inject(BookService);

    displayedColumnsBook: string[] = ['id', 'title', 'author', 'isbn', 'publishDate', 'category'];
    displayedColumnsPerson: string[] = ['id', 'name', 'email', 'signupDate', 'phone'];

    dataSourceBook = new MatTableDataSource<IBook>();
    dataSourcePerson = new MatTableDataSource<IBook>();
    pageIndexBook: number = 0;
    totalBook: number = 0;
    pageIndexPerson: number = 0;
    totalPerson: number = 0;

    requestBook = {};
    ngOnInit() {
      this.loadBooks(this.requestBook);
    }
    loadBooks(request: any){
      this.bookService.get(request).subscribe(
        {
          next: (res) => {
            if(res.body){
              console.log(res.body);
              this.dataSourceBook.data = res.body.content;
              this.pageIndexBook = res.body.page.number;
              this.totalBook = res.body.page.totalElements;
            }
          },
          error: (e) => {
            console.log(e);
          }
        }
      )
    }

    loadPersons(request: any){
      this.bookService.get(request).subscribe(
        {
          next: (res) => {
            if(res.body){
              console.log(res.body);
              this.dataSourcePerson.data = res.body.content;
              this.pageIndexPerson = res.body.page.number;
              this.totalPerson = res.body.page.totalElements;
            }
          },
          error: (e) => {
            console.log(e);
          }
        }
      )
    }

    loadLoans(request: any){
      this.bookService.get(request).subscribe(
        {
          next: (res) => {
            if(res.body){
              console.log(res.body);
              this.dataSourceBook.data = res.body.content;
              this.pageIndexBook = res.body.page.number;
              this.totalBook = res.body.page.totalElements;
            }
          },
          error: (e) => {
            console.log(e);
          }
        }
      )
    }
    nextPageBook(event: PageEvent) {
      this.requestBook = {
        page: event.pageIndex.toString(),
        size: event.pageSize.toString(),
      };
      this.loadBooks(this.requestBook);
    }
}

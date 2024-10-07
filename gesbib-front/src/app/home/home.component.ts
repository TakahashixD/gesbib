import { Component, inject, OnInit } from '@angular/core';
import { BookService } from '../shared/service/book.service';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { IBook } from '../shared/interface/book.interface';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { DatePipe } from '@angular/common';
import { LoaderService } from '../shared/service/loader.service';
import { PersonService } from '../shared/service/person.service';
import { IPerson } from '../shared/interface/person.interface';
import { ILoan } from '../shared/interface/loan.interface';
import { LoanService } from '../shared/service/loan.service';
import { MatIconButton } from '@angular/material/button';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialog } from '@angular/material/dialog';
import { DeleteDialogComponent } from '../components/delete-dialog/delete-dialog.component';
import { EditDialogComponent } from '../components/edit-dialog/edit-dialog.component';
import { CreateDialogComponent } from '../components/create-dialog/create-dialog.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatTableModule, MatPaginator, DatePipe, MatIconButton, MatIconModule, MatTooltipModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
    bookService = inject(BookService);
    personService = inject(PersonService);
    loanService = inject(LoanService);
    dialog = inject(MatDialog);
    loaderService = inject(LoaderService);
    
    book: string = "book";
    person: string = "person";
    loan:string = "loan";

    displayedColumnsBook: string[] = ['options', 'id', 'title', 'author', 'isbn', 'publishDate', 'category'];
    displayedColumnsPerson: string[] = ['options', 'id', 'name', 'email', 'signupDate', 'phone'];
    displayedColumnsLoan: string[] = ['options', 'id', 'personId', 'bookId', 'loanDate', 'returnDate', 'status'];

    dataSourceBook = new MatTableDataSource<IBook>();
    dataSourcePerson = new MatTableDataSource<IPerson>();
    dataSourceLoan = new MatTableDataSource<ILoan>();

    pageIndexBook: number = 0;
    totalBook: number = 0;

    pageIndexPerson: number = 0;
    totalPerson: number = 0;

    pageIndexLoan: number = 0;
    totalLoan: number = 0;

    requestBook = {};
    requestPerson = {};
    requestLoan = {};

    todayDate = new Date();
    ngOnInit() {
      this.loadBooks(this.requestBook);
      this.loadPersons(this.requestPerson);
      this.loadLoans(this.requestLoan);
    }

    loadBooks(request: any){
      this.loaderService.setLoading(true);
      this.bookService.get(request).subscribe(
        {
          next: (res) => {
            if(res.body){
              this.dataSourceBook.data = res.body.content;
              this.pageIndexBook = res.body.page.number;
              this.totalBook = res.body.page.totalElements;
              this.loaderService.setLoading(false);
            }
          },
          error: (e) => {
            this.loaderService.setLoading(false);
            console.log(e);
          }
        }
      )
    }

    loadPersons(request: any){
      this.personService.get(request).subscribe(
        {
          next: (res) => {
            if(res.body){
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
      this.loanService.get(request).subscribe(
        {
          next: (res) => {
            if(res.body){
              this.dataSourceLoan.data = res.body.content;
              this.pageIndexLoan = res.body.page.number;
              this.totalLoan = res.body.page.totalElements;
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

    nextPagePerson(event: PageEvent) {
      this.requestPerson = {
        page: event.pageIndex.toString(),
        size: event.pageSize.toString(),
      };
      this.loadPersons(this.requestPerson);
    }

    nextPageLoan(event: PageEvent) {
      this.requestLoan = {
        page: event.pageIndex.toString(),
        size: event.pageSize.toString(),
      };
      this.loadLoans(this.requestLoan);
    }

    openDialogDelete(id: number, type: String) {
      this.dialog.open(DeleteDialogComponent, {
        data: {id: id, type: type},
      }).afterClosed().subscribe({
        next: res => {
          if(type === "book"){
            this.loadBooks(this.requestBook);
          }
          if(type === "person"){
            this.loadPersons(this.requestPerson);
          }
        }
      });
    }

    openDialogEdit(element: any, type: String) {
      this.dialog.open(EditDialogComponent, {
        data: {element: element, type: type},
      }).afterClosed().subscribe({
        next: res => {
          if(type === "book"){
            this.loadBooks(this.requestBook);
          }
          if(type === "person"){
            this.loadPersons(this.requestPerson);
          }
          if(type === "person"){
            this.loadLoans(this.requestLoan);
          }
        }
      });
    }

    openDialogCreate(type: String) {
      this.dialog.open(CreateDialogComponent, {
        data: { type: type},
      }).afterClosed().subscribe({
        next: res => {
          if(type === "book"){
            this.loadBooks(this.requestBook);
          }
          if(type === "person"){
            this.loadPersons(this.requestPerson);
          }
          if(type === "loan"){
            this.loadLoans(this.requestLoan);
          }
        }
      });
    }
}

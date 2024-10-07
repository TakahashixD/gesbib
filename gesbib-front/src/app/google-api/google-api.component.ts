import { DatePipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatButton, MatIconButton } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { IGoogleBook } from '../shared/interface/google-book.interface';
import { BookService } from '../shared/service/book.service';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { Book } from '../shared/model/book.model';
import { SuccessDialogComponent } from '../components/success-dialog/success-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-google-api',
  standalone: true,
  imports: [MatTableModule, DatePipe, MatInputModule, MatButton, MatPaginator, FormsModule, MatFormFieldModule, ReactiveFormsModule, MatIconModule, MatIconButton],
  templateUrl: './google-api.component.html',
  styleUrl: './google-api.component.scss'
})
export class GoogleApiComponent {
  bookService = inject(BookService);
  router = inject(Router);
  dialog = inject(MatDialog);

  displayedColumnsBook: string[] = ['options', 'publishedDate', 'title', 'author', 'isbn', 'categories'];
  dataSourceBook = new MatTableDataSource<IGoogleBook>();
  searchFormControl = new FormControl('');

  pageIndexBook: number = 0;
  totalBook: number = 0;
  requestBook = {};

  searchBook(){
    const search = this.searchFormControl.getRawValue();
    if(!search){
      alert("empty search field");
      return;
    }
    this.bookService.searchGoogleAPI(search).subscribe({
      next: (res) =>{
        this.dataSourceBook.data = res.body ?? [];
        if(this.dataSourceBook.data.length === 0) alert("Nothing Found");
        this.totalBook = this.dataSourceBook.data.length ?? 0;
      },
      error: (err) =>{
        console.log(err);
        alert("error");
      }
    });
  }

  addTolib(element: IGoogleBook){
    if(element.authors && element.title && element.isbn && element.publishedDate && element.categories){
      const isbn = element.isbn.filter(s => s.type.includes("10"));
      if(isbn.length === 0) return;
      const createBook = new Book(
        element.title,
        element.authors.toString(),
        isbn[0].identifier,
        element.publishedDate,
        element.categories.toString()
      );
      this.bookService.create(createBook).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Book added to your list check in home page"});
        },
        error: err =>{
          alert("Error");
          console.log(err);
        }
      })
    }
  }

  navigateHome(){
    this.router.navigate(['/']);
  }
}

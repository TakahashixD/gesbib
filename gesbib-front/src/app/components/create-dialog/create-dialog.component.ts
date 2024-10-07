import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepicker, MatDatepickerModule } from '@angular/material/datepicker';
import { MAT_DIALOG_DATA, MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BookService } from '../../shared/service/book.service';
import { PersonService } from '../../shared/service/person.service';
import { Book } from '../../shared/model/book.model';
import { SuccessDialogComponent } from '../success-dialog/success-dialog.component';
import { Person } from '../../shared/model/person.model';
import { IBook } from '../../shared/interface/book.interface';
import { IPerson } from '../../shared/interface/person.interface';
import { MatSelectModule } from '@angular/material/select';
import { Loan } from '../../shared/model/loan.model';
import { LoanService } from '../../shared/service/loan.service';


@Component({
  selector: 'app-create-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatLabel,
    MatDatepickerModule, MatNativeDateModule, MatDatepicker, MatSelectModule],
  templateUrl: './create-dialog.component.html',
  styleUrl: './create-dialog.component.scss'
})
export class CreateDialogComponent implements OnInit{
  dialogRef = inject(MatDialogRef<CreateDialogComponent>);
  dialog = inject(MatDialog);
  data = inject(MAT_DIALOG_DATA);
  bookService = inject(BookService);
  personService = inject(PersonService);
  loanService = inject(LoanService);
  book: string = "book";
  person: string = "person";
  loan: string = "loan";
  
  bookForm = new FormGroup({
    title: new FormControl('', Validators.required),
    author: new FormControl('', Validators.required),
    isbn: new FormControl('', Validators.required),
    publishDate: new FormControl({value: '', disabled: true}, Validators.required),
    category: new FormControl('', Validators.required),
  })

  personForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    phone: new FormControl('', Validators.required),
  })

  loanForm = new FormGroup({
    personId: new FormControl('', Validators.required),
    bookId: new FormControl('', Validators.required),
    returnDate: new FormControl({value: '', disabled: true}, Validators.required)
  })

  booksList: IBook[] = [];
  personsList: IPerson[] = [];

  todayDate = new Date();
  
  ngOnInit() {
    if(this.data.type === this.loan){
      this.bookService.getAll().subscribe({
        next: res => {
          this.booksList = res.body ?? [];
        },
        error: err => {
          
        }
      });
      this.personService.getAll().subscribe({
        next: res => {
          this.personsList = res.body ?? [];
        },
        error: err => {

        }
      });
    }
  }
  createElement(){
    if(this.data.type === "book" && this.bookForm.valid){
      const fromForm = this.bookForm.getRawValue();
      const createBook = new Book(
        fromForm.title ?? "",
        fromForm.author ?? "",
        fromForm.isbn ?? "",
        fromForm.publishDate ? new Date(fromForm.publishDate) : new Date(),
        fromForm.category ?? ""
      );
      this.bookService.create(createBook).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Book created"}).afterClosed().subscribe({
            next: () => this.dialogRef.close()
          });
        },
        error: err =>{
          alert("Error");
          console.log(err);
          this.dialogRef.close();
        }
      })
    }
    if(this.data.type === "person" && this.personForm.valid){
      const fromForm = this.personForm.getRawValue();
      const createPerson = new Person(
        fromForm.name ?? "",
        fromForm.email ?? "",
        new Date(),
        fromForm.phone ?? "",
      );
      this.personService.create(createPerson).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Person created"}).afterClosed().subscribe({
            next: () => this.dialogRef.close()
          });
        },
        error: err =>{
          alert("Error");
          console.log(err);
          this.dialogRef.close();
        }
      })
    }
    if(this.data.type === "loan" && this.loanForm.valid){
      const fromForm = this.loanForm.getRawValue();
        const createLoan = new Loan(
          Number(fromForm.personId),
          Number(fromForm.bookId),
          new Date(),
          fromForm.returnDate ? new Date(fromForm.returnDate) : new Date(),
          true
        );
        this.loanService.create(createLoan).subscribe({
          next: res =>{
            this.dialog.open(SuccessDialogComponent, {data: "Loan created"}).afterClosed().subscribe({
              next: () => this.dialogRef.close()
            });
          },
          error: err =>{
            alert("Error");
            console.log(err);
            this.dialogRef.close();
          }
        })
      }
  }
  disableSave(){
    return (this.bookForm.invalid || this.bookForm.get('publishDate')?.value == '') && this.personForm.invalid && (this.loanForm.invalid || this.loanForm.get('returnDate')?.value == '');
  }
}

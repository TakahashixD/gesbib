import { Component, inject } from '@angular/core';
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


@Component({
  selector: 'app-create-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatLabel,
    MatDatepickerModule, MatNativeDateModule, MatDatepicker],
  templateUrl: './create-dialog.component.html',
  styleUrl: './create-dialog.component.scss'
})
export class CreateDialogComponent {
  dialogRef = inject(MatDialogRef<CreateDialogComponent>);
  dialog = inject(MatDialog);
  data = inject(MAT_DIALOG_DATA);
  bookService = inject(BookService);
  personService = inject(PersonService);

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
  teste(){
    console.log(this.bookForm.invalid || this.personForm.invalid)
    // console.log(this.personForm.invalid)
  }
  createElement(){
    if(this.data.type === "book" && this.bookForm.valid){
      const fromForm = this.bookForm.getRawValue();
      const updateBook = new Book(
        fromForm.title ?? "",
        fromForm.author ?? "",
        fromForm.isbn ?? "",
        fromForm.publishDate ? new Date(fromForm.publishDate) : new Date(),
        fromForm.category ?? ""
      );
      this.bookService.create(updateBook).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Book created"});
          this.dialogRef.close();
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
      const updatePerson = new Person(
        fromForm.name ?? "",
        fromForm.email ?? "",
        new Date(),
        fromForm.phone ?? "",
      );
      this.personService.create(updatePerson).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Person created"});
          this.dialogRef.close();
        },
        error: err =>{
          alert("Error");
          console.log(err);
          this.dialogRef.close();
        }
      })
    }
  }
}

import { Component, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { BookService } from '../../shared/service/book.service';
import { PersonService } from '../../shared/service/person.service';
import { SuccessDialogComponent } from '../success-dialog/success-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { IBook } from '../../shared/interface/book.interface';
import { Book } from '../../shared/model/book.model';
import { Person } from '../../shared/model/person.model';
import { MatDatepicker, MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-edit-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent, ReactiveFormsModule, MatFormFieldModule, MatInputModule, MatLabel,
     MatDatepickerModule, MatNativeDateModule, MatDatepicker],
  templateUrl: './edit-dialog.component.html',
  styleUrl: './edit-dialog.component.scss'
})
export class EditDialogComponent {
  dialogRef = inject(MatDialogRef<EditDialogComponent>);
  dialog = inject(MatDialog);
  data = inject(MAT_DIALOG_DATA);
  bookService = inject(BookService);
  personService = inject(PersonService);

  book: string = "book";
  person: string = "person";

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
    signupDate: new FormControl({value: '', disabled: true}, Validators.required),
    phone: new FormControl('', Validators.required),
  })
  teste(){
    console.log(this.bookForm.invalid || this.personForm.invalid)
    // console.log(this.personForm.invalid)
  }
  editElement(){
    if(this.data.type === "book" && this.bookForm.valid){
      const fromForm = this.bookForm.getRawValue();
      const updateBook = new Book(
        fromForm.title ?? this.data.element.title,
        fromForm.author ?? this.data.element.author,
        fromForm.isbn ?? this.data.element.isbn,
        fromForm.publishDate ?? this.data.element.publishDate,
        fromForm.category ?? this.data.element.category,
        this.data.element.id
      );
      this.bookService.update(updateBook).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Book Edited"});
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
        fromForm.name ?? this.data.element.name,
        fromForm.email ?? this.data.element.email,
        fromForm.signupDate ?? this.data.element.signupDate,
        fromForm.phone ?? this.data.element.phone,
        this.data.element.id
      );
      this.personService.update(updatePerson).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Person Edited"});
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

import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialog, MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { BookService } from '../../shared/service/book.service';
import { PersonService } from '../../shared/service/person.service';
import { SuccessDialogComponent } from '../success-dialog/success-dialog.component';

@Component({
  selector: 'app-delete-dialog',
  standalone: true,
  imports: [MatButtonModule, MatDialogActions, MatDialogClose, MatDialogTitle, MatDialogContent],
  templateUrl: './delete-dialog.component.html',
  styleUrl: './delete-dialog.component.scss'
})
export class DeleteDialogComponent {
  dialogRef = inject(MatDialogRef<DeleteDialogComponent>);
  dialog = inject(MatDialog);
  data = inject(MAT_DIALOG_DATA);
  bookService = inject(BookService);
  personService = inject(PersonService);

  deleteElement(){
    if(this.data.type === "book"){
      this.bookService.delete(this.data.id).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Book Removed"});
          this.dialogRef.close();
        },
        error: err =>{
          this.dialogRef.close();
        }
      })
    }
    if(this.data.type === "person"){
      this.personService.delete(this.data.id).subscribe({
        next: res =>{
          this.dialog.open(SuccessDialogComponent, {data: "Person Removed"});
          this.dialogRef.close();
        },
        error: err =>{
          this.dialogRef.close();
        }
      })
    }
  }
}

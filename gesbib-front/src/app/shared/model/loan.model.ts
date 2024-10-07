import { ILoan } from '../interface/loan.interface';

export class Book implements ILoan{
    constructor(
        public id: number,
        public personId: number,
        public bookId: number,
        public loanDate: Date,
        public returnDate: Date,
        public status: boolean,
    ){
        this.id = id;
        this.personId = personId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }    
}
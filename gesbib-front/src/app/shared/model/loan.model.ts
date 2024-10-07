import { ILoan } from '../interface/loan.interface';

export class Loan implements ILoan{
    constructor(
        public personId: number,
        public bookId: number,
        public loanDate: Date,
        public returnDate: Date,
        public status: boolean,
        public id?: number,
    ){
        this.personId = personId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
        this.id = id;
    }    
}
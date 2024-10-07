export interface ILoan{
    id: number;
    personId: number;
    bookId: number;
    loanDate: Date;
    returnDate: Date;
    status: boolean;
}
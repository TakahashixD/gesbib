import { IPerson } from '../interface/person.interface';

export class Person implements IPerson{
    constructor(
        public name: string,
        public email: string,
        public signupDate: Date,
        public phone: string,
        public id?: number
    ){
        this.name = name;
        this.email = email;
        this.signupDate = signupDate;
        this.phone = phone;
        this.id = id;
    }    
}
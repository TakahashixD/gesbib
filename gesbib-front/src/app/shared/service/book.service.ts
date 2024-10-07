import { inject, Injectable } from '@angular/core';

import { HttpClient, HttpResponse } from '@angular/common/http';

import { map, Observable } from 'rxjs';
import { IBook } from '../interface/book.interface';
import { ApplicationConfigService } from '../config/application-config';
import { IPage } from '../interface/page.interface';
import { IGoogleBook } from '../interface/google-book.interface';

export type EntityResponseType = HttpResponse<IBook>;

export type EntityArrayResponseType = HttpResponse<IBook[]>;

@Injectable({ providedIn: 'root' })
export class BookService {
    http = inject(HttpClient);
    applicationConfigService = inject(ApplicationConfigService);
    resourceUrl: string = this.applicationConfigService.getEndpointFor('api/book/v1');    
    
    create(book: IBook): Observable<EntityResponseType> {
        return this.http.post<IBook>(this.resourceUrl, book, { observe: 'response' })
    }

    update(book: IBook): Observable<EntityResponseType> {
        const pkg = this.convertDate(book);
        return this.http.put<IBook>(`${this.resourceUrl}/${book.id}`, pkg, { observe: 'response' });
    }

    getAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IBook[]>(`${this.resourceUrl}/all`, {  observe: 'response' });
    }

    get(req?: any): Observable<HttpResponse<IPage>> {
        return this.http.get<IPage>(`${this.resourceUrl}`, { params: req, observe: 'response' });
    }

    searchGoogleAPI(req: string): Observable<HttpResponse<IGoogleBook[]>> {
        const search = {title: req};
        return this.http.get<IGoogleBook[]>(`${this.resourceUrl}/search`, { params: search, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    convertDate(book: IBook){
        const convertedBook = {
            id: book.id,
            title: book.title,
            author: book.author,
            isbn: book.isbn,
            publishDate: book.publishDate.toISOString(),
            category: book.category
        }
        return convertedBook;
    }
}

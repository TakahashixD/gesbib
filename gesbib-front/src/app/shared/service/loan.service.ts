import { inject, Injectable } from '@angular/core';

import { HttpClient, HttpResponse } from '@angular/common/http';

import { map, Observable } from 'rxjs';
import { IPerson } from '../interface/person.interface';
import { ApplicationConfigService } from '../config/application-config';
import { IPage } from '../interface/page.interface';
import { ILoan } from '../interface/loan.interface';

export type EntityResponseType = HttpResponse<ILoan>;

export type EntityArrayResponseType = HttpResponse<ILoan[]>;

@Injectable({ providedIn: 'root' })
export class LoanService {
    http = inject(HttpClient);
    applicationConfigService = inject(ApplicationConfigService);
    resourceUrl: string = this.applicationConfigService.getEndpointFor('api/loan/v1');    
    
    create(loan: ILoan): Observable<EntityResponseType> {
        return this.http.post<ILoan>(this.resourceUrl, loan, { observe: 'response' });
    }

    update(loan: ILoan): Observable<EntityResponseType> {
        return this.http.patch<ILoan>(`${this.resourceUrl}/${loan.id}`, loan, { observe: 'response' });
    }

    get(req?: any): Observable<HttpResponse<IPage>> {
        return this.http.get<IPage>(`${this.resourceUrl}`, { params: req, observe: 'response' });
    }
}

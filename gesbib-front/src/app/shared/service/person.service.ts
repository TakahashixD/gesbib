import { inject, Injectable } from '@angular/core';

import { HttpClient, HttpResponse } from '@angular/common/http';

import { map, Observable } from 'rxjs';
import { IPerson } from '../interface/person.interface';
import { ApplicationConfigService } from '../config/application-config';
import { IPage } from '../interface/page.interface';

export type EntityResponseType = HttpResponse<IPerson>;

export type EntityArrayResponseType = HttpResponse<IPerson[]>;

@Injectable({ providedIn: 'root' })
export class PersonService {
    http = inject(HttpClient);
    applicationConfigService = inject(ApplicationConfigService);
    resourceUrl: string = this.applicationConfigService.getEndpointFor('api/person/v1');    
    
    create(person: IPerson): Observable<EntityResponseType> {
        return this.http.post<IPerson>(this.resourceUrl, person, { observe: 'response' });
    }

    update(person: IPerson): Observable<EntityResponseType> {
        return this.http.put<IPerson>(`${this.resourceUrl}/${person.id}`, person, { observe: 'response' });
    }

    get(req?: any): Observable<HttpResponse<IPage>> {
        return this.http.get<IPage>(`${this.resourceUrl}`, { params: req, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

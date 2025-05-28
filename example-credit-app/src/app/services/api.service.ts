import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private httpClient: HttpClient) {

  }

  getByNumeroNfse(numeroNfse: string): Observable<any> {
    return this.httpClient.get(environment.apiUrl + '/creditos/' + numeroNfse);
  }

  getByNumeroCredito(numeroCredito: string): Observable<any> {
    return this.httpClient.get(environment.apiUrl + '/creditos/credito/' + numeroCredito);
  }

}

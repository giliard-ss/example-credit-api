import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private url = "http://localhost:8089/api"

  constructor(private httpClient: HttpClient) {

  }

  getByNumeroNfse(numeroNfse: string): Observable<any> {
    return this.httpClient.get(this.url + '/creditos/' + numeroNfse);
  }

  getByNumeroCredito(numeroCredito: string): Observable<any> {
    return this.httpClient.get(this.url + '/creditos/credito/' + numeroCredito);
  }

}

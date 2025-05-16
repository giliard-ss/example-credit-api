import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { ApiService } from '../../services/api.service';
import { Credito } from '../../interfaces/credito';
import { BrCurrencyPipe } from '../../pipes/br-currency.pipe';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatInput } from '@angular/material/input';
import { MatCard, MatCardContent } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbar } from '@angular/material/toolbar';
import { HttpErrorResponse } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-home',
  imports: [MatTableModule, CommonModule, BrCurrencyPipe, MatLabel, MatFormField, ReactiveFormsModule, MatInput, MatCardContent, MatCard, MatButtonModule,
    MatToolbar, MatProgressSpinnerModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  private apiService = inject(ApiService);
  private fb = inject(FormBuilder);
  private _snackBar = inject(MatSnackBar);

  columnsToDisplay = ['numeroCredito', 'numeroNfse', 'dataConstituicao', 'valorIssqn', 'tipoCredito', 'simplesNacional', 'aliquota', 'valorFaturado', 'valorDeducao', 'baseCalculo'];
  dataSource: Credito[] = []

  form: FormGroup = this.fb.group({
    numeroNfse: [''],
    numeroCredito: ['']
  });

  loading = false;



  ngOnInit(): void {

  }

  search() {
    const { numeroNfse, numeroCredito } = this.form.value;
    this.dataSource = [];

    if (numeroCredito) {
      this.searchByNumeroCredito(numeroCredito);
    } else if (numeroNfse) {
      this.searchByNumeroNfse(numeroNfse);
    }
  }

  clear() {
    this.form.reset();
  }

  get disableButtons(): boolean {
    const form = this.form.controls;
    const existsValue = !!form['numeroNfse'].value || !!form['numeroCredito'].value;
    return this.loading || !existsValue;
  }

  private searchByNumeroCredito(numeroCredito: string) {
    this.switchLoading();
    this.apiService.getByNumeroCredito(numeroCredito)

      .pipe(finalize(() => this.switchLoading()))
      .subscribe({
        next: (data: Credito) => {
          this.dataSource = [data];

        }, error: (e) => this.catchError(e)
      })
  }

  private searchByNumeroNfse(numeroNfse: string) {
    this.switchLoading();
    this.apiService.getByNumeroNfse(numeroNfse)
      .pipe(finalize(() => this.switchLoading()))
      .subscribe({
        next: (data: Credito[]) => {
          this.dataSource = data;
          if(data.length==0) {
              this._snackBar.open("Nenhum recurso encontrado.", "Fechar", { duration: 3000 })
          }

        }, error: (e) => this.catchError(e)

      });
  }

  private catchError(error: HttpErrorResponse) {
    if (error.status == 404) {
      this._snackBar.open("Recurso não encontrado.", "Fechar", { duration: 3000 })
    } else {
      this._snackBar.open("Falha interna.", "Fechar", { duration: 3000 })
      console.log(error);
    }
  }

  private switchLoading() {
    console.log("actual: " + this.loading);
    this.loading = !this.loading;
  }



}

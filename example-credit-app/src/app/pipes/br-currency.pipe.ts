import { CurrencyPipe } from '@angular/common';
import { inject, Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'brCurrency'
})
export class BrCurrencyPipe implements PipeTransform {
  private currency = inject(CurrencyPipe);

  transform(value: number): unknown {
    return this.currency.transform(value, 'BRL', 'symbol', '1.2-2');
  }

}

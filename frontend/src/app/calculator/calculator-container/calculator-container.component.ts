import { Component } from '@angular/core';
import { CalculatorService } from '../calculator.service';

@Component({
  selector: 'app-calculator-container',
  templateUrl: './calculator-container.component.html',
  styleUrls: ['./calculator-container.component.scss']
})
export class CalculatorContainerComponent {
  result: number | undefined;

  constructor(private calculatorService: CalculatorService) {}

  performCalculation(operation: string, numberOne: number, numberTwo: number): void {
    this.calculatorService.calculate(operation, numberOne, numberTwo)
      .subscribe(response => {
        console.log("response:::" + response)
        this.result = response.result
      });
  }
}

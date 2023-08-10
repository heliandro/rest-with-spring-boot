import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-calculator-presentation',
  templateUrl: './calculator-presentation.component.html',
  styleUrls: ['./calculator-presentation.component.scss']
})
export class CalculatorPresentationComponent {
  @Input() result: number | undefined;
  @Output() calculateClicked = new EventEmitter<{ operation: string, numberOne: number, numberTwo: number }>();

  operation: string = 'addition';
  numberOne: number | undefined;
  numberTwo: number | undefined;

  onCalculateClicked(): void {
    if (this.operation && this.numberOne !== undefined && this.numberTwo !== undefined) {
      this.calculateClicked.emit({ operation: this.operation, numberOne: this.numberOne, numberTwo: this.numberTwo });
    }
  }
}

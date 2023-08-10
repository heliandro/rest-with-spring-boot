import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CalculatorContainerComponent } from './calculator-container/calculator-container.component';
import { CalculatorRoutingModule } from './calculator-routing.module';
import { CalculatorPresentationComponent } from './calculator-presentation/calculator-presentation.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    CalculatorContainerComponent,
    CalculatorPresentationComponent
  ],
  imports: [
    CommonModule,
    CalculatorRoutingModule,
    FormsModule
  ]
})
export class CalculatorModule { }

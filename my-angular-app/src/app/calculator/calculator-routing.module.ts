import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CalculatorContainerComponent } from './calculator-container/calculator-container.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: CalculatorContainerComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalculatorRoutingModule { }

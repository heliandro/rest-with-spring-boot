import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  private baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  calculate(operation: string, numberOne: number, numberTwo: number): Observable<{ result: number }> {
    const url = `${this.baseUrl}/${operation}/${numberOne}/${numberTwo}`;
    return this.http.get<{ result: number }>(url);
  }
}
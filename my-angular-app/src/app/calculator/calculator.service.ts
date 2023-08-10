import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CalculatorService {
  private baseUrl = 'http://calculator.127.0.0.1.nip.io:8080';

  constructor(private http: HttpClient) {}

  calculate(operation: string, numberOne: number, numberTwo: number): Observable<{ result: number }> {
    const url = `${this.baseUrl}/calculator/${operation}/${numberOne}/${numberTwo}`;
    return this.http.get<{ result: number }>(url);
  }
}
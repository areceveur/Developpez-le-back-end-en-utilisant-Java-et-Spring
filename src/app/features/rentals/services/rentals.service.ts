import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rental } from 'src/app/features/rentals/interfaces/rental.interface';
import { RentalResponse } from '../interfaces/api/rentalResponse.interface';
import { RentalsResponse } from '../interfaces/api/rentalsResponse.interface';
import {environment} from "../../../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class RentalsService {

  private pathService = `${environment.baseUrl}/api/rentals`;

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<RentalsResponse> {
    return this.httpClient.get<RentalsResponse>(`${this.pathService}/all`);
  }

  public detail(id: string): Observable<Rental> {
    return this.httpClient.get<Rental>(`${this.pathService}/detail/${id}`);
  }

  public create(form: FormData): Observable<RentalResponse> {
    return this.httpClient.post<RentalResponse>(`${this.pathService}/create`, form);
  }

  public update(id: string, form: FormData): Observable<RentalResponse> {
    return this.httpClient.put<RentalResponse>(`${this.pathService}/detail/${id}`, form);
  }
}

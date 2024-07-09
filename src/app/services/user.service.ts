import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import {AuthSuccess} from "../features/auth/interfaces/authSuccess.interface";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private pathService = '/auth';

  constructor(private httpClient: HttpClient) { }

  public login(user: User): Observable<User> {
    return this.httpClient.post<User>(`${this.pathService}/login`, user);
  }


  public getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }
}

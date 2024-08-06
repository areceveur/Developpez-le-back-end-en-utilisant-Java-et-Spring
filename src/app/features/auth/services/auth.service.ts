import {HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { AuthSuccess  } from '../interfaces/authSuccess.interface';
import { RegisterRequest } from '../interfaces/registerRequest.interface';
import { User } from 'src/app/interfaces/user.interface';
import {TokenResponse} from "../../../interfaces/TokenReponse.interface";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = '/api/auth';

  constructor(private httpClient: HttpClient,
              private router: Router) { }

  public register(registerRequest: RegisterRequest): Observable<AuthSuccess> {
    const params = new HttpParams()
      .set('email', registerRequest.email)
      .set('name', registerRequest.username)
      .set('password', registerRequest.password);
    return this.httpClient.post<AuthSuccess>(`${this.pathService}/register`, null, { params });
  }

  public login(loginRequest: LoginRequest): Observable<TokenResponse> {
    const params = new HttpParams()
      .set('email', loginRequest.email)
      .set('password', loginRequest.password);

    return this.httpClient.post<TokenResponse>(`${this.pathService}/login`, null, { params });
  }

  public me(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }

  public logout(): void {
    localStorage.removeItem('token');
  }
}

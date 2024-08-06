import {HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageRequest } from '../interfaces/api/messageRequest.interface';
import { MessageResponse } from '../interfaces/api/messageResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class MessagesService {

  private pathService = 'api/messages';

  constructor(private httpClient: HttpClient) { }

  public send(messageRequest: MessageRequest): Observable<MessageResponse> {
    const params = new HttpParams()
      .set('message', messageRequest.message)
      .set('rental', messageRequest.rental_id.toString());
    return this.httpClient.post<MessageResponse>(this.pathService, null, { params: params });
  }
}

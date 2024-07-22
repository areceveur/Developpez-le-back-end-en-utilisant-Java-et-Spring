import {Component} from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { RentalsService } from '../../services/rentals.service';
import {Observable} from "rxjs";
import {RentalsResponse} from "../../interfaces/api/rentalsResponse.interface";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public rentals$: Observable<RentalsResponse>;

  constructor(
    private sessionService: SessionService,
    private rentalsService: RentalsService
  ) {
    this.rentals$ = this.rentalsService.all();
    this.rentals$.subscribe((response) => {
      console.log(response);
      response.forEach(rental => {
        console.log(rental.picture);
      })
    });
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}


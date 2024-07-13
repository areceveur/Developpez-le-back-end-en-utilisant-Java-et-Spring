import {Component, OnInit} from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { RentalsService } from '../../services/rentals.service';
import {Rental} from "../../interfaces/rental.interface";
import {tap} from "rxjs";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent {

  public rentals$ = this.rentalsService.all().pipe(
    tap(rentals => console.log('Rentals: ', rentals))
  );

  constructor(
    private sessionService: SessionService,
    private rentalsService: RentalsService
  ) { }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}


import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/features/auth/services/auth.service';
import { User } from 'src/app/interfaces/user.interface';
import {Router} from "@angular/router";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user: User | undefined;

  constructor(private authService: AuthService,
              private router :Router) { }

  public ngOnInit(): void {
    this.authService.me().subscribe(
      (user: User) => this.user = user
    )
  }

  public back() {
    window.history.back();
  }

  public updateUser() {
    this.router.navigate(['/update-user']);
  }

  public deleteUser() {
    this.authService.delete().subscribe(() => {
      console.log("User deleted successfully");
      this.authService.logout();
      this.router.navigate(['/login'])
    })
  }
}

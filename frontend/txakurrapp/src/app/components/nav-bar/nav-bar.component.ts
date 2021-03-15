import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/classes/auth/user';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  user!: User;
  roles!: string[];
  isLoggedIn = false;
  showPetOwnerBoard = false;
  showBusinessBoard = false;
  username!: string;

  constructor(
    private tokenService: TokenStorageService,
    private router: Router,
  ) { }

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showPetOwnerBoard = this.roles.includes('ROLE_PETOWNER');
      this.showBusinessBoard = this.roles.includes('ROLE_BUSINESS');

      this.username = user.username;
    }
  }

  redirectIfNotLogged(route: string): string {
    let userStored = this.tokenService.getUser();
    if (userStored != "") {
      return route;
    }
    return "/user/login";
  }

  logout(): void {
    this.tokenService.signOut();
    this.redirect();
  }

  redirect(): void {
    this.router.navigate(['/home']).then(() => {
      window.location.reload();
    });
  }

}

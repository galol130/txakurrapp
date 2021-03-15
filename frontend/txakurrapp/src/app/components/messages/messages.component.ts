import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { PetOwner } from 'src/app/classes/petowner/pet-owner';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  roles!: string[];
  isLoggedIn = false;
  showPetOwnerMessages = false;
  showBusinessOwnerMessages = false;
  petOwner!: PetOwner;
  businessOwner!: BusinessOwner;

  hasFinishedRegister = false;
  @Output() userId!: number;

  constructor(
    private tokenService: TokenStorageService,
    private petOwnerSetvice: PetOwnerService,
    private businessOwnerService: BusinessOwnerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showPetOwnerMessages = this.roles.includes('ROLE_PETOWNER');
      this.showBusinessOwnerMessages = this.roles.includes('ROLE_BUSINESS');

      if (this.showPetOwnerMessages) {
        this.loadPetOwner(user.id);
      }

      if (this.showBusinessOwnerMessages) {
        this.loadBusiness(user.id);
      }

    } else {
      this.router.navigate(['/user/login']);
    }
  }

  loadPetOwner(userId: number): void {
    this.petOwnerSetvice.getPetOwnerFromUserId(userId).subscribe(response => {
      if (response.ok) {
        this.petOwner = response.body as PetOwner;
        this.hasFinishedRegister = true;
      } else {
        //If is logged in but has not finished the registration process, send the user to '/user/account' that will handle that situation
        this.router.navigate(['/user/account'])
      }
    });
  }


  loadBusiness(userId: number): void {
    this.businessOwnerService.getBusinessOwnerFromUserId(userId).subscribe(response => {
      if (response.ok) {
        this.businessOwner = response.body as BusinessOwner;
        this.hasFinishedRegister = true;
      } else {
        //If is logged in but has not finished the registration process, send the user to '/user/account' that will handle that situation
        this.router.navigate(['/user/account'])
      }
    })
  }

}

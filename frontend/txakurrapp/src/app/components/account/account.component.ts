import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { PetOwner } from 'src/app/classes/petowner/pet-owner';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  roles!: string[];
  isLoggedIn = false;
  showPetOwnerBoard = false;
  showBusinessBoard = false;
  username!: string;

  @Output() hasFinishedRegister = false;
  @Output() petOwner!: PetOwner;
  @Output() businessOwner!: BusinessOwner;

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
      
      this.showPetOwnerBoard = this.roles.includes('ROLE_PETOWNER');
      this.showBusinessBoard = this.roles.includes('ROLE_BUSINESS');
      
      this.username = user.username;

      if (this.showPetOwnerBoard) {
        this.loadPetOwner(user.id);
      }
      if (this.showBusinessBoard) {
        this.loadBusiness(user.id);
      }


    }else{
      this.router.navigate(['/user/login']);
    }

  }


  loadPetOwner(userId: number): void {
    this.petOwnerSetvice.getPetOwnerFromUserId(userId).subscribe(response => {
      if (response.ok) {
        this.petOwner = response.body as PetOwner;
        console.log(this.petOwner);
        this.hasFinishedRegister = true;
      }
    });
  }


  loadBusiness(userId: number): void{
    this.businessOwnerService.getBusinessOwnerFromUserId(userId).subscribe(response => {
      if(response.ok){
        this.businessOwner = response.body as BusinessOwner;
        console.log(this.businessOwner);
        this.hasFinishedRegister = true;
      }
    })
  }
}

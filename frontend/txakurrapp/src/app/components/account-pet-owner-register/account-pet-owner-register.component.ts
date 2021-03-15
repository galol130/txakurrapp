import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';

@Component({
  selector: 'app-account-pet-owner-register',
  templateUrl: './account-pet-owner-register.component.html',
  styleUrls: ['./account-pet-owner-register.component.css']
})
export class AccountPetOwnerRegisterComponent implements OnInit {
  profilePicture: string = '../../../assets/images/userPicture.png';

  secondFormGroup: FormGroup;
  firstName: FormControl;
  lastName: FormControl;
  personalId: FormControl;
  birthDate: FormControl;

  thirdFormGroup: FormGroup;
  phone: FormControl;
  street: FormControl;
  streetNumber: FormControl;
  apt: FormControl;
  ohterInfo: FormControl;
  postalCode: FormControl;
  city: FormControl;
  province: FormControl;
  country: FormControl

  fourthFormGroup: FormGroup;


  constructor(
    private tokenService: TokenStorageService,
    private router: Router,
    private petOwnerSetvice: PetOwnerService,
  ) {

    this.firstName = new FormControl('', [Validators.required]);
    this.lastName = new FormControl('', [Validators.required]);
    this.personalId = new FormControl('', [Validators.minLength(6), Validators.maxLength(9)]);
    this.birthDate = new FormControl('');
    this.secondFormGroup = new FormGroup({
      firstName: this.firstName,
      lastName: this.lastName,
      personalId: this.personalId,
      birthDate: this.birthDate,
    });

    this.phone = new FormControl('', [Validators.required, Validators.minLength(6)]);
    this.street = new FormControl('');
    this.streetNumber = new FormControl('');
    this.apt = new FormControl('');
    this.ohterInfo = new FormControl('');
    this.postalCode = new FormControl('');
    this.city = new FormControl('');
    this.province = new FormControl('');
    this.country = new FormControl('');
    this.thirdFormGroup = new FormGroup({
      phone: this.phone,
      street: this.street,
      streetNumber: this.streetNumber,
      apt: this.apt,
      otherInfo: this.ohterInfo,
      postalCode: this.postalCode,
      city: this.city,
      province: this.province,
      country: this.country,
    });

    this.fourthFormGroup = new FormGroup({});
  }

  ngOnInit(): void {
  }

  readUrl(event: any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event: any) => {
        this.profilePicture = event.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  onSubmit(): void {
    if (this.secondFormGroup.valid && this.thirdFormGroup.valid && this.fourthFormGroup.valid) {

      const user = this.tokenService.getUser();
      this.petOwnerSetvice.createPetOwner(user.id, this.secondFormGroup, this.thirdFormGroup, this.profilePicture).subscribe(response => {
        if (response.ok) {
          this.redirect();
        }
      });
    }
  }

  redirect(): void {
    this.router.navigate(['/user/account']).then(() => {
      window.location.reload();
    });
  }



}


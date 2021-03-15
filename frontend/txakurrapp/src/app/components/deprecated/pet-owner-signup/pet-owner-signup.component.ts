// import { Component, OnInit } from '@angular/core';
// import { FormControl, FormGroup, Validators } from '@angular/forms';

// @Component({
//   selector: 'app-pet-owner-signup',
//   templateUrl: './pet-owner-signup.component.html',
//   styleUrls: ['./pet-owner-signup.component.css']
// })
// export class PetOwnerSignupComponent implements OnInit {

//   profilePicture: string = '../../../assets/images/userPicture.png';

//   firstFormGroup: FormGroup;
//   email: FormControl;
//   password: FormControl;

//   secondFormGroup: FormGroup;
//   firstName: FormControl;
//   lastName: FormControl;
//   personalId: FormControl;

//   thirdFormGroup: FormGroup;
//   phone: FormControl;
//   street: FormControl;
//   streetNumber: FormControl;
//   apt: FormControl;
//   ohterInfo: FormControl;
//   postalCode: FormControl;
//   city: FormControl;
//   province: FormControl;
//   country: FormControl

//   fourthFormGroup: FormGroup;



//   constructor() { 
//     this.email = new FormControl('', [Validators.required, Validators.email]);
//     this.password = new FormControl('', [Validators.required]);

//     this.firstFormGroup = new FormGroup({
//       email: this.email,
//       password: this.password
//     });

//     this.firstName = new FormControl('', [Validators.required]);
//     this.lastName = new FormControl('', [Validators.required]);
//     this.personalId = new FormControl('');
//     this.secondFormGroup = new FormGroup({
//       firstName: this.firstName,
//       lastName: this.lastName,
//       personalId: this.personalId
//     });

//     this.phone = new FormControl('', [Validators.required]);
//     this.street = new FormControl('');
//     this.streetNumber = new FormControl('');
//     this.apt = new FormControl('');
//     this.ohterInfo = new FormControl('');
//     this.postalCode = new FormControl('');
//     this.city = new FormControl('');
//     this.province = new FormControl('');
//     this.country = new FormControl('');
//     this.thirdFormGroup = new FormGroup({
//       phone: this.phone,
//       street: this.street,
//       streetNumber: this.streetNumber,
//       apt: this.apt,
//       otherInfo: this.ohterInfo,
//       postalCode: this.postalCode,
//       city: this.city,
//       province: this.province,
//       country: this.country,
//     });

//     this.fourthFormGroup = new FormGroup({});
//   }

//   ngOnInit(): void {
//   }

//   readUrl(event: any) {
//     if (event.target.files && event.target.files[0]) {
//       var reader = new FileReader();
//       reader.onload = (event: any) => {
//         this.profilePicture = event.target.result;
//       }
//       reader.readAsDataURL(event.target.files[0]);
//     }
//   }

// }

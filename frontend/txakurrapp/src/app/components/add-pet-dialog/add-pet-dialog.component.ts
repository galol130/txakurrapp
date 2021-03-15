import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';

@Component({
  selector: 'app-add-pet-dialog',
  templateUrl: './add-pet-dialog.component.html',
  styleUrls: ['./add-pet-dialog.component.css']
})
export class AddPetDialogComponent {

  isSubmitted = false;

  petPicture: string = '../../../assets/images/petPicture.png';

  addPetForm: FormGroup;

  type: FormControl;
  name: FormControl;
  breed: FormControl;
  birthDate: FormControl;
  weight: FormControl;
  otherInfo: FormControl;


  constructor(
    public dialogRef: MatDialogRef<AddPetDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    private tokenService: TokenStorageService,
    private snackBar: MatSnackBar,
    private petOwnerSetvice: PetOwnerService,
  ) {
    this.type = new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(40)]);
    this.name = new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]);
    this.breed = new FormControl('', [Validators.maxLength(40)]);
    this.birthDate = new FormControl('', [Validators.required]);
    this.weight = new FormControl('', [Validators.min(1)]);
    this.otherInfo = new FormControl('');

    this.addPetForm = new FormGroup({
      type: this.type,
      name: this.name,
      breed: this.breed,
      birthDate: this.birthDate,
      weight: this.weight,
      otherInfo: this.otherInfo
    })
  }

  readUrl(event: any) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.onload = (event: any) => {
        this.petPicture = event.target.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (this.addPetForm.valid) {
      this.isSubmitted = true;
      const user = this.tokenService.getUser();

      this.petOwnerSetvice.addPet(user.id, this.addPetForm, this.petPicture).subscribe(response => {
        if (response.ok) {
          this.openSnackBar("Pet added!", "OK");
        }
      });
    }
  }


  openSnackBar(message: string, action: string) {
    let snackBarMessage = this.snackBar.open(message, action, {
      duration: 3500,
    });
    snackBarMessage.onAction().subscribe(() => {
      this.dialogRef.close();
    });
    snackBarMessage.afterDismissed().subscribe(() => {
      this.dialogRef.close();
    });
  }
}

export interface DialogData {
  name: string;
  type: string;
  picName: string;
  breed: string;
  birthDate: Date;
  weight: number;
  otherInfo: string;
}

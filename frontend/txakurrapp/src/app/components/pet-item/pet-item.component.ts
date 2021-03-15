import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Image } from 'src/app/classes/petowner/image';
import { Pet } from 'src/app/classes/petowner/pet';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';
import { Utils } from 'src/app/_helpers/utils';

@Component({
  selector: 'app-pet-item',
  templateUrl: './pet-item.component.html',
  styleUrls: ['./pet-item.component.css']
})
export class PetItemComponent implements OnInit {

  @Input() pet!: Pet;
  petPicture!: Image;
  defaultPic: string = '../../../assets/images/petPicture.png';

  constructor(
    private utils: Utils,
    private tokenService: TokenStorageService,
    private petOwnerService: PetOwnerService,
    private snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.loadPetPicture();
    }, 500);
  }


  getAge(): number {
    return this.utils.calculateAge(this.pet.birthDate);
  }

  loadPetPicture(): void {
    this.petPicture = JSON.parse(JSON.stringify(this.pet.picture));
  }

  edit(petId: number): void {

  }

  remove(petId: number): void {
    let user = this.tokenService.getUser();
    this.petOwnerService.removePet(user.id, petId).subscribe(response => {
      if (response.ok) {
        this.openSnackBar("Pet removed", "OK");
      }
    });
  }

  openSnackBar(message: string, action: string) {
    let snackBarMessage = this.snackBar.open(message, action, {
      duration: 3500,
    });
    snackBarMessage.onAction().subscribe(()=>{
      location.reload();
    });
    snackBarMessage.afterDismissed().subscribe(()=>{
      location.reload();
    });
  }
}

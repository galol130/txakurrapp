import { Component, Input, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PetService } from 'src/app/classes/business/pet-service';
import { Address } from 'src/app/classes/petowner/address';
import { Image } from 'src/app/classes/petowner/image';
import { Pet } from 'src/app/classes/petowner/pet';
import { PetOwner } from 'src/app/classes/petowner/pet-owner';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { AddPetDialogComponent } from '../add-pet-dialog/add-pet-dialog.component';
import {
  rotateInOnEnterAnimation,
  fadeInOnEnterAnimation,
} from "angular-animations";

@Component({
  selector: 'app-account-pet-owner',
  templateUrl: './account-pet-owner.component.html',
  styleUrls: ['./account-pet-owner.component.css'],
  animations: [    
    rotateInOnEnterAnimation(),
    fadeInOnEnterAnimation({delay: 500}),
    // fadeInOnEnterAnimation(),
    // rubberBandOnEnterAnimation({delay: 1500}),
  ],
})
export class AccountPetOwnerComponent implements OnInit {

  @Input() hasFinished!: boolean;
  @Input() owner!: PetOwner;

  address!: Address;
  profilePicture!: Image;
  defaultPic: string = '../../../assets/images/userPicture.png';
  pets: Pet[] = [];
  allServices: PetService[] = [];
  favs: PetService[] = [];

  constructor(
    public dialog: MatDialog,
    private businessOwnerService: BusinessOwnerService,
  ) { }

  ngOnInit(): void {
    document.addEventListener('DOMContentLoaded', (e) => {
      setTimeout(() => {
        this.loadAddress();
        this.loadProfilePicture();
        this.loadPets();
        this.loadPetServices();
        this.loadFavs();
      }, 500)
    })
  }

  ngOnChanges(): void {
    this.loadAddress();
    this.loadProfilePicture();
    this.loadPets();
    this.loadPetServices();
    this.loadFavs();
  }

  loadAddress(): void {
    if (this.owner !== (null || undefined)) {
      this.address = JSON.parse(JSON.stringify(this.owner.address));
    }
  }

  loadProfilePicture(): void {
    if (this.owner !== (null || undefined)) {
      this.profilePicture = JSON.parse(JSON.stringify(this.owner.profilePicture));
    }
  }

  loadPets(): void {
    if (this.owner !== (null || undefined)) {
      let petsArray = this.owner.pets;
      if (petsArray.length > 0) {
        this.pets = [];
        petsArray.forEach(pet => {
          this.pets.push(JSON.parse(JSON.stringify(pet)));
        })

      }
    }
  }

  //It's really necessary to create an endpoint to replace this n requests of these two methods
  loadPetServices(): void {
    this.businessOwnerService.getAllServices().subscribe(response => {
      if (response.body != null) {
        this.allServices = response.body as PetService[];
      }
    });

  }
  loadFavs(): void {
    if (this.owner !== (null || undefined)) {
      let favsIds: number[] = this.owner.favs;

      if (favsIds != undefined && favsIds.length > 0) {
        this.favs = [];
        this.allServices.forEach(service => {
          if (favsIds.includes(service.id)) {
            this.favs.push(service);
          }
        })
      }
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddPetDialogComponent, { width: '100vw' });
    dialogRef.afterClosed().subscribe(result => {
      location.reload();
    });
  }

}






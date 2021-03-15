import { Component, Input, OnInit } from '@angular/core';
import { Address } from 'src/app/classes/business/address';
import { Image } from 'src/app/classes/business/image';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { PetService } from 'src/app/classes/business/pet-service';
import { MatDialog } from '@angular/material/dialog';
import { AddServiceDialogComponent } from '../add-service-dialog/add-service-dialog.component';

@Component({
  selector: 'app-account-business',
  templateUrl: './account-business.component.html',
  styleUrls: ['./account-business.component.css']
})
export class AccountBusinessComponent implements OnInit {

  @Input() hasFinished!: boolean;
  @Input() owner!: BusinessOwner;

  address!: Address;
  profilePicture!: Image;
  defaultPic: string = '../../../assets/images/userPicture.png';
  services: PetService[] = [];

  constructor(public dialog: MatDialog) { 
  }

  ngOnInit(): void {
    document.addEventListener('DOMContentLoaded', (e) => {
      setTimeout(() => {
        this.loadAddress();
        this.loadProfilePicture();
        this.loadServices();
      }, 500)
    })
  }

  ngOnChane():void{
    this.loadAddress();
    this.loadProfilePicture();
    this.loadServices();
  }

  loadAddress(): void{
    if(this.owner !== (null || undefined)){
      this.address = JSON.parse(JSON.stringify(this.owner.address));
    }
  }

  loadProfilePicture(): void{
    if (this.owner !== (null || undefined)) {
      this.profilePicture = JSON.parse(JSON.stringify(this.owner.profilePicture));
    }
  }

  loadServices():void {
    if (this.owner !== (null || undefined)) {
      let servicesArray = this.owner.services;
      if (servicesArray.length > 0) {
        this.services = [];
        servicesArray.forEach(service => {
          this.services.push(JSON.parse(JSON.stringify(service)));
        })

      }
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(AddServiceDialogComponent, {width: '100vw'});
    dialogRef.afterClosed().subscribe(result => {
      location.reload();
    });
  }

}

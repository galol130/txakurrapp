import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Money } from 'src/app/classes/business/money';
import { PetService } from 'src/app/classes/business/pet-service';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { Image } from 'src/app/classes/business/image';
import { Address } from 'src/app/classes/business/address';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { MessageDialogComponent } from '../message-dialog/message-dialog.component';
import {
  fadeInUpBigOnEnterAnimation,
  slideInDownOnEnterAnimation,
  fadeInOnEnterAnimation,
  rubberBandOnEnterAnimation,
} from "angular-animations";

@Component({
  selector: 'app-browse-services',
  templateUrl: './browse-services.component.html',
  styleUrls: ['./browse-services.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('325ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
    fadeInUpBigOnEnterAnimation(),
    slideInDownOnEnterAnimation(),
    fadeInOnEnterAnimation(),
    rubberBandOnEnterAnimation({delay: 1500}),
  ],
})
export class BrowseServicesComponent implements OnInit {

  isLoggedIn: boolean = false;
  services: PetService[] = [];
  dataSource!: MatTableDataSource<any>;
  displayedColumns: string[] = ['name', 'price', 'city', 'available', 'actions'];
  expandedElement!: PetService | null;

  constructor(
    private tokenService: TokenStorageService,
    private businessOwnerService: BusinessOwnerService,
    private petOwnerService: PetOwnerService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getUser();

    this.businessOwnerService.getAllServices().subscribe(response => {
      if (response.ok && response.body != null) {
        this.services = response.body;
        this.dataSource = new MatTableDataSource(this.services);
        console.log(this.dataSource);
      }
    });
  }

  ngOnChange(): void {
    this.dataSource = new MatTableDataSource(this.services);
    console.log(this.dataSource);
  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getPriceAsString(price: Money): string {
    return `${price.amount}  ${price.currency}`;
  }

  getProfilePicture(owner: BusinessOwner): string {
    let picture: Image = JSON.parse(JSON.stringify(owner.profilePicture));
    return picture.name;
  }

  getCity(owner: BusinessOwner): string {
    let address: Address = JSON.parse(JSON.stringify(owner.address));
    return address.city;
  }

  getFullName(owner: BusinessOwner): string {
    return `${owner.firstName} ${owner.lastName}`;
  }

  getSignupDate(owner: BusinessOwner): string {
    return owner.signUpDate.toLocaleString('es-ES');
  }

  stopPropagation(event: Event): void {
    event.stopPropagation();
  }

  addToFavs(serviceId: number, event: Event): void {
    if (this.isLoggedIn) {
      let user = this.tokenService.getUser();
      if (user.roles.includes('ROLE_PETOWNER')) {

        let button = event.target as Element;
        //Couldn't find the way to select the mat-icon to remove it form the DOM....

        this.petOwnerService.addToFavs(user.id, serviceId).subscribe(response => {
          if (response.ok) {
            this.openSnackBar('Added to Favorites', 'OK');
          }
        });
      }
    }
  }

  sendMessage(serviceOwner: BusinessOwner):void {
    if(this.isLoggedIn){
      const dialogRef = this.dialog.open(MessageDialogComponent, {
        width: '55vw',
        data: serviceOwner
      });
      dialogRef.afterClosed().subscribe(result => {
        location.reload();
      });
    }
  }


  openSnackBar(message: string, action: string) {
    let snackBarMessage = this.snackBar.open(message, action, {
      duration: 3500,
    });
    snackBarMessage.onAction().subscribe(() => {
      location.reload();
    });
    snackBarMessage.afterDismissed().subscribe(() => {
      location.reload();
    });
  }

}

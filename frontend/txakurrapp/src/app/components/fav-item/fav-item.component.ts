import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { PetService } from 'src/app/classes/business/pet-service';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';
import { MessageDialogComponent } from '../message-dialog/message-dialog.component';

@Component({
  selector: 'app-fav-item',
  templateUrl: './fav-item.component.html',
  styleUrls: ['./fav-item.component.css']
})
export class FavItemComponent implements OnInit {
  
  iconPIcture1: string = '../../../assets/images/servicePetPic1.png';
  iconPIcture2: string = '../../../assets/images/servicePetPic2.png';

  @Input() fav!: PetService;

  constructor(
    private tokenStorageService: TokenStorageService,
    private businessOwnerService: BusinessOwnerService,
    private petOwnerService: PetOwnerService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
  ) { }

  ngOnInit(): void {
  }

  getIcon(): string{
    if(this.fav !== undefined && this.fav.id % 2 === 0){
      return this.iconPIcture1;
    }else{
      return this.iconPIcture2;
    }
  }

  sendMessage(businessOwner: BusinessOwner):void{
    const dialogRef = this.dialog.open(MessageDialogComponent, {
      width: '55vw',
      data: businessOwner
    });
    dialogRef.afterClosed().subscribe(result => {
      location.reload();
    });
  }

  remove(serviceId: number):void{
    let user = this.tokenStorageService.getUser();
    this.petOwnerService.removeFromFavs(user.id, serviceId).subscribe(response => {
        if(response.ok){
          this.openSnackBar("Service removed from favorites", "OK");
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

import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, Inject, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { take } from 'rxjs/operators';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { PetService } from 'src/app/classes/business/pet-service';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { PetOwnerService } from 'src/app/services/petowner/pet-owner.service';

@Component({
  selector: 'app-message-dialog',
  templateUrl: './message-dialog.component.html',
  styleUrls: ['./message-dialog.component.css']
})
export class MessageDialogComponent {

  isSubmitted = false;

  messageForm: FormGroup;
  messageSubject: FormControl;
  messageBody: FormControl;


  constructor(
    public dialogRef: MatDialogRef<MessageDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public serviceOwner: BusinessOwner,
    private ngZone: NgZone,
    private tokenService: TokenStorageService,
    private petOwnerService: PetOwnerService,
    private businessOwnerService: BusinessOwnerService,
    private snackBar: MatSnackBar,
  ) {
    this.messageSubject = new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(80)]);
    this.messageBody = new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(1000)]);

    this.messageForm = new FormGroup({
      messageSubject: this.messageSubject,
      messageBody: this.messageBody,
    })
  }

  @ViewChild('autosize') autosize!: CdkTextareaAutosize;

  triggerResize() {
    // Wait for changes to be applied, then trigger textarea resize.
    this.ngZone.onStable.pipe(take(1))
      .subscribe(() => this.autosize.resizeToFitContent(true));
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  onSubmit(): void {
    if (this.messageForm.valid && this.serviceOwner != undefined) {
      this.isSubmitted = true;
      console.log(this.messageForm.value);
      const user = this.tokenService.getUser();

      this.petOwnerService.createMessage(user.id, this.serviceOwner.id, this.messageForm).subscribe(response => {
        if (response.ok) {
          this.openSnackBar("Message sent!", "OK");
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

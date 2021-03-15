import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { Component, NgZone, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TokenStorageService } from 'src/app/services/auth/token-storage.service';
import { BusinessOwnerService } from 'src/app/services/business/business-owner.service';
import { take } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-add-service-dialog',
  templateUrl: './add-service-dialog.component.html',
  styleUrls: ['./add-service-dialog.component.css']
})
export class AddServiceDialogComponent {

  isSubmitted = false;

  addServiceForm: FormGroup;

  name: FormControl;
  description: FormControl;
  currency: FormControl;
  amount: FormControl;


  constructor(
    public dialogRef: MatDialogRef<AddServiceDialogComponent>,
    private ngZone: NgZone,
    private tokenService: TokenStorageService,
    private businessOwnerService: BusinessOwnerService,
    private snackBar: MatSnackBar,
  ) {
    this.name = new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]);
    this.description = new FormControl('', [Validators.minLength(6)]);
    this.currency = new FormControl('EUR', [Validators.required]);
    this.amount = new FormControl('', [Validators.min(0.01), Validators.max(999.99)]);

    this.addServiceForm = new FormGroup({
      name: this.name,
      description: this.description,
      currency: this.currency,
      amount: this.amount,
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
    if (this.addServiceForm.valid) {
      this.isSubmitted = true;
      console.log(this.addServiceForm.value);
      const user = this.tokenService.getUser();

      this.businessOwnerService.addService(user.id, this.addServiceForm).subscribe(response => {
        if (response.ok) {
          this.openSnackBar("Service created!", "OK");
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

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm: FormGroup;
  email: FormControl;
  password: FormControl;
  passwordRepeated: FormControl;
  role: FormControl;

  isSuccessful = false;
  isSignUpFailed = false;
  isSubmitted = false;
  errorMessage = '';

  constructor(
    private authService: AuthService
  ) {
    this.email = new FormControl('', [Validators.required, Validators.email]);
    this.password = new FormControl('', [Validators.required, Validators.minLength(5)]);
    this.passwordRepeated = new FormControl('', []);
    this.role = new FormControl('1');

    this.signupForm = new FormGroup({
      email: this.email,
      password: this.password,
      passwordRepeated: this.passwordRepeated,
      role: this.role
    });
  }

  ngOnInit(): void {
  }


  onSubmit(): void {
    console.log(this.signupForm.value);
    if (this.password.value === this.passwordRepeated.value) {

      this.isSubmitted = true;
      this.isSignUpFailed = false;
      this.authService.register(this.signupForm).subscribe(
        data => {
          console.log(data);
          this.isSuccessful = true;
          this.isSignUpFailed = false;
        },
        err => {
          this.errorMessage = err.error.message;
          this.isSignUpFailed = true;
        }
      );
    } else {
      this.errorMessage = "Password fields don't match";
      this.isSubmitted = true;
      this.isSignUpFailed = true;
      this.passwordRepeated.reset();
    }
  }


  //Eliminar luego
  showRadio():void{
    console.log(this.role.value);
  }

}

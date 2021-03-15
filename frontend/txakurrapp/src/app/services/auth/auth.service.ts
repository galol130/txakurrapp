import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

// const API_AUTH = 'http://localhost:8083/api/auth/';
const API_AUTH = 'https://txakurrapp-edge.herokuapp.com/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(loginForm: FormGroup): Observable<any> {
    return this.http.post(API_AUTH + 'login', {
      username: loginForm.controls["email"].value,
      password: loginForm.controls["password"].value
    }, httpOptions);
  }

  register(registerForm: FormGroup): Observable<any> {
    return this.http.post(API_AUTH + 'signup', {
      username: registerForm.controls["email"].value,
      password: registerForm.controls["password"].value,
      role: [registerForm.controls["role"].value == 1 ? 'ROLE_PETOWNER' : 'ROLE_BUSINESS'],
    }, httpOptions);
  }
}



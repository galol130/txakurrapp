import { Injectable } from '@angular/core';
import { User } from 'src/app/classes/auth/user';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const REGISTERED_KEY = "registered-user";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: User): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public saveIsRegistered(ok: boolean): void {
    window.sessionStorage.removeItem(REGISTERED_KEY);
    window.sessionStorage.setItem(REGISTERED_KEY, JSON.stringify(ok));
  }

  public getIsRegistered(): boolean {
    let isRegistered = sessionStorage.getItem(REGISTERED_KEY);
    if(isRegistered == null){
      return false;
    }else{
      return true;
    }
  }

  public getUser(): any {
    let user = sessionStorage.getItem(USER_KEY);
    if(user == null){
      return "";
    }else{
      return JSON.parse(user);
    }
  }
}

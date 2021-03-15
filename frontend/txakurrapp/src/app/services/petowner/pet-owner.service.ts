import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Message } from 'src/app/classes/petowner/message';
import { PetOwner } from 'src/app/classes/petowner/pet-owner';

// const API_BASEURL = 'http://localhost:8083/api/petowner/';
const API_BASEURL = 'https://txakurrapp-edge.herokuapp.com/api/petowner/';

const httpOptions = {
  headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*', 'content-type': 'application/json' })
};


const getTextOptions = { observe: 'body' as const, reponseType: 'text' as const };

const getJSONOptions = { observe: 'body' as const, responseType: 'json' as const };

@Injectable({
  providedIn: 'root'
})
export class PetOwnerService {

  constructor(private http: HttpClient) { }

  // greet(): Observable<any> {
  //   return this.http.get(API_BASEURL + 'hello', { observe: 'body', responseType: 'text' });
  // }

  // getForBusiness(): Observable<any> {
  //   return this.http.get(API_BASEURL + 'nono', { observe: 'body', responseType: 'text' });
  // }

  // getPublicContent(): Observable<any> {
  //   return this.http.get(API_BASEURL + 'public', { observe: 'body', responseType: 'text' });
  // }


  getPetOwnerFromUserId(UserId: number): Observable<HttpResponse<PetOwner>> {
    return this.http.get<PetOwner>(API_BASEURL + 'user-id/' + UserId, { observe: 'response', responseType: 'json' });
  }

  getPetOwnerById(id: number): Observable<HttpResponse<PetOwner>> {
    return this.http.get<PetOwner>(API_BASEURL + 'owner/' + id, { observe: 'response', responseType: 'json' });
  }

  createPetOwner(userId: number, basicInfo: FormGroup, contactInfo: FormGroup, picture: string): Observable<HttpResponse<PetOwner>> {
    return this.http.post<any>(API_BASEURL + 'create', {
      userId: userId,
      firstName: basicInfo.controls["firstName"].value,
      lastName: basicInfo.controls["lastName"].value,
      personalId: basicInfo.controls["personalId"].value,
      birthDate: basicInfo.controls["birthDate"].value,
      phoneNumber: contactInfo.controls["phone"].value,
      address: {
        streetName: contactInfo.controls["street"].value,
        number: contactInfo.controls["streetNumber"].value,
        apt: contactInfo.controls["apt"].value,
        otherInfo: contactInfo.controls["otherInfo"].value,
        postalCode: contactInfo.controls["postalCode"].value,
        city: contactInfo.controls["city"].value,
        province: contactInfo.controls["province"].value,
        country: contactInfo.controls["country"].value
      },
      profilePicture: {
        name: picture,
        type: "image as string",
        picByte: []
      }
    }, { observe: 'response', responseType: 'json' });
  }


  /*        Pets ENDPOINTS       */

  addPet(userId: number, petInfo: FormGroup, picture: string): Observable<HttpResponse<PetOwner>> {
    return this.http.post<any>(API_BASEURL + userId + '/add-pet', {
      type: petInfo.controls['type'].value,
      name: petInfo.controls['name'].value,
      picture: {
        name: picture,
        type: 'image as string',
        picByte: []
      },
      breed: petInfo.controls['breed'].value,
      birthDate: petInfo.controls['birthDate'].value,
      weight: petInfo.controls['weight'].value,
      otherInfo: petInfo.controls['otherInfo'].value,
    }, { observe: 'response', responseType: 'json' });
  }

  removePet(userId: number, petId: number): Observable<HttpResponse<PetOwner>> {
    return this.http.put<any>(API_BASEURL + userId + '/remove-pet/' + petId, {}, { observe: 'response', responseType: 'json' });
  }


  // //Borrar al terminar
  // login(loginForm: FormGroup): Observable<any> {
  //   return this.http.post(API_BASEURL + 'login', {
  //     username: loginForm.controls["email"].value,
  //     password: loginForm.controls["password"].value
  //   }, httpOptions);
  // }

  // //Borrar al terminar
  // register(registerForm: FormGroup): Observable<any> {
  //   return this.http.post(API_BASEURL + 'signup', {
  //     username: registerForm.controls["email"].value,
  //     password: registerForm.controls["password"].value,
  //     role: [registerForm.controls["role"].value === 1 ? 'ROLE_PETOWNER' : 'ROLE_BUSINESS'],
  //   }, httpOptions);
  // }

  /*        Favorites ENDPOINTS       */

  addToFavs(userId: number, serviceId: number): Observable<HttpResponse<PetOwner>> {
    return this.http.put<any>(API_BASEURL + userId + '/add-fav/' + serviceId, {}, { observe: 'response', responseType: 'json' });
  }

  removeFromFavs(userId: number, serviceId: number): Observable<HttpResponse<PetOwner>> {
    return this.http.put<any>(API_BASEURL + userId + '/remove-fav/' + serviceId, {}, { observe: 'response', responseType: 'json' });

  }
  /*        MESSAGES ENDPOINTS       */

  createMessage(userId: number, recipientId: number, messageForm: FormGroup): Observable<HttpResponse<Message>> {
    return this.http.post<any>(API_BASEURL + userId + '/message', {
      recipientId: recipientId,
      messageSubject: messageForm.controls["messageSubject"].value,
      messageBody: messageForm.controls["messageBody"].value
    }, { observe: 'response', responseType: 'json' });
  }

  getAllMessagesFromSender(userId: number): Observable<HttpResponse<Message[]>> {
    return this.http.get<any>(API_BASEURL + userId + '/messages', { observe: 'response', responseType: 'json' });
  }

}


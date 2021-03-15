import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { BusinessOwner } from 'src/app/classes/business/business-owner';
import { PetService } from 'src/app/classes/business/pet-service';

// const API_BASEURL = 'http://localhost:8083/api/business/';
const API_BASEURL = 'https://txakurrapp-edge.herokuapp.com/api/business/';

const httpOptions = {
  headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*', 'content-type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class BusinessOwnerService {

  constructor(private http: HttpClient) { }


  getBusinessOwnerFromUserId(userId: number): Observable<HttpResponse<BusinessOwner>> {
    return this.http.get<any>(API_BASEURL + 'user-id/' + userId, { observe: 'response', responseType: 'json' });
  }

  getBusinessOwnerById(id: number): Observable<HttpResponse<BusinessOwner>> {
    return this.http.get<any>(API_BASEURL + 'owner/' + id, { observe: 'response', responseType: 'json' });
  }

  createBusinessOwner(userId: number, basicInfo: FormGroup, contactInfo: FormGroup, picture: string): Observable<HttpResponse<BusinessOwner>> {
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

  addService(userId: number, serviceInfo: FormGroup): Observable<HttpResponse<BusinessOwner>> {
    return this.http.post<any>(API_BASEURL + userId + '/add-service', {
      name: serviceInfo.controls['name'].value,
      description: serviceInfo.controls['description'].value,
      currency: serviceInfo.controls['currency'].value,
      amount: serviceInfo.controls['amount'].value,
    }, { observe: 'response', responseType: 'json' });
  }


  removeService(userId: number, serviceId: number): Observable<HttpResponse<BusinessOwner>> {
    return this.http.put<any>(API_BASEURL + userId + '/remove-service/' + serviceId, {}, { observe: 'response', responseType: 'json' });
  }

  getAllBusinesses(): Observable<HttpResponse<BusinessOwner[]>> {
    return this.http.get<any>(API_BASEURL + 'businesses', { observe: 'response', responseType: 'json' });
  }

  getAllServices(): Observable<HttpResponse<PetService[]>> {
    return this.http.get<any>(API_BASEURL + 'services' , { observe: 'response', responseType: 'json' });
  }


}

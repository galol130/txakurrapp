import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatStepperModule } from '@angular/material/stepper';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { MatTabsModule } from '@angular/material/tabs';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { TextFieldModule } from '@angular/cdk/text-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { AccountComponent } from './components/account/account.component';
import { AccountPetOwnerComponent } from './components/account-pet-owner/account-pet-owner.component';
import { AccountBusinessComponent } from './components/account-business/account-business.component';
import { AccountPetOwnerRegisterComponent } from './components/account-pet-owner-register/account-pet-owner-register.component';
import { PetItemComponent } from './components/pet-item/pet-item.component';
import { AddPetDialogComponent } from './components/add-pet-dialog/add-pet-dialog.component';
import { AddServiceDialogComponent } from './components/add-service-dialog/add-service-dialog.component';
import { ServiceItemComponent } from './components/service-item/service-item.component';
import { AccountBusinessOwnerRegisterComponent } from './components/account-business-owner-register/account-business-owner-register.component';
import { BrowseServicesComponent } from './components/browse-services/browse-services.component';
import { FavItemComponent } from './components/fav-item/fav-item.component';
import { MessageDialogComponent } from './components/message-dialog/message-dialog.component';
import { FooterComponent } from './components/footer/footer.component';
import { FaqsComponent } from './components/faqs/faqs.component';
import { MessagesComponent } from './components/messages/messages.component';
import { MessagesPetOwnerComponent } from './components/messages-pet-owner/messages-pet-owner.component';
import { MessagesBusinessOwnerComponent } from './components/messages-business-owner/messages-business-owner.component';

@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    HomeComponent,
    NavBarComponent,
    LoginComponent,
    SignupComponent,
    AccountComponent,
    AccountPetOwnerComponent,
    AccountBusinessComponent,
    AccountPetOwnerRegisterComponent,
    PetItemComponent,
    AddPetDialogComponent,
    AddServiceDialogComponent,
    ServiceItemComponent,
    AccountBusinessOwnerRegisterComponent,
    BrowseServicesComponent,
    FavItemComponent,
    MessageDialogComponent,
    FooterComponent,
    FaqsComponent,
    MessagesComponent,
    MessagesPetOwnerComponent,
    MessagesBusinessOwnerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatStepperModule,
    MatButtonModule,
    MatRadioModule,
    MatTabsModule,
    MatExpansionModule,
    MatDialogModule,
    MatSelectModule,
    TextFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatMenuModule,
    MatSnackBarModule,
  ],
  providers: [authInterceptorProviders, MatDatepickerModule],
  bootstrap: [AppComponent]
})
export class AppModule { }

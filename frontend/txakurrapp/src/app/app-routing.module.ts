import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { BrowseServicesComponent } from './components/browse-services/browse-services.component';
import { FaqsComponent } from './components/faqs/faqs.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MessagesComponent } from './components/messages/messages.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SignupComponent } from './components/signup/signup.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'faqs', component: FaqsComponent },
  { path: "user/login", component: LoginComponent },
  { path: "user/signup", component: SignupComponent },
  { path: "user/account", component: AccountComponent },
  { path: "user/messages", component: MessagesComponent },
  { path: "services/browse", component: BrowseServicesComponent },
  { path: '', component: HomeComponent },
  { path: '**', component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

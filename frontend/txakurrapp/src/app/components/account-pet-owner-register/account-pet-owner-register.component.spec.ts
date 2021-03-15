import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountPetOwnerRegisterComponent } from './account-pet-owner-register.component';

describe('AccountPetOwnerRegisterComponent', () => {
  let component: AccountPetOwnerRegisterComponent;
  let fixture: ComponentFixture<AccountPetOwnerRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountPetOwnerRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountPetOwnerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

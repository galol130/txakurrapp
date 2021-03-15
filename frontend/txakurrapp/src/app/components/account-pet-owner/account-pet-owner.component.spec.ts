import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountPetOwnerComponent } from './account-pet-owner.component';

describe('AccountPetOwnerComponent', () => {
  let component: AccountPetOwnerComponent;
  let fixture: ComponentFixture<AccountPetOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountPetOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountPetOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

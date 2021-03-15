import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountBusinessOwnerRegisterComponent } from './account-business-owner-register.component';

describe('AccountBusinessOwnerRegisterComponent', () => {
  let component: AccountBusinessOwnerRegisterComponent;
  let fixture: ComponentFixture<AccountBusinessOwnerRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountBusinessOwnerRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountBusinessOwnerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

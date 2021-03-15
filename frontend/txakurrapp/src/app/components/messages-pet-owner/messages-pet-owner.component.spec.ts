import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagesPetOwnerComponent } from './messages-pet-owner.component';

describe('MessagesPetOwnerComponent', () => {
  let component: MessagesPetOwnerComponent;
  let fixture: ComponentFixture<MessagesPetOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MessagesPetOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagesPetOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

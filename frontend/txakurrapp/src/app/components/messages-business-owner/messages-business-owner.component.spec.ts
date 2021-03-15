import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MessagesBusinessOwnerComponent } from './messages-business-owner.component';

describe('MessagesBusinessOwnerComponent', () => {
  let component: MessagesBusinessOwnerComponent;
  let fixture: ComponentFixture<MessagesBusinessOwnerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MessagesBusinessOwnerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MessagesBusinessOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

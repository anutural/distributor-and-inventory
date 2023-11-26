import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupOwnerKycComponent } from './popup-owner-kyc.component';

describe('PopupOwnerKycComponent', () => {
  let component: PopupOwnerKycComponent;
  let fixture: ComponentFixture<PopupOwnerKycComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PopupOwnerKycComponent]
    });
    fixture = TestBed.createComponent(PopupOwnerKycComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

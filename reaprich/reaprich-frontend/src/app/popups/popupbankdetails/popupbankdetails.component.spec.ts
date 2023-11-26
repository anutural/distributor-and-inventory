import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupbankdetailsComponent } from './popupbankdetails.component';

describe('PopupbankdetailsComponent', () => {
  let component: PopupbankdetailsComponent;
  let fixture: ComponentFixture<PopupbankdetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PopupbankdetailsComponent]
    });
    fixture = TestBed.createComponent(PopupbankdetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

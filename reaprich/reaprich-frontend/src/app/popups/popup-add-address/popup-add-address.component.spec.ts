import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupAddAddressComponent } from './popup-add-address.component';

describe('PopupAddAddressComponent', () => {
  let component: PopupAddAddressComponent;
  let fixture: ComponentFixture<PopupAddAddressComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PopupAddAddressComponent]
    });
    fixture = TestBed.createComponent(PopupAddAddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

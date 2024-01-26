import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupAddItemWarehousComponent } from './popup-add-item-warehous.component';

describe('PopupAddItemWarehousComponent', () => {
  let component: PopupAddItemWarehousComponent;
  let fixture: ComponentFixture<PopupAddItemWarehousComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PopupAddItemWarehousComponent]
    });
    fixture = TestBed.createComponent(PopupAddItemWarehousComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

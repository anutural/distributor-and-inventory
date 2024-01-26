import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryAddItemDetailsComponent } from './inventory-add-item-details.component';

describe('InventoryAddItemDetailsComponent', () => {
  let component: InventoryAddItemDetailsComponent;
  let fixture: ComponentFixture<InventoryAddItemDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [InventoryAddItemDetailsComponent]
    });
    fixture = TestBed.createComponent(InventoryAddItemDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

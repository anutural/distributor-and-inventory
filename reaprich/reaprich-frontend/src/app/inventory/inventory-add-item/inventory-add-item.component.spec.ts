import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryAddItemComponent } from './inventory-add-item.component';

describe('InventoryAddItemComponent', () => {
  let component: InventoryAddItemComponent;
  let fixture: ComponentFixture<InventoryAddItemComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [InventoryAddItemComponent]
    });
    fixture = TestBed.createComponent(InventoryAddItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

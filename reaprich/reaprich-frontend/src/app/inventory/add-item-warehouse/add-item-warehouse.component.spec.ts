import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddItemWarehouseComponent } from './add-item-warehouse.component';

describe('AddItemWarehouseComponent', () => {
  let component: AddItemWarehouseComponent;
  let fixture: ComponentFixture<AddItemWarehouseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AddItemWarehouseComponent]
    });
    fixture = TestBed.createComponent(AddItemWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

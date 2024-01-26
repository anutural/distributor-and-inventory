import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersItemsgridComponent } from './orders-itemsgrid.component';

describe('OrdersItemsgridComponent', () => {
  let component: OrdersItemsgridComponent;
  let fixture: ComponentFixture<OrdersItemsgridComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OrdersItemsgridComponent]
    });
    fixture = TestBed.createComponent(OrdersItemsgridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

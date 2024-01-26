import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersCheckoutComponent } from './orders-checkout.component';

describe('OrdersCheckoutComponent', () => {
  let component: OrdersCheckoutComponent;
  let fixture: ComponentFixture<OrdersCheckoutComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OrdersCheckoutComponent]
    });
    fixture = TestBed.createComponent(OrdersCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

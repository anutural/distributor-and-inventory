import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersHomeComponent } from './orders-home.component';

describe('OrdersHomeComponent', () => {
  let component: OrdersHomeComponent;
  let fixture: ComponentFixture<OrdersHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OrdersHomeComponent]
    });
    fixture = TestBed.createComponent(OrdersHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

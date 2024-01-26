import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersItemstableComponent } from './orders-itemstable.component';

describe('OrdersItemstableComponent', () => {
  let component: OrdersItemstableComponent;
  let fixture: ComponentFixture<OrdersItemstableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OrdersItemstableComponent]
    });
    fixture = TestBed.createComponent(OrdersItemstableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersViewItemsComponent } from './orders-view-items.component';

describe('OrdersViewItemsComponent', () => {
  let component: OrdersViewItemsComponent;
  let fixture: ComponentFixture<OrdersViewItemsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [OrdersViewItemsComponent]
    });
    fixture = TestBed.createComponent(OrdersViewItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

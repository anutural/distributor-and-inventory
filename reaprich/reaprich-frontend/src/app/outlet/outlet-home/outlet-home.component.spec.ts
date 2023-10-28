import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutletHomeComponent } from './outlet-home.component';

describe('OutletHomeComponent', () => {
  let component: OutletHomeComponent;
  let fixture: ComponentFixture<OutletHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OutletHomeComponent]
    });
    fixture = TestBed.createComponent(OutletHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

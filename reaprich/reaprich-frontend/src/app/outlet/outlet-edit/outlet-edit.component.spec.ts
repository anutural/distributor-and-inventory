import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutletEditComponent } from './outlet-edit.component';

describe('OutletEditComponent', () => {
  let component: OutletEditComponent;
  let fixture: ComponentFixture<OutletEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [OutletEditComponent]
    });
    fixture = TestBed.createComponent(OutletEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

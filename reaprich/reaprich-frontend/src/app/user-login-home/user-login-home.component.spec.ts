import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserLoginHomeComponent } from './user-login-home.component';

describe('UserLoginHomeComponent', () => {
  let component: UserLoginHomeComponent;
  let fixture: ComponentFixture<UserLoginHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserLoginHomeComponent]
    });
    fixture = TestBed.createComponent(UserLoginHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

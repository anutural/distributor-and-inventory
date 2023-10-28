import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamdevHomeComponent } from './teamdev-home.component';

describe('TeamdevHomeComponent', () => {
  let component: TeamdevHomeComponent;
  let fixture: ComponentFixture<TeamdevHomeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeamdevHomeComponent]
    });
    fixture = TestBed.createComponent(TeamdevHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

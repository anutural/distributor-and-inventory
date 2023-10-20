import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamdevComponent } from './teamdev.component';

describe('TeamdevComponent', () => {
  let component: TeamdevComponent;
  let fixture: ComponentFixture<TeamdevComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeamdevComponent]
    });
    fixture = TestBed.createComponent(TeamdevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

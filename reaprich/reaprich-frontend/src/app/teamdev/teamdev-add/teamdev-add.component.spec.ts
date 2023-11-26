import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamdevAddComponent } from './teamdev-add.component';

describe('TeamdevAddComponent', () => {
  let component: TeamdevAddComponent;
  let fixture: ComponentFixture<TeamdevAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeamdevAddComponent]
    });
    fixture = TestBed.createComponent(TeamdevAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

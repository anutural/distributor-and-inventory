import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamdevListComponent } from './teamdev-list.component';

describe('TeamdevListComponent', () => {
  let component: TeamdevListComponent;
  let fixture: ComponentFixture<TeamdevListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeamdevListComponent]
    });
    fixture = TestBed.createComponent(TeamdevListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

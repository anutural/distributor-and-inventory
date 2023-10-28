import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamdevEditComponent } from './teamdev-edit.component';

describe('TeamdevEditComponent', () => {
  let component: TeamdevEditComponent;
  let fixture: ComponentFixture<TeamdevEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TeamdevEditComponent]
    });
    fixture = TestBed.createComponent(TeamdevEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

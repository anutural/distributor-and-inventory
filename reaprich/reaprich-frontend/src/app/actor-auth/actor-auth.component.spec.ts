import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorAuthComponent } from './actor-auth.component';

describe('ActorAuthComponent', () => {
  let component: ActorAuthComponent;
  let fixture: ComponentFixture<ActorAuthComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ActorAuthComponent]
    });
    fixture = TestBed.createComponent(ActorAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

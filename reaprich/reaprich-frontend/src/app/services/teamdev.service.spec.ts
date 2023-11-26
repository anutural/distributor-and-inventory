import { TestBed } from '@angular/core/testing';

import { TeamdevService } from './teamdev.service';

describe('TeamdevService', () => {
  let service: TeamdevService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeamdevService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

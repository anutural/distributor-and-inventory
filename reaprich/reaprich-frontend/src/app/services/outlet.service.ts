import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IOutletType } from '../data-type';

@Injectable({
  providedIn: 'root'
})
export class OutletService {

  constructor(private http: HttpClient) { }

  getOutletTypes() {
    
  }
}

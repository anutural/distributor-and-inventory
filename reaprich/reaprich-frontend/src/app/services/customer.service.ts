import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ICustomer } from '../data-type';

@Injectable({
  providedIn: 'root'
})

export class CustomerService {

  constructor(private http: HttpClient) { }

  addCustomer(data: ICustomer){
    return this.http.post('http://localhost:3000/customers',data);    
  }

  customerList(){
    return this.http.get<ICustomer[]>('http://localhost:3000/customers');
  }

  
  deleteCustomer(id: number) {    
    return this.http.delete(`http://localhost:3000/customers/${id}`);
  }

  getCustomer(id: string) {
    return this.http.get<ICustomer>(`http://localhost:3000/customers/${id}`);
  }

  updateCustomer(customer: ICustomer){
    return this.http.put<ICustomer>(`http://localhost:3000/customers/${customer.id}`, customer);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IAccessToken, IAddress, IAddressServerResponse, ICustomer } from '../data-type';

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
  accessTokenObject: IAccessToken | undefined;

  constructor(private http: HttpClient) { }

  async addCustomer(data: ICustomer){
       //read the token from local storage.
       this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
       //header
       let headers = new HttpHeaders();
       headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
       headers = headers.append('Content-Type', 'application/json; charset=utf-8');
       const httpOptions = {
         headers: headers
       };    
    return await this.http.post<ICustomer[]>(
      'http://localhost:8080/v1/user/actor/customer', data, httpOptions
    )
  }

  customerList(){
    return this.http.get<ICustomer[]>('http://localhost:3000/customers');
  }

  
  deleteCustomer(id: number) {    
    return this.http.delete(`http://localhost:3000/customers/${id}`);
  }

  getCustomer(id: string) {
    return this.http.get<ICustomer>(`http://localhost:3000/customer`);
  }

  updateCustomer(customer: ICustomer){
    return this.http.put<ICustomer>(`http://localhost:3000/customers/${customer.id}`, customer);
  }

  getCustomerByUUID(custAddId : string){
    //read the token from local storage.
     this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
     //header
     let headers = new HttpHeaders();
     headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
     headers = headers.append('Content-Type', 'application/json; charset=utf-8');
     const httpOptions = {
       headers: headers
     };    
    return this.http.get<IAddressServerResponse>('http://localhost:8080/v1/user/address?addressID=' + custAddId, httpOptions);
    //return this.http.get<ICustomer>('http://localhost:3000/customers');
  }
}

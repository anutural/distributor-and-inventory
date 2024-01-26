import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { IAccessToken, IAddress, IAddressServerResponse, ICustomer, ICustomerResponse, ICustomerServerResponse } from '../data-type';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class CustomerService {
  accessTokenObject: IAccessToken | undefined;
  m_hostURL = environment.hostURL;
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
      this.m_hostURL + 'user/actor/customer', data, httpOptions
    )
  }

  async editCustomer(data: ICustomer){
    //read the token from local storage.
    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
    //header
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');
    const httpOptions = {
      headers: headers
    };    
 return await this.http.put<ICustomer[]>(
   this.m_hostURL + 'user/actor/customer', data, httpOptions
 )
}  

  customerList() {
    //read the token from local storage.
    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;


    //header
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');

    const httpOptions = {
      headers: headers
    };


    const data: JSON = <JSON><unknown>{
      "actorFilterBy": "NONE",
      "filter": "",
      "subFilter": ""
    }
    
    return this.http.post<ICustomerResponse>(this.m_hostURL + 'user/actor/allcustomers', data, httpOptions);

  }
  

  
  deleteCustomer(id: number) {    
    return this.http.delete(`http://localhost:3000/customers/${id}`);
  }

  getCustomer(id: string) {
    //read the token from local storage.
     this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
     //header
     let headers = new HttpHeaders();
     headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
     headers = headers.append('Content-Type', 'application/json; charset=utf-8');
     const httpOptions = {
       headers: headers
     };         
    return this.http.get<ICustomerServerResponse>(this.m_hostURL + 'user/actor/customer?customerID=' + id, httpOptions);            
  }

  updateCustomer(customer: ICustomer){
    return this.http.put<ICustomer>(`http://localhost:3000/customers/${customer.id}`, customer);
  }

  getCustomerAddressByUUID(custAddId : string){
    //read the token from local storage.
     this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
     //header
     let headers = new HttpHeaders();
     headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
     headers = headers.append('Content-Type', 'application/json; charset=utf-8');
     const httpOptions = {
       headers: headers
     };    
    return this.http.get<IAddressServerResponse>(this.m_hostURL + 'user/address?addressID=' + custAddId, httpOptions);    
  }

}

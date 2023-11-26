import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IAccessToken, IOutlet, IOutletType } from '../data-type';

@Injectable({
  providedIn: 'root'
})
export class OutletService {

  accessTokenObject: IAccessToken | undefined;

  constructor(private http: HttpClient) { }

  getOutletTypes() {

  }

  async addOutlet(data : IOutlet) {
    //read the token from local storage.
    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;


    //header
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');
    const httpOptions = {
      headers: headers
    };

    //the post request
    return await this.http.post<IOutlet[]>(
      'http://localhost:8080/v1/user/actor/outlet', data, httpOptions
    )
  }

  allOutletList(){
    //read the token from local storage.
    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;


    //header
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');

    const httpOptions = {
      headers: headers
    };


    const data:JSON = <JSON><unknown>{
      "actorFilterBy": "NONE",
      "filter":"",
      "subFilter":""
    }
 
    console.warn("myData : ", data);
    return this.http.post<IOutlet[]>('http://localhost:8080/v1/user/actor/alloutlets', data ,httpOptions);
  }
}

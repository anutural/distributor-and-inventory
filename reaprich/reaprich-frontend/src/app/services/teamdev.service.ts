import { Injectable } from '@angular/core';
import { IAccessToken, ITeamDev } from '../data-type';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TeamdevService {
  accessTokenObject: IAccessToken | undefined;

  constructor(private http: HttpClient) { }

  async addTeamdev(data: ITeamDev){
    //read the token from local storage.
    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
    //header
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');
    const httpOptions = {
      headers: headers
    };    
 return await this.http.post<ITeamDev[]>(
   'http://localhost:8080/v1/user/actor/teamdev', data, httpOptions
 )
}
  
}

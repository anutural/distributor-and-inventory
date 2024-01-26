import { Injectable } from '@angular/core';
import { IAccessToken, ITeamDev, ITeamDevServerResponse } from '../data-type';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TeamdevService {
  accessTokenObject: IAccessToken | undefined;
  m_hostURL = environment.hostURL;
  constructor(private http: HttpClient) { }

  async addTeamdev(data: ITeamDev) {

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
    return await this.http.post<ITeamDev[]>(
      this.m_hostURL + 'user/actor/td', data, httpOptions
    )
  }

  

  
  allTeamDevList(){
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
       
    return this.http.post<ITeamDevServerResponse>(this.m_hostURL + 'user/actor/alltds', data ,httpOptions);    
  }
  
}

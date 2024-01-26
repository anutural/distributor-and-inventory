import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IAccessToken, IAddress, IBankDetails, ICountry, IDistrict, IKYCDetails, IProviderInfo, IServerResponsePut, IState, IZone } from '../data-type';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  m_providerInfo: IProviderInfo | undefined;
  m_accessToken: string | undefined;

  accessTokenObject: IAccessToken | undefined;
  m_hostURL = environment.hostURL;

  constructor(private http: HttpClient) {

  }


  async getProviderInfo() {    
    this.http.get<IProviderInfo>(this.m_hostURL + 'provider/',
      { observe: 'response' }).subscribe((result) => {       
        if (result && result.body) {
          this.m_providerInfo = result.body;
          localStorage.setItem('providerInfo', JSON.stringify(result.body))
          return result.body;
        }
        else {
          console.warn("error in getting provider infor", result)
          return undefined;
        }
      }, (err) => {
        console.warn("got error in getting provider info", err)
      })
      ;
  }  


  async postAddress(data: IAddress) {
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
    return await this.http.post(
      this.m_hostURL + 'user/address', data, httpOptions
    )
  }

  async postBankDetails(data: IBankDetails) {

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
    return await this.http.post(
      this.m_hostURL + 'user/bankdetail', data, httpOptions
    );

  }

  async postKYCDetails(data: IKYCDetails) {

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
    return await this.http.post(
      this.m_hostURL + 'user/kycdetail', data, httpOptions
    );
  }

  async addCountry(data: ICountry) {

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
    return await this.http.post<IProviderInfo>(
      this.m_hostURL + 'provider/address/country',  data, { headers: headers,  observe: "response" }
    );

  }


  async addState(data: IState) {

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
    return await this.http.post<IProviderInfo>(
      this.m_hostURL + 'provider/address/state',  data, { headers: headers,  observe: "response" }
    );
    
  }  

  async addZone(data: IZone) {

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
    return await this.http.post<IProviderInfo>(
      this.m_hostURL + 'provider/address/zone',  data, { headers: headers,  observe: "response" }
    );
    
  }  

  async addDistrict(data: IDistrict) {

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
    return await this.http.post<IProviderInfo>(
      this.m_hostURL + 'provider/address/dist',  data, { headers: headers,  observe: "response" }
    );
    
  }    
}

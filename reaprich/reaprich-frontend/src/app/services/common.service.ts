import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IAccessToken, IAddress, IBankDetails, IKYCDetails, IProviderInfo, IServerResponsePut } from '../data-type';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  m_providerInfo: IProviderInfo | undefined;
  m_accessToken: string | undefined;

  accessTokenObject: IAccessToken | undefined;

  constructor(private http: HttpClient) {

  }


  getProviderInfo() {
    this.http.get<IProviderInfo>('http://localhost:8080/v1/provider/',
      { observe: 'response' }).subscribe((result: any) => {
        if (result && result.body) {
          this.m_providerInfo = result.body;
          localStorage.setItem('providerInfo', JSON.stringify(result.body))
          return result.body;
        }
        else {
          console.warn("error in getting provider infor", result)
          return undefined;
        }
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
      'http://localhost:8080/v1/user/address', data, httpOptions
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
      'http://localhost:8080/v1/user/bankdetail', data, httpOptions
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
      'http://localhost:8080/v1/user/kycdetail', data, httpOptions
    );

  }
}

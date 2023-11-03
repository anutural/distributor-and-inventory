import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IAccessToken, IAddress, IProviderInfo } from '../data-type';

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

  saveAddress(data: IAddress){


    // this.http.post(`http://localhost:8080/v1/self/resetpassw`,data,
    // {observe:'response'})
    // .subscribe((result) => {
    //   console.warn('login res' + result);
    //   if(result && result.body){
                      
    //   }
    //   else{
        
    //   }
    // })
    // ;

    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;
    if (this.accessTokenObject) {

      //print the token
      console.warn('token', this.accessTokenObject.access_token)

      //header
      let headers = new HttpHeaders();
      headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
      headers = headers.append('Content-Type', 'application/json; charset=utf-8');

// const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
// return this.httpClient.post<T>(this.httpUtilService.prepareUrlForRequest(url), body, {headers: headers})

      const httpOptions = {
        headers: headers
      };

      this.http.post(
        'http://cors-anywhere.localhost:8080/v1/user/address', data, httpOptions
      ).subscribe(resp => {
        if (resp) {
          console.warn("save add resp" + resp);
        }
        else {
          console.warn("error" + resp);
        }
      }
      );
    }

    //return "firm address id";
  }
}

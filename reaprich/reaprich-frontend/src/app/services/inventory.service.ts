import { Injectable } from '@angular/core';
import { IAccessToken, IAllItemsListServerResponse, IItem, IItemCategory, IItemInfo, IItemSubCategory, IItemWarehouse, IItemWarehouseCollection, IItemWarehouseServerResponse, IPackingType, IProviderInfo } from '../data-type';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  m_providerInfo: IProviderInfo | undefined;
  m_accessToken: string | undefined;

  accessTokenObject: IAccessToken | undefined;
  m_hostURL = environment.hostURL;

  constructor(private http: HttpClient) { }


  async addCategory(data: IItemCategory) {

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
    return await this.http.post<IItemInfo>(
      this.m_hostURL + 'inventory/item/category', data, { headers: headers, observe: "response" }
    );
  }

  async addSubCategory(data: IItemSubCategory) {

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
    return this.http.post<IItemInfo>(
      this.m_hostURL + 'inventory/item/subcategory', data, { headers: headers, observe: "response" }
    );
  }  

  async addPackingType(data: IPackingType) {

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
    return this.http.post<IItemInfo>(
      this.m_hostURL + 'inventory/item/packingtype', data, { headers: headers, observe: "response" }
    );
  }    

  async addItem(data: IItem) {

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
    return this.http.post<IItemInfo>(
      this.m_hostURL + 'inventory/item', data, { headers: headers, observe: "response" }
    );
  }    

  
  getAllItemListOfOutlet(outletId : string){

    //read the token from local storage.
    this.accessTokenObject = JSON.parse(localStorage.getItem("usersreaprich") ?? "access_token") as IAccessToken;


    //header
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.accessTokenObject.access_token);
    headers = headers.append('Content-Type', 'application/json; charset=utf-8');
    const httpOptions = {
      headers: headers
    };

    console.warn('outlet id --- ', outletId)
    //the post request
    return this.http.get<IItemWarehouseServerResponse>(
      this.m_hostURL + 'inventory/warehouse/items?warehouseID=' + outletId, { headers: headers, observe: "response" }
    );
  }


  allItemsList(){
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
      "itemFilterBy": "NONE",
      "filter":""
    }
    
    return this.http.post<IAllItemsListServerResponse[]>(this.m_hostURL + 'inventory/allitems', data ,httpOptions);
  }


  async addItemIntoWarehouse(data: IItemWarehouseCollection) {

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
    return this.http.post<IItemWarehouseCollection>(
      this.m_hostURL + 'inventory/warehouse/items', data, { headers: headers, observe: "response" }
    );
  }    
}

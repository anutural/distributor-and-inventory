import { FormControl } from "@angular/forms"

export interface ILogin{
    email: string,
    password: string
}

export interface ISignUp{
    name: string,
    password: string,
    email: string
}

export interface ICustomer{
    id: string, // PK
    Actor_Type: {id : string | undefined}, //FK
    Customer_Type: {id : string | undefined}, //FK
    Customer_F_Name: string,
    Customer_L_Name: string,
    Customer_Contact_Number: string,
    Customer_Add: {id : string | undefined}
}


export interface ITeamDev {
    id: string,//PK
    Actor_Type: {id : string | undefined}, //FK
    TD_F_Name: string,
    TD_L_Name: string,
    TD_Contact_No: number,
    TD_PAN: string,
    TD_KYC :  {id : string | undefined}
    TD_Add:  {id : string | undefined}
    TD_Bank:  {id : string | undefined}
    Email : string,
    Password: string,
    Status: string
}


export interface IOutlet{
    id:string, //PK
    actorType: {id : string | undefined}, //FK
    
    outletType:  IOutletType,//FK
    firmName: string,
    firmContactNumber: string,
    firmGSTNumber: string,
    firmPAN: string,
    firmAddress: {id : string | undefined}, //FK
    firmBankDetails: {id : string | undefined}, //FK
    ownerFirstName: string,
    ownerLastName: string,
    ownerContactNumber: string,
    ownerPAN: string,
    ownerKYC: {id : string | undefined}, //FK
    ownerAddress : {id : string | undefined}, //FK
    tdEmail: string,
    email: string,
    password: string
}


export interface IOutletServerResponse{
    outlets : IOutlet[],
    error : ""
}

export interface IActorType{
    id: string | undefined,   
    desc : string,    
}

export interface IOutletType{
    id: string,    
    name: string,
    discount: number
}

interface ComplexSetting {
    data: {
      simpleData: {enable: boolean;};
      view: {expandSpeed: string;};
    };
  }

export interface IAddress{
    id:string,
    actorType: {id : string | undefined},
    name:string,
    addressLine1:string,
    addressLine2:string,
    city:string,
    taluka:string,
    pinCode:string,
    dist:IDistrict,
    zone:IZone,
    state:IState,
    country:ICountry
}

export interface IBankDetails{
    id:string,
    actorType: {id : string | undefined},
    name:string,
    acNumber:string,
    acType:string,
    bankName:string,
    branchName:string,
    ifscCode:string,
}

export interface IKYCDetails{
    id:string,
    actorType: {id : string | undefined},
    name:string,
    idType: [FormControl<IPAN>],
    idNumber:string,
    addProofType:[FormControl<IAadhar>],
    addProofNumber:string,
    documentLinks:string,
}

export interface IActorT{
    id: string,       
}

export interface IIDCommonKeyT{
    id: string | undefined,       

}

export interface IPAN{
    id: string,       
}
export interface IAadhar{
    id: string,       
}

export interface IDistrict{
    id: string,
    name: string,
    zone: IZoneList    
}
export interface IZone{
    id: string,    
    name: string,    
}
export interface IState{
    id: string,    
    name: string,    
}
export interface ICountry{
    id: string,    
    name: string
}

export interface IFirmBankDetails{
    id: string,    
}

export interface IOwnerKYC{
    id: string,    
}

export interface IOwnerAddress{
    id: string,    
}

export interface IOutletAddress{
    id: string,    
}

export interface IOutletType{
    id: string,
    name: string,
    discount: number
}

export interface ICustomerType{
    id: string,    
    discount: number,
    desc : string
}

export interface IDistList{
    id: string,
    name: string,
    zone: IZoneList
}

export interface IZoneList{
    id: string,
    name: string,
    state: IStateList
}

export interface IStateList{
    id: string,
    name: string,
    country: ICountryList
}

export interface ICountryList{
    id: string,
    name: string
}

export interface IKYCIdTypes{
    id: string,
    idDesc: string
}

export interface IKYCAddProofTypes{
    id: string,
    idDesc: string
}

export interface IProviderInfo{
    actorType: IActorType[],
    outletType: IOutletType[],
    customerType: ICustomerType[],
    addDistList: IDistList[],
    addZoneList: IZoneList[],
    addStateList: IStateList[],
    addCountryList: ICountryList[],
    kycIdTypes: IKYCIdTypes[],
    kycAddProofType: IKYCAddProofTypes[],
    error : string | null
}

export interface IAccessToken {
    access_token : string;
}

export interface IServerResponsePut {
    id: string,
    error: string
}


export interface IAddressServerResponse{
    address : IAddress,
    error : ""
}





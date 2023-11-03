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
    id: number, // PK
    Actor_Type: number,
    Customer_Type: number,
    Customer_F_Name: string,
    Customer_L_Name: string,
    Customer_Contact_Number: number,
    TD_Add: number
}


export interface ITeamDev {
    id: number,//PK
    Actor_Type: number, //FK
    TD_F_Name: string,
    TD_L_Name: string,
    TD_Contact_No: number,
    TD_PAN: string,
    TD_KYC : number, // FK
    TD_Add: number,// FK
    TD_Bank: number, //FK
    Email : string,
    Password: string,
    Status: string
}


export interface IOutlet{
    id:string, //PK
    actorType: { id: string },//FK
    outletType: { id: string },//FK
    firmName: string,
    firmContactNumber: string,
    firmGSTNumber: string,
    firmPAN: string,
    firmAddress: { id: string }, //FK
    firmBankDetails: { id: string }, //FK
    ownerFirstName: string,
    ownerLastName: string,
    ownerContactNumber: string,
    ownerPAN: string,
    ownerKYC: { id: string }, //FK
    ownerAddress : { id: string }, //FK
    tdEmail: string,
    email: string,
    password: string
}

export interface IActorType{
    id: string,   
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
    actorType: FormControl<IActorType>,
    name:string,
    addressLine1:string,
    addressLine2:string,
    city:string,
    taluka:string,
    pinCode:string,
    dist:FormControl<IDistrict>,
    zone:FormControl<IZone>,
    state:FormControl<IState>,
    country:FormControl<ICountry>
}

export interface IActorT{
    id: string,       
}

export interface IDistrict{
    id: string,    
}
export interface IZone{
    id: string,    
}
export interface IState{
    id: string,    
}
export interface ICountry{
    id: string,    
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






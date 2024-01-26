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
    actorType: {id : string | undefined}, //FK
    customerType: {id : string | undefined}, //FK
    firstName: string,
    lastName: string,
    contactNumber: string,
    address: {id : string | undefined}
}


export interface ITeamDev {
    id: string,//PK
    actorType: {id : string | undefined}, //FK
    firstName: string,
    lastName: string,
    contactNumber: number,
    PAN: string,
    KYC :  {id : string | undefined}
    address:  {id : string | undefined}
    bankDetails:  {id : string | undefined}
    email : string,
    password: string,
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

export interface ICustomerResponse{
    customers : ICustomer[],
    error : ""
}

export interface ICustomerServerResponse{
    customer : ICustomer,
    error : ""
}

export interface ITeamDevServerResponse{
    teamdevs : ITeamDev[],
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
    zone: {id : string | undefined}, 
}
export interface IZone{
    id: string,    
    name: string,    
    state: {id : string | undefined},
}
export interface IState{
    id: string,    
    name: string,    
    country: {id : string | undefined},
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

export interface IItemInfo{
    categories: IItemCategory[],
    packingTypes: IPackingType[],
    subcategories: IItemSubCategory[],    
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

export interface IItemCategory
{
    id : string,
    category : string
}

export interface IItemSubCategory
{
    id : string,
    category: {id : string | undefined},
    subcategory : string
}

export interface IPackingType
{
    id : string,
    packingType : string,
    quantityInGrams : string,
    container : string
}

export interface IItem
{
    id : string,
    name : string,
    packingType : {id : string | undefined},
    category: {id : string | undefined},
    subcategory: {id : string | undefined},
    itemPrices: {gl : string | undefined,sl : string | undefined,pl : string | undefined},
    retailPrice : string,
    pictureLink : string,
    thumbnailLink : string,
    gstprice : string
}


export interface IAllItemsListServerResponse{
    items : IItem[],
    error : ""
}

export interface IItemWarehouse {    
        item: { id: string | undefined },
        outlet: { id: string | undefined },
        batchNumber: string,
        mfgDate: string,
        expDate: string,
        state: string,
        quantity: string    
}

export interface IItemWarehouseCollection {
    addWarehouseInventoryItemsRequests : IItemWarehouse[]
}

export interface IItemWarehouseServerResponse {
    inventoryItems: IInventoryItems[]
}

export interface IInventoryItems {
    itemId: {            
        wareHouseItems: IItemWarehouse[]
    }
}





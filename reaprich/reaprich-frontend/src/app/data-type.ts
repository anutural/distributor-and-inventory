export interface ILogin{
    email: String,
    password: String
}

export interface ISignUp{
    name: string,
    password: string,
    email: string
}

export interface ICustomer{
    id: number,
    actorType: number,
    customerType: number,
    firstName: string,
    lastName: string,
    contactNumber: number,
    teamDevId: number
}

export interface IProduct{
    name:string,
    price:number,
    category:string,
    color:string,
    description:string,
    image:string,
    id: number,
    quantity: undefined | number;
    productId: undefined | number;
}

export class Customer{
    public actorType: string;
    public customerType: string;
    public fName: string;
    public lName: string;
    public address: string;
    public tdAdded: string;
    
    constructor(actorType: string, customerType: string,fName: string, lName: string,address: string, tdAdded: string){
        this.actorType =  actorType;
        this.customerType =  customerType;
        this.fName =  fName;
        this.lName =  lName;
        this.address =  address;
        this.tdAdded =  tdAdded;                                
    }
}
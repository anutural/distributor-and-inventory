import { Component} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IOutletAddress, IOutlet, IOutletType, IProviderInfo,  IAddress } from 'src/app/data-type';
import { CommonService } from 'src/app/services/common.service';
import { OutletService } from 'src/app/services/outlet.service';
import { ModalDismissReasons,NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-outlet-add',
  templateUrl: './outlet-add.component.html',
  styleUrls: ['./outlet-add.component.css']
})
export class OutletAddComponent {    
  closeResult : string | undefined;
  actorType = "Outlet";
  addOutletMessage: string | undefined;
  outletTypeList : IOutletType[] | undefined;
  providerInfo : IProviderInfo | undefined;
  // outletId : string | undefined = "Outlet Type"
  // districtId : string | undefined = "District"
  // zoneId :  string | undefined = "Zone"
  // stateId :  string | undefined = "State"
  // countryId : string | undefined = "Country"
  firmAddressId : string | undefined;

  // outlet firm address
  firmAddress : string | undefined;
  firmAddressModuleError : string | undefined;

  constructor(private outletService : OutletService, 
    private commonService : CommonService, 
    private activatedRoute : ActivatedRoute,
    private modalService : NgbModal){

  }

  ngOnInit(){

    let cartData = localStorage.getItem('providerInfo');
    if (cartData) {
      this.providerInfo = JSON.parse(cartData);
    }
    console.warn('provider:', this.providerInfo);
  }

  addOutletSubmit(outletData: IOutlet){
    console.warn(outletData);
  }

  addFirmAddress(addressData: IOutletAddress){
    console.warn(addressData);
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  submitFirmAddress(firmAddress: IAddress){

    //save the address in address api and close the modal
    // get the address uuid and save it in local variable.
    //that local variable will be used in the Add Outlet Click.
    console.warn('closing the modal : ', firmAddress);
    this.commonService.saveAddress(firmAddress);
    this.closeResult = "closed";

    //convert the address into a single string comma saperated
    

    //Object.keys(firmAddress).map(function(k){return firmAddress[k]}).join(",");

    let tem = Object.values(firmAddress).join(",");
    console.warn('tem-', tem)
    
  
  }
  
}

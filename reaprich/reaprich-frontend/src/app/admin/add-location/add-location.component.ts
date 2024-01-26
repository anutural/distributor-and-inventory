import {Component} from '@angular/core';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule, FormGroup, FormControl} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { CommonService } from 'src/app/services/common.service';
import { ICountry, IDistrict, IProviderInfo, IServerResponsePut, IState, IZone } from 'src/app/data-type';
import { MatOptionModule } from '@angular/material/core';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';

/**
 * @title Stepper vertical
 */
@Component({
  selector: 'app-add-location',
  templateUrl: './add-location.component.html',
  styleUrls: ['./add-location.component.css'],
  standalone: true,
  imports: [
    MatButtonModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatOptionModule,
    CommonModule,
    MatSelectModule 
  ],
})

export class AddLocationComponent {
  firstFormGroup = this._formBuilder.group({
    id : ['', Validators.required],
    name: ['', Validators.required],
  });

  

  secondFormGroup = this._formBuilder.group({
    id: ['', Validators.required],
    name: ['', Validators.required],
    country : new FormGroup({
      id: new FormControl()
    })
  });

  thirdFormGroup = this._formBuilder.group({
    id: ['', Validators.required],
    name: ['', Validators.required],
    country : new FormGroup({
      id: new FormControl()
    }),
    state : new FormGroup({
      id: new FormControl()
    })
  });
  fourthFormGroup = this._formBuilder.group({
    id: ['', Validators.required],
    name: ['', Validators.required],
    country : new FormGroup({
      id: new FormControl()
    }),
    state : new FormGroup({
      id: new FormControl()
    }),
    zone : new FormGroup({
      id: new FormControl()
    })
  });    
  isLinear = false; //previous form is required to completed before going to next
  errorMessage : string | undefined;
  providerInfo: IProviderInfo | undefined;



  constructor(private _formBuilder: FormBuilder, private commonService : CommonService) {


  }


  ngOnInit(){
    let providerInfo = this.getFromLocalStorage('providerInfo');
    if (providerInfo) {
      this.providerInfo = JSON.parse(providerInfo);
    }
    
  }

  async onAddCountry(countryObject: ICountry) {

      if (countryObject.id == "" || countryObject.name == ""){    
        this.errorMessage = "Please enter valid value";
        return;
      }

     (await this.commonService.addCountry(countryObject)).subscribe((result: any) => {
      console.warn("I am here");
      let servResp = result as IProviderInfo;
      console.warn("res1 " + servResp);
      if (result) {
        localStorage.setItem('providerInfo', JSON.stringify(result.body))
        this.providerInfo = result.body;
        alert("Country Added Successfully!");        
      }
    }, (err) => {
      this.errorMessage = err.error.error;
      alert(err.error.error);   
    });
  }

  async onAddState(stateObject : IState)
  {
    if (stateObject.id == "" || stateObject.name == ""){    
      alert('Please enter valid values');
      return;
    }

    (await this.commonService.addState(stateObject)).subscribe((result: any) => {
      console.warn("I am here");
      let servResp = result as IProviderInfo;      
      if (result) {
        localStorage.setItem('providerInfo', JSON.stringify(result.body))
        this.providerInfo = result.body;
        alert("State Added Successfully!");        
      }
    }, (err) => {
      this.errorMessage = err.error.error;
      alert(err.error.error);   
    });
  }

  async onAddZone(zoneObject : IZone)
  {
    if (zoneObject.id == "" || zoneObject.name == ""){    
      alert('Please enter valid values');
      return;
    }

    console.warn("Zone Object : ", zoneObject);

    (await this.commonService.addZone(zoneObject)).subscribe((result: any) => {
      console.warn("I am here");
      let servResp = result as IProviderInfo;      
      if (result) {
        localStorage.setItem('providerInfo', JSON.stringify(result.body))
        this.providerInfo = result.body;
        alert("Zone Added Successfully!");        
      }
    }, (err) => {
      this.errorMessage = err.error.error;
      alert(err.error.error);   
    });
  }  


  async onAddDistrict(distObject : IDistrict)
  {
    if (distObject.id == "" || distObject.name == ""){    
      alert('Please enter valid values');
      return;
    }

    console.warn("Zone Object : ", distObject);

    (await this.commonService.addDistrict(distObject)).subscribe((result: any) => {      
      let servResp = result as IProviderInfo;      
      if (result) {
        localStorage.setItem('providerInfo', JSON.stringify(result.body))
        this.providerInfo = result.body;
        alert("District Added Successfully!");        
      }
    }, (err) => {
      this.errorMessage = err.error.error;
      alert(err.error.error);   
    });
  }  

  getFromLocalStorage(key: string) {
    return localStorage.getItem(key);
  }


}

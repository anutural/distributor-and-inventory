import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  //images = "../../assets/images/home-image-1.png";
   images = [944, 1011, 984].map((n) => `../../assets/images/home-image-1.png`);

}

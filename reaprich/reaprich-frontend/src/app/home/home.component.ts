import { Component } from '@angular/core';
import { IProduct } from '../data-type';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  popularProducts : undefined | IProduct[];
  trendyProducts: undefined | IProduct[];
  images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);

}

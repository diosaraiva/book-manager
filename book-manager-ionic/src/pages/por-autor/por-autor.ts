import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { RestProvider } from '../../providers/rest/rest';

@Component({
  selector: 'page-por-autor',
  templateUrl: 'por-autor.html'
})
export class PorAutorPage {

  constructor(public navCtrl: NavController) {
  }
  
}

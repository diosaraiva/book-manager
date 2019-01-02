import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { VisualizarLivrosPage } from '../visualizar-livros/visualizar-livros';
import { EditarLivroPage } from '../editar-livro/editar-livro';
import { PorAutorPage } from '../por-autor/por-autor';

@Component({
  selector: 'page-tabs',
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root: any = VisualizarLivrosPage;
  tab2Root: any = EditarLivroPage;
  tab3Root: any = PorAutorPage;
  constructor(public navCtrl: NavController) {
  }
  
}

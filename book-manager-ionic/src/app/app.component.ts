import { Component, ViewChild } from '@angular/core';
import { Platform, Nav } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { EditarLivroPage } from '../pages/editar-livro/editar-livro';
import { PorAutorPage } from '../pages/por-autor/por-autor';


import { VisualizarLivrosPage } from '../pages/visualizar-livros/visualizar-livros';



@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) navCtrl: Nav;
    rootPage:any = VisualizarLivrosPage;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
    });
  }
  goToVisualizarLivros(params){
    if (!params) params = {};
    this.navCtrl.setRoot(VisualizarLivrosPage);
  }goToEditarLivro(params){
    if (!params) params = {};
    this.navCtrl.setRoot(EditarLivroPage);
  }goToPorAutor(params){
    if (!params) params = {};
    this.navCtrl.setRoot(PorAutorPage);
  }
}

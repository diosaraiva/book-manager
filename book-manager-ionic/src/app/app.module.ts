import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { VisualizarLivrosPage } from '../pages/visualizar-livros/visualizar-livros';
import { EditarLivroPage } from '../pages/editar-livro/editar-livro';
import { PorAutorPage } from '../pages/por-autor/por-autor';
import { TabsPage } from '../pages/tabs/tabs';
import { HttpClientModule } from '@angular/common/http'; 

import { AdicionarLivroPage } from '../pages/adicionar-livro/adicionar-livro';
import { AdicionarAutorPage } from '../pages/adicionar-autor/adicionar-autor';
import { AdicionarEditoraPage } from '../pages/adicionar-editora/adicionar-editora';
import { AdicionarCriticaPage } from '../pages/adicionar-critica/adicionar-critica';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { RestProvider } from '../providers/rest/rest';

@NgModule({
  declarations: [
    MyApp,
    
    VisualizarLivrosPage,
    EditarLivroPage,
    AdicionarLivroPage,
    PorAutorPage,
    
    AdicionarAutorPage,
    AdicionarEditoraPage,
    AdicionarCriticaPage,
    
    TabsPage
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    
    VisualizarLivrosPage,
    EditarLivroPage,
    PorAutorPage,
    
    AdicionarLivroPage,
    AdicionarAutorPage,
    AdicionarEditoraPage,
    AdicionarCriticaPage,
    
    TabsPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    RestProvider
  ]
})
export class AppModule {}
import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AdicionarCriticaPage } from './adicionar-critica';

@NgModule({
  declarations: [
    AdicionarCriticaPage,
  ],
  imports: [
    IonicPageModule.forChild(AdicionarCriticaPage),
  ],
})
export class AdicionarCriticaPageModule {}

import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AdicionarAutorPage } from './adicionar-autor';

@NgModule({
  declarations: [
    AdicionarAutorPage,
  ],
  imports: [
    IonicPageModule.forChild(AdicionarAutorPage),
  ],
})
export class AdicionarAutorPageModule {}

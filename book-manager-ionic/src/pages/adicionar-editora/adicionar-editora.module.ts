import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AdicionarEditoraPage } from './adicionar-editora';

@NgModule({
  declarations: [
    AdicionarEditoraPage,
  ],
  imports: [
    IonicPageModule.forChild(AdicionarEditoraPage),
  ],
})
export class AdicionarEditoraPageModule {}

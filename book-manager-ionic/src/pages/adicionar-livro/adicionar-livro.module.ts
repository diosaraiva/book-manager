import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { AdicionarLivroPage } from './adicionar-livro';

@NgModule({
  declarations: [
    AdicionarLivroPage,
  ],
  imports: [
    IonicPageModule.forChild(AdicionarLivroPage),
  ],
})
export class AdicionarLivroPageModule {}

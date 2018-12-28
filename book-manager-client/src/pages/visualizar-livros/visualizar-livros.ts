import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { EditarLivroPage } from "../editar-livro/editar-livro";

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-visualizar-livros',
    templateUrl: 'visualizar-livros.html'
})
export class VisualizarLivrosPage {
    
    data = [];
    
    constructor(public navCtrl: NavController, public rp:RestProvider) {
        this.getLivros();
        
    }
    
    //
    getLivros() {
        this.rp.getAll()
        .then(
                data => {
                    
                    this.data = data;
                    console.log(this.data);
                }
        );
    }
    
    //
    pushLivro(item) {
        this.navCtrl.push(EditarLivroPage, {item:item});
    }
    
}

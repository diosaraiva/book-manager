import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { Observable } from 'rxjs';

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-editar-livro',
    templateUrl: 'editar-livro.html'
})
export class EditarLivroPage {
    
    isbn;
    opcoes;
    data = [];
    
    constructor(public navCtrl: NavController, nav:NavParams, public rp:RestProvider) {
        this.isbn = nav.get("item");
        this.getIsbn(this.isbn);
        this.opcoes = "livro";
    }
    
    getIsbn(isbn:number) {
        this.rp.selecionarLivroPorIsbn(isbn)
        .then(
                data => {
                    
                    this.data = data;
                    console.log(this.data);
                }
        );
    }
    
    deleteLivro(isbn:number){
        
        this.rp.removerLivro(isbn);
    
    }
}

import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { EditarLivroPage } from "../editar-livro/editar-livro";

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-por-autor',
    templateUrl: 'por-autor.html'
})
export class PorAutorPage {
    
    id;
    data;
    
    constructor(public navCtrl: NavController, public rp:RestProvider) {
        this.id=0;
        this.getAutores();
    }
    
    pushLivro(item) {
        this.navCtrl.push(EditarLivroPage, {item:item});
    }
    
    getAutores() {
        
        if(this.id == ""){
            this.id=0;
        }
        
        this.rp.listarLivrosPorAutor(this.id)
        .then(
                data => {
                    this.data = data;
                    console.log(this.data);
                }
        ).catch(error=> {
            console.log("Nao foi possivel encontrar autor");
        });
    }
    
}

import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { EditarLivroPage } from "../editar-livro/editar-livro";

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-visualizar-livros',
    templateUrl: 'visualizar-livros.html'
})
export class VisualizarLivrosPage {
    
    data;
    datas;
    
    constructor(public navCtrl: NavController, public rp:RestProvider) {
        
        this.getLivros();      
        
    }
    
    getLivros() {
        this.rp.listarLivros()
        .then(
                data => {
                    this.data = data;
                    console.log(this.data);
                }
        ).then(()=>this.initializeLivros());
    }
    
    
    pushLivro(item) {
        this.navCtrl.push(EditarLivroPage, {item:item});
    }
    
    initializeLivros() {
        
        
        this.datas = this.data;
        
        console.log(this.datas);
    }    
    
    findLivros(ev) {
        
        // Reset items back to all of the items
        this.initializeLivros();
        
        // Set val to the value of the Search Bar
        let val = ev.target.value;
        
        // If the value is an empty string don't filter the items
        if (val && val.trim() != '') {
            this.datas = this.datas.filter((item) => {
                return (item.titulo.toLowerCase().indexOf(val.toLowerCase()) > -1);
            })
        }
    }
    
}

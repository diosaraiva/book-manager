import { Component } from '@angular/core';
import { NavController, NavParams, AlertController } from 'ionic-angular';
import { VisualizarLivrosPage } from "../visualizar-livros/visualizar-livros";
import { Observable } from 'rxjs';

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-editar-livro',
    templateUrl: 'editar-livro.html'
})
export class EditarLivroPage {
    
    id;
    isbn;
    titulo;
    dataPublicacao;
    preco;
    valorPorExtenso;
    sinopse;
    
    opcoes;
    data;
    
    constructor(public navCtrl: NavController, nav:NavParams, public rp:RestProvider, public alertCtrl: AlertController) {
        this.id = nav.get("item");
        this.getIsbn(this.id);
        this.opcoes = "livro";
    }
    
    adicionarLivro(){
        this.rp.adicionarLivro(this.isbn, this.titulo, this.dataPublicacao, this.preco, this.sinopse).then(data=>{
            console.log("sucesso");
        });
    }
    
    getIsbn(isbn:number) {
        this.rp.selecionarLivroPorIsbn(isbn)
        .then(
                data => {
                    this.isbn = data["isbn"];
                    this.titulo = data["titulo"];
                    this.dataPublicacao = new Date(data["dataPublicacao"]).toISOString;
                    this.preco = data["preco"];
                    this.valorPorExtenso = data["valorPorExtenso"];
                    this.sinopse = data["sinopse"];
                    
                    this.data = data;
                    console.log(this.data);
                }
        );
    }
    
    deleteLivro(isbn:number){
        
        this.rp.removerLivro(isbn).then(result=>{
            this.alertaExcluir();
        }).then(
                result => {this.navCtrl.setRoot(VisualizarLivrosPage)});
        
    }
    
    formatarData(date){
        
        let formatado = new Date(date);
        return formatado.getFullYear()+"-"+(formatado.getMonth()+1)+"-"+formatado.getDay();
    }
    
    alerta(isbn:number) {
        const confirm = this.alertCtrl.create({
            title: 'Alerta!',
            message: 'Você tem certeza disso?',
            buttons: [
                      {
                          text: 'Sim',
                          handler: () => {
                              this.deleteLivro(isbn);
                          }
                      },
                      {
                          text: 'Não'
                      }
                      ]
        });
        confirm.present();
    }
    
    alertaExcluir() {
        const confirm = this.alertCtrl.create({
            title: 'Alerta!',
            message: 'Excluido com sucesso',
            buttons: ["OK"]
        });
        confirm.present();
    }
    
    adicionarAutor(){
        
    }
    
    editarAutor(id: number){
        
    }
    
    removerAutor(id: number){
        
    }
    
    adicionarEditora(){
        
    }
    
    editarEditora(id: number){
        
    }
    
    removerEditora(id: number){
        
    }
    
    adicionarCritica(){
        
    }
    
    editarCritica(id: number){
        
    }
    
    removerCritica(id: number){
        
    }
    
}

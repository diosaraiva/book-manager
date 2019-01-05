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
    
    
    //////////////// OBJETOS DE LIVRO ////////////////
    isbn;
    titulo;
    linkImg;
    dataPublicacao;
    preco;
    valorPorExtenso;
    sinopse;
    
    
    //////////////// OBJETOS DE AUTOR ////////////////
    
    
    //////////////// OBJETOS DE EDITORA ////////////////
    
    
    //////////////// OBJETOS DE CRÍTICA ////////////////
    
    
    //////////////// OUTROS OBJETOS ////////////////
    id;
    opcoes;
    data;
    
    
    constructor(public navCtrl: NavController, nav:NavParams, public rp:RestProvider, public alertCtrl: AlertController) {
        
        this.id = nav.get("item");
        
        this.opcoes = "livro";
        
        this.getIsbn(this.id);
    }
    
    
    //////////////// INFORMACOES DO LIVRO ////////////////
    adicionarLivro(){
        this.rp.adicionarLivro(this.isbn, this.titulo, this.linkImg, this.dataPublicacao, this.preco, this.sinopse).then(data=>{
            console.log("sucesso");
        });
    }
    
    getIsbn(isbn:number) {
        this.rp.selecionarLivroPorIsbn(isbn)
        .then(
                data => {
                    this.isbn = data["isbn"];
                    this.titulo = data["titulo"];
                    this.linkImg = data["linkImg"];
                    this.dataPublicacao = this.formatarData(data["dataPublicacao"]);
                    this.preco = data["preco"];
                    this.valorPorExtenso = data["valorPorExtenso"];
                    this.sinopse = data["sinopse"];
                    
                    this.data = data;
                    console.log(this.data);
                }
        );
    }
    
    removerLivro(isbn:number){
        
        this.rp.removerLivro(isbn).then(result=>{
            this.alertaExcluir();
        }).then(
                result => {this.navCtrl.setRoot(VisualizarLivrosPage)});
    }
    
    
    //////////////// AUTOR ////////////////
    adicionarAutor(){
        
    }
    
    editarAutor(id: number){
        
    }
    
    removerAutor(id: number){
        
    }
    
    
    //////////////// EDITORA ////////////////
    adicionarEditora(){
        
    }
    
    editarEditora(id: number){
        
    }
    
    alterarEditora(id: number){
        
    }
    
    removerEditora(id: number){
        
    }
    
    
    //////////////// CRITICA ////////////////
    adicionarCritica(){
        
    }
    
    editarCritica(id: number){
        
    }
    
    removerCritica(id: number){
        
    }
    
    
    //////////////// OUTROS METODOS ////////////////
    formatarData(date){
        
        let formatado = new Date(date).toISOString().substr(0,10);
        return formatado;
    }
    
    alerta(isbn:number) {
        const confirm = this.alertCtrl.create({
            title: 'Confirmação',
            message: 'Confirma a ação?',
            buttons: [
                      {
                          text: 'Sim',
                          handler: () => {
                              this.removerLivro(isbn);
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
    
}

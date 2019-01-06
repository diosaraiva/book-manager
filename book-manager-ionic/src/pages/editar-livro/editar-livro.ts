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
    autores;
    
    
    //////////////// OBJETOS DE EDITORA ////////////////
    editora;
    idEditora;
    nomeEditora;
    site;
    
    //////////////// OBJETOS DE CRÍTICA ////////////////
    criticas;
    idCritica;
    nomeCritico;
    nota;
    texto;
    
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
        
        let date = new Date().setUTCMilliseconds(this.dataPublicacao);
        
        this.rp.adicionarLivro(this.isbn, this.titulo, this.linkImg, date, this.preco, this.sinopse).then(data=>{
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
                    
                    
                    this.editora = data["editora"];
                    
                    this.idEditora = this.editora["id"];
                    this.nomeEditora = this.editora["nomeEditora"];
                    this.site = this.editora["site"];
                    
                    if(data["autores"].length>0){
                        
                        this.autores = data["autores"];
                        
                    }else{
                        this.autores = "";
                    }
                    
                    if(data["criticas"].length>0){
                        
                        this.criticas = data["criticas"];
                        
                    }else{
                        this.criticas = "";
                    }
                    
                    this.data = data;
                    console.log(this.data);
                    
                }
        );
    }
    
    editarLivro(){
        
        let date = new Date().setUTCMilliseconds(this.dataPublicacao);
        
        this.rp.editarLivro(this.isbn, this.titulo, this.linkImg, date, this.preco, this.sinopse).then(data=>{
            console.log("sucesso");
        });
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
        console.log(this.nomeEditora);
        console.log(this.site);
        
        this.rp.adicionarEditora(this.nomeEditora, this.site).then(data=>{
            console.log("sucesso");
        });
    }
    
    editarEditora(idEditora: number){
        
        this.rp.editarEditora(this.idEditora, this.nomeEditora, this.site).then(data=>{
            console.log("sucesso");
        });
    }
    
    removerEditora(idEditora: number){
        
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

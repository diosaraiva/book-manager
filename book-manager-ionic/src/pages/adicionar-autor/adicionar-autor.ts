import { Component } from '@angular/core';
import { ModalController, NavParams, ViewController, AlertController } from 'ionic-angular';

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-adicionar-autor',
    templateUrl: 'adicionar-autor.html',
})
export class AdicionarAutorPage {
    
    id=0;
    nomeAutor;
    nacionalidade;
    
    data=[];
    
    constructor(public rp:RestProvider,
            public params: NavParams,
            public viewCtrl: ViewController,
            public alertCtrl: AlertController
    ){
        
        this.data = params.get("item");
        
        console.log(this.data);
        this.getAutor();
    }
    
    dismiss() {
        this.viewCtrl.dismiss();
        
    }
    
    getAutor(){
        if(this.data != null){
            this.id = this.data["id"];
            this.nomeAutor = this.data["nomeAutor"];
            this.nacionalidade = this.data["nacionalidade"];
        }
    }
    
    adicionarAutor(){
        
        if(this.id != 0){
            this.rp.editarAutor(this.id, this.nomeAutor, this.nacionalidade).then(data=>{
                console.log(data);
            }).then(data => {
                this.alerta("Mensagem","Titulo")
            });
        }else{
            this.rp.adicionarAutor(0, this.nomeAutor, this.nacionalidade).then(data=>{
                console.log(data);
            }).then(data => {
                this.alerta("Mensagem","Titulo")
            });
        }
        
    }
    
    alerta(mensagem, titulo){
        let alert = this.alertCtrl.create({
            title: titulo,
            subTitle: mensagem,
            buttons: [{
                text: "Ok, Obrigado",
                handler: () => { 
                    this.dismiss();
                }
            }]
        });
        alert.present();
    }
}

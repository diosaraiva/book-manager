import { Component } from '@angular/core';
import { ModalController, NavParams, ViewController, AlertController } from 'ionic-angular';

import { RestProvider } from '../../providers/rest/rest';

@Component({
    selector: 'page-adicionar-editora',
    templateUrl: 'adicionar-editora.html',
})
export class AdicionarEditoraPage {
    
    id;
    nomeEditora;
    site;
    
    constructor(public rp:RestProvider,
            public params: NavParams,
            public viewCtrl: ViewController,
            public alertCtrl: AlertController
    ){ }
    
    ionViewDidLoad() {
        console.log('ionViewDidLoad AdicionarEditoraPage');
    }
    
    dismiss() {
        this.viewCtrl.dismiss();
    }
    
    adicionarEditora(){
        console.log(this.nomeEditora);
        console.log(this.site);
        
        this.rp.adicionarEditora(0, this.nomeEditora, this.site).then(data=>{
            console.log(data);
        }).then(data => {
            this.alerta("Mensagem","Titulo")
        });
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

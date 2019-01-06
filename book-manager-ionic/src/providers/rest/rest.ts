import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RequestOptions, Request, RequestMethod} from '@angular/http';
import { Injectable } from '@angular/core';
import { concat } from 'rxjs/operators';
import 'rxjs/add/operator/map';

/*
  Generated class for the RestProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
 */

@Injectable()
export class RestProvider {
    
    //String de URL do Servidor BookManager
    URL:string = 'http://localhost:8080'; 

data:any;
novo:any;

constructor(public http: HttpClient) {
    console.log('============== BookManager REST Provider ==============');
}


///////////////////////// LIVRO /////////////////////////
adicionarLivro(isbn, titulo, linkImg, dataPublicacao, preco, sinopse){
    
    return new Promise((resolve, reject) => {
        var livro = {
                isbn: isbn,
                titulo: titulo,
                linkImg: linkImg,
                dataPublicacao: dataPublicacao,
                preco: preco,
                sinopse: sinopse
        }
        this.http.post(this.URL+'/livros/novo', livro)
        .subscribe((result) => {
            resolve(result);
        },
        (error) => {
            reject(error);
        });
    }); 
}

listarLivros(){
    
    return new Promise((resolve,reject) => {
        this.http.get(this.URL+'/livros')
        .subscribe(data => {
            this.data = data;
            console.log(data);
            resolve(this.data);
        });
    });
}

selecionarLivroPorIsbn(isbn: number) {
    
    return new Promise((resolve, reject) => {
        this.http.get(this.URL+'/livros/'+isbn)
        .subscribe(data => {
            this.data = data;
            console.log(data);
            resolve(this.data);
        });
    });
}

editarLivro(isbn, titulo, linkImg, dataPublicacao, preco, sinopse){
    
    return new Promise((resolve, reject) => {
        var livro = {
                isbn: isbn,
                titulo: titulo,
                linkImg: linkImg,
                dataPublicacao: dataPublicacao,
                preco: preco,
                sinopse: sinopse
        }        
        this.http.put(this.URL+'/livros/'+livro.isbn, livro)
        .subscribe((result: any) => {
            resolve(result);
        },
        (error) => {
            reject(error);
        });
    });
}

removerLivro(isbn:number){
    
    return new Promise((resolve, reject) => {
        this.http.delete(this.URL+'/livros/'+isbn)
        .subscribe((result: any) => {
            resolve(result);
        },
        (error) => {
            reject(error);
        });
    });
}


///////////////////////// AUTOR /////////////////////////
adicionarAutor(id, nomeAutor, nacionalidade) {
    return new Promise((resolve, reject) => {
        var autor = {
                id: id,
                nomeAutor: nomeAutor,
                nacionalidade: nacionalidade,  
        }
        this.http.post(this.URL+'/autores/novo', autor)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

editarAutor(id, nomeAutor, nacionalidade) {
    return new Promise((resolve, reject) => {
        var autor = {
                id: id,
                nomeAutor: nomeAutor,
                nacionalidade: nacionalidade
        }
        this.http.put(this.URL+'/autores/'+id, autor)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

removerAutor(id){
    return new Promise((resolve, reject) => {
        this.http.delete(this.URL+'/autores/'+id)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

///////////////////////// EDITORA /////////////////////////
adicionarEditora(nomeEditora, site) {
    return new Promise((resolve, reject) => {
        var editora = {
                nomeEditora: nomeEditora,
                site: site,
        }
        this.http.post(this.URL+'/editoras/novo', editora)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

editarEditora(id, nomeEditora, site) {
    return new Promise((resolve, reject) => {
        var editora = {
                id: id,
                nomeEditora: nomeEditora,
                site: site
        }
        this.http.put(this.URL+'/editoras/', editora)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

removerEditora(id){
    return new Promise((resolve, reject) => {
        this.http.delete(this.URL+'/editoras/'+id)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}


///////////////////////// CRÍTICA /////////////////////////
adicionarCritica(nomeCritica, site) {
    return new Promise((resolve, reject) => {
        var editora = {
                nomeCritica: nomeCritica,
                site: site,
        }
        this.http.post(this.URL+'/criticas/novo', editora)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

editarCritica(id, nomeCritica, site) {
    return new Promise((resolve, reject) => {
        var editora = {
                id: id,
                nomeCritica: nomeCritica,
                site: site
        }
        this.http.put(this.URL+'/criticas/', editora)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

removerCritica(id){
    return new Promise((resolve, reject) => {
        this.http.delete(this.URL+'/criticas/'+id)
        .subscribe(dados => {
            console.log(dados);
            resolve(dados);
        }, error => {
            console.log(error);
            reject(error);
        });
    });
}

///////////////////////// OUTROS /////////////////////////


}

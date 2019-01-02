import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RequestOptions, Request, RequestMethod} from '@angular/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';

/*
  Generated class for the RestProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
 */

@Injectable()
export class RestProvider {
    
    data:any;
novo:any;

constructor(public http: HttpClient) {
    console.log('Hello RestProvider Provider');
}

editarLivro(livro){
    return new Promise((resolve, reject) => {
        
        this.http.put("http://localhost:8080/livros/"+livro.isbn, livro)
        .subscribe((result: any) => {
            resolve(result.json());
        },
        (error) => {
            reject(error.json());
        });
    });
}

adicionarLivro(livro){
    return new Promise((resolve, reject) => {
        this.http.post("http://localhost:8080/livros/novo", livro)
        .subscribe((result: any) => {
            resolve(result.json());
        },
        (error) => {
            reject(error.json());
        });
    }); 
}

selecionarLivroPorIsbn(isbn: number) {
    return new Promise(resolve => {
        this.http
        .get(
                `http://localhost:8080/livros/`+isbn
        )
        .subscribe(data => {
            this.data = data;
            console.log(data);
            resolve(this.data);
        });
    });
}

removerLivro(isbn:number){
    
    return new Promise((resolve, reject) => {
        this.http.delete("http://localhost:8080/livros/"+isbn)
        .subscribe((result: any) => {
            resolve(result);
        },
        (error) => {
            reject(error);
        });
    });
}

listarLivros(){ 
    if (this.data)
    {
        return Promise.resolve(this.data);  
    }
    
    return new Promise(resolve => {
        this.http.get('http://localhost:8080/livros')
        .subscribe(data => {
            this.data = data;
            console.log(data);
            resolve(this.data);
        });
    });
}}

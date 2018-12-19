package br.com.diosaraiva.bookmanager.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nomeEditora;
    private String site;

}
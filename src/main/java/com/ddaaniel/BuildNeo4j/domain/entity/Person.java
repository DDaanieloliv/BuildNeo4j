package com.ddaaniel.BuildNeo4j.domain.entity;

public class Person {

    private String nome;

    public Person () {}

    public Person (String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNomeAuthor(String nome){
        this.nome= this.nome;
    }
}

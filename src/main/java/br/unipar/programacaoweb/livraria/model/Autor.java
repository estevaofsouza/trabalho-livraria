package br.unipar.programacaoweb.livraria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String nacionalidade;

    private Date dataNascimento;

    private String email;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Livro> livros = new ArrayList<>();

}
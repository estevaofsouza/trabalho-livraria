package br.unipar.programacaoweb.livraria.repository;

import br.unipar.programacaoweb.livraria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeContainingIgnoreCase(String nome);

    // Query para buscar autores com seus livros
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> findAllWithLivros();
}
package br.unipar.programacaoweb.livraria.dto;

import br.unipar.programacaoweb.livraria.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {
    private Long id;
    private String titulo;
    private Integer numeroPaginas;
    private String genero;

    public static LivroDTO fromLivro(Livro livro) {
        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setNumeroPaginas(livro.getNumeroPaginas());
        dto.setGenero(livro.getGenero());
        return dto;
    }
}
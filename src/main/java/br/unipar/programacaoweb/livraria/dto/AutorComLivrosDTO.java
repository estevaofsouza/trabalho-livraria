package br.unipar.programacaoweb.livraria.dto;

import br.unipar.programacaoweb.livraria.model.Autor;
import br.unipar.programacaoweb.livraria.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorComLivrosDTO {
    private Long id;
    private String nome;
    private String nacionalidade;
    private Date dataNascimento;
    private String email;
    private List<LivroDTO> livros;

    public static AutorComLivrosDTO fromAutor(Autor autor) {
        AutorComLivrosDTO dto = new AutorComLivrosDTO();
        dto.setId(autor.getId());
        dto.setNome(autor.getNome());
        dto.setNacionalidade(autor.getNacionalidade());
        dto.setDataNascimento(autor.getDataNascimento());
        dto.setEmail(autor.getEmail());

        // Converter cada livro para LivroDTO (sem recursividade ao autor)
        dto.setLivros(autor.getLivros().stream()
                .map(LivroDTO::fromLivro)
                .collect(Collectors.toList()));

        return dto;
    }
}
package br.unipar.programacaoweb.livraria.controller;

import br.unipar.programacaoweb.livraria.dto.AutorComLivrosDTO;
import br.unipar.programacaoweb.livraria.model.Autor;
import br.unipar.programacaoweb.livraria.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autor")
public class AutorController {

    private AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Autor>> listarAutores() {
        List<Autor> autores = autorService.listarTodos();
        if(autores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Autor> buscarAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if(autor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autor);
    }

    @GetMapping("/buscar/nome/{nome}")
    public ResponseEntity<List<Autor>> buscarAutoresPorNome(@PathVariable String nome) {
        List<Autor> autores = autorService.buscarPorNome(nome);
        if(autores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(autores);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Autor> salvarAutor(@RequestBody Autor autor) {
        Autor autorSalvo = autorService.salvar(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(autorSalvo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id) {
        Autor autor = autorService.buscarPorId(id);
        if(autor == null) {
            return ResponseEntity.notFound().build();
        }
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Autor> editarAutor(@PathVariable Long id,
                                             @RequestBody Autor autor) {
        Autor autorAtual = autorService.buscarPorId(id);
        if(autorAtual == null) {
            return ResponseEntity.notFound().build();
        }

        autorAtual.setNome(autor.getNome());
        autorAtual.setNacionalidade(autor.getNacionalidade());
        autorAtual.setDataNascimento(autor.getDataNascimento());
        autorAtual.setEmail(autor.getEmail());

        return ResponseEntity.ok(autorService.salvar(autorAtual));
    }

    @GetMapping("/listar-com-livros")
    public ResponseEntity<List<AutorComLivrosDTO>> listarAutoresComLivros() {
        List<Autor> autores = autorService.listarTodosComLivros();
        if(autores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<AutorComLivrosDTO> autoresDTO = autores.stream()
                .map(AutorComLivrosDTO::fromAutor)
                .collect(Collectors.toList());

        return ResponseEntity.ok(autoresDTO);
    }
}
package challenge_forum_hub.api.controller;

import challenge_forum_hub.api.dto.TopicoRequest;
import challenge_forum_hub.api.dto.TopicoResponse;
import challenge_forum_hub.api.dto.TopicoUpdateRequest;
import challenge_forum_hub.api.model.EstadoTopico;
import challenge_forum_hub.api.model.Topico;
import challenge_forum_hub.api.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller REST responsável pelos endpoints de tópicos
@RestController
// Caminho base para os endpoints de tópico
@RequestMapping("/topicos")
public class TopicoController {

    // Repositório para operações no banco de dados de tópicos
    private final TopicoRepository repository;

    // Injeção de dependência via construtor
    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    // Criar tópico
    @PostMapping
    public ResponseEntity<TopicoResponse> criar(@RequestBody @Valid TopicoRequest request) {

        // Cria uma nova entidade Topico
        Topico topico = new Topico();

        // Preenche os dados vindos do request
        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());
        topico.setAutor(request.getAutor());
        topico.setCurso(request.getCurso());

        // Salva o tópico no banco
        repository.save(topico);

        // Retorna os dados do tópico criado
        return ResponseEntity.ok(new TopicoResponse(topico));
    }

    // Listar todos os tópicos (ordenados por id)
    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listar() {

        // Busca todos os tópicos ordenados por id crescente
        List<TopicoResponse> resposta = repository.findAll(Sort.by(Sort.Direction.ASC, "id"))
                // Converte cada entidade Topico em TopicoResponse (DTO de saída)
                .stream()
                .map(TopicoResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    // Listar tópicos ATIVOS (ordenados por id)
    @GetMapping("/ativos")
    public ResponseEntity<List<TopicoResponse>> listarAtivos() {

        // Busca apenas tópicos com estado ATIVO
        List<TopicoResponse> resposta = repository.findByEstado(EstadoTopico.ATIVO, Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(TopicoResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    // Listar tópicos INATIVOS (ordenados por id)
    @GetMapping("/inativos")
    public ResponseEntity<List<TopicoResponse>> listarInativos() {

        // Busca apenas tópicos com estado INATIVO
        List<TopicoResponse> resposta = repository.findByEstado(EstadoTopico.INATIVO, Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(TopicoResponse::new)
                .toList();

        return ResponseEntity.ok(resposta);
    }

    // Atualizar tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid TopicoUpdateRequest request
    ) {

        // Busca o tópico pelo id
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        // Atualiza os campos permitidos
        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());
        topico.setCurso(request.getCurso());

        // Retorna o tópico atualizado
        return ResponseEntity.ok(new TopicoResponse(topico));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        // Busca o tópico pelo id
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        // Remove fisicamente o registro do banco
        repository.delete(topico);

        return ResponseEntity.noContent().build();
    }


    // PATCH - Ativar tópico
    @PatchMapping("/{id}/ativar")
    @Transactional
    public ResponseEntity<Void> ativar(@PathVariable Long id) {

        // Busca o tópico pelo id
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        // Define o estado como ATIVO
        topico.setEstado(EstadoTopico.ATIVO);

        return ResponseEntity.noContent().build();
    }

    // PATCH - Inativar tópico
    @PatchMapping("/{id}/inativar")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id) {

        // Busca o tópico pelo id
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));

        // Define o estado como INATIVO
        topico.setEstado(EstadoTopico.INATIVO);

        return ResponseEntity.noContent().build();
    }
}

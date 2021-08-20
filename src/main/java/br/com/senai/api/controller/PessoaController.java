package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.assembler.UsuarioAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.api.model.input.UsuarioInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Usuario;
import br.com.senai.domain.repository.PessoaRepository;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;

    @GetMapping
    public List<PessoaDTO> listar(){
        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
    }


    @GetMapping("{pessoaId}")
    public ResponseEntity<PessoaDTO> buscar(@PathVariable Long pessoaId){
        return pessoaRepository.findById(pessoaId)
                .map(pessoa -> ResponseEntity.ok(pessoaAssembler.toModel(pessoa)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO){
        Pessoa newPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        newPessoa.getUsuario().setSenha(
                new BCryptPasswordEncoder()
                        .encode(pessoaInputDTO.getUsuario().getSenha()));

        Pessoa pessoa = pessoaService.cadastrar(newPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> editar(
            @Valid @PathVariable Long pessoaId,
            @RequestBody Pessoa pessoa
    ){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoa.setId(pessoaId);
        pessoa.getUsuario().setSenha(
                new BCryptPasswordEncoder()
                        .encode(pessoa.getUsuario().getSenha()));
        pessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@PathVariable Long pessoaId){
        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }
}

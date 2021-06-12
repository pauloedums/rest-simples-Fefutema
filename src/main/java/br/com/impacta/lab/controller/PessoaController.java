package br.com.impacta.lab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	//http://localhost:8080/pessoas
	public static List<Pessoa> bancoDeDados = new ArrayList<>();
	
	@PostMapping("")
	public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
		bancoDeDados.add(pessoa);
		
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Pessoa>> consultarPessoas() {
		return ResponseEntity.ok(bancoDeDados);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> consultarPessoaPorId(@PathVariable("id") int id) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Pessoa pessoa = bancoDeDados.get(contador);
			if (pessoa.getId() == id) {
				return ResponseEntity.ok(pessoa);
			}
		}
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> alterarStatusDeVida(@PathVariable("id") int id,
			@RequestBody Pessoa newPessoa) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Pessoa pessoa = bancoDeDados.get(contador);
			if (pessoa.getId() == id) {
				pessoa.setVivo(newPessoa.isVivo());
				
				return ResponseEntity.ok(pessoa);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> deletarPessoa(@PathVariable("id") int id) {
		
		for (int contador = 0; contador < bancoDeDados.size(); contador++) {
			Pessoa pessoa = bancoDeDados.get(contador);
			if (pessoa.getId() == id) {
				bancoDeDados.remove(contador);
				return ResponseEntity.ok(pessoa);
			}
		}
		return ResponseEntity.notFound().build();
	}
	
}

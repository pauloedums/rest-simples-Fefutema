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
@RequestMapping("/produtos")
public class ProdutoController {
  
  public static List<Produto> bancoDeDados = new ArrayList<>();

  @PostMapping
  public ResponseEntity<Produto> inserirProduto(@RequestBody Produto produto) {
    bancoDeDados.add(produto);
    return ResponseEntity.ok(produto);
  } 

  @GetMapping
  public ResponseEntity<List<Produto>> pegarTodosProdutos() {
    return ResponseEntity.ok(bancoDeDados);
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<Produto> alterarProduto(@PathVariable("codigo") int codigo,
                                    @RequestBody Produto novoProduto) {

    for(Produto produto : bancoDeDados) {
      if (codigo == produto.getCodigo()) {
        produto.setValor(novoProduto.getValor());
        return ResponseEntity.ok(produto);
      }
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<Produto> deletaProduto(@PathVariable("codigo") int codigo) {

    for(Produto produto : bancoDeDados) {
      if (codigo == produto.getCodigo()) {
        bancoDeDados.remove(produto);
        return ResponseEntity.ok(produto);
      }
    }

    return ResponseEntity.notFound().build();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Produto> pegarProdutoPeloCodigo(@PathVariable("codigo") int codigo) {

    for(Produto produto : bancoDeDados) {
      if (codigo == produto.getCodigo()) {
        return ResponseEntity.ok(produto);
      }
    }

    return ResponseEntity.notFound().build();
  }

}

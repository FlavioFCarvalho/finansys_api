package com.finansys;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.finansys.domain.Categoria;
import com.finansys.domain.Cidade;
import com.finansys.domain.Cliente;
import com.finansys.domain.Endereco;
import com.finansys.domain.Estado;
import com.finansys.domain.Produto;
import com.finansys.domain.enuns.TipoCliente;
import com.finansys.repositories.CategoriaRepository;
import com.finansys.repositories.CidadeRepository;
import com.finansys.repositories.ClienteRepository;
import com.finansys.repositories.EnderecoRepository;
import com.finansys.repositories.EstadoRepository;
import com.finansys.repositories.ProdutoRepository;

@SpringBootApplication
public class FinansysApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;


	public static void main(String[] args) {
		SpringApplication.run(FinansysApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática",null);
		Categoria cat2 = new Categoria(null,"Escritório",null);
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 100.00);
		Produto p3 = new Produto(null, "Mouse", 20.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null ,"São Paulo");
		Estado est2 = new Estado (null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "São Paulo", est1);
		Cidade cid2 = new Cidade(null, "Capinas", est1);
		Cidade cid3 = new Cidade(null, "Uberlândia", est2);
		
		Cliente cli1 = new Cliente(null, "flavio", "f@gmail.com", "9999999999", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("22222-22222","9999-8888"));
		
		Endereco e1 = new Endereco(null, "Rua A", "50", "", "Centro", "280100-100", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua B", "50", "", "Lapa", "280100-100", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1 ,e2));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}

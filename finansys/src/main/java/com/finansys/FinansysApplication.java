package com.finansys;

import java.text.SimpleDateFormat;
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
import com.finansys.domain.ItemPedido;
import com.finansys.domain.Pagamento;
import com.finansys.domain.PagamentoComBoleto;
import com.finansys.domain.PagamentoComCartao;
import com.finansys.domain.Pedido;
import com.finansys.domain.Produto;
import com.finansys.domain.enuns.EstadoPagamento;
import com.finansys.domain.enuns.TipoCliente;
import com.finansys.repositories.CategoriaRepository;
import com.finansys.repositories.CidadeRepository;
import com.finansys.repositories.ClienteRepository;
import com.finansys.repositories.EnderecoRepository;
import com.finansys.repositories.EstadoRepository;
import com.finansys.repositories.ItemPedidoRepository;
import com.finansys.repositories.PagamentoRepository;
import com.finansys.repositories.PedidoRepository;
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
    
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("20/09/2017 21:20"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/09/2017 21:20"), cli1, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("30/07/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		ItemPedido ip1 = new ItemPedido(p1, ped1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(p3, ped1 , 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(p2, ped2 , 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
	    ped2.getItens().addAll(Arrays.asList(ip3));
	    
	    p1.getItens().addAll(Arrays.asList(ip1));
	    p2.getItens().addAll(Arrays.asList(ip3));
	    p3.getItens().addAll(Arrays.asList(ip2));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}

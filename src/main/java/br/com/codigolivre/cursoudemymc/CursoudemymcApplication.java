package br.com.codigolivre.cursoudemymc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.codigolivre.cursoudemymc.domain.Categoria;
import br.com.codigolivre.cursoudemymc.domain.Cidade;
import br.com.codigolivre.cursoudemymc.domain.Cliente;
import br.com.codigolivre.cursoudemymc.domain.Endereco;
import br.com.codigolivre.cursoudemymc.domain.Estado;
import br.com.codigolivre.cursoudemymc.domain.Pagamento;
import br.com.codigolivre.cursoudemymc.domain.PagamentoComBoleto;
import br.com.codigolivre.cursoudemymc.domain.PagamentoComCartao;
import br.com.codigolivre.cursoudemymc.domain.Pedido;
import br.com.codigolivre.cursoudemymc.domain.Produto;
import br.com.codigolivre.cursoudemymc.domain.enums.EstadoPagamento;
import br.com.codigolivre.cursoudemymc.domain.enums.TipoCliente;
import br.com.codigolivre.cursoudemymc.repositories.CategoriaRepository;
import br.com.codigolivre.cursoudemymc.repositories.CidadeRepository;
import br.com.codigolivre.cursoudemymc.repositories.ClienteRepository;
import br.com.codigolivre.cursoudemymc.repositories.EnderecoRepository;
import br.com.codigolivre.cursoudemymc.repositories.EstadoRepository;
import br.com.codigolivre.cursoudemymc.repositories.PagamentoRepository;
import br.com.codigolivre.cursoudemymc.repositories.PedidoRepository;
import br.com.codigolivre.cursoudemymc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoudemymcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	EstadoRepository estadoRepository;
	@Autowired
	CidadeRepository cidadeRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursoudemymcApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p3));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27163323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "centro", "38777012", cli1, c2);

		cli1.getEndercos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));

		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

	}

}

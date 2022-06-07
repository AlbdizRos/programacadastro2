package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.models.ProdutosCadastroModel;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@Controller
public class ProdutosCadastroController {

	//método para mapear a rota da página de cadastro de tarefa
	@RequestMapping(value = "/produtos-cadastro")
	public ModelAndView cadastro() {
		
		//nome da página /WEB-INF/views/tarefas-cadastro.jsp
		ModelAndView modelAndView = new ModelAndView("produtos-cadastro");
		modelAndView.addObject("model", new ProdutosCadastroModel());
		
		return modelAndView;
	}
	
	//método para mapear a rota do formulário de cadastro
		@RequestMapping(value = "/cadastrar-produto", method = RequestMethod.POST)
		public ModelAndView cadastrarTarefa(ProdutosCadastroModel model, HttpServletRequest request) {
					
			ModelAndView modelAndView = new ModelAndView("produtos-cadastro");
			
			try {
				
				//capturar o usuário autenticado no sistema (sessão)
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
				
				//capturar os dados da produto
				Produto produto= new Produto();
				
				produto.setNomeProduto(model.getNomeProduto());
				produto.setQuantidadeProduto(model.getQuantidadeProduto());
				produto.setPrecoProduto(model.getPrecoProduto());
				produto.setDataValidade(DateHelper.formatToDate(model.getDataValidade()));
				produto.setDescricaoProduto(model.getDescricaoProduto());
				produto.setIdUsuario(usuario.getIdUsuario());
				
				ProdutoRepository produtoRepository = new ProdutoRepository();
				produtoRepository.create(produto);
				
				//gerar mensagem de sucesso
				modelAndView.addObject("mensagem_sucesso", "Parabéns! O produto '" + produto.getNomeProduto() + "' foi cadastrado com sucesso.");
			}
			catch(Exception e) {
				//gerar mensagem de erro
				modelAndView.addObject("mensagem_erro", e.getMessage());
			}
			
			modelAndView.addObject("model", new ProdutosCadastroModel());	
			return modelAndView;
		}
	
}

package br.com.cotiinformatica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.models.ProdutosConsultaModel;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@Controller
public class ProdutosConsultaController {

	@RequestMapping(value = "/produtos-consulta")
	public ModelAndView consulta() {

		//nome da página /WEB-INF/views/tarefas-consulta.jsp
		ModelAndView modelAndView = new ModelAndView("produtos-consulta");
		
		modelAndView.addObject("model", new ProdutosConsultaModel());
		return modelAndView;	
	}
	
	@RequestMapping(value = "/consultar-produtos", method = RequestMethod.POST)
	public ModelAndView consultarTarefas(ProdutosConsultaModel model, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("produtos-consulta");
		
			try {
			
			//capturar o usuário autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//executar a consulta de tarefas
			ProdutoRepository tarefaRepository = new ProdutoRepository();
			List<Produto> produtos = tarefaRepository.findAll(model.getNomeProduto(), usuario.getIdUsuario());
			
			//enviando a lista de tarefas para a página
			modelAndView.addObject("produtos", produtos);
			
			//enviando mensagens
			if(produtos.size() > 0)
				modelAndView.addObject("mensagem_sucesso", produtos.size() + " produto(s) obtido(s) para a consulta realizada.");
			else
				modelAndView.addObject("mensagem_alerta", "Nenhum resultado foi encontrado para a pesquisa realizada.");
		}
			catch(Exception e) {
				modelAndView.addObject("mensagem_erro", e.getMessage());
			}
		
		modelAndView.addObject("model", new ProdutosConsultaModel());		
		return modelAndView;
	}
	
	@RequestMapping(value = "/excluirproduto")
	public ModelAndView excluirProduto(Integer idProduto, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("produtos-consulta");
		
			try {
			
			//capturar o usuário autenticado
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			Produto produto = new Produto();
			produto.setIdProduto(idProduto);
			produto.setIdUsuario(usuario.getIdUsuario());
			
			//excluindo a tarefa do usuário
			ProdutoRepository produtoRepository = new ProdutoRepository();
			produtoRepository.delete(produto);
			
			modelAndView.addObject("mensagem_sucesso", "Produto excluído com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("model", new ProdutosConsultaModel());		
		return modelAndView;
	}	
}


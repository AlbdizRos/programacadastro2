package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.models.ProdutosEdicaoModel;
import br.com.cotiinformatica.repositories.ProdutoRepository;

@Controller
public class ProdutosEdicaoController {

	@RequestMapping(value = "/produtos-edicao")
	public ModelAndView edicao(Integer id, HttpServletRequest request) {
		
		ProdutosEdicaoModel model = new ProdutosEdicaoModel();

		//nome da página /WEB-INF/views/tarefas-edicao.jsp
		ModelAndView modelAndView = new ModelAndView("produtos-edicao");
		
try {
			
			//capturando o usuário autenticado na aplicação
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
			
			//consultando os dados do produto no banco através do id
			ProdutoRepository produtoRepository = new ProdutoRepository();
			Produto produto= produtoRepository.findById(id);
			
			//verificando se a tarefa obtida pertence ao usuário
			if(produto.getIdUsuario() == usuario.getIdUsuario()) {
				
				model.setIdProduto(produto.getIdProduto());
				model.setNomeProduto(produto.getNomeProduto());
				model.setQuantidadeProduto(produto.getQuantidadeProduto());
				model.setPrecoProduto(produto.getPrecoProduto());
				model.setDataValidade(DateHelper.formatToString(produto.getDataValidade()));
				model.setDescricaoProduto(produto.getDescricaoProduto());
				
			}
			else {
				modelAndView = new ModelAndView("redirect:/produtos-consulta");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
			modelAndView.addObject("model", model);		
			return modelAndView;
	}
	
	@RequestMapping(value = "/atualizar-produto", method = RequestMethod.POST)
	public ModelAndView atualizarProduto(ProdutosEdicaoModel model, HttpServletRequest request) {
		
		//nome da página /WEB-INF/views/produtos-edicao.jsp
		ModelAndView modelAndView = new ModelAndView("produtos-edicao");
		
		//capturando o usuário autenticado na aplicação
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_auth");
		
try {
			
			//capturar os dados da produto
			Produto produto= new Produto();
			
			produto.setIdProduto(model.getIdProduto());
			produto.setNomeProduto(model.getNomeProduto());
			produto.setQuantidadeProduto(model.getQuantidadeProduto());
			produto.setPrecoProduto(model.getPrecoProduto());
			produto.setDataValidade(DateHelper.formatToDate(model.getDataValidade()));
			produto.setDescricaoProduto(model.getDescricaoProduto());	
			produto.setIdUsuario(usuario.getIdUsuario());
			
			//atualizando no banco de dados
			ProdutoRepository tarefaRepository = new ProdutoRepository();
			tarefaRepository.update(produto);
			
			modelAndView.addObject("mensagem_sucesso", "Produto atualizado com sucesso.");			
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("model", model);		
		return modelAndView;
	}

}

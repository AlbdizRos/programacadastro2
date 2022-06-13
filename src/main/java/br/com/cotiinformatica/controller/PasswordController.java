package br.com.cotiinformatica.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.messages.MailMessage;
import br.com.cotiinformatica.models.PasswordModel;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class PasswordController {

	@RequestMapping(value = "/password")
	public ModelAndView password() {

		ModelAndView modelAndView = new ModelAndView("password"); // WEB-INF/views/password.jsp
		modelAndView.addObject("model", new PasswordModel());
		return modelAndView;
	}
	
	@RequestMapping(value = "/recuperar-senha", method = RequestMethod.POST)
	public ModelAndView recuperarSenha(PasswordModel model) {
		
		ModelAndView modelAndView = new ModelAndView("password");
		
		try {
				
			//pesquisar o usu�rio atrav�s do e-mail informado
			UsuarioRepository usuarioRepository = new UsuarioRepository();
			Usuario usuario = usuarioRepository.find(model.getEmail());
			
			//verificar se o usu�rio foi encontrado
			if(usuario != null) {
				
				//criando uma nova senha para o usu�rio 
				String novaSenha = getNewPassword();
				
				//atualizar a senha do usu�rio no banco de dados
				usuarioRepository.update(usuario.getIdUsuario(), novaSenha);
				
				//enviando  a mensagem para o usu�rio
				String  to =  usuario.getEmail();
				String subject = "Recupera��o de Senha - Programa Cadastro";
				String body = "Ol�" + usuario.getNome() +"\n"
						+ "Sua nova senha de acesso �: " + novaSenha
						+ "Utilize esta senha para acessar o sistema e, depois, caso deseje, altere para uma"
						+ "senha de sua prefer�ncia atrav�s do menu 'Minha Conta'"
						+ "\n"
						+ "Atenciosamente, "
						+ "Equipe Programa Cadastro";
				
				//enviando a mensagem
				MailMessage.sendMessage(to, subject, body);
				
				modelAndView.addObject("mensagem_sucesso", "Uma nova senha foi enviada com sucesso para o e-mail " + usuario.getEmail());
				
			}
			else {
				throw new Exception("E-mail inv�lido. Usu�rio n�o encontrado.");
			}
		}
		catch(Exception e){
			modelAndView.addObject("mensagem_erro", e.getMessage());
			
		}
		modelAndView.addObject("model", model);
		
		return modelAndView;
	}
	
	//m�todo para gerar uma nova senha para o usu�rio
	public String getNewPassword() {
		return  String.valueOf(new Random().nextInt(88888888));
		
	}

}

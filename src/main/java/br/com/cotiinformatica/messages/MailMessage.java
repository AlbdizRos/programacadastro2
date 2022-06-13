package br.com.cotiinformatica.messages;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailMessage {
	
	//atributos para envio de e-mail
	private static final String CONTA = "albertitonoreply@gmail.com";
	private static final String SENHA = "Qwerty1379!";
	private static final String SMTP = "smtp.gmail.com";
	private static final Integer PORTA = 587;
	
	//método para fazer o envio das mensagens
	public static void sendMessage(final String to, final String subject, final String body) throws Exception{
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		
		mailSenderImpl.setHost(SMTP);
		mailSenderImpl.setPort(PORTA);
		mailSenderImpl.setUsername(CONTA);
		mailSenderImpl.setPassword(SENHA);
		
		Properties properties = new Properties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.transport.protocol", "smtp");
		properties.put("mail.debug", "true");
		
		mailSenderImpl.setJavaMailProperties(properties);
		
		//enviando o e-mail
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(CONTA);
				message.setSubject(subject);
				message.setText(body);
				
			}
			
		};
		
		//enviando o e-mail
		mailSenderImpl.send(preparator);
	}

}

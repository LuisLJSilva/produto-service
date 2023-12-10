package br.com.infnet.produtoservice;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.Random;

@SpringBootApplication
public class ProdutoServiceApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ProdutoServiceApplication.class);
		Properties properties = new Properties();
//		int serverPort = new Random().nextInt(8010,8020);
		String appNickName = MobyNamesGenerator.getRandomName();
		properties.put("spring.application.serverNick", appNickName);
//		properties.put("server.port", serverPort);
		application.setDefaultProperties(properties);
		application.run(args);
	}

}

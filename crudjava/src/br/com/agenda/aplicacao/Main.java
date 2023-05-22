package br.com.agenda.aplicacao;

import java.sql.Date;

import bc.com.agenda.model.Contato;
import br.com.agenda.dao.ContatoDAO;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Luana Kelly");
		contato.setIdade(12);
		contato.setDataCadastro(new Date(0));
		
		//contatoDao.save(contato);
		
		//Atualizar o contato 
		Contato c1 = new Contato();
		c1.setNome("Luana Kelly Carneiro");
		c1.setIdade(13);
		c1.setDataCadastro(new Date(0));
		
		contatoDao.update(c1);
		
		//Visualização dos registros do banco
		
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Nome: "+c.getNome());
			System.out.println("Idade: "+c.getIdade());
			System.out.println("Data de Cadastro: "+c.getDataCadastro());
		}
		
	}

}

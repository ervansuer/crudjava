package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import bc.com.agenda.model.Contato;
import br.com.agenda.factory.ConnectionFactory;

public class ContatoDAO {
	/*
	 * CRUD 
	 * c: Create - OK
	 * r: Read - OK
	 * u:Update
	 * d: Delete
	 */
	
	public void save(Contato contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//Executar a query
			pstm.execute();
			System.out.println("Dado inserido com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			//fechar conexões
			try {
				if(pstm!=null) {
					pstm.close();	
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update(Contato contato) {
		
		String sql = "UPADATE contatos SET nome = ?, idade = ?, dataCadastro = ?"+
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//Qual o ID do registro que deseja atualizar?
			pstm.setInt(4, contato.getId());
			
			//Executar a query
			pstm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
					if(conn!=null) {
					conn.close();
					}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

	public List<Contato> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que recupera os dados do banco
		ResultSet rset = null;
	
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Contato contato = new Contato();
				
				//recuperar o id
				contato.setId(rset.getInt("id"));
				//recuperar o nome
				contato.setNome(rset.getString("nome"));
				//recuperar a idade 
				contato.setIdade(rset.getInt("Idade"));
				//recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("dataCadastro"));
			
				contatos.add(contato);
			}	
			
		}catch (Exception e) {
				e.printStackTrace();
				}finally {
						try {
							if(rset!=null) {
								rset.close();
							}
								if(pstm!=null) {
								pstm.close();
								}
									if(conn!=null) {
											conn.close();
									}
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				return contatos;
			}
}
		





package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserCursoJava;

public class UserCursoJavaDao {

	private Connection connection;
	
	public UserCursoJavaDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void inserir(UserCursoJava userCursoJava) {
		try {
			String sql = "insert into usercursojava (nome, email) values (?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userCursoJava.getNome());
			insert.setString(2, userCursoJava.getEmail());
			insert.execute();
			connection.commit(); // salva no banco de dados
		} catch (Exception e) {
			try {
				connection.rollback(); // reverte a operação no banco de dados
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<UserCursoJava> listar() throws Exception {
		List<UserCursoJava> lista = new ArrayList<UserCursoJava>();
		
		String sql = "select * from usercursojava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			UserCursoJava userJava = new UserCursoJava();
			userJava.setId(resultado.getLong("id"));
			userJava.setNome(resultado.getString("nome"));
			userJava.setEmail(resultado.getString("email"));
			
			lista.add(userJava);
		}
		return lista;
	}
	
	public UserCursoJava buscar(Long id) throws Exception {
		UserCursoJava userJava = new UserCursoJava();
		String sql = "select * from usercursojava where id = " + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) { // retorna somente um ID ou nenhum
			userJava.setId(resultado.getLong("id"));
			userJava.setNome(resultado.getString("nome"));
			userJava.setEmail(resultado.getString("email"));
		}
		return userJava;
	}
	
	public void atualizar(UserCursoJava userJava) {
		try {
			String sql = "update usercursojava set nome = ? where id = " + userJava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userJava.getNome());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from usercursojava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void inserirTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values(?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<BeanUserFone> listarUserFone(Long id) {
		List<BeanUserFone> userFones = new ArrayList<BeanUserFone>();
		
		try {
			String sql = " select nome, numero, email from telefoneuser as fone ";
			sql += " inner join usercursojava as cursojava ";
			sql += " on fone.usuariopessoa = cursojava.id ";
			sql += " where cursojava.id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanUserFone beanUserFone = new BeanUserFone();
				beanUserFone.setNome(resultSet.getString("nome"));
				beanUserFone.setNumero(resultSet.getString("numero"));
				beanUserFone.setEmail(resultSet.getString("email"));
				userFones.add(beanUserFone);
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return userFones;
	}
	
	public void deletarUserFone(Long id) {
		try {
			String sqlFone = "delete from telefoneuser where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();
			connection.commit();
			
			String sqlUser = "delete from usercursojava where id = " + id;
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}

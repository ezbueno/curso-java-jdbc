package testejdbc;

import java.util.List;

import org.junit.Test;
import dao.UserCursoJavaDao;
import model.BeanUserFone;
import model.Telefone;
import model.UserCursoJava;

public class TesteBancoJDBC {
	
	@Test
	public void initInserir() {
		UserCursoJavaDao dao = new UserCursoJavaDao();
		UserCursoJava userJava = new UserCursoJava();
		
		userJava.setNome("Aline Spadotto");
		userJava.setEmail("teste@gmail.com");
		
		dao.inserir(userJava);
	}
	
	@Test
	public void initListar() {
		UserCursoJavaDao dao = new UserCursoJavaDao();
		try {
			List<UserCursoJava> lista = dao.listar();
					
			for (UserCursoJava userCursoJava : lista) {
				System.out.println(userCursoJava);
				System.out.println("-----------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		UserCursoJavaDao dao = new UserCursoJavaDao();		
		try {
			UserCursoJava userJava = dao.buscar(2L);
			System.out.println(userJava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		UserCursoJavaDao dao = new UserCursoJavaDao();
		
		try {
			UserCursoJava userJava = dao.buscar(2L);
			userJava.setNome("Nayara Balarotti");
			dao.atualizar(userJava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletar() {
		try {
			UserCursoJavaDao dao = new UserCursoJavaDao();
			dao.deletar(3L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initInserirTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("(11) 99999-6666");
		telefone.setTipo("celular");
		telefone.setUsuario(6L);
		
		UserCursoJavaDao dao = new UserCursoJavaDao();
		dao.inserirTelefone(telefone);
	}
	
	@Test
	public void initListarUserFone() {
		UserCursoJavaDao dao = new UserCursoJavaDao();
		List<BeanUserFone> userFones = dao.listarUserFone(2L);
		
		for (BeanUserFone beanUserFone : userFones) {
			System.out.println(beanUserFone);
			System.out.println("----------");
		}
	}
	
	@Test
	public void initDeletarUserFone() {
		UserCursoJavaDao dao = new UserCursoJavaDao();
		dao.deletarUserFone(6L);
	}
}

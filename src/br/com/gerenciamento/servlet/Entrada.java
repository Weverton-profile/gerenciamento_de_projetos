package br.com.gerenciamento.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gerenciamento.actions.Acao;

@WebServlet("/entrada")
public class Entrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String paramAcao = req.getParameter("action");
		
		HttpSession sessao = req.getSession();
		boolean usuarioNaoEstaLogado = (sessao.getAttribute("usuarioLogado") == null);
		boolean ehUmaAcaoProtegida = !(paramAcao.equals("Logar") || paramAcao.equals("CadastrarNovoUsuario") || paramAcao.equals("Login") || paramAcao.equals("Inscricao"));
		
		if(ehUmaAcaoProtegida && usuarioNaoEstaLogado) {
			resp.sendRedirect("entrada?action=Logar");
			return;
		}
		
		String nomeDaClasse = "br.com.gerenciamento.actions." + paramAcao; 
		
		String nome = null;
		try {
			@SuppressWarnings("rawtypes")
			Class classe = Class.forName(nomeDaClasse);
			@SuppressWarnings("unchecked")
			Object acao = classe.getDeclaredConstructor().newInstance();
			nome = ((Acao) acao).executa(req, resp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException | SQLException
				| ServletException | IOException e) {
			e.printStackTrace();
		}
		

		String[] tipoEndereco = nome.split(":");
		if(tipoEndereco[0].equals("forward")) {
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/" + tipoEndereco[1]);
			rd.forward(req, resp);
		} else {
			resp.sendRedirect(tipoEndereco[1]);
		}
	}

}

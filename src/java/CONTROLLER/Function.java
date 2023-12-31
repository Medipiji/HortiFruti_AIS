/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

import MODEL.Usuario;
import MODEL.Produto;
import MODEL.Carrinho;

import DAL.UsuarioDAL;
import DAL.PedidoDAL;
import DAL.ProdutosDAL;
import DAL.CarrinhoDAL;



/**
 *
 * @author jr_ma
 */


@WebServlet(urlPatterns = {"/Function", "/logar", "/cadastrar", "/pedido", "/produtoLista", "/editarProduto", "/excluirProduto", "/produtoCadastro", "/carrinhoLoad", "/carrinhoInsert", "/deleteProduto", "/finalizarCompra", "/finalizarSessao"})
public class Function extends HttpServlet {
    
    //FUNÇÃO DE TESTE DO SERVLET
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {   
        if(request.getServletPath().equals("/logar")){
            try {
                Usuario obj = getLogin(request.getParameter("email_input"), request.getParameter("senha_input"));
                HttpSession session = request.getSession(); // Obtém a sessão           
                if (obj.Nome() != null || obj.Email() != null || obj.Senha() != null) {
                    session.setAttribute("usuario", obj);
                    response.sendRedirect("home.jsp");
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login falhou");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(request.getServletPath().equals("/pedido")){
            try {
                boolean resultado = gerarPedido();
                if(resultado){
                    String pedido = pegarPedido();
                    HttpSession session = request.getSession(); // Obtém a sessão 
                    if(!pedido.equals("")){
                        session.setAttribute("pedido",pedido);
                        session.setAttribute("pedidoRealizado",true);
                    } else {
                        session.setAttribute("pedido","Ocorreu um erro, tente novamente.");
                        session.setAttribute("pedidoRealizado",false);
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getServletPath().equals("/produtoLista")){
            try{
                 List<Produto> objLista = getProdutos();
                 if(!objLista.isEmpty()){
                     HttpSession session = request.getSession();
                     session.setAttribute("produtos", objLista);
                     response.setStatus(HttpServletResponse.SC_OK);
                 } else {
                     response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                 }
            } catch (SQLException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(request.getServletPath().equals("/carrinhoLoad")){
            try{
                
                int pedido = Integer.parseInt(request.getParameter("carrinho_id"));
                
                List<Carrinho> objLista = getCarrinho(pedido);
                if(!objLista.isEmpty()){
                     HttpSession session = request.getSession();
                     session.setAttribute("carrinho", objLista);
                     response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getServletPath().equals("/deleteProduto")){
            try{
                String idCarrinho = request.getParameter("idCarrinho");
                 if(deleteProduto(idCarrinho)){
                     response.setStatus(HttpServletResponse.SC_OK);
                 } else {
                     response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                 }
            } catch (SQLException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        
        
        if(request.getServletPath().equals("/finalizarCompra")){
            try{
                String idPedido = request.getParameter("idPedido");
                 if(finalizarPedido(idPedido)){
                     response.setStatus(HttpServletResponse.SC_OK);
                 } else {
                     response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                 }
            } catch (SQLException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        
        if(request.getServletPath().equals("/finalizarSessao")){
            try{
                HttpSession session = request.getSession();
                session.invalidate();
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (Exception ex){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } 
        
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        if(request.getServletPath().equals("/cadastrar")){       
            try{
                String nome = request.getParameter("nome_registro");
                String email = request.getParameter("email_registro");
                String senha = request.getParameter("senha_registro");
                String confirm_senha = request.getParameter("confirm_registro");
                
                if(senha.equals(confirm_senha)){
                    boolean operacao = postCadastro(nome, email, senha, 0);
                    HttpSession session = request.getSession();
                    if(operacao){   
                        session.setAttribute("popupOK", true);
                        response.setStatus(HttpServletResponse.SC_CREATED);
                    } else {
                        session.setAttribute("popupERROR", true);
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if(request.getServletPath().equals("/produtoCadastro")){       
            try{
                String nome = request.getParameter("cadastro-produto-nome");
                String categoria = request.getParameter("cadastro-produto-categoria");
                String valor = request.getParameter("cadastro-produto-valor");
                int quantidade = Integer.parseInt(request.getParameter("cadastro-produto-quantidade"));
                
                if(!nome.equals("") && !categoria.equals("") && !valor.equals("") && quantidade >= 0){
                    boolean operacao = postEnviarProdutos(nome, categoria, valor, quantidade);
                    if(operacao){
                        response.setStatus(HttpServletResponse.SC_CREATED);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getServletPath().equals("/carrinhoInsert")){       
            try{
                String idCarrinho = request.getParameter("idCarrinho");
                String idProduto = request.getParameter("idProduto");
                String idNmProduto = request.getParameter("idNmProduto");
                String idEstoque = request.getParameter("idEstoque");
                String idValor = decimalCorrect(request.getParameter("idValor"));
                int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                
                if(!idCarrinho.equals("") && !idProduto.equals("") && !idNmProduto.equals("") && quantidade >= 0){
                    boolean operacao = postInsertCarrinho(idCarrinho, idProduto, idNmProduto, idEstoque, idValor, quantidade);
                    if(operacao){
                        response.setStatus(HttpServletResponse.SC_OK);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
        if(request.getServletPath().equals("/excluirProduto")){       
            try{
                String idProduto = request.getParameter("idProduto");
                if(!idProduto.equals("")){
                    boolean operacao = excluirProduto(idProduto);
                    if(operacao){   
                        response.setStatus(HttpServletResponse.SC_OK);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(request.getServletPath().equals("/editarProduto")){       
            try{
                String idProduto = request.getParameter("idProdutoEdit");
                String idNmProduto = request.getParameter("nmProdutoEdit");
                String idCategoria = request.getParameter("categoriaEdit");
                String idEstoque = request.getParameter("estoqueEdit");
                String idValor = decimalCorrect(request.getParameter("valorEdit"));
                
                if(!idCategoria.equals("") && !idProduto.equals("") && !idNmProduto.equals("")){
                    boolean operacao = postEditarProduto(idProduto, idNmProduto, idCategoria, idEstoque, idValor);
                    if(operacao){
                        response.setStatus(HttpServletResponse.SC_OK);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } catch (SQLException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex){
                Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
    }
    
    protected Usuario getLogin(String email, String senha) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Usuario obj = new Usuario();
        UsuarioDAL objDAL = new UsuarioDAL();
        obj = objDAL.login(email, senha);   
        return obj;
    } 
    
    protected boolean postCadastro(String nome, String email, String senha, int admin) throws SQLException, ClassNotFoundException {
        UsuarioDAL objDAL = new UsuarioDAL();
        return objDAL.cadastro(nome, email, senha, admin);
    }
    
    protected boolean gerarPedido() throws SQLException, ClassNotFoundException {
        PedidoDAL objDAL = new PedidoDAL();
        return objDAL.gerarCadastro();
    }
    
    protected String pegarPedido() throws SQLException, ClassNotFoundException {
        PedidoDAL objDAL = new PedidoDAL();
        return objDAL.pegarCadastro();
    }
    
    protected List<Produto> getProdutos()  throws SQLException, ClassNotFoundException {
        ProdutosDAL objDAL = new ProdutosDAL();
        List<Produto> listaObj = objDAL.getProdutos();
        return listaObj ;
    }
    
    protected boolean postEnviarProdutos(String nome, String categoria, String preco, int quantidade) throws SQLException, ClassNotFoundException {
        ProdutosDAL objDAL = new ProdutosDAL();
        boolean resultado = objDAL.setProdutos(nome, categoria, preco, quantidade);
        return resultado;
    }
    
    protected List<Carrinho> getCarrinho(int id)  throws SQLException, ClassNotFoundException {
        CarrinhoDAL objDAL = new CarrinhoDAL();
        List<Carrinho> listaObj = objDAL.getCarrinho(id);
        return listaObj;
    }
    
    protected boolean postInsertCarrinho(String idCarrinho, String idProduto, String idNmProduto, String idEstoque, String idValor, int quantidade)  throws SQLException, ClassNotFoundException{
        CarrinhoDAL objDAL = new CarrinhoDAL();
        return objDAL.insertCarrinho(idCarrinho, idProduto, idNmProduto,idEstoque, idValor, quantidade);
    }
    
    protected boolean postEditarProduto(String idProduto, String idNmProduto,String idCategoria,String idEstoque, String idValor) throws SQLException, ClassNotFoundException{
        ProdutosDAL objDAL = new ProdutosDAL();
        return objDAL.editarProduto(idProduto, idNmProduto, idCategoria, idEstoque, idValor);
    }
    
    protected boolean deleteProduto(String idCarrinho)  throws SQLException, ClassNotFoundException {
        CarrinhoDAL objDAL = new CarrinhoDAL();
        return objDAL.excluirProduto(idCarrinho);
    }
    
    protected boolean finalizarPedido(String idPedido)  throws SQLException, ClassNotFoundException {
        CarrinhoDAL objDAL = new CarrinhoDAL();
        return objDAL.finalizarPedido(idPedido);
    }
    
    protected boolean excluirProduto(String idProduto)  throws SQLException, ClassNotFoundException {
        ProdutosDAL objDAL = new ProdutosDAL();
        return objDAL.excluirProduto(idProduto);
    }
    
    private String decimalCorrect(String num){
        return num.replace(",", ".");
    }
    
}

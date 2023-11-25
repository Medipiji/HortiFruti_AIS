/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package CONTROLLER;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CONTROLLER.Redirect;

@WebServlet(urlPatterns = {"" ,"/Controller", "/login", "/cadastro", "/home", "/carrinho", "/produtos", "/inserirProdutos"})
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Login
        if( request.getServletPath()!= null){
            if( request.getServletPath().equals("") || request.getServletPath().equals("/login")){
            Redirect.gotoLogin(request, response);
            }
        }
        
        //Cadastro
        if( request.getServletPath()!= null && request.getServletPath().equals("/cadastro")){
            Redirect.gotoCadastro(request, response);
        }
        
        //Home
        if( request.getServletPath()!= null && request.getServletPath().equals("/home")){
             Redirect.gotoHome(request, response);
        }
        
        //Carrinho
        if( request.getServletPath()!= null && request.getServletPath().equals("/carrinho")){
             Redirect.gotoCarrinho(request, response);
        }
        
        //Produtos
        if( request.getServletPath()!= null && request.getServletPath().equals("/produtos")){
             Redirect.gotoProdutos(request, response);
        }
        
        //Inserir Produtos
        if( request.getServletPath()!= null && request.getServletPath().equals("/inserirProdutos")){
             Redirect.gotoInserirProdutos(request, response);
        }
        
    }
    
    
}

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

import MODEL.Usuario;
/**
 *
 * @author jr_ma
 */


@WebServlet("/redirect")
public class Redirect extends HttpServlet {
    
    //FUNÇÃO DE TESTE DO SERVLET
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            response.getWriter().write("Teste de servlet");
    }
    
    public static void gotoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    public static void gotoCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("cadastro.jsp");
    }
    
    public static void gotoHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("home.jsp");
    }
    
    public static void gotoCarrinho(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("carrinho.jsp");
    }
    
    public static void gotoProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("produtos.jsp");
    }
    
    public static void gotoInserirProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("inserir_produtos.jsp");
    }
    
    
}


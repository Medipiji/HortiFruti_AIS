<%-- 
    Document   : home
    Created on : 07/10/2023, 02:42:16
    Author     : jr_ma
--%>

<%@page import="MODEL.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>

<%
    String pedido = "";
    boolean pedidoRealizado = false;
            
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    
    
    if(session.getAttribute("pedido") != null)
    {
        pedido = session.getAttribute("pedido").toString();
    }
    if(session.getAttribute("pedidoRealizado") != null){
        pedidoRealizado = (boolean) session.getAttribute("pedidoRealizado");
    }
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horti-Fruit</title>
        <link rel="icon" type="image/png" href="./ASSETS/IMAGES/3082061.png" >
        <link rel="stylesheet" type="text/css" href="./STYLE/styleHome.css" >
        <script defer src="./SCRIPT/script.js"></script> 
    </head>
    <body>
        <div class="container">
            <div class="bg-black"></div>    
            <header>
                <div class="header-div">
                    <h3><%= usuario.Nome() %></h3>
                    <h3> 
                        <%
                            if(pedidoRealizado){ out.println("Pedido - "+pedido); }
                        %>
                    </h3>
                </div>
                <div class="header-div">
                    <h1>Hortifruit</h1>
                </div>
                <div class="header-div">
                    <ul class="menu">
                        <li> 
                            <a href="#">Menu</a>
                            <ul class="submenu">
                                <li><a href="home">Pedido</a></li>
                                <li><a href="produtos">Produtos</a></li>
                                <li><a href="carrinho">Carrinho</a></li>
                                <%if(usuario.Admin()){ out.println("<li><a href='inserirProdutos'>Inserir Produtos</a></li>");}%>
                            </ul>
                        </li>
                    </ul>
                </div>
            </header>

                            
                            
            <div class="card-holder">
                <div class="card-header">
                    <p> .:Pedido:. </p>
                </div>
                <div class="input-pack">
                    <%    
                   if(!pedidoRealizado){
                        out.print("<p>Por gentileza, gerar seu pedido.</p>");
                        out.println(pedido);
                        out.println("<button onclick='gerarPedido()'>Gerar</button>");
                   } else {
                        out.println("Pedido já gerado!");
                   }
                    %>
                    <br>
                    <p>É necessário gerar um pedido para que você possa adicionar items ao seu "carrinho".</p>
                    <p>Após gerar, você encontrará o número do seu pedido na parte superior esquerda.</p>
                </div>
            </div>
            
            <footer>
                <p>Powered by Healtec &trade;</p>
            </footer>
        </div>
    </body>
</html>

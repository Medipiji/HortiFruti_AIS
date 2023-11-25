
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Horti-Fruit</title>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="./ASSETS/IMAGES/3082061.png" >
        <link rel="stylesheet" type="text/css" href="./STYLE/style.css" >
        <script defer src="./SCRIPT/script.js" ></script> 
    </head>
    <body>
        <div class="container">
            <div class="bg-black"></div>
            <div class="card">
                <div class="input-pack">
                    <img class="icon-login" src="./ASSETS/IMAGES/3082061.png" alt="alt"/>
                    <h2>Horti-Fruit</h2> 
                </div>
                <hr>
                <form id="form-registro" method="POST">
                    <div class="input-pack">
                        <label>Nome</label>
                        <input type="text" name="nome_registro" required="true">
                        <label>E-mail</label>
                        <input type="text" name="email_registro" required="true">
                        <label>Senha</label>
                        <input type="password" name="senha_registro" required="true">
                        <label>Confirmar Senha</label>
                        <input type="password" name="confirm_registro" required="true">
                    </div>
                    <div class="input-pack">
                        <button onclick="enviarCadastro()">CADASTRAR</button>
                        <p>Ja possui uma conta? <a href="login">Logar</a></p>
                    </div>
                </form>
            </div>
            
            
            <div id="popupOk">
                <div class="bg-black"></div>
                <div class="cardOk">
                    <p>Sucesso em criar conta</p>
                    <button onclick="showPopupOk()">OK</button>
                </div>
            </div>
            <div id="popupError">
                <div class="bg-black"></div>
                <div class="cardError">
                    <p>Aconteceu um erro no procedimento</p>
                    <button onclick="showPopupError()">OK</button>
                </div>
            </div>
        </div>
    </body>
</html>

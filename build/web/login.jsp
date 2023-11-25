<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Horti-Fruit</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" type="image/png" href="./ASSETS/IMAGES/3082061.png" >
        <link rel="stylesheet" type="text/css" href="./STYLE/style.css" >
        <script defer src="./SCRIPT/script.js"></script> 
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
                <form id="frm_login" >
                    <div class="input-pack">
                        <label>E-mail</label>
                        <input type="text" name="email_input">
                    </div>
                    <div class="input-pack">
                        <label>Senha</label>
                        <input type="password" name="senha_input">
                    </div>
                
                    <div class="input-pack">
                        <button type="submit" onclick="enviarLogin()">LOGAR</button>
                        <p>NÃo tem uma conta? <a href="cadastro">Cadastre-se</a></p>
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

<%@page import="model.Emp" %>

<%
Emp valeur= (Emp)request.getAttribute("emp");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% 
    out.println(valeur.getId());
    out.println(valeur.getNom());
    out.println(valeur.getPrenom());
    out.println(valeur.getDatenaissance());
    %>
        

   
    
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="/framework/emp-save.go" method="post" enctype="multipart/form-data">
        <p>Nom:<input type="text" name="nom"></p>
        <p>Prenom:<input type="text" name="prenom"></p>
        <p>Date de Naissance:<input type="date" name="datenaissance"></p>
        <p>CV: <input type="file" name="cv"></p>
        <p><input type="submit" value="Valider"></p>
    </form>
</body>
</html>
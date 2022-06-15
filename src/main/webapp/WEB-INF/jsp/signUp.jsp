<!DOCTYPE html>
<html lang="en">
<head>
    <link href="/css/register.css" rel="stylesheet">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registeration</title>
</head>
<body>
    <div class="container">
        <h1>Register</h1>
    <form action="/register" method="POST">
        <input type="text" name = "name"  placeholder="Name" class="field">
        <input type="text" name = "email" placeholder="Email" class="field">
        <input type="text" name = "address" placeholder="Address" class="field">
        <input type="text" name = "contact" placeholder="Contact" class="field">
        <input type="password" name = "password" placeholder="password" class="field">
        <input type="submit" value="Register" class="btn">
    </form>
    <div class="pass-link">
        <a href="/login" >Already have an Account?</a>
    </div>	
</div>
</body>
</html>
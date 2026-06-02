<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Distraction Tracker Login</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Segoe UI',sans-serif;
}

body{
    height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
    background:linear-gradient(-45deg,#1e3c72,#2a5298,#3498db,#6dd5fa);
    background-size:400% 400%;
    animation:gradientBG 10s ease infinite;
}

@keyframes gradientBG{
    0%{background-position:0% 50%;}
    50%{background-position:100% 50%;}
    100%{background-position:0% 50%;}
}

.login-container{
    width:420px;
    background:rgba(255,255,255,0.95);
    padding:40px;
    border-radius:20px;
    box-shadow:0 15px 40px rgba(0,0,0,0.3);
    animation:slideUp 1s ease;
}

@keyframes slideUp{
    from{
        opacity:0;
        transform:translateY(60px);
    }
    to{
        opacity:1;
        transform:translateY(0);
    }
}

.logo{
    text-align:center;
    margin-bottom:25px;
}

.logo h1{
    color:#2c3e50;
    margin-bottom:8px;
}

.logo p{
    color:#777;
    font-size:14px;
}

.input-group{
    margin-bottom:20px;
}

.input-group label{
    display:block;
    margin-bottom:8px;
    font-weight:600;
    color:#333;
}

.input-group input{
    width:100%;
    padding:14px;
    border:1px solid #ddd;
    border-radius:10px;
    font-size:15px;
    transition:0.3s;
}

.input-group input:focus{
    border-color:#3498db;
    outline:none;
    box-shadow:0 0 10px rgba(52,152,219,.4);
}

.show-password{
    margin-bottom:20px;
}

.login-btn{
    width:100%;
    padding:14px;
    border:none;
    border-radius:10px;
    background:#3498db;
    color:white;
    font-size:18px;
    cursor:pointer;
    transition:0.3s;
}

.login-btn:hover{
    background:#2980b9;
    transform:translateY(-2px);
}

.error{
    color:red;
    text-align:center;
    margin-bottom:15px;
}

.footer{
    text-align:center;
    margin-top:20px;
}

.footer a{
    text-decoration:none;
    color:#3498db;
    font-weight:bold;
}

</style>

<script>

function validateForm(){

    let email=document.getElementById("email").value;
    let password=document.getElementById("password").value;

    if(email.trim()=="" || password.trim()==""){
        alert("Please fill all fields");
        return false;
    }

    return true;
}

function togglePassword(){

    let pwd=document.getElementById("password");

    if(pwd.type==="password"){
        pwd.type="text";
    }else{
        pwd.type="password";
    }
}

</script>

</head>

<body>

<div class="login-container">

```
<div class="logo">
    <h1>Distraction Tracker</h1>
    <p>Track and Manage Your Daily Habits</p>
</div>

<% if(request.getAttribute("error") != null){ %>
    <div class="error">
        <%= request.getAttribute("error") %>
    </div>
<% } %>

<form action="login" method="post"
      onsubmit="return validateForm()">

    <div class="input-group">
        <label>Email</label>
        <input type="email"
               id="email"
               name="email"
               placeholder="Enter your email"
               required>
    </div>

    <div class="input-group">
        <label>Password</label>
        <input type="password"
               id="password"
               name="password"
               placeholder="Enter your password"
               required>
    </div>

    <div class="show-password">
        <input type="checkbox"
               onclick="togglePassword()">
        Show Password
    </div>

    <button type="submit" class="login-btn">
        Login
    </button>

</form>

<div class="footer">
    New User?
    <a href="register.jsp">Register Here</a>
</div>
```

</div>

</body>
</html>

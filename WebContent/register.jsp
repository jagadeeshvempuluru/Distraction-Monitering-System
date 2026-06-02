<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Register - Distraction Tracker</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Segoe UI',sans-serif;
}

body{
    min-height:100vh;
    display:flex;
    justify-content:center;
    align-items:center;
    overflow:hidden;

    background:linear-gradient(-45deg,
    #1e3c72,
    #2a5298,
    #4facfe,
    #00f2fe);

    background-size:400% 400%;
    animation:bgAnimation 12s ease infinite;
}

@keyframes bgAnimation{
    0%{background-position:0% 50%;}
    50%{background-position:100% 50%;}
    100%{background-position:0% 50%;}
}

/* Floating Circles */

.circle{
    position:absolute;
    border-radius:50%;
    background:rgba(255,255,255,0.1);
    animation:float 8s infinite ease-in-out;
}

.circle1{
    width:180px;
    height:180px;
    top:10%;
    left:10%;
}

.circle2{
    width:250px;
    height:250px;
    bottom:10%;
    right:10%;
}

.circle3{
    width:120px;
    height:120px;
    top:70%;
    left:20%;
}

@keyframes float{
    0%{
        transform:translateY(0);
    }
    50%{
        transform:translateY(-30px);
    }
    100%{
        transform:translateY(0);
    }
}

/* Register Card */

.container{

    width:400px;

    background:rgba(255,255,255,0.15);

    backdrop-filter:blur(15px);

    border-radius:20px;

    padding:35px;

    box-shadow:0 8px 32px rgba(0,0,0,0.25);

    color:white;

    animation:slideUp .8s ease;
}

@keyframes slideUp{

    from{
        opacity:0;
        transform:translateY(50px);
    }

    to{
        opacity:1;
        transform:translateY(0);
    }
}

h2{
    text-align:center;
    margin-bottom:10px;
}

.subtitle{
    text-align:center;
    margin-bottom:25px;
    opacity:0.9;
}

label{
    display:block;
    margin-bottom:8px;
    margin-top:12px;
    font-weight:bold;
}

input[type=text],
input[type=email],
input[type=password]{

    width:100%;

    padding:12px;

    border:none;

    border-radius:10px;

    outline:none;

    margin-bottom:10px;

    font-size:15px;
}

input[type=text]:focus,
input[type=email]:focus,
input[type=password]:focus{

    box-shadow:0 0 10px rgba(255,255,255,0.5);
}

input[type=submit]{

    width:100%;

    padding:14px;

    border:none;

    border-radius:10px;

    background:#28a745;

    color:white;

    font-size:16px;

    font-weight:bold;

    cursor:pointer;

    margin-top:10px;

    transition:.3s;
}

input[type=submit]:hover{

    background:#218838;

    transform:translateY(-2px);
}

.login-link{

    text-align:center;

    margin-top:20px;
}

.login-link a{

    color:white;

    font-weight:bold;

    text-decoration:none;
}

.login-link a:hover{

    text-decoration:underline;
}

</style>

</head>

<body>

<div class="circle circle1"></div>
<div class="circle circle2"></div>
<div class="circle circle3"></div>

<div class="container">

```
<h2>🚀 Create Account</h2>

<p class="subtitle">
    Register to start tracking your distractions
</p>

<form action="register" method="post">

    <label>Full Name</label>

    <input
        type="text"
        name="name"
        placeholder="Enter your full name"
        required>

    <label>Email</label>

    <input
        type="email"
        name="email"
        placeholder="Enter your email"
        required>

    <label>Password</label>

    <input
        type="password"
        name="password"
        placeholder="Enter your password"
        required>

    <input
        type="submit"
        value="Register">

</form>

<div class="login-link">

    Already have an account?

    <a href="login.jsp">
        Login
    </a>

</div>
```

</div>

</body>
</html>

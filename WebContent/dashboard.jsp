<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="com.distraction.model.Distraction" %>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Distraction Tracker Dashboard</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Segoe UI',sans-serif;
}

body{
    background:#f4f7fc;
}

/* HEADER */

.header{
    background:linear-gradient(135deg,#1e3a5f,#27496d);
    color:white;
    text-align:center;
    padding:22px;
    font-size:30px;
    font-weight:bold;
    box-shadow:0 2px 10px rgba(0,0,0,0.2);
}

/* TOP BAR */

.top-bar{
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:25px 40px;
}

.btn{
    padding:12px 22px;
    border-radius:8px;
    text-decoration:none;
    color:white;
    font-weight:bold;
    transition:.3s;
}

.add-btn{
    background:#28a745;
}

.add-btn:hover{
    background:#218838;
    transform:translateY(-2px);
}

.logout-btn{
    background:#dc3545;
}

.logout-btn:hover{
    background:#c82333;
    transform:translateY(-2px);
}

/* CARDS */

.cards{
    display:flex;
    justify-content:center;
    gap:25px;
    flex-wrap:wrap;
    margin:20px;
}

.card{
    width:280px;
    border-radius:18px;
    padding:30px;
    text-align:center;
    color:white;
    box-shadow:0 8px 20px rgba(0,0,0,0.15);
    transition:.3s;
}

.card:hover{
    transform:translateY(-8px);
}

.total{
    background:linear-gradient(135deg,#3498db,#2980b9);
}

.today{
    background:linear-gradient(135deg,#2ecc71,#27ae60);
}

.week{
    background:linear-gradient(135deg,#f39c12,#d35400);
}

.card h3{
    margin-bottom:15px;
    font-size:22px;
}

.card h1{
    font-size:55px;
}

/* SECTION */

.section{
    width:92%;
    margin:30px auto;
    background:white;
    border-radius:18px;
    padding:25px;
    box-shadow:0 5px 15px rgba(0,0,0,0.08);
}

.section h2{
    margin-bottom:20px;
    color:#1e3a5f;
}

/* TABLE */

table{
    width:100%;
    border-collapse:collapse;
}

table th{
    background:#1e3a5f;
    color:white;
    padding:14px;
}

table td{
    padding:14px;
    text-align:center;
    border-bottom:1px solid #ddd;
}

table tr:hover{
    background:#f8f9fa;
}

/* DELETE BUTTON */

.delete-btn{
    background:#dc3545;
    color:white;
    padding:8px 15px;
    text-decoration:none;
    border-radius:5px;
}

.delete-btn:hover{
    background:#c82333;
}

/* ANIMATION */

.fade{
    animation:fadeUp .8s ease;
}

@keyframes fadeUp{
    from{
        opacity:0;
        transform:translateY(30px);
    }
    to{
        opacity:1;
        transform:translateY(0);
    }
}

</style>

</head>

<body>

<div class="header">
    📊 Distraction Tracker Dashboard
</div>

<div class="top-bar">

<a href="addDistraction.html" class="btn add-btn">
    + Add Distraction
</a>

<a href="logout" class="btn logout-btn">
    Logout
</a>


</div>

<div class="cards fade">


<div class="card total">
    <h3>Total Minutes</h3>
    <h1>${total}</h1>
</div>

<div class="card today">
    <h3>Today's Minutes</h3>
    <h1>${today}</h1>
</div>

<div class="card week">
    <h3>Last 7 Days</h3>
    <h1>${last7}</h1>
</div>


</div>

<div class="section fade">

<h2>Distraction History</h2>

<table>

    <tr>
        <th>Date</th>
        <th>Type</th>
        <th>Minutes</th>
        <th>Action</th>
    </tr>

    <%
    List<Distraction> history =
    (List<Distraction>)request.getAttribute("history");

    if(history != null){

        for(Distraction d : history){
    %>

    <tr>

        <td><%= d.getDate() %></td>

        <td><%= d.getType() %></td>

        <td><%= d.getMinutes() %></td>

        <td>
            <a href="deleteDistraction?did=<%= d.getDid() %>"
       class="delete-btn"
       onclick="return confirm('Are you sure you want to delete this record?')">
       Delete
    </a>
        </td>

    </tr>

    <%
        }
    }
    %>

</table>

</div>

<div class="section fade">

<h2>Weekly Summary</h2>

<table>

    <tr>
        <th>Date</th>
        <th>Total Minutes</th>
    </tr>

    <%
    List<String[]> weeklySummary =
    (List<String[]>)request.getAttribute("weeklySummary");

    if(weeklySummary != null){

        for(String[] row : weeklySummary){
    %>

    <tr>
        <td><%= row[0] %></td>
        <td><%= row[1] %></td>
    </tr>

    <%
        }
    }
    %>

</table>


</div>

<script>

document.addEventListener("DOMContentLoaded", ()=>{

    const cards=document.querySelectorAll(".card");

    cards.forEach((card,index)=>{

        card.style.opacity="0";

        setTimeout(()=>{

            card.style.opacity="1";
            card.style.transition="all 0.6s ease";

        },index*250);

    });

});

</script>

</body>
</html>

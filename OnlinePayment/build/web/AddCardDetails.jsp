<%-- 
    Document   : AddCardDetails
    Created on : 25 May, 2016, 4:53:51 PM
    Author     : Dell
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="pack.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin: Home</title>
        <!-- for-mobile-apps -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Smart Shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!-- pignose css -->
        <link href="css/pignose.layerslider.css" rel="stylesheet" type="text/css" media="all" />


        <!-- //pignose css -->
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- js -->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <!-- //js -->
        <!-- cart -->
        <script src="js/simpleCart.min.js"></script>
        <!-- cart -->
        <!-- for bootstrap working -->
        <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
        <!-- //for bootstrap working -->
        <link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic' rel='stylesheet' type='text/css'>
        <script src="js/jquery.easing.min.js"></script>
        <script>
            function Validation()
            {
            var bankname=document.addreg.bankname.value;
            var card=document.addreg.cardnumber.value;
            if(bankname==0)
            {
            alert("Please enter bank name");
            document.addreg.bankname.focus();
            return false;
            }
            if(card==0)
            {
            alert("Please enter card number");
            document.addreg.cardnumber.focus();
            return false;
            }
            if (isNaN(card)) {
            alert("Card Number should be numeric");
            document.addreg.cardnumber.focus();
            return false;
            }
            var phoneno = /^\d{12}$/;  
            if(card.match(phoneno))  
            {  
            return true;  
            }  
            else  
            {  
            alert("Card should be 12 in length");  
            document.addreg.cardnumber.focus();
            return false;  
            }  
            }
        </script>
    </head>
    <body>
        <%
            if (request.getParameter("status") != null) {
                out.println("<script>alert('Details added successfully')</script>");
            }
            if (request.getParameter("failed") != null) {
                out.println("<script>alert(' Details already added')</script>");
            }
        %>
        <div class="header-bot">
            <div class="container">
                <div class="col-md-3 header-left">
                    <h1><a href="index.jsp"><img src="images/logo3.jpg"></a></h1>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="ban-top">
            <div class="container">
                <div class="top_nav_left">
                    <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <!-- Brand and toggle get grouped for better mobile display -->
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                    <span class="sr-only">Toggle navigation</span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                            </div>
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse menu--shylock" id="bs-example-navbar-collapse-1">
                                <ul class="nav navbar-nav menu__list">
                                    <li class="menu__item"><a class="menu__link" href="AdminHome.jsp">Home </a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddMens.jsp">Add Mens Wear</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddWomens.jsp">Add Womens Wear</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddElectronics.jsp">Add Electronics</a></li>
                                    <li class=" active menu__item menu__item--current"><a class="menu__link" href="AddCardDetails.jsp">Add Card Details</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>	
                </div>

                <div class="clearfix"></div>
            </div>
        </div>
        <div class="checkout" style="padding:20px 0;">
            <div class="container">
                <div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s" style="height: 300px">
                   
                    <center>
                        <br>
                        <form name="addreg" action="addcard">
                            <table class="timetable_sub" style="overflow: scroll;width: 400px">
                                <thead>
                                    <tr>
                                        <th>Enter Merchants mail Id</th>
                                        <th>
                                            <input type="text" id="mail" name="mail" style="width: 85%;color: black">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>
                                            Enter Password:
                                        </th>
                                        <th>
                                            <input type="password" id="pass" name="pass" style="width: 85%;color: black">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>
                                            Enter Name
                                        </th>
                                        <th>
                                            <input type="text" id="mname" name="mname" style="width: 85%;color: black">
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>
                                            Enter Mobile Number
                                        </th>
                                        <th>
                                            <input type="text" id="mobile" name="mobile" style="width: 85%;color: black">
                                        </th>
                                    </tr>
                                     <tr>
                                        <th>
                                            Enter Mobile Number
                                        </th>
                                        <th>
                                            <textarea style="width: 85%;color: black" id="address" name="address"></textarea>
                                         
                                        </th>
                                    </tr>
                                </thead>

                            </table>
                            <br>
                            <br>
                            <input type="submit" value="Add Merchant Details">
                        </form>
                    </center>

                </div>

            </div>
        </div>	

    </body>
</html>


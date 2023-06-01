<%-- 
    Document   : StatusView
    Created on : 26 May, 2016, 10:30:03 PM
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
    </head>
    <body>
        <%
            if (request.getParameter("status") != null) {
                out.println("<script>alert('Item added successfully')</script>");
            }
            if (request.getParameter("failed") != null) {
                out.println("<script>alert('Failed to add item')</script>");
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
                                    <li class="menu__item"><a class="menu__link" href="AdminHome.jsp">Home</a></li>
                                    <li class="menu__item"><a class="menu__link" href="AddMens.jsp">Add Mens Wear</a></li>
                                    <li class="menu__item"><a class="menu__link" href="AddWomens.jsp">Add Womens Wear</a></li>
                                    <li class="active menu__item menu__item--current"><a class="menu__link" href="AddElectronics.jsp">Add Electronics</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddCardDetails.jsp">Add Card Details</a></li>
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
                    <table class="timetable_sub" style="overflow: scroll">
                        <thead>
                            <tr>
                                <th>Delete</th>
                                <th>Product</th>
                                <th>Product Name</th>
                                <th>Sub-Category</th>
                                <th>Product Description</th>
                                <th>Original Price</th>
                                <th>Sale Price</th>
                            </tr>
                        </thead>
                        <%
                            DBConnection db = new DBConnection();
                            Statement st = db.st;
                            ResultSet rs = st.executeQuery("select * from items where cat='Electronics'");
                            while (rs.next()) {
                                String image = rs.getString("path");
                                String name = rs.getString("name");
                                String des = rs.getString("description");
                                String price = rs.getString("price");
                                String dprice = rs.getString("dprice");
                                String cat = rs.getString("subcat");

                        %>
                        <tr class="rem1">
                            <td class="invert-closeb" style="width: 20px">
                                <div class="rem">
                                    <div class="close1" style="width: 25px; height: 25px; position: absolute; right: 20px; top: -13px;"> </div>
                                </div>
                            </td>
                            <td class="invert-image" style="width:15%;"><a href="#"><img src="images/<%=image%>" alt=" " class="img-responsive" /></a></td>
                            <td class="invert" style="width: 120px">
                                <%=name%>
                            </td>
                            <td class="invert" style="width: 120px"><%=cat%></td>
                            <td class="invert" style="text-align: justify"><%=des%></td>
                            <td class="invert" style="width: 120px"><%=price%></td>
                            <td class="invert" style="width: 120px"><%=dprice%></td>
                        </tr>
                        <%}%>
                    </table>
                    <br>
                    <br>
                    <center>    <input class="hvr-outline-out button2" data-toggle="modal" data-target="#myModal4" type="submit" value=" Add Item " ></center>
                </div>

            </div>
        </div>	
        <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content modal-info">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
                    </div>
                    <div class="modal-body modal-spa">
                        <div class="login-grids">
                            <div class="login">
                                <form action="addelectronics" method="post" enctype="multipart/form-data">

                                    <div class="login-bottom">
                                        <h3>Add New Item</h3>
                                        <div class="sign-up">
                                            <h4>Item Name</h4>
                                            <input type="text" placeholder="Enter Item Name" name="productname" id="productname" onfocus="this.value = '';"  required="">	
                                        </div>
                                        <div class="sign-up">

                                        </div>
                                        <div class="sign-up">
                                            <h4>Enter Original Price</h4>
                                            <input type="text" placeholder="Enter Price" name="productprice" id="productprice" onfocus="this.value = '';" required="">

                                        </div>
                                        <div class="sign-up">
                                            <h4>Description</h4>
                                            <input type="text" placeholder="Enter Description" name="productdes" id="productdes" onfocus="this.value = '';" required="">

                                        </div>
                                        <div class="sign-up">
                                            <input type="submit" value="Add Item" >
                                        </div>


                                    </div>
                                    <div class="login-right" style="float: right">
                                        <div class="sign-in">
                                            <br>
                                            <br>
                                        </div>
                                        <div class="sign-in">
                                            <h4>Sub Category</h4>
                                            <select id="productcat" name="productcat">
                                                <option>Mobiles</option>
                                                <option>Tablets</option>
                                                <option>Computers</option>
                                                <option value="large">Large Applications</option>
                                                <option value="tv">Televisions</option>
                                                <option>Cameras</option>
                                                <option value="kitchen">Kitchen Appliances</option>
                                            </select>
                                        </div>
                                        <div class="sign-in">
                                            <h4>Discount Price</h4>
                                            <input type="text" placeholder="Discount Price" name="productdprice" id="productdprice" onfocus="this.value = '';" required="">

                                        </div>
                                        <div class="sign-in">
                                            <h4>Product Image</h4>

                                            <input type="file" value="Select File" name="productimage" id="productimage">

                                        </div>
                                    </div>
                                </form>
                                <div class="clearfix"></div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

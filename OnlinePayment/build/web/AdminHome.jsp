<%-- 
    Document   : AdminHome
    Created on : 5 May, 2016, 11:32:21 AM
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
            String[] args = {};
//datalib.DataLib.main(args);
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
                                    <li class="active menu__item menu__item--current"><a class="menu__link" href="AdminHome.jsp">Home <span class="sr-only">(current)</span></a></li>
                                    <li class="menu__item"><a class="menu__link" href="AddMens.jsp">Add Mens Wear</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddWomens.jsp">Add Womens Wear</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddElectronics.jsp">Add Electronics</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="AddCardDetails.jsp">Add Card Details</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="logout.jsp">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>	
                </div>

                <div class="clearfix"></div>
            </div>
        </div>
        <div class="banner-grid">
            <div id="visual">
                <div class="slide-visual">
                    <!-- Slide Image Area (1000 x 424) -->
                    <ul class="slide-group">
                        <li><img class="img-responsive" src="images/ba1.jpg" alt="Dummy Image" /></li>
                        <li><img class="img-responsive" src="images/ba2.jpg" alt="Dummy Image" /></li>
                        <li><img class="img-responsive" src="images/ba3.jpg" alt="Dummy Image" /></li>
                    </ul>

                    <!-- Slide Description Image Area (316 x 328) -->
                    <div class="script-wrap">
                        <ul class="script-group">
                            <li><div class="inner-script"><img class="img-responsive" src="images/baa1.jpg" alt="Dummy Image" /></div></li>
                            <li><div class="inner-script"><img class="img-responsive" src="images/baa2.jpg" alt="Dummy Image" /></div></li>
                            <li><div class="inner-script"><img class="img-responsive" src="images/baa3.jpg" alt="Dummy Image" /></div></li>
                        </ul>
                        <div class="slide-controller">
                            <a href="#" class="btn-prev"><img src="images/btn_prev.png" alt="Prev Slide" /></a>
                            <a href="#" class="btn-play"><img src="images/btn_play.png" alt="Start Slide" /></a>
                            <a href="#" class="btn-pause"><img src="images/btn_pause.png" alt="Pause Slide" /></a>
                            <a href="#" class="btn-next"><img src="images/btn_next.png" alt="Next Slide" /></a>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="checkout" style="padding:20px 0;">

                <div class="container">
                    <center> <h2>Order Stats</h2><br></center>
                    <div class="table-responsive checkout-right animated wow slideInUp" data-wow-delay=".5s" style="height: 400px">
                        <table class="timetable_sub" style="overflow: scroll">
                            <thead>
                                <tr>
                                    <th>Today's Order / Price</th>
                                    <th>WEEK's Order / Price </th>
                                    <th>MONTH's Order / Price </th>
                                    <th>Year's Order / Price</th>

                                </tr>
                            </thead>
                            <%
                                DBConnection db = new DBConnection();
                                Statement st = db.st;
                                int today = 0, week = 0, month = 0, year = 0;
                                double amount_t = 0, amount_w = 0, amount_m = 0, amount_y = 0;
                                String sql = "select COUNT(*) as total,IFNULL(SUM(amount),0) as SaleAmount from orders where date_ between date_sub(now(),INTERVAL 1 DAY) and now()";
                                ResultSet rs = st.executeQuery(sql);
                                if (rs.next()) {
                                    today = rs.getInt("total");
                                    amount_t = rs.getDouble("SaleAmount");
                                }
                                rs.close();
                                sql = "select COUNT(*) as total,IFNULL(SUM(amount),0) as SaleAmount from orders where date_ between date_sub(now(),INTERVAL 1 WEEK) and now()";
                                rs = st.executeQuery(sql);
                                if (rs.next()) {
                                    week = rs.getInt("total");
                                    amount_w = rs.getDouble("SaleAmount");
                                }
                                rs.close();
                                sql = "select COUNT(*) as total,IFNULL(SUM(amount),0) as SaleAmount from orders where date_ between date_sub(now(),INTERVAL 1 MONTH) and now()";
                                rs = st.executeQuery(sql);
                                if (rs.next()) {
                                    month = rs.getInt("total");
                                    amount_m = rs.getDouble("SaleAmount");
                                }
                                rs.close();
                                sql = "select COUNT(*) as total,IFNULL(SUM(amount),0) as SaleAmount from orders where date_ between date_sub(now(),INTERVAL 1 YEAR) and now()";
                                rs = st.executeQuery(sql);
                                if (rs.next()) {
                                    year = rs.getInt("total");
                                    amount_y = rs.getDouble("SaleAmount");
                                }


                            %>
                            <tr class="rem1">
                                <td class="invert" ><%=today%> / <%=amount_t%></td>
                                <td class="invert" ><%=week%> / <%=amount_w%></td>
                                <td class="invert" ><%=month%> / <%=amount_m%></td>
                                <td class="invert" ><%=year%> / <%=amount_y%></td>
                            </tr>

                        </table>
                        <br>
                        <br>

                    </div>

                </div>
            </div>	
            <script type="text/javascript" src="js/pignose.layerslider.js"></script>
            <script type="text/javascript">
                //<![CDATA[
                $(window).load(function () {
                    $('#visual').pignoseLayerSlider({
                        play: '.btn-play',
                        pause: '.btn-pause',
                        next: '.btn-next',
                        prev: '.btn-prev'
                    });
                });
                //]]>
            </script>

        </div>
    </body>
</html>

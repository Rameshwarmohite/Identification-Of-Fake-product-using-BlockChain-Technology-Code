<%-- 
    Document   : adminorder
    Created on : 26 May, 2016, 4:55:14 PM
    Author     : Dell
--%>

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
                                    <li class="active menu__item menu__item--current"><a class="menu__link" href="adminorder.jsp">Home <span class="sr-only">(current)</span></a></li>
                                    <li class="menu__item"><a class="menu__link" href="MerchantOrder.jsp">View Order Details</a></li>
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
            <script type="text/javascript" src="js/pignose.layerslider.js"></script>
            <script type="text/javascript">
                //<![CDATA[
                $(window).load(function() {
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
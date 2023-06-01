
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="pack.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <head>
        <title>Smart Shop a Ecommerce Online Shopping Category Flat Bootstrap Responsive Website Template | Single :: w3layouts</title>
        <!-- for-mobile-apps -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Smart Shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="css/flexslider.css" type="text/css" media="screen" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- js -->
        <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
        <!-- //js -->
        <!-- single -->
        <script src="js/imagezoom.js"></script>
        <script src="js/jquery.flexslider.js"></script>
        <!-- single -->
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
        <!-- header -->
        <%

            HttpSession user = request.getSession();
            // String email = "";
            String total = "0.00";
            String count = "0";
            String email = request.getParameter("email").toString();
            String id = request.getParameter("id").toString();

            user.setAttribute("email", email);
            user.setAttribute("name", email);
            if (user.getAttribute("cart") != null) {
                total = user.getAttribute("total").toString();
                count = user.getAttribute("count").toString();
            }

            // String id = request.getQueryString();
%>
        <div class="header">
            <div class="container">
                <ul>
                    <li><span class="glyphicon glyphicon-time" aria-hidden="true"></span>Free and Fast Delivery</li>
                    <li><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Free shipping On all orders</li>
                    <li><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span><a href="mailto:info@example.com">info@example.com</a></li>
                </ul>
            </div>
        </div>
        <!-- //header -->
        <!-- header-bot -->
        <div class="header-bot">
            <div class="container">
                <div class="col-md-3 header-left">
                    <h1><a href="index.jsp"><img src="images/logo3.jpg"></a></h1>
                </div>
                <div class="col-md-6 header-middle">
                    <form>
                        <div class="search">
                            <input type="search" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Search';
                                    }" required="">
                        </div>
                        <div class="section_room">
                            <select id="country" onchange="change_country(this.value)" class="frm-field required">
                                <option value="null">All categories</option>
                                <option value="null">Electronics</option>     
                                <option value="AX">kids Wear</option>
                                <option value="AX">Men's Wear</option>
                                <option value="AX">Women's Wear</option>
                                <option value="AX">Watches</option>
                            </select>
                        </div>
                        <div class="sear-sub">
                            <input type="submit" value=" ">
                        </div>
                        <div class="clearfix"></div>
                    </form>
                </div>
                <div class="col-md-3 header-right footer-bottom">
                    <ul>
                        <li><a href="#" class="use1"><span><%=email%></span></a>

                        </li>
                        <li><a class="fb" href="#"></a></li>
                        <li><a class="twi" href="#"></a></li>
                        <li><a class="insta" href="#"></a></li>
                        <li><a class="you" href="#"></a></li>
                    </ul>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!-- //header-bot -->
        <!-- banner -->
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
                                    <li class="active menu__item menu__item--current"><a class="menu__link" href="index.jsp">Home <span class="sr-only">(current)</span></a></li>
                                    <li class="dropdown menu__item">
                                        <a href="#" class="dropdown-toggle menu__link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">men's wear <span class="caret"></span></a>
                                        <ul class="dropdown-menu multi-column columns-3">
                                            <div class="row">
                                                <div class="col-sm-6 multi-gd-img1 multi-gd-text ">
                                                    <a href="mens.jsp"><img src="images/woo1.jpg" alt=" "/></a>
                                                </div>
                                                <div class="col-sm-3 multi-gd-img">
                                                    <ul class="multi-column-dropdown">
                                                        <li><a href="mens.jsp?clothing">Clothing</a></li>
                                                        <li><a href="mens.jsp?wallets">Wallets</a></li>
                                                        <li><a href="mens.jsp?footwear">Footwear</a></li>
                                                        <li><a href="mens.jsp?watches">Watches</a></li>
                                                        <li><a href="mens.jsp?bags">Bags</a></li>
                                                        <li><a href="mens.jsp?sunglasses">Sunglasses</a></li>
                                                        <li><a href="mens.jsp?caps">Caps & Hats</a></li>
                                                    </ul>
                                                </div>

                                            </div>
                                        </ul>
                                    </li>
                                    <li class="dropdown menu__item">
                                        <a href="#" class="dropdown-toggle menu__link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">women's wear <span class="caret"></span></a>
                                        <ul class="dropdown-menu multi-column columns-3">
                                            <div class="row">
                                                <div class="col-sm-6 multi-gd-img1 multi-gd-text ">
                                                    <a href="womens.jsp"><img src="images/woo.jpg" alt=" "/></a>
                                                </div>
                                                <div class="col-sm-3 multi-gd-img">
                                                    <ul class="multi-column-dropdown">
                                                        <li><a href="womens.jsp?clothing">Clothing</a></li>
                                                        <li><a href="womens.jsp?wallets">Wallets</a></li>
                                                        <li><a href="womens.jsp?footwear">Footwear</a></li>
                                                        <li><a href="womens.jsp?watches">Watches</a></li>
                                                        <li><a href="womens.jsp?bags">Bags</a></li>
                                                        <li><a href="womens.jsp?jewellery">Jewellery</a></li>
                                                        <li><a href="womens.jsp?caps">Caps & Hats</a></li>
                                                    </ul>
                                                </div>

                                                <div class="clearfix"></div>
                                            </div>
                                        </ul>
                                    </li>
                                    <li class="dropdown menu__item">
                                        <a href="#" class="dropdown-toggle menu__link" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Electronics<span class="caret"></span></a>
                                        <ul class="dropdown-menu multi-column columns-3">
                                            <div class="row">
                                                <div class="col-sm-6 multi-gd-img1 multi-gd-text ">
                                                    <a href="electronics.jsp"><img src="images/woo2.jpg" alt=" "/></a>
                                                </div>
                                                <div class="col-sm-3 multi-gd-img">
                                                    <ul class="multi-column-dropdown">
                                                        <li><a href="electronics.jsp?mobiles">Mobiles</a></li>
                                                        <li><a href="electronics.jsp?tablets">Tablets</a></li>
                                                        <li><a href="electronics.jsp?computers">Computers</a></li>
                                                        <li><a href="electronics.jsp?large">Large Applications</a></li>
                                                        <li><a href="electronics.jsp?tv">Televisions</a></li>
                                                        <li><a href="electronics.jsp?cameras">Cameras</a></li>
                                                        <li><a href="electronics.jsp?kitchen">Kitchen Appliances</a></li>
                                                    </ul>
                                                </div>

                                                <div class="clearfix"></div>
                                            </div>
                                        </ul>
                                    </li>
                                    <li class=" menu__item"><a class="menu__link" href="orserstatus.jsp">Order Status</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="contact.jsp">contact</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="logout.jsp">log out</a></li>   </ul>
                                </ul>
                            </div>
                        </div>
                    </nav>	
                </div>
                <div class="top_nav_right">
                    <div class="cart box_1">
                        <a href="checkout.jsp">
                            <h3> <div class="total">
                                    <i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i>
                                    <span>$<%=total%></span> (<span><%=count%></span> items)</div>

                            </h3>
                        </a>
                        <p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>

                    </div>	
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!-- //banner-top -->
        <!-- banner -->
        <div class="page-head">
            <div class="container">
                <h3>Single</h3>
            </div>
        </div>
        <!-- //banner -->
        <!-- single -->
        <div class="single">
            <div class="container">
                <div class="col-md-6 single-right-left animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
                    <div class="grid images_3_of_2">
                        <div class="flexslider">
                            <!-- FlexSlider -->
                            <script>
                                // Can also be used with $(document).ready()
                                $(window).load(function () {
                                    $('.flexslider').flexslider({
                                        animation: "slide",
                                        controlNav: "thumbnails"
                                    });
                                });
                            </script>
                            <!-- //FlexSlider-->
                            <%
                                DBConnection db = new DBConnection();
                                Statement st = db.st;
                                String image = "";
                                String des = "";
                                String name = "";
                                String price = "";
                                String dprice = "";
                                ResultSet rs = st.executeQuery("select * from items where id='" + id + "'");
                                if (rs.next()) {
                                    name = rs.getString("name");
                                    image = rs.getString("path");
                                    des = rs.getString("description");
                                    price = rs.getString("price");
                                    dprice = rs.getString("dprice");

                                }
                                int cnt = 0;
                                String sql = "select count(status)as total from liketable_ where id='" + id + "' and status='like'";
                                ResultSet rs1 = st.executeQuery(sql);
                                if (rs1.next()) {
                                    cnt = rs1.getInt("total");
                                }
                                int cnt1 = 0;
                                String sql2 = "select count(status)as total from liketable_ where id='" + id + "' and status='unlike'";
                                ResultSet rs3 = st.executeQuery(sql2);
                                if (rs3.next()) {
                                    cnt1 = rs3.getInt("total");
                                }
                            %>
                            <ul class="slides">
                                <li data-thumb="images/<%=image%>">
                                    <div class="thumb-image"> <img src="images/<%=image%>" data-imagezoom="true" class="img-responsive"> </div>
                                </li>

                            </ul>
                            <div class="clearfix"></div>
                        </div>	
                    </div>
                </div>

                <div class="col-md-6 single-right-left simpleCart_shelfItem animated wow slideInRight animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInRight;">
                    <h3><%=name%></h3>
                    <p><span class="item_price">$<%=dprice%></span> <del>- $<%=price%></del></p>
                    <div class="rating1">
                        <span class="starRating">
                            <input id="rating5" type="radio" name="rating" value="5">
                            <label for="rating5">5</label>
                            <input id="rating4" type="radio" name="rating" value="4">
                            <label for="rating4">4</label>
                            <input id="rating3" type="radio" name="rating" value="3" checked="">
                            <label for="rating3">3</label>
                            <input id="rating2" type="radio" name="rating" value="2">
                            <label for="rating2">2</label>
                            <input id="rating1" type="radio" name="rating" value="1">
                            <label for="rating1">1</label>
                        </span>
                    </div>
                    <div>
                        <%=cnt%>&nbsp;<a href="LikePage?id=<%=id%>"><img src="images/ll1.png" height="35" width="35"></a> &nbsp;&nbsp;
                        <a href="LikePage?id=<%=id%>"><img src="images/d1.png" height="35" width="35"></a>&nbsp;<%=cnt1%>
                    </div>
                    <div class="description">
                        <h5>Check delivery, payment options and charges at your location</h5>
                        <input type="text" value="Enter pincode" onfocus="this.value = '';" onblur="if (this.value == '') {
                                    this.value = 'Enter pincode';
                                }" required="">
                        <input type="submit" value="Check">
                    </div>

                    <div class="occasional">

                        <div class="clearfix"> </div>
                    </div>
                    <div class="occasion-cart">
                        <a href="addtocart.jsp?<%=id%>" class="item_add hvr-outline-out button2">Add to cart</a>
                    </div>

                </div>
                <div class="clearfix"> </div>

                <div class="bootstrap-tab animated wow slideInUp animated" data-wow-delay=".5s" style="visibility: visible; animation-delay: 0.5s; animation-name: slideInUp;">
                    <div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
                        <ul id="myTab" class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#home" id="home-tab" role="tab" data-toggle="tab" aria-controls="home" aria-expanded="true">Description</a></li>

                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div role="tabpanel" class="tab-pane fade in active bootstrap-tab-text" id="home" aria-labelledby="home-tab">
                                <h5>Product Brief Description</h5>
                                <p><span><%=des%></span></p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="profile" aria-labelledby="profile-tab">
                                <div class="bootstrap-tab-text-grids">
                                    <div class="bootstrap-tab-text-grid">
                                        <div class="bootstrap-tab-text-grid-left">
                                            <img src="images/men3.jpg" alt=" " class="img-responsive">
                                        </div>
                                        <div class="bootstrap-tab-text-grid-right">
                                            <ul>
                                                <li><a href="#">Admin</a></li>
                                                <li><a href="#"><span class="glyphicon glyphicon-share" aria-hidden="true"></span>Reply</a></li>
                                            </ul>
                                            <p>Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis 
                                                suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem 
                                                vel eum iure reprehenderit.</p>
                                        </div>
                                        <div class="clearfix"> </div>
                                    </div>

                                    <div class="add-review">
                                        <h4>add a review</h4>
                                        <form>
                                            <input type="text" value="Name" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Name';
                                                    }" required="">
                                            <input type="email" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Email';
                                                    }" required="">

                                            <textarea type="text" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Message...';
                                                    }" required="">Message...</textarea>
                                            <input type="submit" value="SEND">
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="dropdown1" aria-labelledby="dropdown1-tab">
                                <p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr.</p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade bootstrap-tab-text" id="dropdown2" aria-labelledby="dropdown2-tab">
                                <p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral, mustache readymade thundercats keffiyeh craft beer marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park vegan.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //single -->
        <!-- //product-nav -->
        <div class="coupons">
            <div class="container">
                <div class="coupons-grids text-center">
                    <div class="col-md-3 coupons-gd">
                        <h3>Buy your product in a simple way</h3>
                    </div>
                    <div class="col-md-3 coupons-gd">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        <h4>LOGIN TO YOUR ACCOUNT</h4>
                        <p>Neque porro quisquam est, qui dolorem ipsum quia dolor
                            sit amet, consectetur.</p>
                    </div>
                    <div class="col-md-3 coupons-gd">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                        <h4>SELECT YOUR ITEM</h4>
                        <p>Neque porro quisquam est, qui dolorem ipsum quia dolor
                            sit amet, consectetur.</p>
                    </div>
                    <div class="col-md-3 coupons-gd">
                        <span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span>
                        <h4>MAKE PAYMENT</h4>
                        <p>Neque porro quisquam est, qui dolorem ipsum quia dolor
                            sit amet, consectetur.</p>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
        </div>
        <!-- footer -->
        <div class="footer">
            <div class="container">
                <div class="col-md-3 footer-left">
                    <h2><a href="index.jsp"><img src="images/logo3.jpg" alt=" " /></a></h2>
                    <p>Neque porro quisquam est, qui dolorem ipsum quia dolor
                        sit amet, consectetur, adipisci velit, sed quia non 
                        numquam eius modi tempora incidunt ut labore 
                        et dolore magnam aliquam quaerat voluptatem.</p>
                </div>
                <div class="col-md-9 footer-right">
                    <div class="col-sm-6 newsleft">
                        <h3>SIGN UP FOR NEWSLETTER !</h3>
                    </div>
                    <div class="col-sm-6 newsright">
                        <form>
                            <input type="text" value="Email" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Email';
                                    }" required="">
                            <input type="submit" value="Submit">
                        </form>
                    </div>
                    <div class="clearfix"></div>
                    <div class="sign-grds">
                        <div class="col-md-4 sign-gd">
                            <h4>Information</h4>
                            <ul>
                                <li><a href="index.jsp">Home</a></li>
                                <li><a href="mens.jsp">Men's Wear</a></li>
                                <li><a href="womens.jsp">Women's Wear</a></li>
                                <li><a href="electronics.jsp">Electronics</a></li>
                                <li><a href="contact.jsp">Contact</a></li>
                            </ul>
                        </div>

                        <div class="col-md-4 sign-gd-two">
                            <h4>Store Information</h4>
                            <ul>
                                <li><i class="glyphicon glyphicon-map-marker" aria-hidden="true"></i>Address : 1234k Avenue, 4th block, <span>Newyork City.</span></li>
                                <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>Email : <a href="mailto:info@example.com">info@example.com</a></li>
                                <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>Phone : +1234 567 567</li>
                            </ul>
                        </div>
                        <div class="col-md-4 sign-gd flickr-post">
                            <h4>Flickr Posts</h4>
                            <ul>
                                <li><a href="single.jsp"><img src="images/b15.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b16.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b17.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b18.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b15.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b16.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b17.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b18.jpg" alt=" " class="img-responsive" /></a></li>
                                <li><a href="single.jsp"><img src="images/b15.jpg" alt=" " class="img-responsive" /></a></li>
                            </ul>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <p class="copy-right">&copy 2016 Smart Shop. All rights reserved | Design by <a href="http://w3layouts.com/">W3layouts</a></p>
            </div>
        </div>
        <!-- //footer -->
        <!-- login -->
        <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content modal-info">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
                    </div>
                    <div class="modal-body modal-spa">
                        <div class="login-grids">
                            <div class="login">
                                <div class="login-bottom">
                                    <h3>Sign up for free</h3>
                                    <form>
                                        <div class="sign-up">
                                            <h4>Email :</h4>
                                            <input type="text" value="Type here" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Type here';
                                                    }" required="">	
                                        </div>
                                        <div class="sign-up">
                                            <h4>Password :</h4>
                                            <input type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Password';
                                                    }" required="">

                                        </div>
                                        <div class="sign-up">
                                            <h4>Re-type Password :</h4>
                                            <input type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Password';
                                                    }" required="">

                                        </div>
                                        <div class="sign-up">
                                            <input type="submit" value="REGISTER NOW" >
                                        </div>

                                    </form>
                                </div>
                                <div class="login-right">
                                    <h3>Sign in with your account</h3>
                                    <form>
                                        <div class="sign-in">
                                            <h4>Email :</h4>
                                            <input type="text" value="Type here" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Type here';
                                                    }" required="">	
                                        </div>
                                        <div class="sign-in">
                                            <h4>Password :</h4>
                                            <input type="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {
                                                        this.value = 'Password';
                                                    }" required="">
                                            <a href="#">Forgot password?</a>
                                        </div>
                                        <div class="single-bottom">
                                            <input type="checkbox"  id="brand" value="">
                                            <label for="brand"><span></span>Remember Me.</label>
                                        </div>
                                        <div class="sign-in">
                                            <input type="submit" value="SIGNIN" >
                                        </div>
                                    </form>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <p>By logging in you agree to our <a href="#">Terms and Conditions</a> and <a href="#">Privacy Policy</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- //login -->
    </body>
</html>


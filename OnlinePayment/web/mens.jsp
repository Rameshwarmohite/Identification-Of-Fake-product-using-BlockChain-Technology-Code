<%-- 
    Document   : mens
    Created on : 4 May, 2016, 2:08:51 PM
    Author     : Dell
--%>

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
        <title>Smart Shop a Ecommerce Online Shopping Category Flat Bootstrap Responsive Website Template | Mens :: w3layouts</title>
        <!-- for-mobile-apps -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Smart Shop Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
            function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- //for-mobile-apps -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
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
        <!-- header -->
        <%
            HttpSession user = request.getSession(true);
            String email = "";
            String total = "0.00";
            String count = "0";
            if (request.getAttribute("item") != null) {
                out.println("<script>alert('There is no any item in cart')</script>");
            }
            if (user.getAttribute("email") == null) {
                user.setAttribute("login", "failed");

                response.sendRedirect("index.jsp");
            } else {
                email = user.getAttribute("name").toString();
            }
            if (user.getAttribute("cart") != null) {
                total = user.getAttribute("total").toString();
                count = user.getAttribute("count").toString();
            }


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
                     <form action="search.jsp">
                        <div class="search">
                            <input type="search" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Search';
                                    }" required="" name="search" id="search">
                        </div>
                        <div class="section_room">
                            <select id="category" onchange="change_country(this.value)" class="frm-field required" name="category">
                                <option value="null">All categories</option>
                                <option value="Electronics">Electronics</option>     
                                <option value="Mens Wear">Men's Wear</option>
                                <option value="Womens Wear">Women's Wear</option>
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
                                                       
                                                        <li><a href="electronics.jsp?tv">Televisions</a></li>
                                                        <li><a href="electronics.jsp?cameras">Cameras</a></li>
                                                        <li><a href="electronics.jsp?kitchen">Kitchen Appliances</a></li>
                                                    </ul>
                                                </div>

                                                <div class="clearfix"></div>
                                            </div>
                                        </ul>
                                    </li>
                                    <li class=" menu__item"><a class="menu__link" href="orderstatus.jsp">order Status</a></li>
                                    <li class=" menu__item"><a class="menu__link" href="logout.jsp">log out</a></li>   </ul>
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
                <h3>Men's Wear</h3>
            </div>
        </div>
        <!-- //banner -->
        <!-- mens -->
        <div class="men-wear">
            <div class="container">
                <div class="col-md-4 products-left">
                    <div class="filter-price">
                        <h3>Filter By Price</h3>
                        <ul class="dropdown-menu6">
                            <li>                
                                <div id="slider-range"></div>							
                                <input type="text" id="amount" style="border: 0; color: #ffffff; font-weight: normal;" />
                            </li>			
                        </ul>
                        <!---->
                        <script type='text/javascript'>//<![CDATA[ 
                            $(window).load(function() {
                            $("#slider-range").slider({
                            range: true,
                            min: 0,
                            max: 9000,
                            values: [1000, 7000],
                            slide: function(event, ui) {
                            $("#amount").val("$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ]);
                            }
                            });
                            $("#amount").val("$" + $("#slider-range").slider("values", 0) + " - $" + $("#slider-range").slider("values", 1));

                            });//]]>  

                        </script>
                        <script type="text/javascript" src="js/jquery-ui.js"></script>
                        <!---->
                    </div>
                    <div class="css-treeview">
                        <h4>Categories</h4>
                        <ul class="tree-list-pad">
                            <li><input type="checkbox" checked="checked" id="item-0" /><label for="item-0"><span></span>Men's Wear</label>
                                <ul>
                                    <li><input type="checkbox" id="item-0-0" /><label for="item-0-0">Ethinic Wear</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Caps</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                            <li><a href="mens.jsp">Trousers</a></li>
                                        </ul>
                                    </li>
                                    <li><input type="checkbox"  id="item-0-1" /><label for="item-0-1">Party Wear</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Caps</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                            <li><a href="mens.jsp">Trousers</a></li>
                                        </ul>
                                    </li>
                                    <li><input type="checkbox"  id="item-0-2" /><label for="item-0-2">Casual Wear</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Caps</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                            <li><a href="mens.jsp">Trousers</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <li><input type="checkbox" id="item-1" checked="checked" /><label for="item-1">Best Collections</label>
                                <ul>
                                    <li><input type="checkbox" checked="checked" id="item-1-0" /><label for="item-1-0">New Arrivals</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                        </ul>
                                    </li>

                                </ul>
                            </li>
                            <li><input type="checkbox" checked="checked" id="item-2" /><label for="item-2">Best Offers</label>
                                <ul>
                                    <li><input type="checkbox"  id="item-2-0" /><label for="item-2-0">Summer Discount Sales</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                        </ul>
                                    </li>
                                    <li><input type="checkbox" id="item-2-1" /><label for="item-2-1">Exciting Offers</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                        </ul>
                                    </li>
                                    <li><input type="checkbox" id="item-2-2" /><label for="item-2-2">Flat Discounts</label>
                                        <ul>
                                            <li><a href="mens.jsp">Shirts</a></li>
                                            <li><a href="mens.jsp">Shoes</a></li>
                                            <li><a href="mens.jsp">Pants</a></li>
                                            <li><a href="mens.jsp">SunGlasses</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <div class="clearfix"></div>
                </div>
                <div class="col-md-8 products-right">

                    <div class="sort-grid">
                        <div class="sorting">
                            <div class="clearfix"></div>
                        </div>
                        <div class="sorting">
                            <div class="clearfix"></div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="men-wear-top">
                        <script src="js/responsiveslides.min.js"></script>
                        <script>
                            // You can also use "$(window).load(function() {"
                            $(function() {
                            // Slideshow 4
                            $("#slider3").responsiveSlides({
                            auto: true,
                            pager: true,
                            nav: false,
                            speed: 500,
                            namespace: "callbacks",
                            before: function() {
                            $('.events').append("<li>before event fired.</li>");
                            },
                            after: function() {
                            $('.events').append("<li>after event fired.</li>");
                            }
                            });
                            });
                        </script>
                        <div  id="top" class="callbacks_container">
                            <ul class="rslides" id="slider3">
                                <li>
                                    <img class="img-responsive" src="images/men1.jpg" alt=" "/>
                                </li>
                                <li>
                                    <img class="img-responsive" src="images/men2.jpg" alt=" "/>
                                </li>
                                <li>
                                    <img class="img-responsive" src="images/men1.jpg" alt=" "/>
                                </li>
                                <li>
                                    <img class="img-responsive" src="images/men2.jpg" alt=" "/>
                                </li>
                            </ul>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="men-wear-bottom">
                        <div class="col-sm-4 men-wear-left">
                            <img class="img-responsive" src="images/men3.jpg" alt=" " />
                        </div>
                        <div class="col-sm-8 men-wear-right">
                            <h4>Exclusive Men's Collections</h4>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
                                accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae 
                                ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt
                                explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut
                                odit aut fugit. </p>
                        </div>
                        <div class="clearfix"></div>
                    </div>


                    <div class="clearfix"></div>
                </div>
                <div class="clearfix"></div>
                <div class="single-pro">
                    <%
                        try {
                            DBConnection db = new DBConnection();
                            Statement st = db.st;
                            ResultSet rs = null;
                            String cat = new String();
                            if (request.getQueryString() != null) {
                                cat = request.getQueryString();
                            }
                            System.out.println("item name " + cat);
                            if (cat.equals("") || cat.equals("null")) {
                                rs = st.executeQuery("select * from items where cat='Mens Wear'");
                            } else {
                                rs = st.executeQuery("select * from items where cat='Mens Wear' and subcat='" + cat + "'");
                            }
                            while (rs.next()) {

                                String name = rs.getString("name");
                                String id = rs.getString("id");
                                String image = rs.getString("path");
                                String price = rs.getString("price");
                                String dprice = rs.getString("dprice");

                    %>
                    <div class="col-md-3 product-men">
                        <div class="men-pro-item simpleCart_shelfItem">
                            <div class="men-thumb-item">
                                <img src="images/<%=image%>" height="290px" width="80%" alt="" class="pro-image-front">
                              
                                <img src="images/<%=image%>" alt="" class="pro-image-back">
                                <div class="men-cart-pro">
                                    <div class="inner-men-cart-pro">
                                        <a href="single.jsp?<%=id%>" class="link-product-add-cart">Quick View</a>
                                    </div>
                                </div>
                                <span class="product-new-top">New</span>				
                            </div>
                            <div class="item-info-product ">
                                <h4><a href="single.jsp?<%=id%>"><%=name%></a></h4>
                                <div class="info-product-price">
                                    <span class="item_price">$<%=dprice%></span>
                                    <del>$<%=price%></del>
                                </div>
                                <a href="addtocart.jsp?<%=id%>" class="item_add single-item hvr-outline-out button2">Add to cart</a>									
                                <center>  <a href="#" class="invert-image" ><img src="qr_codes/<%=id%>.png" alt=" " class="img-responsive" style="width:120px" /></a></center>
                            </div>
                        </div>
                    </div>
                    <%}
                        } catch (Exception e) {
                            e.printStackTrace();
                        }%>
                    <div class="clearfix"></div>
                </div>

            </div>
        </div>	
        <!-- //mens -->
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
                                            <h4>Name</h4>
                                            <input type="text" name="fname" value="Full Name" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Full Name';
                                    }" required="">

                                        </div>
                                        <div class="sign-up">
                                            <input type="text" value="Address" name="address" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'Address';
                                    }" required="">

                                        </div>
                                        <div class="sign-up">
                                            <input type="text" value="mobile number" name="mobile" onfocus="this.value = '';" onblur="if (this.value == '') {
                                        this.value = 'mobile number';
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

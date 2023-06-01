<%-- 
    Document   : login
    Created on : Jan 26, 2017, 3:45:18 PM
    Author     : SHRI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <head>
        <title>Smart Shop a Ecommerce Online Shopping Category Flat Bootstrap Responsive Website Template | Home :: w3layouts</title>
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
         <div id="myModal3" tabindex="-1"  aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document" style="width: 400px;">
                <div class="modal-content modal-info">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
                    </div>
                    <div class="modal-body modal-spa">
                        <div class="login-grids">
                            <div class="login">
                                <div class="login-right" style="float: left; width: 100%">
                                    <h3>Sign in with your account</h3>
                                    <form action="logincheck">
                                        <div class="sign-in">
                                            <h4>Email :</h4>
                                            <input type="text" placeholder="email" name="uemail" id="uemail" onfocus="this.value = '';"  required="">	
                                        </div>
                                        <div class="sign-in">
                                            <h4>Password :</h4>
                                            <input type="password" name="upass" id="upass" placeholder="password"  onfocus="this.value = '';"  required="">
                                            <a href="Forgot.jsp">Forgot password?</a>&nbsp;&nbsp; &nbsp;&nbsp;  <a href="#" data-toggle="modal" data-target="#myModal4">New User?</a>
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

        <div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content modal-info">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
                    </div>
                    <div class="modal-body modal-spa">
                        <div class="login-grids">
                            <div class="login">
                                <form action="ureg" method="post">
                                    <div class="login-bottom">
                                        <h3>Sign up for free</h3>

                                        <div class="sign-up">
                                            <h4>Email :</h4>
                                            <input type="text" name="email" placeholder="Type here" onfocus="this.value = '';"  required="">	
                                        </div>
                                        <div class="sign-up">
                                            <h4>Password :</h4>
                                            <input type="password" name="pass" placeholder="Password" onfocus="this.value = '';"  required="">

                                        </div>
                                        <div class="sign-up">
                                            <h4>Re-type Password :</h4>
                                            <input type="password" name="cpass" placeholder="Confirm Password" onfocus="this.value = '';"  required="">

                                        </div>
                                       
                                        <div class="sign-up">
                                            <input type="submit" value="REGISTER NOW" >
                                        </div>
                                    </div>
                                    <div class="login-right" style="float: right">
                                        <br><br>
                                        <div class="sign-in">
                                            <h4>Full Name</h4>
                                            <input type="text" placeholder="Full Name" name="fname" onfocus="this.value = '';" required="">
                                        </div>
                                        <div class="sign-in">
                                            <h4>Address</h4>
                                            <input type="text" placeholder="Address" name="address" onfocus="this.value = '';"  required="">

                                        </div>
                                        <div class="sign-in">
                                            <h4>Mobile Number</h4>
                                            <input type="text" placeholder="mobile number" name="mobile" onfocus="this.value = '';" required="">
                                      
                                        </div>
                                       
                                    </div>
                                </form>


                                <div class="clearfix"></div>
                            </div>
                            <p>By logging in you agree to our <a href="#">Terms and Conditions</a> and <a href="#">Privacy Policy</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!doctype html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Grievance</title>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="/css/style.css">
    </head>

    <body>


        <!-- navbar -->

        <nav class="flex align-center">
            <p><span>Griev</span>ance</p>
            <ul>
                <li>
                    <a href="http://localhost:8080/user/complains">Home</a>
                    <a href="http://localhost:8080/dashboard">Complain</a>
                    <a href="http://localhost:8080/logout">Logout</a>
                    <!-- <button class="btn register">Register</button>
        <button class="btn login">Log In</button> -->
                </li>
            </ul>
        </nav>


        <!-- content -->
        <div class="container" style="padding-top: 65px;">

            <!-- <button id="addRow" type="button" class="btn btn-success">Add another product</button> -->

            <!-- <svg xmlns="http://www.w3.org/2000/svg" id="addRow" style="size: 1cm;" type="button" viewBox="0 0 24 24"><path d="M12,2A10,10,0,1,0,22,12,10,10,0,0,0,12,2Zm0,18a8,8,0,1,1,8-8A8,8,0,0,1,12,20Zm4-9H13V8a1,1,0,0,0-2,0v3H8a1,1,0,0,0,0,2h3v3a1,1,0,0,0,2,0V13h3a1,1,0,0,0,0-2Z"/></svg> -->
            <!-- <form method="post" name="catalog" enctype="multipart/form-data" id="catalogForm" action="/auctionhouse/catalog" > -->
            <form method="POST" id="complainform" name="complainform" enctype="multipart/form-data" action="/complain">



                <!--This is to add auction details-->


                <input type="hidden" id="complainerId" name="complainerId" value="${complainerId}">


                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" name="title" id="title" placeholder="Enter the Title"
                            required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPassword4">Complaint Type</label>
                        <select id="category" name="category" class="form-control" required>
                            <option selected>Choose...</option>
                            <c:forEach var="category" items="${categories}">
                                <option>${category.categoryName}</option>
                            </c:forEach>
                        </select>


                        <!-- 
                            <input type="date" class="form-control" id="startDate" name="startDate"
                                placeholder="dd/mm/yyyy" required> -->
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="startTime">Address</label>
                        <input type="text" class="form-control" name="address" id="address" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="ward">Ward</label>
                        <input type="text" class="form-control" id="ward" name="ward" placeholder="Ward" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Description</label><br>
                    <textarea type="text" class="form-control" id="description" name="description" required></textarea>
                </div>



                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="image">Proof</label><br>
                        <input type="file" class="form-control" id="img" name="img" required>
                    </div>

                </div>


                <br>
                <button type="button" class="btn btn-success" onclick="registered()">Submit</button>
                <!-- <input type="submit" class="btn btn-success" value="Submit"> -->
            </form>
        </div>

        <script type="text/javascript">

            function registered() {
                let myform = document.getElementById("complainform");
                let fd = new FormData(myform);
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/complain",
                    data: fd,
                    cache: false,
                    processData: false,
                    contentType: false,
                    success: function (result) {

                        if (result == "success") {
                            swal({
                                title: "Complain Registered",
                                text: "Complain will resolve soon",
                                icon: "success",
                            }).then(function () {
                                window.location.href = "http://localhost:8080/user/complains";
                            });
                        }
                        console.log(result);
                    }
                });
            }

        </script>
    </body>

    </html>
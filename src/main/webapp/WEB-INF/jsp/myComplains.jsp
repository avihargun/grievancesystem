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
        <a href="http://localhost:8080/dashboard">Complaint</a>
        <a href="http://localhost:8080/logout">Logout</a>
        <!-- <button class="btn register">Register</button>
        <button class="btn login">Log In</button> -->
      </li>
    </ul>
  </nav>
  <br>
  <center ><h2>My Complains</h2></center>
  <br>

  <div class="container">
  <table class="table">
    <thead>
      <tr>
        <th scope="col">ID</th>
        <th scope="col">Title</th>
        <th scope="col">Status</th>
        <th scope="col">Review</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="complain" items="${complains}" varStatus="status">
            <tr>
                <th scope="row">${status.index+1}</th>
                <td>${complain.title}</td>
                <c:if test="${complain.status.ordinal() ==1}">
                    <td>Solved</td>
                    <c:if test="${complain.rating.rate >0}">
                        <td>    <button class="btn btn-success">Reviewed</button></td>
                    </c:if>
                    <c:if test="${complain.rating.rate == null}">
                        <td>    <a href="http://localhost:8080/rating/${complain.complainId}"><button class="btn btn-outline-success">Review</button></a></td>
                    </c:if>
                    
                </c:if>
              <c:if test="${complain.status.ordinal()==0}">
                <td>Pending</td>
                <td>    <button class="btn btn-outline-success disabled">Review</button></td>
              </c:if>  
              
              </tr>

        </c:forEach>
    </tbody>
  </table>
</div>
    </body>
    </html>
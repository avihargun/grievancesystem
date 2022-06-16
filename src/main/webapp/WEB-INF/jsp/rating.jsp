<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Form in HTML Example </title>

    <link rel="stylesheet" href="/css/rating.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <!--Only for demo purpose - no need to add.-->
    <!-- <link rel="stylesheet" href="/css/demo.css" /> -->

  </head>

  <body>


    <section>
      <div class="rt-container">
        <div class="col-rt-12">
          <div class="Scriptcontent">


            <div class="feedback">
              <p>Dear Citizen,<br>
                Thank you for your Co-opration. We would like to know how we performed. Please
                spare some moments to give us your valuable feedback as it will help us in improving our service.
              </p>

              <h4>Please rate your service experience for the following parameters</h4>

              <form method="post" id="ratingform" name="ratingform" action="/rating">
                <input type="number" hidden id="complainId" name="complainId" value="${complainId}">
                <hr class="survey-hr">
                <label>1. Friendliness and courtesy shown to you whle solving your query</label><br><br />
                <div style="color:grey">
                  <span style="float:left">
                    POOR
                  </span>
                  <span style="float:right">
                    BEST
                  </span>

                </div>
                <span class="scale-rating">
                  <label value="1">
                    <input type="radio" value="1" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="2">
                    <input type="radio" value="2" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="3">
                    <input type="radio" value="3" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="4">
                    <input type="radio" value="4" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="5">
                    <input type="radio" value="5" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="6">
                    <input type="radio" value="6" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="7">
                    <input type="radio" value="7" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="8">
                    <input type="radio" value="8" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="9">
                    <input type="radio" value="9" name="rate">
                    <label style="width:100%;"></label>
                  </label>
                  <label value="10">
                    <input type="radio" value="10" name="rate" value="10">
                    <label style="width:100%;"></label>
                  </label>
                </span>

                <div class="clear"></div>
                <hr class="survey-hr">
                <label for="m_3189847521540640526commentText">2. Any Other suggestions:</label><br /><br />
                <textarea cols="75" name="feedback" rows="5" style="100%"></textarea><br>
                <br>
                <div class="clear"></div>
                <input style="background:#43a7d5;color:#fff;padding:12px;border:0" type="button" onclick="rated()"
                  value="Submit your review">&nbsp;
              </form>
    </section>



    <!-- Analytics -->
    <script type="text/javascript">

      function rated() {
        let myform = document.getElementById("ratingform");
        let fd = new FormData(myform);
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/rating",
          data: fd,
          cache: false,
          processData: false,
          contentType: false,
          success: function (result) {

            if (result == "success") {
              swal({
                title: "Feedback submitted",
                text: "Thank you",
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
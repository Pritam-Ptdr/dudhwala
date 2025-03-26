<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,javax.servlet.*" %>
<%@ page import="org.json.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/signup.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="signup-box">
    <img class="logo" src="images/milkcanlogo.png" alt="Logo">
    <button class="signup-button" onclick="openSignupModal()">Sign Up</button>
</div>

<!-- Signup Modal -->
<div id="signupModal" class="modal">
    <span onclick="closeSignupModal()" class="close">&times;</span>
    <form id="signupForm" class="modal-content">
        <div class="container">
            <h1>Sign Up</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>

            <label for="firstName"><b>First Name</b></label>
            <input type="text" id="firstName" placeholder="Enter First Name" required>

            <label for="lastName"><b>Last Name</b></label>
            <input type="text" id="lastName" placeholder="Enter Last Name" required>

            <label for="email"><b>Email</b></label>
            <input type="email" id="email" placeholder="Enter Email" required>

            <label for="mobileNumber"><b>Mobile Number</b></label>
            <input type="text" id="mobileNumber" placeholder="Enter Mobile Number" required>

            <label for="password"><b>Password</b></label>
            <input type="password" id="password" placeholder="Enter Password" required>

            <label for="confirmPassword"><b>Confirm Password</b></label>
            <input type="password" id="confirmPassword" placeholder="Confirm Password" required>

            <p>By creating an account, you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

            <div class="clearfix">
                <button type="button" class="cancelbtn" onclick="closeSignupModal()">Cancel</button>
                <button type="submit" class="signupbtn">Sign Up</button>
            </div>
        </div>
    </form>
</div>

<!-- JavaScript -->
<script>
function openSignupModal() {
    document.getElementById('signupModal').style.display = 'block';
}

function closeSignupModal() {
    document.getElementById('signupModal').style.display = 'none';
}

// API Binding
$(document).ready(function() {
    $("#signupForm").submit(function(event) {
        event.preventDefault();

        let userData = {
            firstName: $("#firstName").val(),
            lastName: $("#lastName").val(),
            email: $("#email").val(),
            mobileNumber: $("#mobileNumber").val(),
            password: $("#password").val()
        };

        if ($("#password").val() !== $("#confirmPassword").val()) {
            alert("Passwords do not match!");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/signup",
            contentType: "application/json",
            data: JSON.stringify(userData),
            success: function(response) {
                alert(response.message);
                closeSignupModal();
            },
            error: function(error) {
                alert("Signup failed: " + error.responseJSON.message);
            }
        });
    });
});
</script>

</body>
</html>

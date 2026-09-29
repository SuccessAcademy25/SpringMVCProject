<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>My E-Commerce Application</title>

<style>
/* =========================================
           #MAIN NAVIGATION BAR
========================================= */
.tabs {
	display: flex;
	width: 100%;
	height: 90px;
	background-color: #F58727;
	align-items: stretch;
}

/* =========================================
           NAVIGATION TAB
========================================= */
.tab {
	display: flex;
	align-items: center;
	padding: 0 35px;
	color: white;
	background-color: #F52776;
	cursor: pointer;
	font-size: 25px;
	font-family: Arial, sans-serif;
	white-space: nowrap;
}

.tab:hover {
	background-color: #555555;
}

.tab.active {
	background-color: #007bff;
}

/* =========================================
           CONTACT DETAILS
========================================= */
.contact-details {
	margin-left: auto;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: flex-end;
	padding: 0 30px;
	color: white;
	font-family: Arial, sans-serif;
	white-space: nowrap;
}

.contact-title {
	font-size: 18px;
	font-weight: bold;
	margin-bottom: 5px;
}

.contact-info {
	font-size: 14px;
	line-height: 20px;
}

/* =========================================
           CONTENT AREA
========================================= */
#content {
	border: 1px solid #04662A;
	padding: 30px;
	margin-top: 20px;
	min-height: 300px;
	font-family: Arial, sans-serif;
	background-color: #94F26D;
}

#content h1 {
	font-size: 38px;
}

#content p {
	font-size: 20px;
}
/* =========================================
   FOOTER STYLES
   ========================================= */
footer {
	background-color: #851E07;
	color: #ffffff;
	text-align: center;
	padding: 25px 0;
	margin-top: 30px;
	font-family: Arial, sans-serif;
	font-size: 14px;
}

/* =========================================
           SEARCH PAGE & TABLE
 ========================================= */
.search-container {
	width: 100%;
}

.search-title {
	text-align: center;
	color: #333333;
}

.search-form {
	width: 100%;
	text-align: center;
	margin-bottom: 30px;
}

.search-form table {
	margin-left: auto;
	margin-right: auto;
}

.search-form input[type="text"] {
	font-size: 18px;
	padding: 6px;
	color: red;
}

.search-form input[type="submit"] {
	font-size: 18px;
	padding: 7px 20px;
	color: white;
	background-color: #007bff;
	border: none;
	cursor: pointer;
}

.customer-table {
	border-collapse: collapse;
	width: 100%;
}

.customer-table th {
	background-color: #850731;
	color: white;
	padding: 10px;
}

.customer-table td {
	padding: 8px;
	text-align: center;
	border: 1px solid #F5140C;
	
}

.customer-table tr:nth-child(even) {
	background-color: #f2f2f2;
}

.action-link {
	color: #F50C20;
	font-weight: bold;
	text-decoration: none;
	cursor: pointer;
}

.action-link:hover {
	text-decoration: underline;
}

.no-data {
	text-align: center;
	padding: 20px;
	font-weight: bold;
}

/* =========================================
           MODAL DIALOG STYLES
========================================= */
.modal {
	display: none;
	position: fixed;
	z-index: 1000;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	background-color: #0CE5F5;
	margin: 5% auto;
	padding: 25px;
	border: 1px solid #888;
	width: 450px;
	border-radius: 8px;
	font-family: Arial, sans-serif;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.modal-content h2 {
	text-align: center;
	margin-top: 0;
	color: #333;
}

.modal-content table {
	width: 100%;
}

.modal-content td {
	padding: 6px;
}

.modal-content input[type="text"], .modal-content input[type="number"],
	.modal-content input[type="date"] {
	width: 95%;
	padding: 6px;
	font-size: 14px;
}

.modal-actions {
	text-align: center;
	margin-top: 15px;
}

.btn-save {
	background-color: #28a745;
	color: white;
	border: none;
	padding: 8px 16px;
	cursor: pointer;
	font-size: 16px;
}

.btn-cancel {
	background-color: #dc3545;
	color: white;
	border: none;
	padding: 8px 16px;
	cursor: pointer;
	font-size: 16px;
	margin-left: 10px;
}

.btn-delete {
    background-color: #dc3545; /* Red background */
    color: #ffffff;
    border: none;
    padding: 6px 12px;
    border-radius: 4px;
    cursor: pointer;
    font-weight: bold;
}

.btn-delete:hover {
    background-color: #c82333; /* Darker red on hover */
}
</style>

<script>
        /* =========================================
           SHOW CONTENT
           ========================================= */
        function showContent(page, clickedTab) {
            var tabs = document.getElementsByClassName("tab");
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove("active");
            }

            if (clickedTab) {
                clickedTab.classList.add("active");
            }

            fetch(page)
                .then(function(response) {
                    if (!response.ok) throw new Error("HTTP Error : " + response.status);
                    return response.text();
                })
                .then(function(data) {
                    document.getElementById("content").innerHTML = data;
                })
                .catch(function(error) {
                    console.error(error);
                    document.getElementById("content").innerHTML = "<h2>Error loading page !!!</h2><p>" + error.message + "</p>";
                });
        }

        /* =========================================
           SEARCH CUSTOMERS VIA AJAX
           ========================================= */
        function searchCustomers(event, form) {
            event.preventDefault();
            var id = form.id.value;
            var email = form.email.value;
            var phone = form.phone.value;

            var queryString = "id=" + encodeURIComponent(id) +
                              "&email=" + encodeURIComponent(email) +
                              "&phone=" + encodeURIComponent(phone);

            fetch("searchCustomers.htm?" + queryString)
                .then(function(response) {
                    if (!response.ok) throw new Error("HTTP Error : " + response.status);
                    return response.text();
                })
                .then(function(data) {
                    document.getElementById("content").innerHTML = data;
                })
                .catch(function(error) {
                    console.error(error);
                    document.getElementById("content").innerHTML = "<h2>Error searching customers</h2><p>" + error.message + "</p>";
                });
        }

        /* =========================================
           DELETE CUSTOMER VIA AJAX
           ========================================= */
        function deleteCustomer(custId) {
            if (confirm("Are you sure you want to delete customer ID: " + custId + "?")) {
                fetch("deleteCustomers.htm?CustId=" + encodeURIComponent(custId))
                    .then(function(response) {
                        if (!response.ok) throw new Error("HTTP Error : " + response.status);
                        return response.text();
                    })
                    .then(function(data) {
                        document.getElementById("content").innerHTML = data;
                    })
                    .catch(function(error) {
                        console.error(error);
                        alert("Failed to delete customer: " + error.message);
                    });
            }
        }

        /* =========================================
           UPDATE MODAL CONTROLS
           ========================================= */
        function openUpdateModal(id, name, email, phone, city, salary, gender, date) {
            document.getElementById("updateCustId").value = id;
            document.getElementById("updateCustName").value = name;
            document.getElementById("updateCustEmail").value = email;
            document.getElementById("updatePhone").value = phone;
            document.getElementById("updateCustCity").value = city;
            document.getElementById("updateCustSalary").value = salary;
            document.getElementById("updateGender").value = gender;
            document.getElementById("updateDate").value = date;

            document.getElementById("updateModal").style.display = "block";
        }

        function closeModal() {
            document.getElementById("updateModal").style.display = "none";
        }

        /* =========================================
           UPDATE CUSTOMER VIA AJAX
           ========================================= */
        function updateCustomer(event) {
            event.preventDefault();

            var form = document.getElementById("updateForm");
            var formData = new URLSearchParams(new FormData(form));

            fetch("updateCustomers.htm", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: formData.toString()
            })
            .then(function(response) {
                if (!response.ok) throw new Error("HTTP Error : " + response.status);
                return response.text();
            })
            .then(function(data) {
                closeModal();
                document.getElementById("content").innerHTML = data;
            })
            .catch(function(error) {
                console.error(error);
                alert("Failed to update customer: " + error.message);
            });
        }

        /* =========================================
           HOME
           ========================================= */
        function showHome(clickedTab) {
            var tabs = document.getElementsByClassName("tab");
            for (var i = 0; i < tabs.length; i++) {
                tabs[i].classList.remove("active");
            }
            clickedTab.classList.add("active");

            document.getElementById("content").innerHTML =
                "<h1>Welcome to My E-Commerce Application</h1><p>Select a tab above to perform any action</p>";
        }
        
        
        /* =========================================
        SUBMIT REGISTRATION VIA AJAX
        ========================================= */
     function submitRegistration(form) {
         var formData = new URLSearchParams(new FormData(form));

         fetch("addCustomers.jsa", {
             method: "POST",
             headers: { "Content-Type": "application/x-www-form-urlencoded" },
             body: formData.toString()
         })
         .then(function(response) {
             if (!response.ok) throw new Error("HTTP Error: " + response.status);
             return response.text();
         })
         .then(function(data) {
             // Highlight MyService tab (index 4)
             var tabs = document.getElementsByClassName("tab");
             for (var i = 0; i < tabs.length; i++) {
                 tabs[i].classList.remove("active");
             }
             if (tabs[4]) {
                 tabs[4].classList.add("active");
             }

             // Render response inside content area
             document.getElementById("content").innerHTML = data;
         })
         .catch(function(error) {
             console.error(error);
             var msgElement = document.getElementById("registerMsg");
             if (msgElement) {
                 msgElement.innerHTML = "Registration failed: " + error.message;
             } else {
                 alert("Registration failed: " + error.message);
             }
         });
     }
        
     /* =========================================
     SUBMIT LOGIN VIA AJAX
     ========================================= */
  function submitLogin(form) {
      var formData = new URLSearchParams(new FormData(form));

      fetch("loginCustomers.jsa", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: formData.toString()
      })
      .then(function(response) {
          if (!response.ok) throw new Error("HTTP Error: " + response.status);
          return response.text();
      })
      .then(function(data) {
          // If login succeeded, navigate to MyService/Search tab (Index 4) and display customer list
          var tabs = document.getElementsByClassName("tab");
          for (var i = 0; i < tabs.length; i++) {
              tabs[i].classList.remove("active");
          }
          if (tabs[4]) {
              tabs[4].classList.add("active");
          }

          document.getElementById("content").innerHTML = data;
      })
      .catch(function(error) {
          console.error(error);
          var msgElement = document.getElementById("loginMsg");
          if (msgElement) {
              msgElement.innerHTML = "Login failed: Invalid Credentials or Error occurred.";
          } else {
              alert("Login failed: " + error.message);
          }
      });
  }
        
    </script>

</head>

<body>

	<div class="tabs">
		<div class="tab active" onclick="showHome(this)">Home</div>
		<div class="tab" onclick="showContent('register.jsa', this)">Register</div>
		<div class="tab" onclick="showContent('login.jsa', this)">Login</div>
		<div class="tab" onclick="showContent('logout.jsa', this)">Logout</div>
		<div class="tab" onclick="showContent('search.jsa', this)">MyService</div>

		<div class="contact-details">
			<div class="contact-title">Contact Us</div>
			<div class="contact-info">📞 +91 7070493663</div>
			<div class="contact-info">✉ successacademybangluru@gmail.com</div>
		</div>
	</div>

	<div id="content">
		<h1>Welcome to My E-Commerce Application</h1>
		<p>Select a tab above.</p>
	</div>

	<!-- =========================================
         UPDATE CUSTOMER MODAL
         ========================================= -->
	<div id="updateModal" class="modal">
		<div class="modal-content">
			<h2>Update Customer Details</h2>
			<form id="updateForm" onsubmit="updateCustomer(event)">
				<table>
					<tr>
						<td><strong>ID:</strong></td>
						<td><input type="text" id="updateCustId" name="custId"
							readonly style="background-color: #eee;"></td>
					</tr>
					<tr>
						<td>Name:</td>
						<td><input type="text" id="updateCustName" name="custName"
							required></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" id="updateCustEmail" name="custEmail"
							required></td>
					</tr>
					<tr>
						<td>Phone:</td>
						<td><input type="text" id="updatePhone" name="phone"
							maxlength="10" required></td>
					</tr>
					<tr>
						<td>City:</td>
						<td><input type="text" id="updateCustCity" name="custCity"></td>
					</tr>
					<tr>
						<td>Salary:</td>
						<td><input type="number" id="updateCustSalary"
							name="custsalary" step="0.01"></td>
					</tr>
					<tr>
						<td>Gender:</td>
						<td><select id="updateGender" name="gender"
							style="width: 100%; padding: 6px;">
								<option value="Male">Male</option>
								<option value="Female">Female</option>
						</select></td>
					</tr>
					<tr>
						<td>DOB:</td>
						<td><input type="date" id="updateDate" name="date"></td>
					</tr>
				</table>
				<div class="modal-actions">
					<button type="submit" class="btn-save">Save Changes</button>
					<button type="button" class="btn-cancel" onclick="closeModal()">Cancel</button>
				</div>
			</form>
		</div>
	</div>
	<!-- =========================================
         FOOTER
         ========================================= -->
	<footer><h2>JAVA SUCCESS ACADEMY</h2> &copy; All Rights Reserved </footer>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Account Created Confirmation</title>
    <style>
      body {
        font-family: "Arial", sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f8f8f8;
        color: #263248;
      }

      .container {
        max-width: 600px;
        margin: 20px auto;
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 20px rgba(38, 50, 72, 0.1);
      }

      h1 {
        color: #3498db;
        margin-bottom: 20px;
      }

      p {
        line-height: 1.6;
        margin-bottom: 20px;
      }

      .company-logo {
        max-width: 100px;
        margin-bottom: 20px;
        display: block;
        margin-left: auto;
        margin-right: auto;
      }

      table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
      }

      table,
      th,
      td {
        border: 1px solid #ddd;
      }

      th,
      td {
        padding: 12px;
        text-align: left;
      }

      .footer {
        margin-top: 20px;
        padding-top: 20px;
        border-top: 1px solid #ddd;
        text-align: center;
        color: #888;
        font-size: 14px;
      }
      h1 {
        color: rgb(38, 50, 72);
        font-weight: lighter;
      }
      strong {
        color: #f8981c;
      }
      a {
        text-decoration: none;
        color: black;
      }
      a:hover {
        color: #f8981c;
      }
    </style>
  </head>

  <body>
    <div class="container">
      <h1>Welcome to TES<strong>TY</strong>ANTRA</h1>
      <p>
        Your account has been successfully created. Here are your account
        details:
      </p>

      <table>
        <tr>
          <td><strong>Username:</strong></td>
          <td>${userName}</td>
        </tr>
        <tr>
          <td><strong>Email:</strong></td>
          <td>${userEmail}</td>
        </tr>
        <tr>
          <td><strong>Password:</strong></td>
          <td>${userPassword}</td>
        </tr>
      </table>
      <p>
        You can reset this password by clicking on the forgot password link.
      </p>
      <a href="#">AlphaAttendance.com</a>
      <p>
        Thank you for choosing TES<strong>TY</strong>ANTRA. If you have any questions, feel free
        to contact our support team.
      </p>

      <div class="footer">
        <p>
          TES<strong>TY</strong>ANTRA | #88, 3rd Floor, Brigade Chambers, Gandhi Bazaar Main Rd,
          Basavanagudi, Bangalore-560004 Karnataka, India |
          contactus@testyantra.com
        </p>
      </div>
    </div>
  </body>
</html>

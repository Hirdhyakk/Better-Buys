/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

  function turnONConfirmPass()
  {
    var x = document.getElementById("confirm-password-field");
    var y = document.getElementById("toggle-confirm-password");
    if (x.type === "password") 
    {
      y.className="fa fa-eye";
      x.type = "text";
    } 
    else 
    {
      y.className="fa fa-eye-slash";
      x.type = "password";
    }
  } 
  function turnONPass()
  {
    var x = document.getElementById("new-password-field");
    var y = document.getElementById("toggle-new-password");
    if (x.type === "password") 
    {
      y.className="fa fa-eye";
      x.type = "text";
    } 
    else 
    {
      y.className="fa fa-eye-slash";
      x.type = "password";
    }
  } 
  function onlyNumberKey(evt) 
  {
    // Only ASCII character in that range allowed
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 31 && (ASCIICode < 48 || ASCIICode > 57))
      return false;
    else
      return true;
  }
  function onlyAlphaKey(evt) 
  {
    // Only ASCII character in that range allowed
    var ASCIICode = (evt.which) ? evt.which : evt.keyCode
    if (ASCIICode > 32 && (ASCIICode < 65 || ASCIICode > 90)&&(ASCIICode < 97 || ASCIICode > 122) )
      return false;
    else
      return true;
  }

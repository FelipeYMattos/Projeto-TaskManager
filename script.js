
document.getElementById("loginForm").addEventListener("submit", function(e) {
  e.preventDefault();

  const user = document.getElementById("username").value;
  const senha = document.getElementById("password").value;

  if (user === "testeAdmin" && senha === "123") {
    alert("Login como ADMIN bem-sucedido!");
    window.open("dashboardAdmin.html", "_self", "Página Admin.");
  } 
  else if (user === "testeUser" && senha === "123") {
    alert("Login como USER bem-sucedido!");
    window.open("dashboardUser.html", "_seft", "Página Usuário.");
  } 
  else {
    alert("Usuário ou senha incorretos!");
  }
});



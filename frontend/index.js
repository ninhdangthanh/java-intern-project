var logoutBtn = document.querySelector(".content_left_item_logout");
logoutBtn.addEventListener("click", function () {
  localStorage.clear();
  window.location.href = "login.html";
});

const intern_refresh_token = localStorage.getItem("intern_refresh_token");
if (intern_refresh_token) {
  console.log("Login");
} else {
  console.log("Not login");
  window.location.href = "login.html";
}

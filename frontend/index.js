var logoutBtn = document.querySelector(".content_left_item_logout");
logoutBtn.addEventListener("click", function () {
  localStorage.clear();
  window.location.href = "login.html";
});

const intern_access_token_login = sessionStorage.getItem("intern_access_token");
const intern_refresh_token_login = localStorage.getItem("intern_refresh_token");
if (intern_access_token_login && intern_refresh_token_login) {
  console.log("Login");
} else {
  console.log("Not login");
  window.location.href = "login.html";
}

var all_product_field = document.querySelector(
  "[role_field=all_product_field]"
);
var sort_field = document.querySelector("[role_field=sort_field]");
var my_product_field = document.querySelector("[role_field=my_product_field]");
var list_user_field = document.querySelector("[role_field=list_user_field]");
var account_field = document.querySelector("[role_field=account_field]");

const intern_user_role = localStorage.getItem("intern_user_role");
if (intern_user_role == "ROLE_ADMIN") {
  all_product_field.style.display = "none";
  sort_field.style.display = "none";
  my_product_field.style.display = "none";
} else if (intern_user_role == "ROLE_SALESMAN") {
  sort_field.style.display = "none";
  list_user_field.style.display = "none";
} else if (intern_user_role == "ROLE_USER") {
  my_product_field.style.display = "none";
  list_user_field.style.display = "none";
}

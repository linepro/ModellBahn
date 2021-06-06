const register = async () => {
  let messages = document.getElementById("messages");
  let formData = document.getElementById("registration").formData;
 
  await fetch(window.location.origin.replace(/\/$/, "/api/register"), {
    method: "POST",
    headers : {
      "accept": "application/json, text/html, application/xhtml+xml, application/xml",
      "accept-Encoding": "gzip, deflate, br",
      "accept-Language": sessionStorage.getItem("language") ? sessionStorage.getItem("language") : navigator.language,
      "cache-Control": "no-cache",
      "content-Type": "application/json"
      },
      body: JSON.stringify(Object.fromEntries(formData))
    })
    .then( response => response.json(), reject => new { message: reject.message })
    .then(json => messages.value = json.message)
    .catch(err => messages.value = err.message);
};

const equalityCheck = (element1, element2, message) => {
  let value = document.getElementById(element1);
  let confirm = document.getElementById(element2);
  confirm.setCustomValidity(confirm.value === value.value ?"" : message);
};

const toggle = (element, toggle) => {
  let icon = document.getElementById(toggle);
  let field = document.getElementById(element);
  if (icon.classList.contains("fa-eye")) {
    icon.classList.replace("fa-eye","fa-eye-slash");
    field.type = "text";
  } else {
    icon.classList.replace("fa-eye-slash","fa-eye");
    field.type = "password";
  }
};
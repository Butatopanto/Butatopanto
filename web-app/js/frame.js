function reveal(character) {
  setElementText('character', character)
  setOnClickToOpenTrain('character');
  setElementVisibility('number', 'visible')
}

function setElementText(name, text) {
  var element = document.getElementById(name);
  element.innerHTML = text;
}

function setOnClickToOpenTrain(name, text) {
  var element = document.getElementById(name);
  element.onclick = new Function("next('train')");
}

function next(nextUrl) {
  open(nextUrl, "_self")
}
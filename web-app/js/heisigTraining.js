function reveal(character) {
    var card = document.getElementById('character');
    card.innerHTML = character;
    card.onclick = new Function("next('show')");
}

function next(nextUrl) {
   open(nextUrl,"_self")
}
function reveal(character) {
    var card = document.getElementById('character');
    card.innerHTML = character;
    card.onclick = new Function("next('train')");
}

function next(nextUrl) {
   open(nextUrl,"_self")
}
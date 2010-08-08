function reveal(character) {
    var button = document.getElementById('character');
    button.innerHTML = character;
    button.onclick = new Function("next('show')");
}
function next(nextUrl) {
   open(nextUrl,"_self")
}
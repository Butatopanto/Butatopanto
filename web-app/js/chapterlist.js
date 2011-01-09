
function openStoryDialog(html, title) {

var win = new Window({className: "story",  width:300, height:300, zIndex: 100,  title: title, showEffect:Effect.BlindDown, hideEffect: Effect.SwitchOff, draggable:true, wiredDrag: true})

win.getContent().innerHTML= "<div style='padding:10px'> Hallo.</div>"
win.showCenter();
}

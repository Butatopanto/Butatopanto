function openStoryDialog(html, title, url) {
    var win = new Window({className: "story", url: url,  width: 215, height: 270, zIndex: 100,  title: title, draggable:true, wiredDrag: true})
    win.showCenter(true);
}

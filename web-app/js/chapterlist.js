function openStoryDialog(html, title, url) {
    var win = new Window({className: "story", url: url,  width: 230, height: 290, zIndex: 100,  title: title, draggable:true, wiredDrag: true})
    win.showCenter(true);
}

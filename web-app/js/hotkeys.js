function click(id) {
    var element = document.getElementById(id);
    element.simulate('click');
}

function registerHotkey(key, element) {
    var withoutControlKey = {
        ctrlKey: false
    };
    new HotKey(key, function(event) {
        click(element);
    }, withoutControlKey);
}
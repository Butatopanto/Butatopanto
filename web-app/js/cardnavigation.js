function click(id) {
    var element = document.getElementById(id)
    element.simulate('click');
}
document.observe('dom:loaded', function() {
    var withoutControlKey = {
        ctrlKey: false
    };
    new HotKey(' ', function(event) {
        click('card');
    }, withoutControlKey);
    new HotKey(confirmKey, function(event) {
        click('confirmButton');
    }, withoutControlKey);
    new HotKey(declineKey, function(event) {
        click('declineButton');
    }, withoutControlKey);
});
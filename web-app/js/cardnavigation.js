document.observe('dom:loaded', function() {
    new HotKey(' ', function(event) {
        var element = document.getElementById('card')
        element.simulate('click');
    }, {
        ctrlKey: false
    });
    new HotKey(confirmKey, function(event) {
        var element = document.getElementById('confirmButton')
        element.simulate('click');
    }, {
        ctrlKey: false
    });
    new HotKey(declineKey, function(event) {
        var element = document.getElementById('declineButton')
        element.simulate('click');
    }, {
        ctrlKey: false
    });
});
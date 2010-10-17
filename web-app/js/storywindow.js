document.observe('dom:loaded', function() {
    var story = new Control.Modal($('story'), {
    position: 'relative', 
    offsetTop: 20,
    offsetLeft: 0,
    width: 215,
        overlayOpacity: 0.75,
        className: 'storyWindow',
        fade: true
    });
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
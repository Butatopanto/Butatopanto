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
    new HotKey('y', function(event) {
        var element = document.getElementById('yesButton')
        element.simulate('click');
    }, {
        ctrlKey: false
    });
    new HotKey('n', function(event) {
        var element = document.getElementById('noButton')
        element.simulate('click');
    }, {
        ctrlKey: false
    });
});
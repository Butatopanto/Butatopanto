document.observe('dom:loaded', function() {
    var story = new Control.Modal($('story'), {
        position: 'relative',
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
});
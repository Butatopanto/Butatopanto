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
});
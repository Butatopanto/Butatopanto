document.observe('dom:loaded', function() {
  var story = new Control.Modal($('story'), {
    overlayOpacity: 0.75,
    className: 'storyWindow',
    fade: true
  });
});
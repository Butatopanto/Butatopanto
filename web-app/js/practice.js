var storyWin;

function openStoryDialog(html, url) {
  storyWin = new Window({title: "Geschichte", url: url, className:"story", parent:$('showStory'), draggable:false})
  storyWin.setDestroyOnClose();
  storyWin.setConstraint(true, {top:0, left: 0, right:0, bottom:0})
  storyWin.show();
  storyWin.maximize()
}

function closeStoryDialog() {
  if (storyWin != null) {
    storyWin.close()
  }
}
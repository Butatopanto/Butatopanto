function startEditStory() {
  getStoryEdit().style.display = "block";
  getStoryView().style.display = "none";
}

function cancelEditStory() {
  getStoryEdit().style.display = "none";
  getStoryView().style.display = "block";
}

function getStoryView() {
  return document.getElementById("storyView");
}

function getStoryEdit() {
  return document.getElementById("storyEdit");
}
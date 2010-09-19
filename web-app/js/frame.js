function prepareForEntry(nullText) {
    getSaveButton().style.display = "block";
    removePlaceholder(nullText);
}

function finishEntry(nullText) {
    getSaveButton().style.display = "none";
    insertPlaceholder(nullText);
}

function getSaveButton() {
    return document.getElementById("save");
}

function removePlaceholder(nullText) {
    var story = getStoryTextArea();
    if (story.value == nullText) {
        story.value = "";
    }
}

function insertPlaceholder(nullText) {
    var story = getStoryTextArea();
    if (story.value == "") {
        story.value = nullText;
    }
}

function getStoryTextArea() {
    return document.getElementById("story");
}
document.observe('dom:loaded', function() {
    registerHotkey(' ', 'card');
    registerHotkey(confirmKey, 'confirmButton');
    registerHotkey(declineKey, 'declineButton');
});
function onOptionClick(textTarget, textValue, valueTarget, value) {
    document.getElementById(textTarget).innerText = textValue;
    document.getElementById(valueTarget).value = value;
}
//--------------------------------------------------------------------------
$(document).ready(function () {
    $("#advBtn").click(function () {
        $("#advModal").modal();
    });
});
//--------------------------------------------------------------------------
$(document).ready(function () {
    $('[data-toggle="popover"]').popover();
});
//--------------------------------------------------------------------------

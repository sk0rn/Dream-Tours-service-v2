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
function clearFindToursForm() {
    document.getElementById("searchSubject").value = -1;
    document.getElementById("searchPlace").value = -1;
    document.getElementById("searchDuration").value = -1;
    document.getElementById("searchInWishList").value = 0;
    document.getElementById("searchDateBegin").value = "";
    document.getElementById("searchDateEnd").value = "";
    document.getElementById("searchCostBegin").value = "";
    document.getElementById("searchCostEnd").value = "";
    document.getElementById("searchDurationBegin").value = "";
    document.getElementById("searchDurationEnd").value = "";
}

//--------------------------------------------------------------------------
function submitSearchToursByItem(itemName, itemValue) {
    clearFindToursForm();
    document.getElementById(itemName).value = itemValue;
    document.getElementById("searchToursForm").submit();
}

//--------------------------------------------------------------------------

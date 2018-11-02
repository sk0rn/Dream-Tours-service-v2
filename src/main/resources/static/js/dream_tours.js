//--------------------------------------------------------------------------
//Копирование значений из временной формы в форму поиска и обратно
function val2val(idName, direction) {
    $("#search" + idName + (direction ? "" : "View")).val($("#search" + idName + (direction ? "View" : "")).val());
}

//--------------------------------------------------------------------------
//Копирование расширенных значений поиска из временной формы в форму поиска и обратно
function form2form(toForm) {
    val2val("DateBegin", toForm);
    val2val("DateEnd", toForm);
    val2val("CostBegin", toForm);
    val2val("CostEnd", toForm);
    val2val("DurationBegin", toForm);
    val2val("DurationEnd", toForm);
}

//--------------------------------------------------------------------------
//Опустошить форму поиска туров
function clearFindToursForm() {
    $("#searchSubject").val(-1);
    $("#searchPlace").val(-1);
    $("#searchDuration").val(-1);
    $("#searchInWishList").val(0);
    $("#searchDateBegin").val("");
    $("#searchDateEnd").val("");
    $("#searchCostBegin").val("");
    $("#searchCostEnd").val("");
    $("#searchDurationBegin").val("");
    $("#searchDurationEnd").val("");
}

//--------------------------------------------------------------------------
$(document).ready(function () {

    //Показать окно дополнительных параметров
    //поиска туров
    $("#advBtn").click(function () {
        form2form(false);
        $("#advModal").modal("show");
    });

    //закрыть окно дополнительных параметров
    //поиска туров
    $("#advOkButton").click(function () {
        form2form(true);
        updateExtBadge();
        $("#advModal").modal("hide");
    });

    //показать всплывающее сообщение
    $('[data-toggle="popover"]').popover();

    //поиск тура по одному параметру
    //эта лабуда нужна
    //для отправки данных методом POST
    $(".form-submit-link").click(function () {
        clearFindToursForm();
        $($(this).attr("submit-param")).val($(this).attr("submit-value"));
        $("#searchToursForm").submit();
    });

    //клик по выпадающим спискам
    //показывает в меню строку
    //а в форму заносит значение
    $(".menu-item-click").click(function () {
        $($(this).attr("menu-id")).text($(this).attr("menu-value"));
        $($(this).attr("target-id")).val($(this).attr("target-value"));
    });

    //Добавление/удаление тура в/из
    //вишлист (избранное)
    $(".a-wishList").click(function (e) {
        var tourId = $(this).attr("tour-id");
        var tagImg = $("#wl-" + tourId);
        var formData = {idTour: tourId, operation: tagImg.hasClass("not-in-wish-list") ? 1 : 0};
        formData[csrfParamName] = csrfParamValue;

        e.preventDefault();

        jQuery.ajax({
            method: "POST",
            url: "/addToWishlist",
            data: formData
        }).done(function (msg) {
            switch (msg.trim()) {
                case "add":
                    tagImg.removeClass("not-in-wish-list");
                    break;

                case "remove":
                    tagImg.addClass("not-in-wish-list");
                    break;

                case "error":
                    alert("При попытке добавить тур в WishList произошла ошибка. Мы уже работаем над этой проблемой. Попробуйте повторить попытку позже.");
                    break;

                default:
                    alert("При попытке добавить тур в WishList произошла непредвиденная ситуация, результат операции не определён. Попробуйте повторить попытку позже.");
                    break;
            }
        });
    });


    $(".RESTpay").submit(function (e) {
        e.preventDefault();

        jQuery.ajax({
            method: "POST",
            url: "https://restfordreamtours.herokuapp.com/",
            contentType: "application/json",
            data: JSON.stringify({tour: $("#tour").val(), cardNumber: $("#cardNumber").val(), cost: $("#cost").val()})
        }).done(function (msg) {
            alert(JSON.stringify(msg));
        });
    });

    restoreSearchView();
});

//--------------------------------------------------------------------------
function updateExtBadge() {
    var paramsSetted = 0;
    if (hasValue($("#searchDateBegin").val())) ++paramsSetted;
    if (hasValue($("#searchDateEnd").val())) ++paramsSetted;
    if (hasValue($("#searchCostBegin").val())) ++paramsSetted;
    if (hasValue($("#searchCostEnd").val())) ++paramsSetted;
    if (hasValue($("#searchDurationBegin").val())) ++paramsSetted;
    if (hasValue($("#searchDurationEnd").val())) ++paramsSetted;

    if (paramsSetted) {
        $("#ExtBadge").text(paramsSetted);
        $("#ExtBadge").show();
    } else $("#ExtBadge").hide();
}

//--------------------------------------------------------------------------
function hasValue(val) {
    return val != "" && val != "0";
}

//--------------------------------------------------------------------------
function restoreSearchView() {
//Закидываем данные доп условий поиска во временную форму и обновляем бадж
    form2form(false);
    updateExtBadge();

//Обновляем данные в меню по выпадающим спискам
    $("[target-id = '#searchSubject'][target-value = " + $("#searchSubject").val() + "]").click();
    $("[target-id = '#searchPlace'][target-value = " + $("#searchPlace").val() + "]").click();
    $("[target-id = '#searchDuration'][target-value = " + $("#searchDuration").val() + "]").click();

//Обновляем чекбокс Вишлист в меню
    $("#searchInWishListView").attr("checked", $("#searchInWishList").val() === "1");
}

//--------------------------------------------------------------------------

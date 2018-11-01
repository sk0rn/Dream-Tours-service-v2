//--------------------------------------------------------------------------
function val2val(idName, toForm) {
    $("#search" + idName + (toForm ? "" : "View")).val($("#search" + idName + (toForm ? "View" : "")).val());
}

//--------------------------------------------------------------------------
function form2form(toForm) {
    val2val("DateBegin", toForm);
    val2val("DateEnd", toForm);
    val2val("CostBegin", toForm);
    val2val("CostEnd", toForm);
    val2val("DurationBegin", toForm);
    val2val("DurationEnd", toForm);
}

//--------------------------------------------------------------------------
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
    $(".a-wishList").click(function () {
        var tourId = $(this).attr("tour-id");
        var tagImg = $("#wl-" + tourId);
        var formData = {idTour: tourId, operation: tagImg.hasClass("not-in-wish-list") ? 1 : 0};
        formData[csrfParamName] = csrfParamValue;

        jQuery.ajax({
            method: "POST",
            url: "/addInWishlist",
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
});
//--------------------------------------------------------------------------

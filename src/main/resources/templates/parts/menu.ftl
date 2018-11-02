<#ftl encoding="utf-8">
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="dropdown navbar-brand">
        <#if roles?? && roles?seq_contains("ROLE_USER")>
            <#include "clientMenu.ftl">
        <#elseif roles?? && roles?seq_contains("ROLE_ADMIN")>
            <#include "employeeMenu.ftl">
        <#else>
            <#include "guestMenu.ftl">
        </#if>
    </div>

    <!-- эта кнопка будет видна, когда для меню не хватит места -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/tours">Все туры<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="subjectDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Тематика</a>
                <div class="dropdown-menu" aria-labelledby="subjectDropdown">
                    <a class='dropdown-item menu-item-click' href='#'
                       menu-id="#subjectDropdown"
                       menu-value="Тематика"
                       target-id="#searchSubject"
                       target-value="-1"
                    >Любая тематика</a>
                    <div class="dropdown-divider"></div>
                    <#list subjects![] as subj>
                        <a class='dropdown-item menu-item-click' href='#'
                           menu-id="#subjectDropdown"
                           menu-value="${subj.getName()}"
                           target-id="#searchSubject"
                           target-value="${subj.getId()}">
                            ${subj.getName()}</a>
                    </#list>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="placeDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Места</a>
                <div class="dropdown-menu" aria-labelledby="placeDropdown">
                    <a class='dropdown-item menu-item-click' href='#'
                       menu-id="#placeDropdown"
                       menu-value="Места"
                       target-id="#searchPlace"
                       target-value="-1">Любое место</a>
                    <div class="dropdown-divider"></div>
                    <#list places![] as place>
                        <a class='dropdown-item menu-item-click' href='#'
                           menu-id="#placeDropdown"
                           menu-value="${place.getName()}"
                           target-id="#searchPlace"
                           target-value="${place.getId()}"
                        >${place.getName()}</a>
                    </#list>
                </div>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="durationDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">Длительность</a>
                <div class="dropdown-menu" aria-labelledby="durationDropdown">
                    <a class='dropdown-item menu-item-click' href='#'
                       menu-id="#durationDropdown"
                       menu-value="Длительность"
                       target-id="#searchDuration"
                       target-value="-1">Любая&nbsp;длительность</a>
                    <div class="dropdown-divider"></div>
                    <#list durations![] as dur>
                        <a class='dropdown-item menu-item-click' href='#'
                           menu-id="#durationDropdown"
                           menu-value="${dur.getName()}"
                           target-id="#searchDuration"
                           target-value="${dur.getId()}"
                        >${dur.getName()}</a>
                    </#list>
                </div>
            </li>
<#if roles?? && roles?seq_contains("ROLE_USER")>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="searchInWishListView"
                               onchange="document.getElementById('searchInWishList').value = this.checked ? 1 : 0;">
                        <label class="form-check-label" for="searchInWishListView">
                            В избранном
                        </label>
                    </div>
                    <span class="sr-only">(current)</span></a>
            </li>
</#if>
            <li class="nav-item">
                <a class="nav-link" href="#" id="advBtn">Дополнительно
                    <span class="badge badge-warning" style="display: none;" id="ExtBadge"></span>
                    <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>
    <form class="form-inline my-2 my-lg-0" action="/tours" method="post" id="searchToursForm" name="searchToursForm">
        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
        <input type="hidden" id="searchSubject" name="subjectId" value="${(subjectIdValue)!}">
        <input type="hidden" id="searchPlace" name="placeId" value="${(placeIdValue)!}">
        <input type="hidden" id="searchDuration" name="durationId" value="${(durationIdValue)!}">
        <input type="hidden" id="searchInWishList" name="inWishList" value="${(inWishListValue)!}">
        <input type="hidden" id="searchDateBegin" name="DateBegin" value="${(dateBeginValue)!}">
        <input type="hidden" id="searchDateEnd" name="DateEnd" value="${(dateEndValue)!}">
        <input type="hidden" id="searchCostBegin" name="costFrom" value="${(costFromValue)!}">
        <input type="hidden" id="searchCostEnd" name="costTo" value="${(costToValue)!}">
        <input type="hidden" id="searchDurationBegin" name="durationFrom" value="${(durationFromValue)!}">
        <input type="hidden" id="searchDurationEnd" name="durationTo" value="${(durationToValue)!}">

        <input class="form-control mr-sm-2" type="search" placeholder="Фудзи сакура ..." aria-label="Search"
               name="searchString" value="${(searchStringValue)!}">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Найти</button>
    </form>
</nav>


<!-- Modal -->
<div class="modal fade" id="advModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="padding:15px 20px;">
                <h4><span class="glyphicon glyphicon-lock"></span>Дополнительные параметры</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body" style="padding:20px 25px;">
                <form role="form">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="searchDateBeginView">Дата&nbsp;вылета</label>
                            <input type="date" class="form-control" id="searchDateBeginView" placeholder="с"
                                   min="${.now?date?iso_utc}">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="searchDateEndView">&nbsp;</label>
                            <input type="date" class="form-control" id="searchDateEndView" placeholder="по"
                                   min="${.now?date?iso_utc}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="searchCostBeginView">Стоимость</label>
                            <input type="number" class="form-control" id="searchCostBeginView"
                                   placeholder="от" min="0">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="searchCostEndView">&nbsp;</label>
                            <input type="number" class="form-control" id="searchCostEndView" placeholder="до" min="0">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="searchDurationBeginView">Продолжительность</label>
                            <input type="number" class="form-control" id="searchDurationBeginView" placeholder="от"
                                   min="0" max="365">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="searchDurationEndView">&nbsp;</label>
                            <input type="number" class="form-control" id="searchDurationEndView" placeholder="до"
                                   min="0" max="365">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success" id="advOkButton">
                    <span class="glyphicon glyphicon-off"></span> Ок
                </button>
                <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
                    <span class="glyphicon glyphicon-remove"></span> Отмена
                </button>
            </div>
        </div>
    </div>
</div>
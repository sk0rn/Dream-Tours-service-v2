<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
<table>
    <#list tours![] as tour>
        <tr>
            <td class="px-2">
                <a href="/tour/${tour.getId()}">
                    <img class="card-img-top" style="width: 22rem;"
                    <#--TODO доавить поле в entity, проверить вывод картинки-->
                         src="${remoteConnectionHost}${tour.getAlbumGuid().getName()}/01.jpg"
                         alt="${tour.getName()}">
                </a>
            </td>
            <td class="px-2">
                <#if roles?? && roles?seq_contains("ROLE_ADMIN")>
                    <button type="button" class="btn btn-outline-danger"
                            onclick="document.location.href='/admin/content/${tour.getId()}'">
                        Изменить
                    </button>&nbsp;
                <#elseif roles?? && roles?seq_contains("ROLE_USER")>
                    <#assign inWishList=wishList?seq_contains(tour.getId()) />
                        <a href="#" class="a-wishList" tour-id="${(tour.getId())!}">
                            <img src="/static/image/star.ico" id="wl-${tour.getId()}"
                                 width="24" ${inWishList?string("", "class='not-in-wish-list'")}>
                        </a>
                </#if>
                <a href="/tour/${tour.getId()}" class="badge badge-info">${tour.getName()}
                </a><br>
                <nobr>
                    <#list tour.getSubjects() as subj>
                        <a href="#"
                           submit-param="#searchSubject"
                           submit-value="${subj.getId()}"
                           class="form-submit-link badge badge-success"
                           title="${subj.getName()}"
                           data-content="${subj.getDescr()}" data-toggle="popover"
                           data-trigger="hover">
                            ${subj.getName()}
                        </a>
                    </#list>
                </nobr>
                <div class="form-group">
                <#--TODO сделать "затухание" текста-->
                    <div class="form-tours-dream" id="exampleFormControlTextarea1" rows="5"
                         name="tourName"><p class="text-justify">${tour.getDescr()}</p></div>
                    <nobr>
                        <#if roles?? && roles?seq_contains("ROLE_USER")>
                            <button type="button" class="btn-sm btn-success"
                                    onclick="document.location.href='/pay'">
                                Купить
                            </button>&nbsp;
                        </#if>
                        <#list tour.getPlaces() as place>
                            <a href="#"
                               submit-param="#searchPlace"
                               submit-value="${place.getId()}"
                               class="form-submit-link badge badge-secondary"
                               title="${place.getName()}"
                               data-content="${place.getDescr()}" data-toggle="popover"
                               data-trigger="hover">
                                ${place.getName()}
                            </a>
                        </#list>
                    </nobr>
                </div>
            </td>
        </tr>
        <#sep>
        <tr>
            <td colspan="2">
                <hr class="divider">
            </td>
        </tr>
        </#sep>
    <#else>
        <div class="center">
            <a style="color: #4736ff">К сожалению, по Вашему запросу ничего не найдено</a>
        </div>
    </#list>
</table>
<script>
    var csrfParamName = "${_csrf.parameterName}";
    var csrfParamValue = "${_csrf.token}";
</script>
</@i.page>
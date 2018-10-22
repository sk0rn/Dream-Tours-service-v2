<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
<div align="center">
    <#list tours![] as tour>
            <div class="col-12" align="left">
                <div class="form-row">
                    <div class="col-3">
                        <div class="card" style="width: 22rem;">
                            <img class="card-img-top"
                                 <#--TODO доавить поле в entity, проверить вывод картинки-->
                                 src="${remoteConnectionHost}${tour.getAlbumGuid()}/01.jpg"
                                 alt="${tour.getName()}">

                        </div>
                    </div>
                    <div class="col-9">
                        <#if role?? && role == "[ROLE_ADMIN]">
                         <button type="button" class="btn-sm btn-success"
                         <#--TODO Для админской панели не сделан перевод на спринг-->
                                 <#--onclick="document.location.href='/admin/add_content?id=${tour.getTour().getId()}'">-->
                                 onclick="document.location.href='/admin/content/${tour.getId()}'">
                             Изменить
                         </button>&nbsp;
                        </#if>
                        <a href="/tour?id=${tour.getId()}">${tour.getName()}</a><br>
                        <nobr>
                            <#list tour.getSubjects() as subj>
                                <a href="#"
                                   submit-param="#searchSubject"
                                   submit-value="${subj.getId()}"
                                   class="form-submit-link"
                                   title="${subj.getName()}"
                                   data-content="${subj.getDescr()}" data-toggle="popover"
                                   data-trigger="hover">
                                    ${subj.getName()}
                                </a>
                            </#list>
                        </nobr>
                        <div class="form-group">
                        <div class="form-tours-dream" id="exampleFormControlTextarea1" rows="5"
                                  name="tourName">${tour.getDescr()}</div>
                            <nobr>
                            <#list tour.getPlaces() as place>
                                <a href="#"
                                   submit-param="#searchPlace"
                                   submit-value="${place.getId()}"
                                   class="form-submit-link"
                                   title="${place.getName()}"
                                   data-content="${place.getDescr()}" data-toggle="popover"
                                   data-trigger="hover">
                                    ${place.getName()}
                                </a>
                            </#list>
                            </nobr>
                        </div>
                    </div>
                </div>
            </div>
    <#else>
        <a style="color: #4736ff">К сожалению, по Вашему запросу ничего не найдено</a>
    </#list>
</div>
</@i.page>
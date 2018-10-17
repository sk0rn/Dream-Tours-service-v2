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
                                 src="/images?album=${tour.getTour().getAlbumGuid()}&filename=${tour.getImageName()}"
                                 alt="Card image cap" name="imgArea">
                        </div>
                    </div>
                    <div class="col-9">
                        <#if Session.options?? && Session.options == 1>
                         <button type="button" class="btn-sm btn-success"
                                 onclick="document.location.href='/admin/add_content?id=${tour.getTour().getId()}'">
                             Изменить
                         </button>&nbsp;
                        </#if>
                        <a href="/tour?id=${tour.getTour().getId()}">${tour.getTour().getName()}
                        </a><br>
                        <nobr>
                            <#list tour.getSubjects() as subj>
                                <a href="/tours?subject=${subj.getId()}" title="${subj.getName()}"
                                   data-content="${subj.getDesc()}" data-toggle="popover"
                                   data-trigger="hover">
                                    ${subj.getName()}
                                </a>
                            </#list>
                        </nobr>
                        <div class="form-group">
                        <div class="form-control" id="exampleFormControlTextarea1" rows="5"
                                  name="tourName">${tour.getTour().getDesc()}</div>
                            <nobr>
                            <#list tour.getPlaces() as place>
                                <a href="/tours?place=${place.getId()}" title="${place.getName()}"
                                   data-content="${place.getDesc()}" data-toggle="popover"
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
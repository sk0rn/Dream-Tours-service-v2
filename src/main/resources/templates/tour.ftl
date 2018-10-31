<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
    <div class="col-12" align="left">
        <h4 style="left: ">${tour.getName()}</h4><br>
        <table>
            <tr>
                <td>
                    <div id="carouselExampleControls" class="carousel slide"
                         style="width: 560px; height: auto;display: inline-block;" data-ride="carousel">
                        <div class="carousel-inner">
    <#list tour.getAlbumGuid().getFiles()![] as file>
        <div class="carousel-item${file?is_first?string(' active', '')}">
            <img class="d-block w-100" src="${remoteConnectionHost}${tour.getAlbumGuid().getName()}/${file.getName()}">
        </div>
    </#list>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls" role="button"
                           data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls" role="button"
                           data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>

                <td class="align-top">
                    <div class="form-tours-dream" id="exampleFormControlTextarea1" rows="5"
                         name="tourName"><h5>В стоимость входит:</h5>
                        ${tour.getFeatures()}
                    </div>

                <td class="align-top">
                    <div class="form-tours-dream" id="exampleFormControlTextarea1" rows="6"
                         name="tourName"><h5>Города:</h5>
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
                        <br>
                        <h5>Тематики:</h5>
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
                    </div>


        </table>

        <div class="form-tours-dream" id="exampleFormControlTextarea1" rows="5"
             name="tourName"><h5>Обзор тура:</h5>${tour.getDescr()}
        </div>

        <br>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/${tour.getYoutubeUrl()}"
                frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
    </div>

</@i.page>

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
                <#--</div>-->
                <td class="align-top">
                <#--<div class="right" style="position: relative; left:560px; right: 0; top: 0; width: available; display: inline-block">-->
                    <div class="form-tours-dream" id="exampleFormControlTextarea1" rows="5"
                         name="tourName">${tour.getDescr()}</div>
                <#--</div>-->

        </table>

        <br>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/${tour.getYoutubeUrl()}"
                frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
    </div>

</@i.page>

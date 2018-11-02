<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
    <div class="col-12" align="left">
        <#--<h4 class="badge badge-primary" style="left:">${tour.getName()}</h4><br>-->
            <h3><span class="badge badge-info">${tour.getName()}</span></h3>
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

                    <td class="align-top px-3">
                        <div name="tourName">
                            <h5 class="text-primary">В стоимость входит:</h5>
                        <#escape x as x?html?replace('\r\n', '<br>')> ${tour.getFeatures()}</#escape>
                            <br>
                        <#if roles?? && roles?seq_contains("ROLE_USER")>
                            <button type="button" class="btn btn-outline-danger"
                                    onclick="document.location.href='/pay'">
                                Купить
                            </button>&nbsp;
                            <#assign inWishList=wishList?seq_contains(tour.getId()) />
                        <a href="#" class="a-wishList" tour-id="${(tour.getId())!}">
                            <img src="/static/image/star.ico" id="wl-${tour.getId()}"
                                 width="24" ${inWishList?string("", "class='not-in-wish-list'")}>
                        </a>
                        </#if>
                        </div>

                    <td class="align-top px-3">
                        <div name="tourName">
                            <h5>Города:</h5>
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
                            <br>
                            <h5>Тематики:</h5>
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
                            <br><br>
                            <h5 style="color: crimson">Ближайшие даты начала:</h5>
                                 <#list tour.getReleases() as release>
                                     ${release.getBeginTime()}
                                <h6>Количество дней:</h6>
                                     ${release.getDuration().getName()}
                                 </#list>
                        </div>
            </table>

            <div name="tourName">
                <h5>Обзор тура:</h5>
                <p class="text-justify">${tour.getDescr()}</p>
            </div>

            <nobr>
                <iframe width="560" height="315" src="https://www.youtube.com/embed/${tour.getYoutubeUrl()}"
                        frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
                <iframe src="https://yandex.ru/map-widget/v1/-/${tour.getMapUrl()}" width="560" height="315"
                        frameborder="1" allowfullscreen="true"></iframe>
            </nobr>

            <div id="accordion">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne"
                                    aria-expanded="false"
                                    aria-controls="collapseOne">
                                Документы
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <h5>Необходимые документы для оформления визы:</h5>
                            1.Загранпаспорт + 1 ксерокопия первой страницы; Паспорт РФ Ксерокопия российского паспорта
                            (1-ю
                            страницу + 2.страница с регистрацией);<br>
                            3.Анкета для посольства Анкета для посольства (2 шт);<br>
                            4.Анкета-опросник (1шт);<br>
                            5.Фото 45х45 мм Фотографии цветные или черно-белые 45х45 мм, сделанные недавно и
                            соответствующие
                            современному облику заявителя (2 шт);<br>
                            6.Справка с работы Справка с места работы на фирменном бланке компании с указанием должности
                            и
                            оклада; ВНИМАНИЕ: турист не должен сам себе подписывать справку<br>
                            7.Авиабилет в Японию;<br>
                            8.Доверенность Простая письменная форма на подачу документов в посольство, в случае если
                            подает
                            доверительное лицо;<br>
                            9.Выписка со счета<br><br>
                            Посольство Японии в России: Тел.:(495) 229-2550 ,<br>
                            (495)229-2551 129090, Москва, Грохольский переулок, дом 27.<br><br>
                            Консульский отдел посольства Японии в Москве: Тел.:(495) 229-2520 129090,<br>
                            Москва, Грохольский переулок, дом 27.
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h5 class="mb-0">
                            <button class="btn btn-link collapsed" data-toggle="collapse" data-target="#collapseTwo"
                                    aria-expanded="false" aria-controls="collapseTwo">
                                Памятка туристу
                            </button>
                        </h5>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
                        <div class="card-body">
                            Лучше всего посещать Японию весной (март-апрель) или осенью (октябрь-ноябрь). Температура
                            воздуха в
                            это время<br>
                            обычно не опускается ниже 17-20оС днем и 10-15оС - ночью. Весной и осенью в стране обычно
                            проходит
                            множество<br>
                            разнообразных праздников. Самый плохой сезон - лето, когда идут дожди, а температура
                            достигает +40
                            градусов.
                            <br><br>
                            Национальная денежная единица страны - иена. $1 равен 130 иенам. Обменять валюту лучше всего
                            в
                            аэропорту, так как<br>
                            в гостиницах меняют не более $300 на человека, а в банках процедура обмена сильно затруднена
                            бюрократическими<br>
                            формальностями.
                            <br><br>
                            В номерах гостиниц можно пить обычную водопроводную воду. В Японии очень строго следят за
                            санитарными нормами.<br>
                            <br><br>
                            В Японии - левостороннее движение, поэтому брать автомобиль напрокат гражданам России нет
                            никакого
                            смысла.<br>
                            Самое удобное средство передвижения - общественный городской транспорт. Оплата проезда на
                            метро и
                            автобусе<br>
                            зависит от продолжительности проезда. Минимальная оплата проезда в метро - 150 иен, в
                            автобусе - 220
                            иен.
                            <br><br>
                            "Чаевые" за услуги в отелях и такси давать не принято.<br>
                            <br><br>
                            Напряжение в электросети - 120 вольт.
                            <br><br>
                            Международный аэропорт Нарита находится в часе езды от Токио. Лучше всего добираться из него
                            в центр
                            японской<br>
                            столицы на автобусе-экспрессе "шаттл-бас".
                            <br><br>
                            При выезде из страны в аэропорту каждый пассажир обязан оплатить сбор в размере две тысячи
                            иен.<br>
                            <br><br>
                            Минимальный срок для получения японской визы - 21 день. Консульский сбор при этом составляет
                            158
                            рублей.<br>
                            Для получения визы необходима поддержка со стороны японских партнеров.
                            <br><br>
                            Стоимость одной минуты телефонного разговора с городами России - примерно $3. Выход на
                            международную
                            линию - 001.<br>
                            Для звонков необходима телефонная карта.
                            <br><br>
                            Разница во времени с Москвой составляет +6 часов зимой и +5 часов летом.<br>
                            <br><br>
                            Японская кухня весьма специфична. Блюда национальной кухни подаются сразу все, но в
                            разнообразном
                            наборе.<br>
                            Для начала рекомендуется попробовать традиционные "суси" и "сасими" (блюда из сырой
                            рыбы),<br>
                            а также "мраморное" мясо "кобэгю". Из напитков рекомендуется попробовать рисовую водку
                            "сакэ",
                            которую пьют подогретой.
                            <br><br>
                            Японские отели весьма дороги. Трехзвездочный отель, который соответствует европейскому
                            четырехзвездочному уровню,<br>
                            может обойтись клиенту в $80-100 за ночь.
                        </div>
                    </div>
                </div>

            </div>

            <script>
                var csrfParamName = "${_csrf.parameterName}";
                var csrfParamValue = "${_csrf.token}";
            </script>
        </@i.page>

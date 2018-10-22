<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
<div class="row">
    <div class="col-3">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link active" id="v-pills-tour-tab" data-toggle="pill" href="#v-pills-tour" role="tab"
               aria-controls="v-pills-tour" aria-selected="true">Добавить тур</a>
            <a class="nav-link" id="v-pills-place-tab" data-toggle="pill" href="#v-pills-place" role="tab"
               aria-controls="v-pills-place" aria-selected="false">Добавить место</a>
            <a class="nav-link" id="v-pills-subject-tab" data-toggle="pill" href="#v-pills-subject" role="tab"
               aria-controls="v-pills-subject" aria-selected="false">Добавить тематику</a>
            <a class="nav-link" id="v-pills-duration-tab" data-toggle="pill" href="#v-pills-duration" role="tab"
               aria-controls="v-pills-duration" aria-selected="false">Продолжительность тура</a>
            <a class="nav-link" id="v-pills-release-tab" data-toggle="pill" href="#v-pills-release" role="tab"
               aria-controls="v-pills-release" aria-selected="false">Туристическая группа</a>
            <a class="nav-link" id="v-pills-cost-tab" data-toggle="pill" href="#v-pills-cost" role="tab"
               aria-controls="v-pills-cost" aria-selected="false">Цены</a>
        </div>
    </div>
    <div class="col-8">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-tour" role="tabpanel" aria-labelledby="v-pills-tour-tab">
                <form action="/admin/content" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input class="form-control" type="hidden"
                           placeholder="название" name="idTour" value="${(tour.getId())!}">
                    <br><input class="form-control" type="text" placeholder="название" name="tourName" required
                               value="${(tour.getName())!}">
                    <div class="form-group">
                        <label for="exampleFormControlTextarea1"></label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" placeholder="описание"
                                  name="descTour" required>${(tour.getDescr())!}</textarea>
                    </div>
                    <label for="basic-url">Ссылка на видео:</label>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="basic-addon3">https://www.youtube.com/</span>
                        </div>
                        <input type="text" class="form-control" id="basic-url" aria-describedby="basic-addon3"
                               name="youtubeUrl" value="${(tour.getYoutubeUrl())!}">
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlFile1">Добавить изображение:</label>
                        <input type="file" class="form-control-file" id="exampleFormControlFile1" name="imageTour">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">${(tour??)?then('Изменить', 'Добавить')}
                    </button>
                </form>
            </div>
            <div class="tab-pane fade" id="v-pills-place" role="tabpanel" aria-labelledby="v-pills-place-tab">
                <form action="/admin/content" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <br><input class="form-control" type="text" placeholder="название" name="placeName" required>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea2"></label>
                        <textarea class="form-control" id="exampleFormControlTextarea2" rows="5" placeholder="описание"
                                  name="descPlace"></textarea>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
            <div class="tab-pane fade" id="v-pills-subject" role="tabpanel" aria-labelledby="v-pills-subject-tab">
                <form action="/admin/content" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <br><input class="form-control" type="text" placeholder="название" name="subjectName" required>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea3"></label>
                        <textarea class="form-control" id="exampleFormControlTextarea3" rows="5" placeholder="описание"
                                  name="descSubject"></textarea>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
            <div class="tab-pane fade" id="v-pills-duration" role="tabpanel" aria-labelledby="v-pills-duration-tab">
                <form action="/admin/content" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <br><input class="form-control" type="text" placeholder="продолжительность (дней)"
                               name="numberDays" required>
                    <div class="form-group">
                        <label for="exampleFormControlTextarea4"></label>
                        <textarea class="form-control" id="exampleFormControlTextarea4" rows="5" placeholder="описание"
                                  name="nameDuration"></textarea>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
            <div class="tab-pane fade" id="v-pills-release" role="tabpanel" aria-labelledby="v-pills-release-tab">
                <form action="/admin/content" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <label>Название тура:</label>
                    <br><select class="form-control" name="tourList">
                    <#list tours! as tour>
                        <option value="${tour.getId()}">${tour.getName()}
                        </option>
                    </#list>
                    <br>
                </select><br>
                    <label>Продолжительность тура:</label>
                    <br><select class="form-control" name="tourDurationList">
                    <#list durations! as dur>
                        <option value="${dur.getId()}">${dur.getNumberDays()} (дней),  ${dur.getName()}
                        </option>
                    </#list>
                </select><br>
                    <div class="form-group">
                        <label>Дата начала тура:</label>
                        <input type="datetime-local" class="form-control" name="dateStart" required>
                    </div>
                    <br><input class="form-control" type="text" placeholder="кол-во мест" name="capacity">
                    <br>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
            <div class="tab-pane fade" id="v-pills-cost" role="tabpanel" aria-labelledby="v-pills-cost-tab">
                <form action="/admin/content" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <br><label>Туристическая группа:</label>
                    <br><select class="form-control" name="tourReleaseList">
                        <#list tourReleases! as tr>
                        <#--TODO здесь необходмо настроить вывод тут релизов-->
                            <option value="${tr.getId()}">${tr.getTour().getName()}; ${tr.getDuration().getNumberDays()}
                                (дней), ${tr.getDuration().getName()}; дата начала тура:
                                ${tr.getBeginTime()}; количество мест: ${tr.getCapacity()}.
                        </option>
                        </#list>
                </select><br>
                    <br><select class="form-control" name="kind">
                    <option>перелет</option>
                    <option>проживание</option>
                </select>
                    <br>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">руб</span>
                        </div>
                        <input type="text" class="form-control" aria-label="Amount (to the nearest dollar)"
                               name="tourCost" required>
                        <div class="input-group-append">
                            <span class="input-group-text">.00</span>
                        </div>
                    </div>
                    <br><input class="form-control" type="text" placeholder="возраст бесплатного перелета/проживания"
                               name="clippingAge">
                    <br>
                    <div class="form-group form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1" name="isParticipant">
                        <label class="form-check-label" for="exampleCheck1">Участник группы</label>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</@i.page>
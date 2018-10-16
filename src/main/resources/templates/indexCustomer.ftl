<#import "index.ftl" as i>
<@i.page>
<div class="row">
    <div class="col-3">
        <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link active" id="v-pills-profile-tab" data-toggle="pill" href="#v-pills-profile"
               role="tab"
               aria-controls="v-pills-profile" aria-selected="true">Личная информация</a>
            <a class="nav-link" id="v-pills-wishlist-tab" data-toggle="pill" href="#v-pills-wishlist" role="tab"
               aria-controls="v-pills-wishlist" aria-selected="false">Список желаний</a>
            <a class="nav-link" id="v-pills-order-tab" data-toggle="pill" href="#v-pills-order" role="tab"
               aria-controls="v-pills-order" aria-selected="false">Заказы</a>
        </div>
    </div>

    <div class="col-8">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-profile" role="tabpanel"
                 aria-labelledby="v-pills-profile-tab">
                <form action="" method="">
                    <br>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"
                                  id="inputGroup-sizing-default">ФИО:</span>
                        </div>
                        <input type="text" class="form-control" value="Иванов Петр Сидорович" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">Изменить</button>
                        </div>
                    </div>
                    <br>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text"
                                  id="inputGroup-sizing-default">email:</span>
                        </div>
                        <input type="text" class="form-control" value="Piter.the.great@russia.com" aria-label="Default"
                               aria-describedby="inputGroup-sizing-default">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">Изменить</button>
                        </div>
                    </div>
                    <br>
                    <h4>Количество бонусных очков: 42</h4>
                    <br>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span>Подписаться на рассылку </span>
                        </div>
                        <div class="input-group-append">
                            <label class="switch switch_type1" role="switch">
                                <input type="checkbox" class="switch__toggle" checked>
                                <span class="switch__label"></span>
                            </label>
                        </div>
                    </div>
                    <input type="button" class="btn btn-danger" value="Удалить аккаунт..." style="float: right;">
                </form>
            </div>

            <div class="tab-pane fade" id="v-pills-wishlist" role="tabpanel" aria-labelledby="v-pills-wishlist-tab">
                <form action="" method="">
                    <div class="form-group">
                        <label for="exampleFormControlSelectСall"></label>
                        <select multiple class="form-control" id="exampleFormControlSelectСall" name="callSelect">
                            <option>Список желаний</option>
                        </select>
                        <br>
                        <button type="submit" class="btn btn-primary">Редактировать</button>
                        <button type="submit" class="btn btn-primary">Удалить из списка</button>
                    </div>
                </form>
            </div>

            <div class="tab-pane fade" id="v-pills-order" role="tabpanel" aria-labelledby="v-pills-order-tab">
                <form action="" method="">
                    <label for="exampleFormControlSelectСall"></label>

                    <select multiple class="form-control" id="exampleFormControlSelectСall" name="callSelect">
                        <option>Список заказов</option>
                    </select>
                    <br>
                </form>
            </div>
        </div>
    </div>
</div>
</@i.page>
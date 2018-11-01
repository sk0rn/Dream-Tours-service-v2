<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
Платёж!


                <form class="RESTpay" action="https://restfordreamtours.herokuapp.com/" method="post">
                    <div class="form-group">
                        <input class="form-control" type="text" id="tour"
                               placeholder="название тура" name="tour" value="">
                        <input class="form-control" type="text" id="cardNumber"
                               placeholder="номер карты" name="cardNumber" value="">
                        <input class="form-control" type="text" id="cost"
                               placeholder="стоимость" name="cost" value="">


                        <button type="submit" class="btn btn-primary">Оплатить</button>
                    </div>
                </form>

</@i.page>
<#ftl encoding="utf-8">
<button class="btn btn-outline-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
        aria-haspopup="true" aria-expanded="false">
    Войдите
</button>

<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <form class="px-4 py-3" action="/login" method="post">
        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
    <div class="form-group">
        <label for="exampleDropdownFormEmail1">Логин</label>
        <input type="text" class="form-control" id="exampleDropdownFormEmail1" placeholder="login" name="username">
    </div>
    <div class="form-group">
        <label for="exampleDropdownFormPassword1">Пароль</label>
        <input type="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="password"
               name="password">
    </div>
    <button type="submit" class="btn btn-primary">Войти</button>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="/register">Регистрация</a>
</form>
</div>
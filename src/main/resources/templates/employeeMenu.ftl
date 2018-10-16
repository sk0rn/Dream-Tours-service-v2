<#ftl encoding="utf-8">
<#import "index.ftl" as i>
<@i.page>
<button class="btn btn-outline-info dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown"
        aria-haspopup="true" aria-expanded="false">
    Меню сотрудника
</button>

<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="/admin/add_content">Добавить</a><br>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item" href="/login?action=logout">Выйти</a>
</div>
</@i.page>
<#ftl encoding="utf-8">
<#macro page>
<!DOCTYPE html>
<html>
<#include "headTag.ftl">
<body class="bg">
<#include "menu.ftl" >
<#nested>
<#include "tail.ftl">
</body>
</html>
</#macro>
<#ftl encoding="utf-8">
<#import "parts/index.ftl" as i>
<@i.page>
<div align="center">
    <#--TODO если будет время можно покрасивее обработать данные сообщения,-->
    <#--например показывать в всплывающем окошке-->
    <#if  logout?? && logout!false>
        <a style="color: #4736ff">You've been logged out successfully.</a>
    <#elseif error?? && error!false>
         <a style="color: #4736ff">Invalid username or password.</a>
    <#elseif denied?? && denied!false>
         <a style="color: #4736ff">Forbidden.</a>
    <#else>
        <a style="color: #4736ff">You need to log in.</a>
    </#if>
</div>
</@i.page>
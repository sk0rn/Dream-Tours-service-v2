<#ftl encoding="utf-8">
<#import "/spring.ftl" as spring /> <#--юзаем полезные макросы-->
<#import "parts/index.ftl" as i>
<@i.page>
<div class="container">

    <form class="form-registration" method="POST" action="/registration">
        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
        <h5 class="form-heading">Пожалуйста, заполните все поля</h5>

	    <@spring.bind "registrationForm"/>
	    <#assign fieldError>
            <@spring.showErrors "<br/>"/>
        </#assign>
	    <#if fieldError?has_content>
		    <div class="alert alert-danger" role="alert">${fieldError}</div>
        </#if>

        <div class="form-group col-md-3">
            <@spring.bind "registrationForm.login"/>
            <#assign fieldError>
                <@spring.showErrors "<br/>"/>
            </#assign>
            <label class="sr-only">Login</label>
            <input name="login" type="text"
                   class="form-control ${fieldError?has_content?then('is-invalid','')}"
                   placeholder="Login"
                   value="${registrationForm.login!''}"
                   autofocus required />
            <#if fieldError?has_content>
                <div class="invalid-feedback">${fieldError}</div>
            </#if>
        </div>

        <div class="form-group col-md-3">
            <@spring.bind "registrationForm.phone"/>
            <#assign fieldError>
                <@spring.showErrors "<br/>"/>
            </#assign>
            <label class="sr-only">Phone</label>
            <input name="phone" type="text"
                   class="form-control ${fieldError?has_content?then('is-invalid','')}"
                   placeholder="Phone"
                   value="${registrationForm.phone!''}"
                   autofocus required />
            <#if fieldError?has_content>
                <div class="invalid-feedback">${fieldError}</div>
            </#if>
            <small id="passwordHelpInline" class="text-muted">
                Длина 10 цифр
            </small>
        </div>

        <div class="form-group col-md-3">
            <@spring.bind "registrationForm.email"/>
            <#assign fieldError>
                <@spring.showErrors "<br/>"/>
            </#assign>
            <label class="sr-only">Email address</label>
            <input name="email" type="email"
                   class="form-control ${fieldError?has_content?then('is-invalid','')}"
                   placeholder="Email address"
                   value="${registrationForm.email!''}"
                   autofocus required />
            <#if fieldError?has_content>
                <div class="invalid-feedback">${fieldError}</div>
            </#if>
        </div>

        <div class="form-group col-md-3">
            <@spring.bind "registrationForm.password"/>
            <#assign fieldError>
                <@spring.showErrors "<br/>"/>
            </#assign>
            <label class="sr-only">Password</label>
            <input name="password" type="password"
                   class="form-control ${fieldError?has_content?then('is-invalid','')}"
                   placeholder="Password"
                   value="${registrationForm.password!''}"
                   required />
            <#if fieldError?has_content>
                <div class="invalid-feedback">${fieldError}</div>
            </#if>
            <small id="passwordHelpInline" class="text-muted">
                Минимальная длина 8 символов (минимум 2 строчные буквы, 2 заглавные буквы, 2 цифры)
            </small>
        </div>

        <div class="form-group col-md-3">
            <@spring.bind "registrationForm.repeatPassword"/>
            <#assign fieldError>
                <@spring.showErrors "<br/>"/>
            </#assign>
            <label class="sr-only">Repeat password</label>
            <input name="repeatPassword" type="password"
                   class="form-control ${fieldError?has_content?then('is-invalid','')}"
                   placeholder="Repeat password"
                   value="${registrationForm.repeatPassword!''}"
                   required />
            <#if fieldError?has_content>
                <div class="invalid-feedback">${fieldError}</div>
            </#if>
            <div class="dropdown-divider"></div>
        <button class="btn btn-lg btn-primary" type="submit">Зарегистрироваться</button>
        </div>

    </form>

</div>
</@i.page>
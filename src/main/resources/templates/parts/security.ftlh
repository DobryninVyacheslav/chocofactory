<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.isAdmin()
    isTechnologist = user.isTechnologist()
    isBeater = user.isBeater()
    isResearcher = user.isResearcher()
    isSquirrel = user.isSquirrel()
    >
<#else>

    <#assign
    name = "unknown"
    isAdmin = false
    isTechnologist = false
    isBeater = false
    isResearcher = false
    isSquirrel = false
    >

</#if>
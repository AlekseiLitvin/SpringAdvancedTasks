<#import "/spring.ftl" as spring />

<html>
<head>
    <title>Login page</title>
</head>
<body>


    <#assign loginUrl>
        <@spring.url '/login'/>
    </#assign>

    <form action="${loginUrl}" method="post">
        <h2>Please sign in</h2>
        <input type="email" name="user_email" placeholder="Email address" required autofocus><br>
        <input type="password" name="user_password" placeholder="Password" required><br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">Enter</button>
    </form>

</body>
</html>
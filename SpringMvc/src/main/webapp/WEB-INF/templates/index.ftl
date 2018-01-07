<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>你好，springboot</title>
</head>
<body style="height:900px">
<div class="wrap">
    ${name!""}
    <#if user??>
        ${user.userName!""}${user.password!""}
    </#if>
    ${redis!""}
    <img src="/images/psb.jpg">
</div>
</body>
</html>
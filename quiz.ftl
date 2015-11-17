<html>

<div class="wrapper">
<head>

<link rel="stylesheet" href="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story.css">

<header class="header">
<div id="image">
<img src="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/header.jpg">
</div>

<div id="textHead">
<h> Story Reader</h>
</div>

</header>


</head>

<body>
<article class="nav">
</article>

<article class="list">
<div id="stories">
<#if (!STORIES??)>
Story 1
<br>
Story 2
<br>
Story 3
<br>
Story 4
<br>
<#else>
<ul>
    <#list STORIES?keys as key>
        <li>
        <a
href="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/servlet/story/${key}/1">${STORIES[key]}</a>
        </li>
    </#list>

</ul>
</#if>
</div>
</article>

<article class="identity">
<#if (!CURRENTUSER??)>
<FORM METHOD='Post' ACTION="">
<div id="user">
Username:
<input type='text' size=25 name='user'>
</div>
<div id="email">
Password:
<input type='password' size=25 name='password'>
</div>
<div id="submit">
<input type='submit' value='Enter'>
</div>
</FORM>
<#else>
<tr><td>Username: </td><td>${CURRENTUSER}</td></td>

</#if>
</div>
</article>
</body>

<footer class="footer">
<#if (ADMIN??)>
<a href = ${ADMIN}> Admin </a>
</#if>

<#include "footer.ftl">
</footer>
</div>
</html>

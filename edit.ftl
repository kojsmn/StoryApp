<html>

<div class="wrapper">
<head>

<link rel="stylesheet" href="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story.css">

<header class="header">
<div id="image">
<img src="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/header.jpg">
</div>

<div id="textHead">
<h> Story Editor</h>
</div>

</header>


</head>

<body>
<article class="nav">
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

<h> Title: ${TITLE} </h>
<br>
<h> Author: ${AUTHOR} </h>
<br>
<h> Content: ${CONTENT} </h>
</body>

<footer class="footer">
<#if (ADMIN??)>
<a href = ${ADMIN}> Admin </a>
</#if>

<#include "footer.ftl">
</footer>
</div>
</html>

<html>

<div class="wrapper">
<head>

<link rel="stylesheet"
href="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/story.css">

<header class="header">
<div id="image">
<img src="http://kojsmn.383.csi.miamioh.edu:8080/StoryApp/header.jpg">
</div>

<div id="textHead">
<h> Story Reader ADMIN</h>
</div>

</header>


</head>

<body>
<article class="nav">
</article>

<article class="list">
<div id="stories">
<#if (!STORYLINK1??)>
Story 1
<br>
Story 2
<br>
Story 3
<br>
Story 4
<br>
<#else>
<a href=${STORYLINK1}>Story 1</a>
<a href=${DELETESTORY1}>Delete</a>
<a href=${EDITSTORY1}>Edit</a>
<br>
<a href=${STORYLINK2}>Story 2</a>
<br>
<a href=${STORYLINK3}>Story 3</a>
<br>
<a href=${STORYLINK4}>Story 4</a>
<br>
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
<a href = ${MANAGEUSERS}>Manage Users </a>

<#include "footer.ftl">
</footer>
</div>
</html>
                  

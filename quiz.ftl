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
<a href=${STORYLINK1}>Story 1</a>
<br>
<a href=${STORYLINK2}>Story 2</a>
<br>
<a href=${STORYLINK3}>Story 3</a>
<br>
<a href=${STORYLINK4}>Story 4</a>
<br>
</div>
</article>

<article class="identity">
<#if (!EMAIL??) && (!USER??)>
<FORM METHOD='Post' ACTION="">
<div id="user">
UserName:
<input type='text' size=25 name='user'>
</div>
<div id="email">
Email:
<input type='text' size=25 name='email'>
</div>
<div id="submit">
<input type='submit' value='Enter'>
</div>
</FORM>
<#else>
<tr><td>Email:</td><td>${EMAIL}</td></td>
<tr><td>Name:</td><td>${USER}</td></td>

</#if>
</div>
</article>

</body>

<footer class="footer">
<#include "footer.ftl">
</footer>
</div>
</html>

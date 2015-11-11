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
<h> Story Reader</h>
</div>

</header>

</head>

<body>

<article class="nav">
<a href=${HOME}>Home</a>
<br>
<#if (PREVSTORY??)>
<a href=${PREVSTORY}>Previous Story</a>
</#if>
<br>
<#if (NEXTSTORY??)>
<a href=${NEXTSTORY}>Next Story</a>
</#if>
<br>
</article>

<article class="list">
<div id="content">
${CONTENT}
</div>
</article>


<article class="identity">

<#if (!EMAIL??) && (!USER??)>
<FORM METHOD='Post' ACTION="">
<div id="user">
Username:
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
<div id="email">
<tr><td>Email:</td><td>${EMAIL}</td></td>
</div>
<div id="user">
<tr><td>Name:</td><td>${USER}</td></td>
</div>

</#if>
</article>

</div>
</body>

<footer class="footer">
<#include "footer.ftl">
</footer>
</html>


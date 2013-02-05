
$$(".navHolder li.login").onMouseOver = test()
function test()
{
	new Effect.SlideDown($('userLoginBox'));
   $$(".navHolder li.login a").addClassName("userLoginHover");
}
$$(".navHolder li.login").onMouseOut = test1()
function test1()
{
   $$(".userLoginBox").slideUp();
   $$(".navHolder li.login a").removeClassName("userLoginHover");
}
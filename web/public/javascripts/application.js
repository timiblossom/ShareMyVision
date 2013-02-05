// Place your application-specific JavaScript functions and classes here
// This file is automatically included by javascript_include_tag :defaults
//Event.observe(document, 'blur', validate);
//Event.observe(document, 'change', validate);


function validate()
{ 
	
	var msg ='';
	
	if ($F('user_firstName')=='') {
		msg += 'Please Enter First Name <BR>';		
	   }
	if ($F('user_lastName')=='') {
		msg += 'Please Enter Last Name <BR>';		
	   }
	if ($F('user_email')=='') {
		msg += 'Please Enter Email <BR>';		
	   }
	
	if($F('user_email')!='' &&  !isEmailValid($F('user_email')))
	{
		msg += 'Please Enter a valid Email <BR>';
	}
	if ($F('user_password')=='') {
		msg += 'Please Enter Password <BR>';		
	   }
	if ($F('user_password')!='' && $F('user_password_confirmation')=='') {
		msg += 'Please Enter Confirm Password <BR>';		
	   }
	if($F('user_password')!='' &&  $F('user_password_confirmation')!='' && $F('user_password')!=$F('user_password_confirmation'))
	{
		msg += 'Password doesn\'t match <BR>';
		$('user_password').value='';
		$('user_password_confirmation').value='';
		
	}
	if ($F('recaptcha_response_field')=='') {
		msg += 'Please enter the two words printed on this page. <BR>';		
	   }
	if(msg!='')
	{
		$('errormsg').update(msg);
		return false;
	}
	
	$('errormsg').update(msg);
	return true;
}

register = function()
{
	if(validate())
	{
		$('submit').hide(); 
		$('update_tag').show().update('updating..........');
		return true;
	}
	return false;
}

retrievePassword =  function()
{
	var msg ='';
	if ($F('user_email')=='') {
		msg += 'Please Enter Email <BR>';		
	   }
	if($F('user_email')!='' &&  !isEmailValid($F('user_email')))
	{
		msg += 'Please Enter a valid Email <BR>';
	}
	if(msg!='')
	{
		Element.update("flash", msg);
        $("submit").show();
        $("password_sucess").hide();
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});
		return false;
	}
	else
	{
	   $('submit').hide(); 
	   $('password_sucess').show().update('updating..........');
	   return true;
	}
}

successMsg = function(req)
{
	var data = eval('(' + req.responseText + ')');
    if(data.status =='active')
    {
      $('registration').hide();
	  $('update_tag').hide();
	  $('success_msg').show().update('successfully registered........../user/activate/'+data.activation_code);
    }
    if(data.status =='inactive')
    {
		Element.update("flash", data.errorMesage);
		Recaptcha.reload()
		$('update_tag').hide()
        $("submit").show();
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});
		return false;
    }
    
}

cancel = function()
{
	$('submit').show();
	$('update_tag').hide();
}

isEmailValid = function(email)
{
	var email_pattern =/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if(email=='')
	{
		return false;
	}
	if(!email.match(email_pattern))
	{
		return false;		
	}
	return true;
}


isEmailAlreadyRegistered = function()
{
	var msg ='';
	if(!isEmailValid($F('user_email')))
	{
      	msg = "Please enter a valid email address.";
      	$('errormsg').update(msg);
      	Element.update("flash", msg);
		$("user_email").focus();
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});
      	return false;
	}
	
	var checkAuth = new Ajax.Updater('uaDiv', '/web/user/check_email', { parameters:{user:$F('user_email')},method:'post', asynchronous: true,onComplete:function(transport){checkEmailMsg(transport) }});
}

forgotPassword = function()
{
	var msg ='';
	if(!isEmailValid($F('user_email')))
	{
      	msg = "Please enter a valid email address.";
     	Element.update("flash", msg);
		$("user_email").focus();
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});
      	return false;
	}
	
	var checkAuth = new Ajax.Updater('uaDiv', '/web/user/forgotPasswordCheckEmail', { parameters:{user:$F('user_email')},method:'post', asynchronous: true,onComplete:function(transport){checkEmailMsg(transport) }});
}

checkEmailMsg = function(res)
{
    $('submit').enable();
	if(res.status==200)
    {
	   var jsondata=eval("("+res.responseText+")");
	   if(jsondata.status=='inactive')
       {
          Element.update("flash", jsondata.errorMesage);
		  $("user_email").focus();
          $("flash").show();
          $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
          $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	      return;
       }
    }
	else
	{
	      Element.update("flash", "Unable to get response");
		  $('user_email').focus();
          $("flash").show();
          $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
          $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	      return ;
	}
}
memberRegistrationIcon = function()
{
}

chkLogin = function()
{
	
    return true;
}
redirectHome = function(req)
{
    $(location).attr('href','/web/user');


}

showMsg =  function(resp)
{
	$('submit').enable();
	if(resp.responseText=='false' || resp.responseText==false){
   		Element.update("flash", 'Please enter a valid password');
		$('submit').disable(); 
		$('user_password').value='';
		$('user_password').focus();
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	}	
}

changePasswordSubmit = function()
{
	$('submit').enable();
	msg="";
	if($('user_password').value=='')
	{
		msg= 'Please enter a valid password';
   		$('submit').disable(); 
		$('user_password').focus();        
	}
	if($('user_newPassword').value=='')
	{
		msg='Please enter the new password';
		$('submit').disable(); 
		$('user_newPassword').focus();
	}
	if($('user_password_confirmation').value=='')
	{
		msg='Please enter the confirm password';
 	    $('submit').disable();  
		$('user_password_confirmation').focus();
	}
	if($('user_newPassword').value!='' && $('user_password_confirmation').value!='' && ($('user_newPassword').value != $('user_password_confirmation').value))
	{
		msg='New and confirm password do not match.';
		$('submit').disable(); 
        $('user_password_confirmation').focus();
	}
	if(msg!='')
	{
        Element.update("flash", msg);		
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
		$('submit').enable(); 
        return false;
	}
	else
	{      
	  $('submit').hide();
	  Element.update("flash", 'updating.....');
      $("flash").show();
      $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
      $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	}
	
	
}
changePasswordMsg = function(res)
{
	if(res.status==200)
    {
	   var jsondata=eval("("+res.responseText+")");
	   if(jsondata.status=='active')
       {
          Element.update("flash", 'Password Changed Successfully');
          $("flash").show();
          $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
          $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	      $('change_password_form').hide();
	      $('success_tag').show();
	      $('success_tag').innerHTML='Password Changed Successfully';
	      return;
       }
	   if(jsondata.status=='inactive')
       {
          Element.update("flash", jsondata.errorMesage);
          $("flash").show();
          $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
          $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	      $('success_tag').hide();
	      $('submit').show();
	      return;
       }
    }
	else
	{
	      Element.update("flash", "Unable to get response");		  
          $("flash").show();
          $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
          $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	      return ;
	}

   	$('show_update').hide();
	$('submit').show();
	
   
}

updatePasswordSubmit = function()
{
	$('submit').enable();
	var msg='';
	if ($F('user_email')=='') {
		msg= 'Please Enter Email';
   		$('submit').disable(); 
		$('user_email').focus();				
		
	}
	if($F('user_email')!='' &&  !isEmailValid($F('user_email')))
	{
		msg= 'Please Enter a valid Email';
   		$('submit').disable(); 
		$('user_email').focus();		
	}
	if($('user_password').value=='')
	{
		msg='Please enter the new password';
		$('submit').disable(); 
		$('user_password').focus();
	}
	if($('user_password').value!= '' && $('user_password_confirmation').value=='')
	{
		msg='Please enter the confirm password';
 	    $('submit').disable();  
		$('user_password_confirmation').focus();
	}
	if($('user_password').value!='' && $('user_password_confirmation').value!='' && ($('user_password').value != $('user_password_confirmation').value))
	{	
		msg='New and confirm password do not match.';
		$('submit').disable(); 
        $('user_password_confirmation').focus();
	}
	if(msg!='')
	{
        Element.update("flash", msg);		
        $("flash").show();
        $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
        $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
		$('submit').enable(); 
        return false;
	}
	else
	{      
	  $('submit').hide();
	  Element.update("flash", 'updating.....');
      $("flash").show();
      $("flash").visualEffect("pulsate", {"queue":{"position":"end","scope":"flash"}});
      $("flash").visualEffect("fade", {"queue":{"position":"end","scope":"flash"}});	  	   
	}	
}
updatePasswordMsg = function(res)
{
	if(res.status==200)
    {
	   var jsondata=eval("("+res.responseText+")");
	   if(jsondata.status=='active')
       {
	      $('update_password_form').hide();
	      $('success_tag').show();
	      $('success_tag').innerHTML='Password Changed Successfully';
	      return;
       }

    }
   	$('show_update').hide();
	$('submit').show();
	
   
}

/*window effect, popup in events page*/
var Application =  {
		  lastId: 0,
		  currentSampleNb: 0,

		  getNewId: function() {
		    Application.lastId++;
		    return "window_id_" + Application.lastId;
		  },
		  
		  showCode: function(a, id) {
		    a.innerHTML = $(id + "_codediv").visible() ? "View source" : "Hide source"
		    $(id + "_codediv").toggle();
		  },

		  editCode: function(id) {
		    var pre = $(id);
		    // First time
		    if (!pre.textarea) {
		      var textarea = document.createElement("textarea");
		      var dim = pre.getDimensions();
		      textarea.setAttribute('id', id + "_edit");
		      textarea.setAttribute('class', 'listing');
		  		
		  		pre.textarea = textarea
		  		
		  		pre.parentNode.insertBefore(textarea, pre);
		    } 
		    // Show text area
		    if (pre.visible()) {
		      var dim = pre.getDimensions();      
		      
		      pre.textarea.value = pre.innerHTML;
		      pre.hide();
		      pre.textarea.style.height = dim.height + "px"
		      pre.textarea.style.width = "100%"
		      pre.textarea.style.display = "block";
		  
		  		pre.textarea.focus();
		  		$(id+'_edit_button').innerHTML = "Stop editing";
		    }
		    // Remove text area
		    else {
		      pre.update(pre.textarea.value);
		      pre.textarea.style.display = "none";
		      pre.show();
		  		$(id+'_edit_button').innerHTML = "Edit Source";
		    }
		  },
		  
		  evalCode: function(id) {
		    var pre = $(id);
		    var code;
		    if (pre.textarea && pre.textarea.visible)
		      code = pre.textarea.value;
		    else
		      code = pre.innerHTML;
		    
		    code = code.gsub("&lt;", "<");
		    code = code.gsub("&gt;", ">");
		    
		    try {
		      eval(code);
		    }
		    catch (error) {
		      Dialog.alert(" error accurs while interprating your javascript code <br/>" + error, {windowParameters: {width:300, showEffect:Element.show}, okLabel: "close"});
		    }
		  },

			addTitle: function(title, id) {
			  Application.currentSampleNb++;
			  idButton = id + '_click_button';
			  
			  document.write("<h2>" + Application.currentSampleNb + ". " + title + " (<span class='title'  id='" + idButton + "' href='#' onmouseover=\"$('" + idButton + "').addClassName('selected')\" onmouseout=\"$('" + idButton + "').removeClassName('selected')\" onclick=\"Application.evalCode('" + id + "', true)\">click here</span>)</h2>")
			},

			addShowButton: function(id) {
			  idButton = id + '_show_button';
			  document.write("<span class='title2' id='" + idButton + "' onmouseover=\"$('" + idButton + "').addClassName('selected')\" onmouseout=\"$('" + idButton + "').removeClassName('selected')\" onclick=\"Application.showCode(this, '" + id + "')\">View source</span>")
			},

			addEditButton: function(id) {
			  idButton = id + '_edit_button';
				html = "<p class='buttons'><span class='button' onmouseover=\"$('" + idButton + "').addClassName('selected')\" onmouseout=\"$('" + idButton + "').removeClassName('selected')\" id='" + idButton + "' onclick=\"Application.editCode('" + id + "')\">Edit Source</span> </p>";
		    document.write(html)
			},
			
			addViewThemeButton: function(theme, modal) {
			  idButton = theme + '_theme_button';
				html = "<span class='title2' onmouseover=\"$('" + idButton + "').addClassName('selected')\" onmouseout=\"$('" + idButton + "').removeClassName('selected')\" id='" + idButton + "' onclick=\"Application.openThemeWindow('" + theme + "')\">View a window</span>";
		    document.write(html)
			},
			
			addViewThemeDialogButton: function(theme, modal) {
			  idButton = theme + '_modal_theme_button';
				html = "<span class='title2' onmouseover=\"$('" + idButton + "').addClassName('selected')\" onmouseout=\"$('" + idButton + "').removeClassName('selected')\" id='" + idButton + "' onclick=\"Application.openThemeDialog('" + theme + "')\">Open a alert dialog</span>";
		    document.write(html)
			},
			
			openThemeWindow: function(theme, modal) {
			  var win = new Window(Application.getNewId(), {className: theme, width:300, height:200, title: "Theme : " + theme});
			  win.getContent().innerHTML= "Lorem ipsum dolor sit amet, consectetur adipiscing elit, set eiusmod tempor incidunt et labore et dolore magna aliquam. Ut enim ad minim veniam, quis nostrud exerc.";
			  win.showCenter(modal);
			},
			
			openThemeDialog: function(theme, modal) {
		    Dialog.alert("Lorem ipsum dolor sit amet, consectetur adipiscing elit, set eiusmod tempor incidunt et labore et dolore magna aliquam. Ut enim ad minim veniam, quis nostrud exerc", 
		                {windowParameters: {className: theme, width:350}, okLabel: "close"});
			},
			
			insertDocOverview: function() {
			  var div = $('Overview');
			  var html = "<table class='overview''><tr>";
			  
			  // Window
			  html += "<td>Window Class <ul>";
			  $$(".window").each(function(element){html += "- <a href='#" + element.title + "'>" + element.title + "</a><br/>"});
			  html += "</ul></td>";
			  
			  // Dialog
			  html += "<td>Dialog Module <ul>";
			  $$(".dialogmodule").each(function(element){html += "- <a href='#" + element.title + "'>" + element.title + "</a><br/>"});
			  html += "</ul></td>";
			  
			  // Windows
			  html += "<td>Windows Module <ul>";
			  $$(".windows").each(function(element){html += "- <a href='#" + element.title + "'>" + element.title + "</a><br/>"});
			  html += "</ul></td>";
				  
			  // Windows
			  html += "<td>Windows Add-ons <ul>";
			  $$(".addons").each(function(element){html += "- <a href='#" + element.title + "'>" + element.title + "</a><br/>"});
			  html += "</ul></td>";
				  
			  html += "</tr></table>"
			  div.innerHTML = html;
			},

			insertNavigation: function(selected) {
			  document.write('\
			  <h1><a href="http://prototype-window.xilinus.com"><img border=0 src="pwc.gif"/></a></h1>\
		    <div class="navigation">\
		      <ul>\
		        <li><a id="menu_documentation" href="documentation.html">Documentation</a></li>\
		        <li><a id="menu_samples" href="samples.html">Samples</a></li>\
		        <li><a id="menu_debug" href="debug.html">Debug</a></li>\
		        <li><a id="menu_themes" href="themes.html">Themes</a></li>\
		      </ul>\
		    </div>');
		    $('menu_' + selected).addClassName("selected");
			},
			addRightColumn: function() {
			  document.write('\
			  <style>\
			  div#wrapper{\
		      float:left;\
		      width:100%\
		    }\
		    div#content{\
		      margin-right:0px;\
		      padding-right:10px;\
		      border-right: none;\
		    }\
		    div#navigation{\
		      float:left;\
		      width:00px;\
		      margin-left:0px;\
		      text-align:left;\
		      background:#fef1e2;\
		    }\
			  </style>\
		  ')
			}
}

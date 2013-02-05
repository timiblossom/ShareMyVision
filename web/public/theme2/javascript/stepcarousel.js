//jQuery.noConflict()

var stepcarousel={
	ajaxloadingmsg: '<div style="margin: 1em; font-weight: bold"><img src="ajaxloadr.gif" style="vertical-align: middle" /> Fetching Content. Please wait...</div>', //customize HTML to show while fetching Ajax content
	configholder: {},

	getCSSValue:function(val){ //Returns either 0 (if val contains 'auto') or val as an integer
		return (val=="auto")? 0 : parseInt(val)
	},

	getremotepanels:function($, config){ //function to fetch external page containing the panel DIVs
		config.$belt.html(this.ajaxloadingmsg)
		$.ajax({
			url: config.contenttype[1], //path to external content
			async: true,
			error:function(ajaxrequest){
				config.$belt.html('Error fetching content.<br />Server Response: '+ajaxrequest.responseText)
			},
			success:function(content){
				config.$belt.html(content)
				config.$panels=config.$gallery.find('.'+config.panelclass)
				stepcarousel.alignpanels($, config)
			}
		})
	},

	alignpanels:function($, config){
		var paneloffset=0
		config.paneloffsets=[paneloffset] //array to store upper left offset of each panel (1st element=0)
		config.panelwidths=[] //array to store widths of each panel
		config.$panels.each(function(index){ //loop through panels
			var $currentpanel=$(this)
			$currentpanel.css({float: 'none', position: 'absolute', left: paneloffset+'px'}) //position panel
			$currentpanel.bind('click', function(e){return config.onpanelclick(e.target)}) //bind onpanelclick() to onclick event
			paneloffset+=stepcarousel.getCSSValue($currentpanel.css('marginRight')) + parseInt($currentpanel.get(0).offsetWidth) //calculate next panel offset
			config.paneloffsets.push(paneloffset) //remember this offset
			config.panelwidths.push(paneloffset-config.paneloffsets[config.paneloffsets.length-2]) //remember panel width
		})
		config.paneloffsets.pop() //delete last offset (redundant)
		config.$belt.css({width: paneloffset+'px'}) //Set Belt DIV to total panels' widths
		this.statusreport(config.galleryid)
		config.oninit()
		config.onslideaction(this)
	},

	stepTo:function(galleryid, pindex){ /*User entered pindex starts at 1 for intuitiveness. Internally pindex still starts at 0 */
		var config=stepcarousel.configholder[galleryid]
		if (typeof config=="undefined"){
			alert("There's an error with your set up of Carousel Viewer \""+galleryid+ "\"!")
			return
		}
		var pindex=Math.min(pindex-1, config.paneloffsets.length-1)
		var endpoint=config.paneloffsets[pindex]+(pindex==0? 0 : config.beltoffset)
		config.$belt.animate({left: -endpoint+'px'}, 'slow', function(){config.onslideaction(this)})
		config.currentpanel=pindex
		this.statusreport(galleryid)
	},

	stepBy:function(galleryid, steps){
		var config=stepcarousel.configholder[galleryid]
		if (typeof config=="undefined"){
			alert("There's an error with your set up of Carousel Viewer \""+galleryid+ "\"!")
			return
		}
		var direction=(steps>0)? 'forward' : 'back' //If "steps" is negative, that means backwards
		var pindex=config.currentpanel+steps //index of panel to stop at
		pindex=(pindex>config.paneloffsets.length-1 || pindex<0 && pindex-steps>0)? 0 : (pindex<0)? config.paneloffsets.length+steps : pindex //take into account end or starting panel and step direction 
		var endpoint=config.paneloffsets[pindex]+(pindex==0? 0 : config.beltoffset) //left distance for Belt DIV to travel to
		if (pindex==0 && direction=='forward' || config.currentpanel==0 && direction=='back'){ //decide whether to apply "push pull" effect
			config.$belt.animate({left: -config.paneloffsets[config.currentpanel]-(direction=='forward'? 100 : -30)+'px'}, 'normal', function(){
				config.$belt.animate({left: -endpoint+'px'}, 'slow', function(){config.onslideaction(this)})
			})
		}
		else
			config.$belt.animate({left: -endpoint+'px'}, 'slow', function(){config.onslideaction(this)})
		config.currentpanel=pindex
		this.statusreport(galleryid)
	},

	statusreport:function(galleryid){
		var config=stepcarousel.configholder[galleryid]
		var startpoint=config.currentpanel //index of first visible panel 
		var visiblewidth=0
		for (var endpoint=startpoint; endpoint<config.paneloffsets.length; endpoint++){ //index (endpoint) of last visible panel
			visiblewidth+=config.panelwidths[endpoint]
			if (visiblewidth>config.gallerywidth){
				break
			}
		}
		startpoint+=1 //format startpoint for user friendiness
		endpoint=(endpoint+1==startpoint)? startpoint : endpoint //If only one image visible on the screen and partially hidden, set endpoint to startpoint
		var valuearray=[startpoint, endpoint, config.panelwidths.length]
		for (var i=0; i<config.statusvars.length; i++){
			window[config.statusvars[i]]=valuearray[i] //Define variable (with user specified name) and set to one of the status values
			config.$statusobjs[i].html(valuearray[i]) //Populate element on page with ID="user specified name" with one of the status values
		}
	},

	setup:function(config){
		//Disable Step Gallery scrollbars ASAP dynamically (enabled for sake of users with JS disabled)
		document.write('<style type="text/css">\n#'+config.galleryid+'{overflow: hidden;}\n</style>')
		jQuery(document).ready(function($){
			config.$gallery=$('#'+config.galleryid)
			config.gallerywidth=config.$gallery.width()
			config.$belt=config.$gallery.find('.'+config.beltclass) //Find Belt DIV that contains all the panels
			config.$panels=config.$gallery.find('.'+config.panelclass) //Find Panel DIVs that each contain a slide
			config.onpanelclick=(typeof config.onpanelclick=="undefined")? function(target){} : config.onpanelclick //attach custom "onpanelclick" event handler
			config.onslideaction=(typeof config.onslide=="undefined")? function(){} : function(beltobj){$(beltobj).stop(); config.onslide()} //attach custom "onslide" event handler
			config.oninit=(typeof config.oninit=="undefined")? function(){} : config.oninit //attach custom "oninit" event handler
			config.beltoffset=stepcarousel.getCSSValue(config.$belt.css('marginLeft')) //Find length of Belt DIV's left margin
			config.statusvars=config.statusvars || []  //get variable names that will hold "start", "end", and "total" slides info
			config.$statusobjs=[$('#'+config.statusvars[0]), $('#'+config.statusvars[1]), $('#'+config.statusvars[2])]
			config.currentpanel=0
			stepcarousel.configholder[config.galleryid]=config //store config parameter as a variable
			if (config.contenttype[0]=="ajax" && typeof config.contenttype[1]!="undefined") //fetch ajax content?
				stepcarousel.getremotepanels($, config)
			else
				stepcarousel.alignpanels($, config) //align panels and initialize gallery
		}) //end document.ready
		jQuery(window).bind('unload', function(){ //clean up
			jQuery.each(config, function(ai, oi){
				oi=null
			})
			config=null
		})
	}
}



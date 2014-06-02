$("#daily").on("pageinit", function(){
	"use strict";
	
	var page = {
			id : $(this).attr("id"),
			dom : $(this)
	};
	
	page.model = (function(){
		var pub = {};
		
		
		
		pub.init = function() {
			
		};
		return pub;
	}());

	page.view = (function(){
		
		var sel = {
		};
		
		var pub = {
			
		};
		
		pub.init = function() {
		};
		return pub;
	}());

	page.controller = (function(){
	
		
		
		var pub = {
		};
		
		
		
		
		pub.init = function() {
			page.model.init();
			page.view.init();
		};
		
		return pub;
	}());
	
	
	common.page.setModel(page.id, page.model);
	common.page.setView(page.id, page.view);
	common.page.setController(page.id, page.controller);
	
	page.controller.init();
});

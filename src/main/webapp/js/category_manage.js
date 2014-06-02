$("#category_manage").on("pageinit", function(){
	"use strict";
	
	var page = {
			id : $(this).attr("id"),
			dom : $(this)
	};
	
	page.model = (function(){
		var pub = {
			"changeCategoryUseyn" : function(successCB, params) {
				$.post("/changeCategoryUseyn.do", params, successCB, "json");
			}
		};
		
		pub.init = function() {
			
		};
		return pub;
	}());

	page.view = (function(){
		
		var sel = {
			"categories" : $("select[name='category']", page.dom)
		};
		
		var pub = {
			"getCategories" : function() {
				return sel.categories;
			}
		};
		
		pub.init = function() {
		};
		return pub;
	}());

	page.controller = (function(){
	
		var onChangeCategories = function() {
			page.view.getCategories().on("change", function(event){
				var id = $(this).attr("id");
				var val = $(this).val();
				var successCB = function(data) {};
				var params = "id=" + id + "&useYn=" + val;
				page.model.changeCategoryUseyn(successCB, params);
			});
		};
		
		var pub = {
		};
		
		pub.init = function() {
			page.model.init();
			page.view.init();
			onChangeCategories();
		};
		
		return pub;
	}());
	
	
	common.page.setModel(page.id, page.model);
	common.page.setView(page.id, page.view);
	common.page.setController(page.id, page.controller);
	
	page.controller.init();
});

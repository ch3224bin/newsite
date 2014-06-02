(function(){
	"use strict";
	window.common = {
		"page" : (function(){
			var pub = {};
			
			var model = new Array();
			var view = new Array();
			var controller = new Array();
			
			pub.setModel = function(name, obj) {
				model[name] = obj;
			};
			
			pub.getModel = function(name) {
				return model[name];
			};
			
			pub.setView = function(name, obj) {
				view[name] = obj;
			};
			
			pub.getView = function(name) {
				return view[name];
			};
			
			pub.setController = function(name, obj) {
				controller[name] = obj;
			};
			
			pub.getController = function(name) {
				return controller[name];
			};
			
			return pub;
		}()),
		"view" : (function(){
			var pub = {};
			
			pub.showLoadingBar = function() {
				$.mobile.loading('show');
			};
			
			pub.hideLoadingBar = function() {
				$.mobile.loading('hide');
			};
			
			return pub;
		}())
	};
}());

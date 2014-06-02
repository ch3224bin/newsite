$("#main").on("pageinit", function(){
	"use strict";
	
	var page = {
			id : $(this).attr("id"),
			dom : $(this)
	};
	
	page.model = (function(){
		var pub = {};
		
		pub.getLocalAuthKey = function() {
			var accessToken = localStorage.getItem("ACCESS_TOKEN");
			var tokenSecret = localStorage.getItem("TOKEN_SECRET");
			return {accessToken : accessToken, tokenSecret : tokenSecret};
		};
		
		pub.setLocalAuthKey = function(accessToken, tokenSecret) {
			localStorage.setItem("ACCESS_TOKEN", accessToken);
			localStorage.setItem("TOKEN_SECRET", tokenSecret);
		};
		
		pub.init = function() {
			
		};
		return pub;
	}());

	page.view = (function(){
		
		var sel = {
				"startForm" : $("#startForm", page.dom),
				"finishForm" : $("#finishForm", page.dom),
				"categoryName" : $("#startForm input[name='categoryName']", page.dom),
				"btnSave" : $("#btnSave", page.dom),
				"btnFinish" : $("#btnFinish", page.dom)
		};
		
		var pub = {
			"getStartForm" : function() {
				return sel.startForm;
			},
			"getFinishForm" : function() {
				return sel.finishForm;
			},
			"getCategoryName" : function() {
				return sel.categoryName;
			},
			"setCategoryName" : function(value) {
				return sel.categoryName.val(value);
			},
			"getCategoryNameLabel" : function(categoryId) {
				return $("#startForm label[data-label-id='" + categoryId + "']", page.dom);
			},
			"getSelectedCategoryId" : function() {
				return $("#startForm input[type='radio']:checked", page.dom).val();
			},
			"getBtnSave" : function() {
				return sel.btnSave;
			},
			"getBtnFinish" : function() {
				return sel.btnFinish;
			}
		};
		
		pub.init = function() {
		};
		return pub;
	}());

	page.controller = (function(){
	
		/**
		 * oauth key 저장
		 */
		var processOauthKey = function() {
			var accessToken = $("input[name='access_token']").val();
			var tokenSecret = $("input[name='token_secret']").val();
			if (accessToken && tokenSecret) {
				page.model.setLocalAuthKey(accessToken, tokenSecret);
			}
		};
		
		var onSubmitStartForm = function() {
			page.view.getStartForm().submit(function(event) {
				var categoryId = page.view.getSelectedCategoryId();
				if (!categoryId) {
					alert("카테고리를 선택하세요.");
					event.preventDefault();
					return;
				}
				
				var categoryName = page.view.getCategoryNameLabel(categoryId).text();
				page.view.setCategoryName(categoryName);
			});
		};
		
		var onClickBtnSave = function() {
			var btnSave = page.view.getBtnSave();
			if (btnSave.length > 0) {
				btnSave.on("click", function(){
					var form = page.view.getFinishForm();
					form.attr("action","/update.do");
					form.submit();
				});
			}
		};
		
		var onClickBtnFinish = function() {
			var btnFinish = page.view.getBtnFinish();
			if (btnFinish.length > 0) {
				btnFinish.on("click", function(){
					var form = page.view.getFinishForm();
					form.attr("action", "/finish.do");
					form.submit();
				});
			}
		};
		
		
		var pub = {
			"refresh" : function() {
				window.location.href = "/main.do";
			}
		};
		
		
		
		
		pub.init = function() {
			page.model.init();
			page.view.init();
			processOauthKey();
			onSubmitStartForm();
			onClickBtnSave();
			onClickBtnFinish();
		};
		
		return pub;
	}());
	
	
	common.page.setModel(page.id, page.model);
	common.page.setView(page.id, page.view);
	common.page.setController(page.id, page.controller);
	
	page.controller.init();
});

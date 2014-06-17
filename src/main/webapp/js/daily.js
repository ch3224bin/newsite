$("#daily").on("pageinit", function(){
	"use strict";
	
	var page = {
			id : $(this).attr("id"),
			dom : $(this)
	};
	
	page.model = (function(){
		var pub = {
			"prev" : function(data, successCB, failCB) {
				$.post("/dailyPrev.do", data, successCB, failCB);
			},
			"next" : function(data, successCB, failCB) {
				$.post("/dailyNext.do", data, successCB, failCB);
			},
		};
		
		pub.init = function() {
			
		};
		return pub;
	}());

	page.view = (function(){
		
		var sel = {
			"dailyList" : $("#dailyList"),
			"prev" : $("#prev"),
			"next" : $("#next")
		};
		
		var pub = {
			"getDailyList" : function() {return sel.dailyList;},
			"getPrev" : function() {return sel.prev;},
			"getNext" : function() {return sel.next;}
		};
		
		pub.init = function() {
		};
		return pub;
	}());

	page.controller = (function(){
		
		var refreshPage = function(data) {
			/*
			<li data-role="list-divider">${day.startAt} <span class="ui-li-count">총 ${day.count}건, ${day.min}분</span></li>
		    <c:forEach var="event" items="${day.events}">
		    <li>
		    <h2>${event.categoryName}</h2>
		    <p><strong>${event.title}</strong></p>
		    <div style="font-size: 0.8em;font-weight: normal;"><base:wikiMarkup2Html value="${event.description}" /></div>
		        <p class="ui-li-aside"><strong>${event.min}분</strong>, ${event.startTime} ~ ${event.endTime}</p>
		    </li>
			 */
			page.view.getDailyList().refresh();
		};
		
		var showErrorMsg = function() {
			alert("error.");
		};
		
		var pub = {
			"onClickPrev" : function() {
				page.view.getPrev().on("click", function(){
					page.model.prev(data, refreshPage, showErrorMsg);
				});
			},
			"onClickNext" : function() {
				page.view.getNext().on("click", function(){
					page.model.next(data, refreshPage, showErrorMsg);
				});
			}
		};
		
		pub.init = function() {
			page.model.init();
			page.view.init();
			pub.onClickPrev();
			pub.onClickNext();
		};
		
		return pub;
	}());
	
	
	common.page.setModel(page.id, page.model);
	common.page.setView(page.id, page.view);
	common.page.setController(page.id, page.controller);
	
	page.controller.init();
});

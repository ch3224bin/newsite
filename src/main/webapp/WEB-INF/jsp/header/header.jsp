<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div data-role="header" class="ui-nodisc-icon ui-alt-icon"">
	<a href="#menu_panel" class="ui-btn-left ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-left ui-icon-bars ui-btn-icon-notext">Menu</a>
   	<span class="ui-title"></span>
   	<a href="#settings_popup" data-rel="popup" class="ui-btn-right ui-btn ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-gear ui-btn-icon-notext">Settings</a>
</div>
<div data-role="panel" id="menu_panel" data-position="left" data-display="overlay">
	<ul data-role="listview">
		<li data-role="list-divider">Menu</li>
           <li><a href="/main.do">기록</a></li>
           <li><a href="/daily.do">일간 통계</a></li>
           <li><a href="#">주간 통계</a></li>
           <li><a href="#">월간 통계</a></li>
           <li><a href="#">년간 통계</a></li>
           <li><a href="/categoryManage.do">카테고리 관리</a></li>
           <li><a href="http://calendar.daum.net" rel="external">다음 캘린더</a></li>
           <li data-icon="delete"><a href="#" data-rel="close">Close menu</a></li>
       </ul>    
</div>

<div data-role="popup" id="settings_popup">
	 <ul data-role="listview" data-inset="true">
           <li data-role="list-divider">Settings</li>
           <li><div class="ui-field-contain">
		        <label for="twitter" style="display: inline-block;width: 100px;">Twitter:</label>
		        <select name="twitter" id="twitter" data-role="flipswitch" data-mini="true">
		            <option value="off">Off</option>
		            <option value="on">On</option>
		        </select>
		    </div>
		</li>
           <li><div class="ui-field-contain">
		        <label for="twitter" style="display: inline-block;width: 100px;">GPS:</label>
		        <select name="gps" id="gps" data-role="flipswitch" data-mini="true">
		            <option value="off">Off</option>
		            <option value="on">On</option>
		        </select>
		    </div>
		</li>
       </ul>
</div>

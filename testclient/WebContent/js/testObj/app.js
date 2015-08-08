/**
 * js/testModel/app.js
 */
var app = app || {};

$(function () {
	app.router = new app.TestObjRouter();

	app.items = new app.TestObjs();
	app.items.comparator = 'key';

	app.listView = new app.ListView({collection:app.items});
	app.listView.refresh();
	
	Backbone.history.start();
});
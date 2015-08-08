/**
 * js/testModel/model.js
 */
var app = app || {};

app.TestObj = Backbone.Model.extend({
	urlRoot:'http://127.0.0.1:8080/backbone_jaxrs/rest/testbean',
	defaults:{
		id:null,
		key:null,
		value:null
	}
});
app.TestObjs = Backbone.Collection.extend({
	url: 'http://127.0.0.1:8080/backbone_jaxrs/rest/testbean',
	model: app.TestObj
});

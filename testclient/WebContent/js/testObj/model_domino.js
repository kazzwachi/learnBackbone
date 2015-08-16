/**
 * js/testObj/model.js
 */
var app = app || {};

app.TestObj = Backbone.Model.extend({
	urlRoot:'http://localhost:8080/backbone_jaxrs/rest/testbean',
	defaults:{
		id:null,
		key:null,
		value:null
	}
});
app.TestObjs = Backbone.Collection.extend({
	url: 'http://192.168.56.101/TestServ.nsf/api/data/collections/name/TestView',
	model: app.TestObj,
	comparator: 'key',
	parse: function(response,options){
		$.each(response,function(i,model){
			model.id = model['@unid'];
		});
		$.each(response,function(model){
			console.log(model);
		});
	}
});



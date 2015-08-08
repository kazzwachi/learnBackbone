/**
 * js/testModel/view.js
 */
var app = app || {};

app.View = Backbone.View.extend({
	tagName:'li',
	compiled: _.template($('#model-template').html()),
	initialize: function(options){
		_.bindAll(this,'render');
		this.model.bind('change',this.render);
	},
	render: function(){
		$(this.el).html(this.compiled(this.model.attributes));
		return this;
	}
});
app.CollectionView = Backbone.View.extend({
	el:'#listView',
	intitialize: function(options){
		_.bindAll(this,'render','addToCollectionView');
	},
	render: function(){
		this.collection.each(function(_model){
			var view = new app.View({model:_model});
			$(this.el).append(view.render().el);
		},this);
		console.log($(this.el));
		return this;
	}
});
/**
 * js/testModel/view.js
 */
var app = app || {};

app.lineViewTemplate = _.template($('#item-template-tr').html());
app.dispViewTemplate = _.template($('#item-template-disp').html());
app.editViewTemplate = _.template($('#item-template-edit').html());

app.ListView = Backbone.View.extend({
	el:'.listView',
	initialize: function(options){
		_.bindAll(this,'render','close','refresh');
		this.listenTo(this.collection,'change',this.render);
		this.listenTo(this.collection,'remove',this.render);
	},
	render: function(){
		$(this.el).find('.itemTable').html('');
		this.collection.sort();
		this.collection.each(function(item){
			var lineView = new app.LineView({model:item});
			$(this.el).find('.itemTable').append(lineView.render().el);
		},this);
		return this;
	},
	close: function(){
		this.stopListening(this.collection);
		this.undelegateEvents();
		this.unbind();
	},
	refresh: function(){
		this.collection.fetch({
			success:function(collection,xhr,option){
				app.listView.render();
			}
		});
	}
});
app.LineView = Backbone.View.extend({
	initialize: function(options){
		_.bindAll(this,'render');
	},
	render: function(){
		$(this.el).html(app.lineViewTemplate(this.model.attributes));
		return this;
	}
});
app.ItemView = Backbone.View.extend({
	events:{
		'click .edit'  :'showEditView',
		'click .cancel':'clearView',
		'click .delete':'deleteItem',
		'click .save'  :'saveItem'
	},
	initialize: function(){
		_.bindAll(this,'render','showEditView','clearView','showDispView','deleteItem','saveItem','close');
		this.template = app.editViewTemplate;
		this.listenTo(this.model,'sync',this.showDispView);
		this.listenTo(this.model,'destroy',this.clearView);
	},
	render: function(){
		if(null !== this.model){
			$(this.el).html(this.template(this.model.attributes));			
		}
		return this;
	},
	showEditView: function(){
		this.template = app.editViewTemplate;
		this.render();
	},
	showDispView: function(){
		this.template = app.dispViewTemplate;
		this.render();
	},
	clearView: function(){
		this.model = null;
		$(this.el).html('');
		app.router.navigate('');
	},
	deleteItem: function(){
		app.items.remove(this.model);
		this.model.destroy();
	},
	saveItem: function(){
		this.model.set('key',$('input.key').val());
		this.model.set('value',$('input.value').val());
		this.model.save();
		if(null === this.model.get('id')){
			app.items.add(this.model);
		}
	},
	close: function(){
		this.stopListening(this.model);
		this.undelegateEvents();
		this.unbind();
	}
});
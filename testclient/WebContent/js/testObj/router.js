/**
 * js/lib/testObj/app.js
 */
var app = app || {};

app.TestObjRouter = Backbone.Router.extend({
	routes: {
		'detail/:id': 'showDetail',
		'newItem': 'showNewItemView',
		'list':'refreshList'
	},
	refreshList : function(){
		app.listView.render();
	},
	showDetail : function(id){
		if(app.itemView){
			app.itemView.close();
		}
		app.item = app.items.get(id);
		app.itemView = new app.ItemView({
			el:'.itemView',
			model:app.item
		});
		app.itemView.showDispView();
	},
	
	showNewItemView: function(){
		if(app.itemView){
			app.itemView.close();
		}
		app.item = new app.TestObj({
			key:'(new key)',
			value:'(new value)'
			});
		app.itemView = new app.ItemView({
			el:'.itemView',
			model:app.item
		});
		app.itemView.template = app.editViewTemplate;
		app.itemView.render();
	}
});
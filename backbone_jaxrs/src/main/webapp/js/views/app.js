/**
 * js/views/app.js
 */
var app = app || {};

app.AppView = Backbone.View.extend({
	el: '#todoapp',

	statsTemplate: _.template($('#stats-template').html()),

	//コントローラ
	events: {
		'keypress #new-todo':     'createOnEnter',
		'click #clear-completed': 'clearCompleted',
		'click #toggle-all':      'toggleAllComplete'
	},
	
	initialize: function(){
		this.allCheckbox = this.$('#toggle-all');
		this.$input = this.$('#new-todo');
		this.$footer = this.$('#footer');
		this.$main = this.$('#main');
		
		//app.todoのaddイベントをListenし、addOneに処理させる。
		this.listenTo(app.Todos, 'add', this.addOne);
		//app.todoのresetイベントをListenし、addAllに処理させる。
		this.listenTo(app.Todos, 'reset', this.addAll);
		
		this.listenTo(app.Todos, 'change:completed', this.filterOne);
		
		this.listenTo(app.Todos, 'filter', this.filterAll);
		
		this.listenTo(app.Todos, 'all', this.render);
		
		app.Todos.fetch();
	},
	
	render: function(){
		var completed = app.Todos.completed().length;
		var remaining = app.Todos.remaining().length;
		
		if(app.Todos.length){
			this.$main.show();
			this.$footer.show();

			this.$footer.html(this.statsTemplate({
				completed: completed,
				remaining: remaining
			}));
			
			this.$('#filters li a')
			.removeClass('selected')
			.filter('[href="#/' + (app.TodoFilter || '') + '"]')
			.addClass('selected');
		}else{
			this.$main.hide();
			this.$footer.hide();
		}
	},
	
	//app.todoのaddイベントのハンドラ
	addOne: function(todo){
		var view = new app.TodoView({model: todo});
		$('#todo-list').append(view.render().el);
	},
	//app.todoのresetイベントのハンドラ。
	//リセット時にはローカルストレージから読み込みされるので、addAllを
	addAll: function(){
		this.$('#todo-list').html('');
		//_.each(list,繰り返される処理,呼び出し先でthisとなるもの)
		app.Todos.each(this.addOne, this);
	},
	
	filterOne: function(todo){
		//$().triggerはjQueryの関数。visibleイベントを点火する。
		todo.trigger('visible');
	},
	
	filterAll: function(){
		app.Todos.each(this.filterOne, this);
	},
	
	newAttributes: function(){
		return{
			title :this.$input.val().trim(),
			order :app.Todos.nextOrder(),
			completed: false
		};
	},
	createOnEnter :function(event){
		if(event.which !== ENTER_KEY || !this.$input.val().trim()){
			return;
		}
		app.Todos.create(this.newAttributes());
		this.$input.val('');
	},
	clearCompleted: function(){
		_.invoke(app.Todos.completed(), 'destroy');
		return false;
	},
	toggleAllComplete: function(){
		var completed = this.allCheckbox.checked;
		app.Todos.each(function(todo){
			todo.save({
				'completed': completed
			});
		});
	}
});


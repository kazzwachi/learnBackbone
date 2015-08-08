/**
 * js/testModel/app.js
 */
var app = app || {};

//var testObj,testObjView;
//testObj = new app.TestObj();
//testObj.set({'id':2},{silent: true});
//testObj.fetch().then(function(){
//	testObjView = new app.View({model:testObj});
//	testObjView.render();
//});

var testObjs = new app.TestObjs();
testObjs.fetch().then(function(){
	testObjsView = new app.CollectionView({collection:testObjs});
	testObjsView.render();
});
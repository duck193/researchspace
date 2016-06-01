/**
 * Sentence Application
 */
(function(){
	var app = angular.module('sentence', []);

	app.controller('SentenceController', ['$http', function($http){
		this.sentence = {};
		this.sentence.sortType = "KEY";
		this.sentence.sentence = "";
		this.wordArray = words;
		
		this.checkWords = function(sentence) {
			$http.post('http://localhost:9080/SentenceRS-1.0/sentence/service/sorted', sentence)
			   .success(function(data){
				   sentence = data;
			   })
			   .error(function(){
				   sentence = {};
			   });
		};
	}]);

	/*-----------------------------------------------------------------------------------------*
	 * <title-presentation/>
	 *-----------------------------------------------------------------------------------------*/	
	app.directive('titlePresentation', function(){
		return {
			restrict: 'E',
			templateUrl: 'templates/title-presentation.html'
		};
	});
	
	/*-----------------------------------------------------------------------------------------*
	 * <form-layout/>
	 *-----------------------------------------------------------------------------------------*/
	app.directive('formLayout', function(){
		return {
			restrict: 'E',
			templateUrl: 'templates/form-layout.html',
			controller:['$http', function($http) {
				var sentence = this;
				var wordArray = {};
				
				sentence.sortType = "KEY";
				sentence.sentence = "";
				
				sentence.checkWords = function(sentence, sortType) {
					$http.post('http://localhost:9080/SentenceRS-1.0/sentence/service/sorted', sentence, sortType).success(function(data){
						sentence.words.push(data); 
					});
				};
				
			}],
			controllerAs:'sentenceCtrl'
		};
	});
	
	/*-----------------------------------------------------------------------------------------*
	 * <word-count/> 
	 *-----------------------------------------------------------------------------------------*/
	app.directive('wordCount', function(){
		return {
			restrict: 'E',
			templateUrl: 'templates/word-count.html'
		}
	});
	
	/*-----------------------------------------------------------------------------------------*
	 * Array words
	 *-----------------------------------------------------------------------------------------*/ 
	var words = {
		key: "word",
		value: "some value"	
	}
})();
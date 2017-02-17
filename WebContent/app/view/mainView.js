/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/main.html"], 
function($, _, Utils, page) {
	'use strict';

	return function() {
		this.init = function() {
			this.el = $("#app");
			Utils.load("connect", {"where" : "Connexion"}, function(data) {}, "POST");
			this.render();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
			
			this.checkEvents();
		};
		
		this.checkEvents = function() {
		};
		
		this.init();
	};
});
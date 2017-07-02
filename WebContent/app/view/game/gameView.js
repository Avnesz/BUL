/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/game.html",
        "app/view/game/didactitiel/initView",
        "app/view/game/interface/interfaceView"],
function($, _, Utils, page, Didactitiel, Interface) {
	'use strict';

	return function() {
		this.init = function() {
			this.el = $("#app");
			Utils.load("track", {"where" : "Lancement du jeu"}, function(data) {}, "POST");
			this.render();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
			
			//this.lieu = new Terrain(this);
			
			this.interface = new Interface(this);
			
			var firstTime = false;
			if (firstTime) {
				this.didactitiel = new Didactitiel(this);
			}
		};
		
		this.init();
	};
});
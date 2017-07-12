/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/game.html",
        "app/view/game/didactitiel/initView",
        "app/view/game/interface/interfaceView",
        "app/view/game/lieu/mapView"],
function($, _, Utils, page, Didactitiel, Interface, MapView) {
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
			
			this.map = new MapView(this);
			
			this.interface = new Interface(this);
			
			var firstTime = false;
			if (firstTime) {
				this.didactitiel = new Didactitiel(this);
			}
		};
		
		this.init();
	};
});
/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/game.html",
        "app/view/game/didactitiel/initView",
        "app/view/game/interface/interfaceView",
        "app/view/game/lieu/mapView",
        "app/model/game/playerModel"],
function($, _, Utils, page, Didactitiel, Interface, MapView, PlayerModel) {
	'use strict';

	return function(token) {
		this.init = function(token) {
			this.el = $("#app");
			Utils.load("track", {"where" : "Lancement du jeu"}, function(data) {}, "POST");
			this.player = new PlayerModel(token);
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
			
			this.player.addToInventory("pelle");
			this.player.addToInventory("fleur", 5);
            this.player.pickItem("pelle");
            this.player.pickItem("fleur");
		};
		
		this.init(token);
	};
});
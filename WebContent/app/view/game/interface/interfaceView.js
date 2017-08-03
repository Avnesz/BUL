/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/interface/interface.html",
        "app/view/game/interface/equipementView"],
function($, _, Utils, page, EquipementView) {
	'use strict';

	return function(parent) {
		this.init = function(parent) {
			this.parent = parent;
			this.player = parent.player;
			this.el = $("#interface");
			this.render();
		};

		this.render = function() {
			_.templateSettings.variable = "data";
			var template = _.template(page);
			var templateData = {};
			this.el.html(template(templateData));
			
			this.equipementView = new EquipementView(this.player);
		};
		
		this.init(parent);
	};
});
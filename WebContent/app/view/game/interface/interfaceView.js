/*global define */
define(["jquery",
        'underscore',
        "app/utils/utils",
        "text!app/template/game/interface/interface.html"],
function($, _, Utils, page) {
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
			
			this.makeEvents();
		};
		
		this.makeEvents = function() {
		    var that = this;
		    $(".interface-outils ul .outil").click(function() {
		        var main = $(this).attr("id");
		        that.player.setCursor(main);
		    });
		};
		
		this.init(parent);
	};
});
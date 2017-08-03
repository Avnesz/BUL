/*global define */
define(["jquery"],
function($) {
	'use strict';

	return function(player) {
		this.init = function(player) {
			this.player = player;
			this.el = $("#interface");
			this.render();
		};

		this.render = function() {
			
			this.makeEvents();
		};
		
		this.makeEvents = function() {
			var that = this;
			$(".interface-outils ul .outil").click(function() {
				var main = $(this).attr("id");
		        that.player.setCursor(main);
		    });
		};
		
		this.init(player);
	};
});
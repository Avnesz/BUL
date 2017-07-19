'use strict';
define(["jquery"], function($){
	var data = {
		/**
		 * Outils
		 */
		"pelle" : {
			"name" : "Pelle",
			"texte" : ["Une belle pelle en bois d'acajou magique."],
			"img" : "app/img/item/tools/pelle",
			"use" : function(player, x, y) {
			    player.terrain.update("sol", x, y, "trou")
			},
			"isGraine" : false
		}
	};
	
	return {
		/**
		* Permet d'appeler un WS
		**/
		get : function(key) {
			return $.extend(true, {}, data[key]);
		}
	};
});
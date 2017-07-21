'use strict';
define(["jquery", "app/data/tools"], function($, ToolsData){
	var data = {
		/**
		 * Outils
		 */
		"fleur" : {
			"name" : "Fleur",
			"texte" : ["Une jolie fleur a replanter."],
			"use" : function(player, x, y) {
			    var layers = player.terrain.get(x, y);
			    if (layers.sol.id === "herbe") {
			        player.terrain.modify("layer1", x, y, "fleur");
			    }
			},
			"isGraine" : true,
			"consommable" : true
		}
	};
	
	return {
		/**
		* Permet d'appeler un WS
		**/
		get : function(key) {
			var item = ToolsData.get(key);
			if (item.name) return item;
		    
		    return $.extend(true, {}, data[key]);
		}
	};
});
'use strict';
define(["jquery"], function($, ToolsData){
	var data = {
		"pelle" : {
			"name" : "Pelle",
			"texte" : ["Une belle pelle en bois d'acajou magique."],
			"use" : function(player, x, y) {
			    var layers = player.terrain.get(x, y);
			    
			    if (layers.layer1.id != "") {
			        player.addToInventory(layers.layer1.id, 1);
			        player.terrain.modify("layer1", x, y, "");
			    }else {
			        player.terrain.modify("sol", x, y, "trou");
			    }
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
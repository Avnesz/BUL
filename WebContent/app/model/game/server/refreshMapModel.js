/*global define */
define(["jquery", "app/utils/utils"], 
function($, Utils) {
	'use strict';

	return function() {
	    this.data = {
	        proprietaire : null,
	        lastVersion : null,
    	    modifications : null,
    	    token : null
	    };
	    
	    this.send = function(proprietaire, player, success) {
	        var terrain = player.terrain;
	        this.data.proprietaire = proprietaire;
	        this.data.lastVersion = terrain.version;
	        this.data.modifications = terrain.modifications;
	        this.data.token = player.token;
	        
//	        Utils.load("connect/refreshMap", this.data, function(data) {
//	            terrain.resetModification();
//	            success(data);
//            }, "POST");
	    };
	};
});
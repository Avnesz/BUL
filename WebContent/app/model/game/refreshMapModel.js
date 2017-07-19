/*global define */
define(["jquery", "app/utils/utils"], 
function($, Utils) {
	'use strict';

	return function() {
	    this.data = {
	        proprietaire : null,
	        lastVersion : null,
    	    modifications : null
	    };
	    
	    this.send = function(proprietaire, terrain, success) {
	        this.data.proprietaire = proprietaire;
	        this.data.lastVersion = terrain.version;
	        this.data.modifications = terrain.modifications;
	        
	        Utils.load("refreshMap", this.data, function(data) {
	            terrain.resetModification();
	            success(data);
            }, "POST");
	    };
	};
});
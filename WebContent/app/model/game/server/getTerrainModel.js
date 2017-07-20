/*global define */
define(["jquery", "app/utils/utils"], 
function($, Utils) {
	'use strict';

	return function() {
	    this.data = {
	        token : null,
	        proprietaire : null
	    };
	    
	    this.send = function(proprietaire, token, success) {
	        this.data.proprietaire = proprietaire;
	        this.data.token = token;
	        
	        Utils.load("connect/getTerrain", this.data, function(data) {
	            success(data);
            }, "POST");
	    };
	};
});
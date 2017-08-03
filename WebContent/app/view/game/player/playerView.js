/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function(parent) {
	    this.init = function(parent) {
	    	this.el = "#player";
	    	this.player = parent.player;
	    	this.render();
	    };
	    
	    this.render = function() {
	    	$(this.el).css({
	    		left : 150,
	    		top : 150
	    	});
	    };
	    
	    this.refresh = function() {
	    	var that = this;
	    	//console.log("refresh : ", that.player.position);
	    	$(this.el).css({
	    		left : that.player.position.x,
	    		top : that.player.position.y
	    	});
	    };
	    
	    this.init(parent);
	};
});
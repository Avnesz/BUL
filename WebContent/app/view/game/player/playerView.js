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

	        if (that.player.destination) {
    	    	var sens = {
    	    	        x : 0,
    	    	        y : 0
    	    	};
    	    	
    	    	if (that.player.position.x > that.player.destination.x) sens.x = -1;
    	    	else if (that.player.position.x < that.player.destination.x) sens.x = 1;
    
    	    	if (that.player.position.y > that.player.destination.y) sens.y = -1;
    	    	else if (that.player.position.y < that.player.destination.y) sens.y = 1;
    	    	
    	    	that.player.position.x += that.player.speed * sens.x;
    	    	that.player.position.y += that.player.speed * sens.y;
	        }
	    	
	    	$(this.el).css({
	    		left : that.player.position.x,
	    		top : that.player.position.y
	    	});
	    };
	    
	    this.init(parent);
	};
});
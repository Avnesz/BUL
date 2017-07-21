/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function(item, nbr) {
	    this.item = null;
	    this.nbr = 1;
	    
	    this.init = function(item, nbr) {
	    	this.item = item;
	    	if (nbr) this.nbr = nbr;
	    };
	    
	    this.add = function(nbr) {
	    	this.nbr += nbr;
	    };
	    this.remove = function(nbr) {
	        this.nbr -= nbr;
	    };

	    this.use = function(player, x, y) {
	        this.item.use(player, x, y);
	    };
	    
	    this.init(item, nbr);
	};
});
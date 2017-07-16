/*global define */
define(["jquery"], 
function($) {
	'use strict';

	return function(key, value) {
	    this.key = null;
	    this.value = null;
	    this.nbr = 0;
	    
	    this.init = function(key, value) {
	    	this.key = key;
	    	this.value = value;
	    	this.nbr = 1;
	    };
	    
	    this.add = function(nbr) {
	    	this.nbr += nbr;
	    };
	    
	    this.init();
	};
});
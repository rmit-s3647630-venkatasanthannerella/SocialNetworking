package com.social.exception;


public class NoSuchAgeException extends Exception{
	//author:santhan   
	String str1;
	   /* Constructor of custom exception class
	    * here I am copying the message that we are passing while
	    * throwing the exception to a string and then displaying 
	    * that string along with the message.
	    */
	   public NoSuchAgeException(String str2) {
		str1=str2;
	   }
	   public String toString(){ 
		return ("NoSuchAgeException Occurred: "+str1) ;
	   }
	}
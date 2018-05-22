package com.social.exception;

public class NotToBeFriendsException extends Exception{
	//author:santhan  
	String str1;
	   /* Constructor of custom exception class
	    * here I am copying the message that we are passing while
	    * throwing the exception to a string and then displaying 
	    * that string along with the message.
	    */
	   public NotToBeFriendsException(String str2) {
		str1=str2;
	   }
	   public String toString(){ 
		return ("NotToBeFriendsException Occurred: "+str1) ;
	   }
	}
KML Applet
==========

	A Java applet that interprets KiSS Markup Language (KML) files in a similar 
	way to a DVD Player from KiSS Technologies.
	
	The purpose of this applets is twofolds
	
	* Let you show of your KML site to potential users
	* Eases debugging during development of your kiss site
	
LICENSE
	
	The code is licensed under the GPL license. See 
	
	http://www.gnu.org/copyleft/gpl.html
	
	for more info. You can publish modified versions of this code however I would prefer
	that you contribute modifications at http://developer.berlios.de/projects/kmlapplet/.
	
	You can use the code in your own projects as long as it is also GPL'ed. 

How to use
----------

	Standalone
	----------
	
	You can use it as a standalone browser, implemented in the class

		dk.profundo.kmlbrowser.Browser

	You can start the browser by double clicking the jar file or by using the java
	webstart link.
	
	Applet
	------
	
	The problem with unsigned applets are that they are only allowed to open network
	to the same site as they themselves are hosted. If that is not a problem you can
	use the following applet tag, and replace the url parameter value.
	
	<applet archive="kmlbrowser.jar"
			code="dk.profundo.kmlbrowser.KMLApplet" width="720" height="512">
  			<param name="url" value="http://kml.profundo.dk/index.kml">
	</applet>

Status
------

	It is currently in the "Works for me" phase, however some things can be 
	improved.
	
	* Find fonts that more accurately matches the sizes of the KiSS fonts.
	* Write a custom HTTP library that imitates more accurately the KiSS boxes 
	  so that sites wouldn't know the difference.
	* Verify that is works in the same way as a Kiss box.
	* Sign applet so that it is allowed to connect to other sites.
	  
	So you are very welcome to contribute.
	
Inspiration
-----------

	The code is written by me however the inspiration came from the 
	
		http://kiss.breuking.com/kmlbrowser/kmlbrowser.html
	
	project.
  
/Erik Martino <erik . martino (at) gmail . com>


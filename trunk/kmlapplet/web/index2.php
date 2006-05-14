<?php 
	header('Content-type: text/xml'); 
	echo "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>"; 
?>
<KMLPAGE version="2.0">
<?php
$urls = array (
		'http://kml.profundo.dk/test/fonts.php', 'Fonts',
		'http://webradio2.kiss-technology.com/webradio3/kml/index.php', 'Web Radio 3'
		);
?>
<?php	
	$x = 60;
	$y = 70;
	for($i=0;$i+1 < sizeof($urls);$i+=2) {
		$url = $urls[$i];
		$name = $urls[$i+1];
?>
<MENUITEM id="<?= $i ?>">
<ONCLICK><![CDATA[ 
<GOTO href='<?= $url ?>' />
]]></ONCLICK>
<ONHILITE><![CDATA[<TEXT coords='<?= $x ?>,<?= $y ?>' font="0" pen="255"><?= $name ?></TEXT>]]></ONHILITE>
<ONUNHILITE><![CDATA[<TEXT coords='<?= $x ?>,<?= $y ?>' font="0" pen="16"><?= $name ?></TEXT>]]></ONUNHILITE>
</MENUITEM>
<?php
	$y += 20;
	}
?>

	<KEY id="93">
	<ONCLICK><![CDATA[
	<GOTO href="http://kml.profundo.dk/index.kml" />
	]]></ONCLICK>
	</KEY>

	<BACKGROUND vgradient="146,208,250-11,47,72" />
	<FILLRECT coords="0,0,720,479" pen="0" />
	<FILLRECT coords="0,0,720,56" pen="250" />
	
	<FIP speed="2" intensity="6">Static</FIP>
	
	<TEXT coords="75,1" font="3" pen="16">Erik Martino's Links</TEXT>
	<TEXT coords="74,0" font="3" pen="255">Erik Martino's Links</TEXT>
</KMLPAGE>

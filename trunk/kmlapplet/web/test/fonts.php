<?php 
	header('Content-type: text/xml'); 
	echo "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>"; 
?>
<KMLPAGE version="2.0">
	<KEY id="93">
	<ONCLICK><![CDATA[
	<GOTO href="http://kml.profundo.dk/index2.php" />
	]]></ONCLICK>
	</KEY>

	<BACKGROUND vgradient="146,208,250-11,47,72" />
	<FILLRECT coords="0,0,720,479" pen="0" />
	<FILLRECT coords="0,0,720,56" pen="250" />
	
	<RECT coords="0,0,300,512" pen="255" />
	<RECT coords="0,0,200,512" pen="255" />
	<RECT coords="0,0,100,512" pen="255" />
	
	<RECT coords="0,0,800,100" pen="255" />
	<RECT coords="0,0,800,200" pen="255" />
	<RECT coords="0,0,800,300" pen="255" />
	
	<?php	
	$x = 60;
	$y = 70;
	for($i=0;$i<8;$i++) {
?>
<TEXT coords='<?= $x ?>,<?= $y ?>' font="<?= $i ?>" pen="255">MMMMMMMMMM <?= $i ?></TEXT>
<TEXT coords='<?= $x ?>,<?= $y+40 ?>' font="<?= $i ?>" pen="255">Kiss Technologies <?= $i ?></TEXT>
<?php
	$y += 80;
	}
?>
	
	
	<FIP speed="2" intensity="6">Fonts</FIP>
	
	<TEXT coords="75,1" font="3" pen="16">Font test</TEXT>
	<TEXT coords="74,0" font="3" pen="255">Font test</TEXT>
</KMLPAGE>

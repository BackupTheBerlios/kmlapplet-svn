<?php header('Content-type: text/xml'); echo "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>"; 
/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; version 2 of the License.
  
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details. */

$urls = array (
		# 'www.tv2oj.dk/tv2oj_RSS.asp', 'TV2 &#216;stjylland',
		'politiken.dk/rss/senestenyt.rss', 'Politiken',
		'rss.slashdot.org/Slashdot/slashdot/.', 'Slashdot',
		'www.jp.dk/rss/.', 'Jyllandsposten',
		'osnews.com/files/recent.rdf', 'OSNews', 
		'www.appleinsider.com/appleinsider.rss', 'Apple Insider',
		'sporten.tv2.dk/rss/sporten.xml', 'TV2 Sporten',
		'digg.com/rss/index.xml', 'Digg',
		'rss.news.yahoo.com/rss/topstories/.', 'Yahoo News');
$back = $_GET['back'] + 0;
?><KMLPAGE version="2.0">
<?php	
	$x = 60;
	$y = 70;
	for($i=0;$i+1 < sizeof($urls);$i+=2) {
		$url = $urls[$i];
		$name = $urls[$i+1];
?>
<MENUITEM id="<?= $i ?>">
<ONCLICK><![CDATA[ 
<GOTO href='http://kml.profundo.dk/kissrss.php?rss=<?= $url ?>&amp;back=<?= $i ?>' />
]]></ONCLICK>
<ONHILITE><![CDATA[<TEXT coords='<?= $x ?>,<?= $y ?>' font="0" pen="255"><?= $name ?></TEXT>]]></ONHILITE>
<ONUNHILITE><![CDATA[<TEXT coords='<?= $x ?>,<?= $y ?>' font="0" pen="16"><?= $name ?></TEXT>]]></ONUNHILITE>
</MENUITEM>
<?php
	$y += 20;
	}
?>
<BACKGROUND vgradient="146,208,250-11,47,72" />
<FILLRECT coords="0,0,720,479" pen="0" />
<FILLRECT coords="0,0,720,56" pen="250" />

<FIP speed="2" intensity="6">Profundo RSS Viewer</FIP>

<TEXT coords="75,1" font="3" pen="16">Profundo RSS Viewer</TEXT>
<TEXT coords="74,0" font="3" pen="255">Profundo RSS Viewer</TEXT>
<MENUSELECT item='<?= $back ?>' />
</KMLPAGE>

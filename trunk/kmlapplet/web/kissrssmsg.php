<?php header('Content-type: text/xml'); echo "<?xml version='1.0' encoding='ISO-8859-1' standalone='yes'?>"; 
/*
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; version 2 of the License.
  
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details. */

define('MAGPIE_DIR', 'magpierss/');
require_once(MAGPIE_DIR.'rss_fetch.inc');

$search = array ('@<[^<]+>@',
				 '@&quot;@',
				 '@&amp;@',
                 '@&@',          
                 '@"@',
              	 '@<@',
              	 '@[\r\n\s]+@'); // evaluate as php

$replace = array (' ',
				 '"',
				 '&',
                 '&#38;',
                 '&#34;',
                 '',
                 ' ');

$rssurl = 'rss.slashdot.org/Slashdot/slashdot';
if ($_GET['rss']) $rssurl = $_GET['rss'];

$rss = fetch_rss( "http://" . $rssurl );
?>
<KMLPAGE version="2.0">
<LOADIMAGE id="e2962372" href="http://kml.profundo.dk/commandbar.gif" />
<BACKGROUND vgradient="146,208,250-11,47,72" />
<FILLRECT coords="0,0,719,479" pen="0" />
<FILLRECT coords="0,0,719,56" pen="250" />
<IMAGE coords="84,481" id="e2962372" />
<TEXT coords="583,32" font="0" pen="16"></TEXT>
<?php
	$article = $_GET['art'] + 0;
	$back = $_GET['back'] + 0;
	$backurl = 'http://kml.profundo.dk/kissrss.php?rss=' .  $rssurl . '&amp;back=' . $back . '&amp;art=' . $article;
		$url = 'http://kml.profundo.dk/kissrssmsg.php';
	
	function goto($article) {
		global $url,$rssurl;
		return $url . "?" . "art=" . $article . "&amp;rss=" . $rssurl . 
				"&amp;back=" . $back ;
	}
	
	function wordwrapLine($s, $l) {
	  $tok = strtok($s, " ");
	  while (strlen($tok) != 0) {
	    if (strlen($line) + strlen($tok) < ($l + 2) ) {
	      $line .= " $tok";
	    }
	    else {
	      $formatted .= "$line\n";
	      $line = $tok;
	    }
	    $tok = strtok(" ");
	  }
	  $formatted .= $line;
	  $formatted = trim($formatted);
	  return split("\n",$formatted);
	}
	
	$x = 60;
	$y = 70;

	$item = $rss->items[$article];
	if ($item) {
		$href = $item['link'];
		$title = preg_replace($search, $replace,  $item['title']);
		$desc = wordwrapLine(preg_replace($search, $replace, $item['description']),60);
		for($i=0; $i<sizeof($desc); $i++) {  
?>
<TEXT coords='<?php echo "$x,$y"; ?>' font="0" pen="16"><?= $desc[$i] ?></TEXT>
<?php
		$y += 20;
	}
?>
<TEXT coords="75,11" font="1" pen="16"><?= $title ?></TEXT>
<TEXT coords="74,10" font="1" pen="255"><?= $title ?></TEXT>
<?
	}
?>
<!-- Button: next -->
<KEY id="16388">
<ONCLICK><![CDATA[
<GOTO href="<?= goto($article+1) ?>" />
]]></ONCLICK>
</KEY>
<!-- Button: previous -->
<KEY id="16389">
<ONCLICK><![CDATA[
<GOTO href="<?= goto($article-1) ?>" />
]]></ONCLICK>
</KEY>
<KEY id="93">
<ONCLICK><![CDATA[
<GOTO href="<?= $backurl ?>" />
]]></ONCLICK>
</KEY>
<KEY id="16387">
<ONCLICK><![CDATA[
<GOTO href="<?= $backurl ?>" />
]]></ONCLICK>
</KEY>
<FIP speed="2" intensity="6">Profundo RSS Viewer</FIP>
</KMLPAGE>

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

$search = array ('@<[^<]+>@', 		 // Strip out javascript*
				 '@&quot;@',
				 '@&amp;@',
                 '@&@',          // Strip out HTML tags
                 '@"@',
              	 '@<@',
              	 '@([\r\n])[\s]+@');         // evaluate as php

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
<?php
	$article = $_GET['art'] + 0;
	$back = $_GET['back'] + 0;
	$pagesize = 10;
	$first = $article - ($article % $pagesize);
	
	$url = 'http://kml.profundo.dk/kissrss.php';
	$msgurl = 'http://kml.profundo.dk/kissrssmsg.php';
	
	function goto($article) {
		global $url,$rssurl,$back;
		return $url . "?" . "art=" . $article . "&amp;rss=" . $rssurl . "&amp;back=" . $back;
	}
	
	function gotoMsg($article) {
		global $rssurl,$back,$msgurl;
		return $msgurl . "?" . "art=" . $article . "&amp;rss=" . $rssurl . "&amp;back=" . $back;
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
	for($i=0;$i<$pagesize;$i++) {
		$n = $first + $i;
		$item = $rss->items[$n];
		if ($item) {
			$href = $item['link'];
            $title = $item['title'];
            $title = preg_replace($search, $replace, $title);
            $desc = $item['description'];
            $desc = preg_replace($search, $replace, $desc);
            $desc = wordwrapLine($desc,60);
            
?>
<MENUITEM id="<?= $n ?>">
<? if ($i == 0) { ?>
<ONUP><![CDATA[<GOTO href="<?= goto($n > 0 ? $n-1 : sizeof($rss->items)-1) ?>" />]]></ONUP>
<? } ?>
<? if ($i == $pagesize - 1 || $n+1 == sizeof($rss->items)) { ?>
<ONDOWN><![CDATA[<GOTO href="<?= goto($n+1 < sizeof($rss->items) ? $n+1 : 0) ?>" />]]></ONDOWN>
<? } ?>
<ONCLICK><![CDATA[ 
<GOTO href='<?= gotoMsg($n) ?>' />
]]></ONCLICK>
<ONHILITE><![CDATA[
<FILLRECT coords="60,275,608,200" pen="250" />
<TEXT coords='<?php echo "$x,$y"; ?>' font="0" pen="255"><?= $title ?></TEXT>
<? for($j=0; $j < 9; $j++) { if ($desc[$j]) {
 ?><TEXT coords='60,<?= 280 + $j * 20 ?>' font="0" pen="255"><?= $desc[$j] ?></TEXT><? 
 } }
?>]]></ONHILITE>
<ONUNHILITE><![CDATA[
<TEXT coords='<?php echo "$x,$y"; ?>' font="0" pen="16"><?= $title ?></TEXT>
]]></ONUNHILITE>
</MENUITEM>
<?php
		}
	$y += 20;
	}
?>
<!-- Button: next -->
<KEY id="16388">
<ONCLICK><![CDATA[
<GOTO href="<?= goto($article+$pagesize) ?>" />
]]></ONCLICK>
</KEY>
<!-- Button: previous -->
<KEY id="16389">
<ONCLICK><![CDATA[
<GOTO href="<?= goto($article-$pagesize) ?>" />
]]></ONCLICK>
</KEY>
<KEY id="93">
<ONCLICK><![CDATA[
<GOTO href="http://kml.profundo.dk/kissfront.php?back=<?= $back ?>" />
]]></ONCLICK>
</KEY>
<BACKGROUND vgradient="146,208,250-11,47,72" />
<FILLRECT coords="0,0,719,479" pen="0" />
<FILLRECT coords="0,0,719,56" pen="250" />

<IMAGE coords="84,481" id="e2962372" />

<TEXT coords="583,32" font="0" pen="16"><?= ($first+1).'-' . min(sizeof($rss->items),$first+$pagesize) . '/' . sizeof($rss->items) ?></TEXT>
<FIP speed="2" intensity="6">Profundo RSS Viewer</FIP>
<TEXT coords="75,1" font="3" pen="16"><?= $rss->channel['title'] ?></TEXT>
<TEXT coords="74,0" font="3" pen="255"><?= $rss->channel['title'] ?></TEXT>
<MENUSELECT item='<?= $article ?>' />
</KMLPAGE>

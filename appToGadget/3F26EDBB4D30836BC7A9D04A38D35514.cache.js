(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'my',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var p='com.my.client.My',l='hello',n='moduleStartup',o='onModuleLoadStart',m='startup';var _;function lb(){}
_=lb.prototype={};function r(){}
_=r.prototype=new lb();function gb(){$wnd.document.write(l)}
function F(){}
_=F.prototype=new r();_.a=gb;function bb(a){a.a();return a}
function ab(){}
_=ab.prototype=new F();_=String.prototype;function hb(){!!$stats&&$stats({moduleName:$moduleName,subSystem:m,evtGroup:n,millis:(new Date()).getTime(),type:o,className:p});bb(new ab())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{hb()}catch(a){b(d)}else{hb()}}
$stats && $stats({moduleName:'my',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (my) my.onScriptLoad(gwtOnLoad);})();
(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var u='com.example.friends.client.Friends',s='moduleStartup',t='onModuleLoadStart',r='startup',q='viewer';var _;function sb(){}
_=sb.prototype={};function ab(){}
_=ab.prototype=new sb();function C(a){var b=a.get(q).getData();$wnd.alert(b.getDisplayName())}
function v(){}
_=v.prototype=new ab();_.a=C;function x(b){var a;a=$wnd.opensocial.newDataRequest();a.add(a.newFetchPersonRequest(opensocial.IdSpec.PersonId.VIEWER),q);a.send(b.a);return b}
function w(){}
_=w.prototype=new v();_=String.prototype;function ob(){!!$stats&&$stats({moduleName:$moduleName,subSystem:r,evtGroup:s,millis:(new Date()).getTime(),type:t,className:u});x(new w())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{ob()}catch(a){b(d)}else{ob()}}
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (friends) friends.onScriptLoad(gwtOnLoad);})();
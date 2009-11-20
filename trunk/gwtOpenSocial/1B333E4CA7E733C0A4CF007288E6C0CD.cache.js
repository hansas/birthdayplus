(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var r='Hello World!',v='com.example.friends.client.Friends',t='moduleStartup',u='onModuleLoadStart',s='startup',q='viewer';var _;function xb(){}
_=xb.prototype={};function cb(){}
_=cb.prototype=new xb();function E(a){var b=a.get(q).getData();$wnd.alert(b.getDisplayName())}
function w(){}
_=w.prototype=new cb();_.a=E;function y(b){var a;$wnd.alert(r);a=$wnd.opensocial.newDataRequest();a.add(a.newFetchPersonRequest(opensocial.IdSpec.PersonId.VIEWER),q);a.send(b.a);$wnd.alert(r);return b}
function x(){}
_=x.prototype=new w();_=String.prototype;function tb(){!!$stats&&$stats({moduleName:$moduleName,subSystem:s,evtGroup:t,millis:(new Date()).getTime(),type:u,className:v});y(new x())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{tb()}catch(a){b(d)}else{tb()}}
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (friends) friends.onScriptLoad(gwtOnLoad);})();
(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var s='Hello World!',w='com.example.friends.client.Friends',u='moduleStartup',r='noop',v='onModuleLoadStart',t='startup',q='viewer';var _;function xb(){}
_=xb.prototype={};function cb(){}
_=cb.prototype=new xb();function E(a){if(!a.hadError()){var b=a.get(q).getData();$wnd.alert(b.getDisplayName())}else{$wnd.alert(r)}}
function x(){}
_=x.prototype=new cb();_.a=E;function z(b){var a;a=$wnd.opensocial.newDataRequest();a.add(a.newFetchPersonRequest(opensocial.IdSpec.PersonId.VIEWER),q);$wnd.document.write(s);a.send(b.a);$wnd.alert(s);return b}
function y(){}
_=y.prototype=new x();_=String.prototype;function tb(){!!$stats&&$stats({moduleName:$moduleName,subSystem:t,evtGroup:u,millis:(new Date()).getTime(),type:v,className:w});z(new y())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{tb()}catch(a){b(d)}else{tb()}}
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (friends) friends.onScriptLoad(gwtOnLoad);})();
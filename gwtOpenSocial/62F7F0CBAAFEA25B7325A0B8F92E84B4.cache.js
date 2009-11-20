(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var s='Hello World!',w='com.example.friends.client.Friends',u='moduleStartup',r='noop',v='onModuleLoadStart',t='startup',q='viewer';var _;function yb(){}
_=yb.prototype={};function db(){}
_=db.prototype=new yb();function F(a){if(!a.hadError()){var b=a.get(q).getData();$wnd.alert(b.getDisplayName())}else{$wnd.alert(r)}}
function x(){}
_=x.prototype=new db();_.a=F;function z(b){var a;$wnd.alert(s);a=$wnd.opensocial.newDataRequest();a.add(a.newFetchPersonRequest(opensocial.IdSpec.PersonId.VIEWER),q);a.send(b.a);$wnd.alert(s);return b}
function y(){}
_=y.prototype=new x();_=String.prototype;function ub(){!!$stats&&$stats({moduleName:$moduleName,subSystem:t,evtGroup:u,millis:(new Date()).getTime(),type:v,className:w});z(new y())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{ub()}catch(a){b(d)}else{ub()}}
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (friends) friends.onScriptLoad(gwtOnLoad);})();
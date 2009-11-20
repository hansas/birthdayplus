(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var t='Hello World!',x='com.example.friends.client.Friends',s='getName',v='moduleStartup',r='noop',w='onModuleLoadStart',u='startup',q='viewer';var _;function yb(){}
_=yb.prototype={};function db(){}
_=db.prototype=new yb();function F(a){if(!a.hadError()){var b=a.get(q).getData();$wnd.alert(b.getDisplayName())}else{$wnd.alert(r)}}
function y(){}
_=y.prototype=new db();_.a=F;function A(b){var a;a=$wnd.opensocial.newDataRequest();a.add(a.newFetchPersonRequest(opensocial.IdSpec.PersonId.VIEWER),q);$wnd.alert(s);a.send(b.a);$wnd.alert(t);return b}
function z(){}
_=z.prototype=new y();_=String.prototype;function ub(){!!$stats&&$stats({moduleName:$moduleName,subSystem:u,evtGroup:v,millis:(new Date()).getTime(),type:w,className:x});A(new z())}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{ub()}catch(a){b(d)}else{ub()}}
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (friends) friends.onScriptLoad(gwtOnLoad);})();
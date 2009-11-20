(function(){
var $gwt_version = "1.7.1";
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});
var $intern_3 = 'Hello World!', $intern_2 = 'VIEWER', $intern_7 = 'com.example.friends.client.Friends', $intern_5 = 'moduleStartup', $intern_1 = 'noop', $intern_6 = 'onModuleLoadStart', $intern_4 = 'startup', $intern_0 = 'viewer';
var _;
function java_lang_Object(){
}

_ = java_lang_Object.prototype = {};
function com_google_gwt_gadgets_client_Gadget(){
}

_ = com_google_gwt_gadgets_client_Gadget.prototype = new java_lang_Object();
function com_example_friends_client_Friends_onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2(data){
  if (!data.hadError()) {
    var viewer = data.get($intern_0).getData();
    $wnd.alert(viewer.getDisplayName());
  }
   else {
    $wnd.alert($intern_1);
  }
}

function com_example_friends_client_Friends(){
}

_ = com_example_friends_client_Friends.prototype = new com_google_gwt_gadgets_client_Gadget();
_.onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2 = com_example_friends_client_Friends_onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2;
function com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(this$static){
  var com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0;
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0 = $wnd.opensocial.newDataRequest();
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.add(com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.newFetchPersonRequest($intern_2), $intern_0);
  $wnd.document.write($intern_3);
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.send(this$static.onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2);
  return this$static;
}

function com_example_friends_client_FriendsGadgetImpl(){
}

_ = com_example_friends_client_FriendsGadgetImpl.prototype = new com_example_friends_client_Friends();
_ = String.prototype;
function init(){
  !!$stats && $stats({moduleName:$moduleName, subSystem:$intern_4, evtGroup:$intern_5, millis:(new Date()).getTime(), type:$intern_6, className:$intern_7});
  com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(new com_example_friends_client_FriendsGadgetImpl());
}

function gwtOnLoad(errFn, modName, modBase){
  $moduleName = modName;
  $moduleBase = modBase;
  if (errFn)
    try {
      init();
    }
     catch (e) {
      errFn(modName);
    }
   else {
    init();
  }
}

$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});
if (friends) friends.onScriptLoad(gwtOnLoad);
})();

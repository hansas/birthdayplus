(function(){
var $gwt_version = "1.7.1";
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});
var $intern_8 = '', $intern_5 = '403', $intern_7 = 'An unexpected error occurred.', $intern_10 = 'FRIENDS', $intern_6 = 'Please adjust the gadgets settings to use social features', $intern_9 = 'VIEWER', $intern_14 = 'com.example.friends.client.Friends', $intern_4 = 'forbidden', $intern_3 = 'i got the id', $intern_2 = 'i want to start', $intern_12 = 'moduleStartup', $intern_13 = 'onModuleLoadStart', $intern_11 = 'startup', $intern_0 = 'viewer', $intern_1 = 'viewerFriends';
var _;
function java_lang_Object(){
}

_ = java_lang_Object.prototype = {};
function com_google_gwt_gadgets_client_Gadget(){
}

_ = com_google_gwt_gadgets_client_Gadget.prototype = new java_lang_Object();
function com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(feature){
}

function com_example_friends_client_Friends_onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2(data){
  var user = data.get($intern_0);
  if (!user.hadError()) {
    var viewer = data.get($intern_0).getData();
    var viewerFriends = data.get($intern_1).getData();
    $wnd.document.write($intern_2);
    that.com_example_friends_client_Friends_userId = viewer.getId();
    $wnd.document.write($intern_3);
  }
   else {
    if (user.getErrorCode() == $intern_4 || user.getErrorCode() == $intern_5) {
      $wnd.document.write($intern_6);
    }
     else {
      $wnd.document.write($intern_7);
    }
  }
}

function com_example_friends_client_Friends(){
}

_ = com_example_friends_client_Friends.prototype = new com_google_gwt_gadgets_client_Gadget();
_.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2 = com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2;
_.onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2 = com_example_friends_client_Friends_onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2;
_.com_example_friends_client_Friends_userId = $intern_8;
function com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(this$static){
  var com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0, com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_viewerFriends_0, com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_opt_params_0;
  java_util_AbstractHashMap_$clearImpl__Ljava_util_AbstractHashMap_2(new java_util_HashMap());
  this$static.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(new com_example_friends_client_OpenSocialFeature());
  that = this$static;
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0 = $wnd.opensocial.newDataRequest();
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0.add(com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0.newFetchPersonRequest($intern_9), $intern_0);
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_viewerFriends_0 = $wnd.opensocial.newIdSpec({userId:$intern_9, groupId:$intern_10});
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_opt_params_0 = {};
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_opt_params_0[$wnd.opensocial.DataRequest.PeopleRequestFields.MAX] = 100;
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0.add(com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0.newFetchPeopleRequest(com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_viewerFriends_0, com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_opt_params_0), $intern_1);
  com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0.send(this$static.onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2);
  return this$static;
}

function com_example_friends_client_FriendsGadgetImpl(){
}

_ = com_example_friends_client_FriendsGadgetImpl.prototype = new com_example_friends_client_Friends();
function com_example_friends_client_OpenSocialFeature(){
}

_ = com_example_friends_client_OpenSocialFeature.prototype = new java_lang_Object();
_ = String.prototype;
function java_util_AbstractMap(){
}

_ = java_util_AbstractMap.prototype = new java_lang_Object();
function java_util_AbstractHashMap_$clearImpl__Ljava_util_AbstractHashMap_2(this$static){
}

function java_util_AbstractHashMap(){
}

_ = java_util_AbstractHashMap.prototype = new java_util_AbstractMap();
function java_util_HashMap(){
}

_ = java_util_HashMap.prototype = new java_util_AbstractHashMap();
function init(){
  !!$stats && $stats({moduleName:$moduleName, subSystem:$intern_11, evtGroup:$intern_12, millis:(new Date()).getTime(), type:$intern_13, className:$intern_14});
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

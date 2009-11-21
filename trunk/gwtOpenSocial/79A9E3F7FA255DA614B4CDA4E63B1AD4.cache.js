(function(){
var $gwt_version = "1.7.1";
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});
var $intern_4 = '', $intern_2 = ' ', $intern_6 = 'FRIENDS', $intern_5 = 'VIEWER', $intern_10 = 'com.example.friends.client.Friends', $intern_8 = 'moduleStartup', $intern_3 = 'noop', $intern_9 = 'onModuleLoadStart', $intern_7 = 'startup', $intern_0 = 'viewer', $intern_1 = 'viewerFriends';
var _;
function java_lang_Object(){
}

_ = java_lang_Object.prototype = {};
function com_google_gwt_gadgets_client_Gadget(){
}

_ = com_google_gwt_gadgets_client_Gadget.prototype = new java_lang_Object();
function com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(feature){
}

function com_example_friends_client_Friends_onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2(data){
  if (!data.hadError()) {
    var viewer = data.get($intern_0).getData();
    var viewerFriends = data.get($intern_1).getData();
    this.com_example_friends_client_Friends_viewerId = viewer.getId();
    this.com_example_friends_client_Friends_viewerName = viewer.getDisplayName();
    $wnd.document.write(this.com_example_friends_client_Friends_viewerId + $intern_2 + this.com_example_friends_client_Friends_viewerName);
    viewerFriends.each(function(person){
      if (person.getId()) {
        $wnd.document.write(person.getId());
        $wnd.document.write($intern_2);
        $wnd.document.write(person.getDisplayName());
      }
    }
    );
  }
   else {
    $wnd.document.write($intern_3);
  }
}

function com_example_friends_client_Friends(){
}

_ = com_example_friends_client_Friends.prototype = new com_google_gwt_gadgets_client_Gadget();
_.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2 = com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2;
_.onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2 = com_example_friends_client_Friends_onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2;
_.com_example_friends_client_Friends_viewerId = -1;
_.com_example_friends_client_Friends_viewerName = $intern_4;
function com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(this$static){
  var com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0, com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_viewerFriends_0, com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_opt_params_0;
  this$static.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(new com_example_friends_client_OpenSocialFeature());
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0 = $wnd.opensocial.newDataRequest();
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.add(com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.newFetchPersonRequest($intern_5), $intern_0);
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_viewerFriends_0 = $wnd.opensocial.newIdSpec({userId:$intern_5, groupId:$intern_6});
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_opt_params_0 = {};
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_opt_params_0[$wnd.opensocial.DataRequest.PeopleRequestFields.MAX] = 100;
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.add(com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.newFetchPeopleRequest(com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_viewerFriends_0, com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_opt_params_0), $intern_1);
  com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0.send(this$static.onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2);
  return this$static;
}

function com_example_friends_client_FriendsGadgetImpl(){
}

_ = com_example_friends_client_FriendsGadgetImpl.prototype = new com_example_friends_client_Friends();
function com_example_friends_client_OpenSocialFeature(){
}

_ = com_example_friends_client_OpenSocialFeature.prototype = new java_lang_Object();
_ = String.prototype;
function init(){
  !!$stats && $stats({moduleName:$moduleName, subSystem:$intern_7, evtGroup:$intern_8, millis:(new Date()).getTime(), type:$intern_9, className:$intern_10});
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

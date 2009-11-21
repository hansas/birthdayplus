(function(){
var $gwt_version = "1.7.1";
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});
var $intern_3 = ' ', $intern_6 = 'FRIENDS', $intern_12 = 'Object;', $intern_5 = 'VIEWER', $intern_11 = '[Ljava.lang.', $intern_10 = 'com.example.friends.client.Friends', $intern_8 = 'moduleStartup', $intern_2 = 'name', $intern_4 = 'noop', $intern_9 = 'onModuleLoadStart', $intern_7 = 'startup', $intern_0 = 'viewer', $intern_1 = 'viewerFriends';
var _;
function java_lang_Object(){
}

_ = java_lang_Object.prototype = {};
_.java_lang_Object_typeId$ = 1;
function com_google_gwt_gadgets_client_Gadget(){
}

_ = com_google_gwt_gadgets_client_Gadget.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(feature){
}

function com_example_friends_client_Friends_onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2(data){
  if (!data.hadError()) {
    var viewer = data.get($intern_0).getData();
    var viewerFriends = data.get($intern_1).getData();
    var us = this.com_example_friends_client_Friends_user;
    us.setName__Ljava_lang_String_2($intern_2);
    viewerFriends.each(function(person){
      if (person.getId()) {
        $wnd.document.write(person.getId());
        $wnd.document.write($intern_3);
        $wnd.document.write(person.getDisplayName());
      }
    }
    );
  }
   else {
    $wnd.document.write($intern_4);
  }
}

function com_example_friends_client_Friends(){
}

_ = com_example_friends_client_Friends.prototype = new com_google_gwt_gadgets_client_Gadget();
_.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2 = com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2;
_.onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2 = com_example_friends_client_Friends_onLoadFriends__Lcom_google_gwt_core_client_JavaScriptObject_2;
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(this$static){
  var com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_req_0, com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_viewerFriends_0, com_example_friends_client_Friends_$getName__Lcom_example_friends_client_Friends_2_opt_params_0;
  this$static.com_example_friends_client_Friends_user = new com_example_friends_client_Person();
  java_util_ArrayList_$ArrayList__Ljava_util_ArrayList_2(new java_util_ArrayList());
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
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_OpenSocialFeature(){
}

_ = com_example_friends_client_OpenSocialFeature.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_Person_setName__Ljava_lang_String_2(name){
}

function com_example_friends_client_Person(){
}

_ = com_example_friends_client_Person.prototype = new java_lang_Object();
_.setName__Ljava_lang_String_2 = com_example_friends_client_Person_setName__Ljava_lang_String_2;
_.java_lang_Object_typeId$ = 0;
function com_google_gwt_lang_Array_createFromSeed__II(seedType, length){
  var array = new Array(length);
  if (seedType > 0) {
    var value = [null, 0, false, [0, 0]][seedType];
    for (var i = 0; i < length; ++i) {
      array[i] = value;
    }
  }
  return array;
}

function com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(arrayClass, typeId, queryId, length, seedType){
  var result;
  result = com_google_gwt_lang_Array_createFromSeed__II(seedType, length);
  com_google_gwt_lang_Array$ExpandoWrapper_$clinit__();
  com_google_gwt_lang_Array$ExpandoWrapper_wrapArray__Lcom_google_gwt_lang_Array_2Ljava_lang_Object_2Ljava_lang_Object_2(result, com_google_gwt_lang_Array$ExpandoWrapper_expandoNames, com_google_gwt_lang_Array$ExpandoWrapper_expandoValues);
  result.java_lang_Object_typeId$ = typeId;
  return result;
}

function com_google_gwt_lang_Array(){
}

_ = com_google_gwt_lang_Array.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
function com_google_gwt_lang_Array$ExpandoWrapper_$clinit__(){
  com_google_gwt_lang_Array$ExpandoWrapper_$clinit__ = nullMethod;
  com_google_gwt_lang_Array$ExpandoWrapper_expandoNames = [];
  com_google_gwt_lang_Array$ExpandoWrapper_expandoValues = [];
  com_google_gwt_lang_Array$ExpandoWrapper_initExpandos__Lcom_google_gwt_lang_Array_2Ljava_lang_Object_2Ljava_lang_Object_2(new com_google_gwt_lang_Array(), com_google_gwt_lang_Array$ExpandoWrapper_expandoNames, com_google_gwt_lang_Array$ExpandoWrapper_expandoValues);
}

function com_google_gwt_lang_Array$ExpandoWrapper_initExpandos__Lcom_google_gwt_lang_Array_2Ljava_lang_Object_2Ljava_lang_Object_2(protoType, expandoNames, expandoValues){
  var i = 0, value;
  for (var name in protoType) {
    if (value = protoType[name]) {
      expandoNames[i] = name;
      expandoValues[i] = value;
      ++i;
    }
  }
}

function com_google_gwt_lang_Array$ExpandoWrapper_wrapArray__Lcom_google_gwt_lang_Array_2Ljava_lang_Object_2Ljava_lang_Object_2(array, expandoNames, expandoValues){
  com_google_gwt_lang_Array$ExpandoWrapper_$clinit__();
  for (var i = 0, c = expandoNames.length; i < c; ++i) {
    array[expandoNames[i]] = expandoValues[i];
  }
}

var com_google_gwt_lang_Array$ExpandoWrapper_expandoNames, com_google_gwt_lang_Array$ExpandoWrapper_expandoValues;
function java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2(packageName, className){
  var clazz;
  clazz = new java_lang_Class();
  return clazz;
}

function java_lang_Class(){
}

_ = java_lang_Class.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
_ = String.prototype;
_.java_lang_Object_typeId$ = 2;
function java_util_AbstractCollection(){
}

_ = java_util_AbstractCollection.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
function java_util_AbstractList(){
}

_ = java_util_AbstractList.prototype = new java_util_AbstractCollection();
_.java_lang_Object_typeId$ = 0;
function java_util_ArrayList_$ArrayList__Ljava_util_ArrayList_2(this$static){
  com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1Object_12_1classLit, 0, 0, 0, 0);
  return this$static;
}

function java_util_ArrayList(){
}

_ = java_util_ArrayList.prototype = new java_util_AbstractList();
_.java_lang_Object_typeId$ = 13;
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

function nullMethod(){
}

var com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1Object_12_1classLit = java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2($intern_11, $intern_12);
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});
if (friends) friends.onScriptLoad(gwtOnLoad);
})();

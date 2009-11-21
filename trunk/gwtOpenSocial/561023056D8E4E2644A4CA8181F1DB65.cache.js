(function(){
var $gwt_version = "1.7.1";
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});
var $intern_2 = ' ', $intern_7 = '1', $intern_4 = '403', $intern_6 = 'An unexpected error occurred.', $intern_10 = 'FRIENDS', $intern_16 = 'Object;', $intern_5 = 'Please adjust the gadgets settings to use social features', $intern_9 = 'VIEWER', $intern_15 = '[Ljava.lang.', $intern_14 = 'com.example.friends.client.Friends', $intern_3 = 'forbidden', $intern_12 = 'moduleStartup', $intern_13 = 'onModuleLoadStart', $intern_8 = 'one', $intern_11 = 'startup', $intern_0 = 'viewer', $intern_1 = 'viewerFriends';
var _;
function java_lang_Object(){
}

_ = java_lang_Object.prototype = {};
_.java_lang_Object_typeMarker$ = nullMethod;
_.java_lang_Object_typeId$ = 1;
function com_google_gwt_gadgets_client_Gadget(){
}

_ = com_google_gwt_gadgets_client_Gadget.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(feature){
}

function com_example_friends_client_Friends_onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2(data){
  var user = data.get($intern_0);
  if (!user.hadError()) {
    var viewer = data.get($intern_0).getData();
    var viewerFriends = data.get($intern_1).getData();
    that.com_example_friends_client_Friends_user.set__Ljava_lang_String_2Ljava_lang_String_2(viewer.getId(), viewer.getDisplayName());
    viewerFriends.each(function(person){
      if (person.getId()) {
        var temp = com_example_friends_client_Person_$Person__Lcom_example_friends_client_Person_2Ljava_lang_String_2Ljava_lang_String_2(new com_example_friends_client_Person(), person.getId(), person.getDisplayName());
        that.com_example_friends_client_Friends_userFriends.add__Ljava_lang_Object_2(temp);
        $wnd.document.write(person.getId());
        $wnd.document.write($intern_2);
        $wnd.document.write(person.getDisplayName());
      }
    }
    );
    that.print__();
  }
   else {
    if (user.getErrorCode() == $intern_3 || user.getErrorCode() == $intern_4) {
      $wnd.document.write($intern_5);
    }
     else {
      $wnd.document.write($intern_6);
    }
  }
}

function com_example_friends_client_Friends_print__(){
  $wnd.alert(com_example_friends_client_Person_$toString__Lcom_example_friends_client_Person_2(this.com_example_friends_client_Friends_user));
}

function com_example_friends_client_Friends(){
}

_ = com_example_friends_client_Friends.prototype = new com_google_gwt_gadgets_client_Gadget();
_.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2 = com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2;
_.onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2 = com_example_friends_client_Friends_onLoadInfo__Lcom_google_gwt_core_client_JavaScriptObject_2;
_.print__ = com_example_friends_client_Friends_print__;
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(this$static){
  var com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_req_0, com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_viewerFriends_0, com_example_friends_client_Friends_$getSocialInfo__Lcom_example_friends_client_Friends_2_opt_params_0;
  this$static.com_example_friends_client_Friends_user = com_example_friends_client_Person_$Person__Lcom_example_friends_client_Person_2Ljava_lang_String_2Ljava_lang_String_2(new com_example_friends_client_Person(), $intern_7, $intern_8);
  this$static.com_example_friends_client_Friends_userFriends = java_util_ArrayList_$ArrayList__Ljava_util_ArrayList_2(new java_util_ArrayList());
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
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_OpenSocialFeature(){
}

_ = com_example_friends_client_OpenSocialFeature.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_Person_$Person__Lcom_example_friends_client_Person_2Ljava_lang_String_2Ljava_lang_String_2(this$static, id, name){
  this$static.com_example_friends_client_Person_id = id;
  this$static.com_example_friends_client_Person_name = name;
  return this$static;
}

function com_example_friends_client_Person_$toString__Lcom_example_friends_client_Person_2(this$static){
  return this$static.com_example_friends_client_Person_id + $intern_2 + this$static.com_example_friends_client_Person_name;
}

function com_example_friends_client_Person_set__Ljava_lang_String_2Ljava_lang_String_2(id, name){
  this.com_example_friends_client_Person_id = id;
  this.com_example_friends_client_Person_name = name;
}

function com_example_friends_client_Person(){
}

_ = com_example_friends_client_Person.prototype = new java_lang_Object();
_.set__Ljava_lang_String_2Ljava_lang_String_2 = com_example_friends_client_Person_set__Ljava_lang_String_2Ljava_lang_String_2;
_.java_lang_Object_typeId$ = 0;
_.com_example_friends_client_Person_id = null;
_.com_example_friends_client_Person_name = null;
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
  result.com_google_gwt_lang_Array_queryId$ = queryId;
  return result;
}

function com_google_gwt_lang_Array_setCheck__Lcom_google_gwt_lang_Array_2ILjava_lang_Object_2(array, index, value){
  if (value != null) {
    if (array.com_google_gwt_lang_Array_queryId$ > 0 && !com_google_gwt_lang_Cast_canCastUnsafe__II(value.java_lang_Object_typeId$, array.com_google_gwt_lang_Array_queryId$)) {
      throw new java_lang_ArrayStoreException();
    }
    if (array.com_google_gwt_lang_Array_queryId$ < 0 && (value.java_lang_Object_typeMarker$ == nullMethod || value.java_lang_Object_typeId$ == 2)) {
      throw new java_lang_ArrayStoreException();
    }
  }
  return array[index] = value;
}

function com_google_gwt_lang_Array(){
}

_ = com_google_gwt_lang_Array.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 0;
_.com_google_gwt_lang_Array_queryId$ = 0;
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
function com_google_gwt_lang_Cast_canCastUnsafe__II(srcId, dstId){
  return srcId && com_google_gwt_lang_Cast_typeIdArray[srcId][dstId];
}

var com_google_gwt_lang_Cast_typeIdArray = [{}, {}, {1:1}, {2:1}, {2:1}, {2:1}, {2:1}, {2:1}, {2:1}, {2:1}, {2:1}, {2:1}, {2:1}, {3:1}, {2:1}, {4:1}];
function java_lang_Throwable(){
}

_ = java_lang_Throwable.prototype = new java_lang_Object();
_.java_lang_Object_typeId$ = 3;
function java_lang_Exception(){
}

_ = java_lang_Exception.prototype = new java_lang_Throwable();
_.java_lang_Object_typeId$ = 4;
function java_lang_RuntimeException(){
}

_ = java_lang_RuntimeException.prototype = new java_lang_Exception();
_.java_lang_Object_typeId$ = 5;
function java_lang_ArrayStoreException(){
}

_ = java_lang_ArrayStoreException.prototype = new java_lang_RuntimeException();
_.java_lang_Object_typeId$ = 8;
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
  this$static.java_util_ArrayList_array = com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1Object_12_1classLit, 0, 0, 0, 0);
  this$static.java_util_ArrayList_size = 0;
  return this$static;
}

function java_util_ArrayList_add__Ljava_lang_Object_2(o){
  com_google_gwt_lang_Array_setCheck__Lcom_google_gwt_lang_Array_2ILjava_lang_Object_2(this.java_util_ArrayList_array, this.java_util_ArrayList_size++, o);
  return true;
}

function java_util_ArrayList(){
}

_ = java_util_ArrayList.prototype = new java_util_AbstractList();
_.add__Ljava_lang_Object_2 = java_util_ArrayList_add__Ljava_lang_Object_2;
_.java_lang_Object_typeId$ = 13;
_.java_util_ArrayList_array = null;
_.java_util_ArrayList_size = 0;
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

function nullMethod(){
}

var com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1Object_12_1classLit = java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2($intern_15, $intern_16);
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});
if (friends) friends.onScriptLoad(gwtOnLoad);
})();

(function(){
var $gwt_version = "1.7.1";
var $wnd = window;
var $doc = $wnd.document;
var $moduleName, $moduleBase;
var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});
var $intern_7 = '', $intern_17 = '\n ', $intern_22 = ' out of range', $intern_24 = '"', $intern_10 = '(', $intern_11 = '): ', $intern_5 = ',', $intern_28 = ', ', $intern_34 = ', Size: ', $intern_25 = ':', $intern_9 = ': ', $intern_31 = '=', $intern_0 = '@', $intern_69 = 'AbstractCollection', $intern_71 = 'AbstractHashMap', $intern_73 = 'AbstractHashMap$EntrySet', $intern_74 = 'AbstractHashMap$EntrySetIterator', $intern_76 = 'AbstractHashMap$MapEntryNull', $intern_77 = 'AbstractHashMap$MapEntryString', $intern_78 = 'AbstractList', $intern_79 = 'AbstractList$IteratorImpl', $intern_70 = 'AbstractMap', $intern_75 = 'AbstractMapEntry', $intern_72 = 'AbstractSet', $intern_26 = 'Add not supported on this collection', $intern_80 = 'ArrayList', $intern_59 = 'ArrayStoreException', $intern_61 = 'Class', $intern_62 = 'ClassCastException', $intern_53 = 'Exception', $intern_23 = 'For input string: "', $intern_46 = 'Friends', $intern_47 = 'FriendsGadgetImpl', $intern_44 = 'Gadget', $intern_81 = 'HashMap', $intern_63 = 'IllegalArgumentException', $intern_33 = 'Index: ', $intern_58 = 'IndexOutOfBoundsException', $intern_56 = 'JavaScriptException', $intern_57 = 'JavaScriptObject$', $intern_82 = 'NoSuchElementException', $intern_64 = 'NumberFormatException', $intern_42 = 'Object', $intern_67 = 'Object;', $intern_48 = 'OpenSocialFeature', $intern_54 = 'RuntimeException', $intern_13 = 'String', $intern_40 = 'String;', $intern_65 = 'StringBuffer', $intern_50 = 'StringBufferImpl', $intern_51 = 'StringBufferImplAppend', $intern_52 = 'Throwable', $intern_66 = 'UnsupportedOperationException', $intern_27 = '[', $intern_2 = '[1-9],{0,1}(\\d)* \u20AA', $intern_60 = '[C', $intern_18 = '[JavaScriptObject]', $intern_39 = '[Ljava.lang.', $intern_29 = ']', $intern_20 = 'class ', $intern_45 = 'com.example.friends.client.', $intern_38 = 'com.example.friends.client.Friends', $intern_55 = 'com.google.gwt.core.client.', $intern_49 = 'com.google.gwt.core.client.impl.', $intern_43 = 'com.google.gwt.gadgets.client.', $intern_3 = 'g', $intern_8 = 'http://www.zap.co.il/model.aspx?modelid=738461', $intern_19 = 'interface ', $intern_41 = 'java.lang.', $intern_68 = 'java.util.', $intern_15 = 'message', $intern_36 = 'moduleStartup', $intern_14 = 'name', $intern_12 = 'null', $intern_37 = 'onModuleLoadStart', $intern_4 = 'parse', $intern_6 = 'price is : ', $intern_21 = 'radix ', $intern_1 = 'response', $intern_35 = 'startup', $intern_16 = 'toString', $intern_30 = '{', $intern_32 = '}';
var _;
function java_lang_Object_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Object_12_1classLit;
}

function java_lang_Object_hashCode__(){
  return this.$H || (this.$H = ++com_google_gwt_core_client_impl_Impl_sNextHashId);
}

function java_lang_Object_toString__(){
  return (this.java_lang_Object_typeMarker$ == nullMethod || this.java_lang_Object_typeId$ == 2?this.getClass__$():com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1JavaScriptObject_12_1classLit).java_lang_Class_typeName + $intern_0 + java_lang_Integer_toPowerOfTwoString__II(this.java_lang_Object_typeMarker$ == nullMethod || this.java_lang_Object_typeId$ == 2?this.hashCode__$():this.$H || (this.$H = ++com_google_gwt_core_client_impl_Impl_sNextHashId), 4);
}

function java_lang_Object(){
}

_ = java_lang_Object.prototype = {};
_.getClass__$ = java_lang_Object_getClass__;
_.hashCode__$ = java_lang_Object_hashCode__;
_.toString__$ = java_lang_Object_toString__;
_.toString = function(){
  return this.toString__$();
}
;
_.java_lang_Object_typeMarker$ = nullMethod;
_.java_lang_Object_typeId$ = 1;
function com_google_gwt_gadgets_client_Gadget_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1gadgets_1client_1Gadget_12_1classLit;
}

function com_google_gwt_gadgets_client_Gadget(){
}

_ = com_google_gwt_gadgets_client_Gadget.prototype = new java_lang_Object();
_.getClass__$ = com_google_gwt_gadgets_client_Gadget_getClass__;
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_Friends_$bringLink__Lcom_example_friends_client_Friends_2Ljava_lang_String_2(this$static, url){
  thisTabGui = this$static;
  var params = {};
  params[$wnd.gadgets.io.RequestParameters.CONTENT_TYPE] = $wnd.gadgets.io.ContentType.TEXT;
  $wnd.gadgets.io.makeRequest(url, response, params);
  function response(obj){
    $wnd.document.write($intern_1);
    thisTabGui.com_example_friends_client_Friends_linkText = obj.text;
    $wnd.document.write(obj.text);
    thisTabGui.parse__();
  }

}

function com_example_friends_client_Friends__1match__Ljava_lang_String_2Ljava_util_List_2(text, matches){
  var regExp = new RegExp($intern_2, $intern_3);
  var result = text.match(regExp);
  if (result == null)
    return;
  for (var i = 0; i < result.length; i++)
    matches.add__Ljava_lang_Object_2(result[i]);
}

function com_example_friends_client_Friends_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1example_1friends_1client_1Friends_12_1classLit;
}

function com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(feature){
}

function com_example_friends_client_Friends_match__Ljava_lang_String_2(text){
  var arr, i, matches;
  matches = java_util_ArrayList_$ArrayList__Ljava_util_ArrayList_2(new java_util_ArrayList());
  com_example_friends_client_Friends__1match__Ljava_lang_String_2Ljava_util_List_2(text, matches);
  arr = com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1String_12_1classLit, 0, 1, matches.java_util_ArrayList_size, 0);
  for (i = 0; i < matches.java_util_ArrayList_size; ++i)
    arr[i] = com_google_gwt_core_client_JavaScriptObject_toString_1_1devirtual$__Ljava_lang_Object_2((java_util_AbstractList_checkIndex__II(i, matches.java_util_ArrayList_size) , matches.java_util_ArrayList_array[i]));
  return arr;
}

function com_example_friends_client_Friends_parse__(){
  var $e0, $e1, output, price, splitString, temp;
  $wnd.alert($intern_4);
  output = com_example_friends_client_Friends_match__Ljava_lang_String_2(this.com_example_friends_client_Friends_linkText);
  if (output.length == 0)
    return;
  temp = output[0].substr(0, output[0].length - 2 - 0);
  price = 0;
  try {
    price = java_lang_Number__1_1parseAndValidateInt__Ljava_lang_String_2III(temp, 10, -2147483648, 2147483647);
  }
   catch ($e0) {
    $e0 = com_google_gwt_lang_Exceptions_caught__Ljava_lang_Object_2($e0);
    if (com_google_gwt_lang_Cast_instanceOf__Ljava_lang_Object_2I($e0, 2)) {
      splitString = java_lang_String_$split__Ljava_lang_String_2Ljava_lang_String_2I(temp, $intern_5, 0);
      temp = splitString[0] + splitString[1];
      try {
        price = java_lang_Number__1_1parseAndValidateInt__Ljava_lang_String_2III(temp, 10, -2147483648, 2147483647);
      }
       catch ($e1) {
        $e1 = com_google_gwt_lang_Exceptions_caught__Ljava_lang_Object_2($e1);
        if (com_google_gwt_lang_Cast_instanceOf__Ljava_lang_Object_2I($e1, 2)) {
        }
         else 
          throw $e1;
      }
    }
     else 
      throw $e0;
  }
  $wnd.alert($intern_6 + price);
}

function com_example_friends_client_Friends(){
}

_ = com_example_friends_client_Friends.prototype = new com_google_gwt_gadgets_client_Gadget();
_.getClass__$ = com_example_friends_client_Friends_getClass__;
_.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2 = com_example_friends_client_Friends_initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2;
_.parse__ = com_example_friends_client_Friends_parse__;
_.java_lang_Object_typeId$ = 0;
_.com_example_friends_client_Friends_linkText = $intern_7;
function com_example_friends_client_FriendsGadgetImpl_$FriendsGadgetImpl__Lcom_example_friends_client_FriendsGadgetImpl_2(this$static){
  java_util_AbstractHashMap_$clearImpl__Ljava_util_AbstractHashMap_2(new java_util_HashMap());
  this$static.initializeFeature__Lcom_example_friends_client_OpenSocialFeature_2(new com_example_friends_client_OpenSocialFeature());
  com_example_friends_client_Friends_$bringLink__Lcom_example_friends_client_Friends_2Ljava_lang_String_2(this$static, $intern_8);
  return this$static;
}

function com_example_friends_client_FriendsGadgetImpl_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1example_1friends_1client_1FriendsGadgetImpl_12_1classLit;
}

function com_example_friends_client_FriendsGadgetImpl(){
}

_ = com_example_friends_client_FriendsGadgetImpl.prototype = new com_example_friends_client_Friends();
_.getClass__$ = com_example_friends_client_FriendsGadgetImpl_getClass__;
_.java_lang_Object_typeId$ = 0;
function com_example_friends_client_OpenSocialFeature_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1example_1friends_1client_1OpenSocialFeature_12_1classLit;
}

function com_example_friends_client_OpenSocialFeature(){
}

_ = com_example_friends_client_OpenSocialFeature.prototype = new java_lang_Object();
_.getClass__$ = com_example_friends_client_OpenSocialFeature_getClass__;
_.java_lang_Object_typeId$ = 0;
function java_lang_Throwable_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Throwable_12_1classLit;
}

function java_lang_Throwable_getMessage__(){
  return this.java_lang_Throwable_detailMessage;
}

function java_lang_Throwable_toString__(){
  var className, msg;
  className = this.getClass__$().java_lang_Class_typeName;
  msg = this.getMessage__();
  if (msg != null) {
    return className + $intern_9 + msg;
  }
   else {
    return className;
  }
}

function java_lang_Throwable(){
}

_ = java_lang_Throwable.prototype = new java_lang_Object();
_.getClass__$ = java_lang_Throwable_getClass__;
_.getMessage__ = java_lang_Throwable_getMessage__;
_.toString__$ = java_lang_Throwable_toString__;
_.java_lang_Object_typeId$ = 3;
_.java_lang_Throwable_detailMessage = null;
function java_lang_Exception_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Exception_12_1classLit;
}

function java_lang_Exception(){
}

_ = java_lang_Exception.prototype = new java_lang_Throwable();
_.getClass__$ = java_lang_Exception_getClass__;
_.java_lang_Object_typeId$ = 4;
function java_lang_RuntimeException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1RuntimeException_12_1classLit;
}

function java_lang_RuntimeException(){
}

_ = java_lang_RuntimeException.prototype = new java_lang_Exception();
_.getClass__$ = java_lang_RuntimeException_getClass__;
_.java_lang_Object_typeId$ = 5;
function com_google_gwt_core_client_JavaScriptException_$JavaScriptException__Lcom_google_gwt_core_client_JavaScriptException_2Ljava_lang_Object_2(this$static, e){
  this$static.com_google_gwt_core_client_JavaScriptException_e = e;
  return this$static;
}

function com_google_gwt_core_client_JavaScriptException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1JavaScriptException_12_1classLit;
}

function com_google_gwt_core_client_JavaScriptException_getDescription__Ljava_lang_Object_2(e){
  if (e != null && (e.java_lang_Object_typeMarker$ != nullMethod && e.java_lang_Object_typeId$ != 2)) {
    return com_google_gwt_core_client_JavaScriptException_getDescription0__Lcom_google_gwt_core_client_JavaScriptObject_2(com_google_gwt_lang_Cast_dynamicCastJso__Ljava_lang_Object_2(e));
  }
   else {
    return e + $intern_7;
  }
}

function com_google_gwt_core_client_JavaScriptException_getDescription0__Lcom_google_gwt_core_client_JavaScriptObject_2(e){
  return e == null?null:e.message;
}

function com_google_gwt_core_client_JavaScriptException_getMessage__(){
  if (this.com_google_gwt_core_client_JavaScriptException_message == null) {
    this.com_google_gwt_core_client_JavaScriptException_name = com_google_gwt_core_client_JavaScriptException_getName__Ljava_lang_Object_2(this.com_google_gwt_core_client_JavaScriptException_e);
    this.com_google_gwt_core_client_JavaScriptException_description = com_google_gwt_core_client_JavaScriptException_getDescription__Ljava_lang_Object_2(this.com_google_gwt_core_client_JavaScriptException_e);
    this.com_google_gwt_core_client_JavaScriptException_message = $intern_10 + this.com_google_gwt_core_client_JavaScriptException_name + $intern_11 + this.com_google_gwt_core_client_JavaScriptException_description + com_google_gwt_core_client_JavaScriptException_getProperties__Ljava_lang_Object_2(this.com_google_gwt_core_client_JavaScriptException_e);
  }
  return this.com_google_gwt_core_client_JavaScriptException_message;
}

function com_google_gwt_core_client_JavaScriptException_getName__Ljava_lang_Object_2(e){
  if (e == null) {
    return $intern_12;
  }
   else if (e != null && (e.java_lang_Object_typeMarker$ != nullMethod && e.java_lang_Object_typeId$ != 2)) {
    return com_google_gwt_core_client_JavaScriptException_getName0__Lcom_google_gwt_core_client_JavaScriptObject_2(com_google_gwt_lang_Cast_dynamicCastJso__Ljava_lang_Object_2(e));
  }
   else if (e != null && com_google_gwt_lang_Cast_canCast__II(e.java_lang_Object_typeId$, 1)) {
    return $intern_13;
  }
   else {
    return (e.java_lang_Object_typeMarker$ == nullMethod || e.java_lang_Object_typeId$ == 2?e.getClass__$():com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1JavaScriptObject_12_1classLit).java_lang_Class_typeName;
  }
}

function com_google_gwt_core_client_JavaScriptException_getName0__Lcom_google_gwt_core_client_JavaScriptObject_2(e){
  return e == null?null:e.name;
}

function com_google_gwt_core_client_JavaScriptException_getProperties__Ljava_lang_Object_2(e){
  return e != null && (e.java_lang_Object_typeMarker$ != nullMethod && e.java_lang_Object_typeId$ != 2)?com_google_gwt_core_client_JavaScriptException_getProperties0__Lcom_google_gwt_core_client_JavaScriptObject_2(com_google_gwt_lang_Cast_dynamicCastJso__Ljava_lang_Object_2(e)):$intern_7;
}

function com_google_gwt_core_client_JavaScriptException_getProperties0__Lcom_google_gwt_core_client_JavaScriptObject_2(e){
  var result = $intern_7;
  try {
    for (prop in e) {
      if (prop != $intern_14 && (prop != $intern_15 && prop != $intern_16)) {
        try {
          result += $intern_17 + prop + $intern_9 + e[prop];
        }
         catch (ignored) {
        }
      }
    }
  }
   catch (ignored) {
  }
  return result;
}

function com_google_gwt_core_client_JavaScriptException(){
}

_ = com_google_gwt_core_client_JavaScriptException.prototype = new java_lang_RuntimeException();
_.getClass__$ = com_google_gwt_core_client_JavaScriptException_getClass__;
_.getMessage__ = com_google_gwt_core_client_JavaScriptException_getMessage__;
_.java_lang_Object_typeId$ = 6;
_.com_google_gwt_core_client_JavaScriptException_description = null;
_.com_google_gwt_core_client_JavaScriptException_e = null;
_.com_google_gwt_core_client_JavaScriptException_message = null;
_.com_google_gwt_core_client_JavaScriptException_name = null;
function com_google_gwt_core_client_JavaScriptObject_hashCode_1_1devirtual$__Ljava_lang_Object_2(this$static){
  return this$static.java_lang_Object_typeMarker$ == nullMethod || this$static.java_lang_Object_typeId$ == 2?this$static.hashCode__$():this$static.$H || (this$static.$H = ++com_google_gwt_core_client_impl_Impl_sNextHashId);
}

function com_google_gwt_core_client_JavaScriptObject_toString_1_1devirtual$__Ljava_lang_Object_2(this$static){
  return this$static.java_lang_Object_typeMarker$ == nullMethod || this$static.java_lang_Object_typeId$ == 2?this$static.toString__$():this$static.toString?this$static.toString():$intern_18;
}

var com_google_gwt_core_client_impl_Impl_sNextHashId = 0;
function com_google_gwt_core_client_impl_StringBufferImpl_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1impl_1StringBufferImpl_12_1classLit;
}

function com_google_gwt_core_client_impl_StringBufferImpl(){
}

_ = com_google_gwt_core_client_impl_StringBufferImpl.prototype = new java_lang_Object();
_.getClass__$ = com_google_gwt_core_client_impl_StringBufferImpl_getClass__;
_.java_lang_Object_typeId$ = 0;
function com_google_gwt_core_client_impl_StringBufferImplAppend_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1impl_1StringBufferImplAppend_12_1classLit;
}

function com_google_gwt_core_client_impl_StringBufferImplAppend(){
}

_ = com_google_gwt_core_client_impl_StringBufferImplAppend.prototype = new com_google_gwt_core_client_impl_StringBufferImpl();
_.getClass__$ = com_google_gwt_core_client_impl_StringBufferImplAppend_getClass__;
_.java_lang_Object_typeId$ = 0;
_.com_google_gwt_core_client_impl_StringBufferImplAppend_string = $intern_7;
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

function com_google_gwt_lang_Array_getClass__(){
  return this.com_google_gwt_lang_Array_arrayClass$;
}

function com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(arrayClass, typeId, queryId, length, seedType){
  var result;
  result = com_google_gwt_lang_Array_createFromSeed__II(seedType, length);
  com_google_gwt_lang_Array$ExpandoWrapper_$clinit__();
  com_google_gwt_lang_Array$ExpandoWrapper_wrapArray__Lcom_google_gwt_lang_Array_2Ljava_lang_Object_2Ljava_lang_Object_2(result, com_google_gwt_lang_Array$ExpandoWrapper_expandoNames, com_google_gwt_lang_Array$ExpandoWrapper_expandoValues);
  result.com_google_gwt_lang_Array_arrayClass$ = arrayClass;
  result.java_lang_Object_typeId$ = typeId;
  result.com_google_gwt_lang_Array_queryId$ = queryId;
  return result;
}

function com_google_gwt_lang_Array_initValues__Ljava_lang_Class_2IILcom_google_gwt_lang_Array_2(arrayClass, typeId, queryId, array){
  com_google_gwt_lang_Array$ExpandoWrapper_$clinit__();
  com_google_gwt_lang_Array$ExpandoWrapper_wrapArray__Lcom_google_gwt_lang_Array_2Ljava_lang_Object_2Ljava_lang_Object_2(array, com_google_gwt_lang_Array$ExpandoWrapper_expandoNames, com_google_gwt_lang_Array$ExpandoWrapper_expandoValues);
  array.com_google_gwt_lang_Array_arrayClass$ = arrayClass;
  array.java_lang_Object_typeId$ = typeId;
  array.com_google_gwt_lang_Array_queryId$ = queryId;
  return array;
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
_.getClass__$ = com_google_gwt_lang_Array_getClass__;
_.java_lang_Object_typeId$ = 0;
_.com_google_gwt_lang_Array_arrayClass$ = null;
_.length = 0;
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
function com_google_gwt_lang_Cast_canCast__II(srcId, dstId){
  return srcId && !!com_google_gwt_lang_Cast_typeIdArray[srcId][dstId];
}

function com_google_gwt_lang_Cast_canCastUnsafe__II(srcId, dstId){
  return srcId && com_google_gwt_lang_Cast_typeIdArray[srcId][dstId];
}

function com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(src, dstId){
  if (src != null && !com_google_gwt_lang_Cast_canCastUnsafe__II(src.java_lang_Object_typeId$, dstId)) {
    throw new java_lang_ClassCastException();
  }
  return src;
}

function com_google_gwt_lang_Cast_dynamicCastJso__Ljava_lang_Object_2(src){
  if (src != null && (src.java_lang_Object_typeMarker$ == nullMethod || src.java_lang_Object_typeId$ == 2)) {
    throw new java_lang_ClassCastException();
  }
  return src;
}

function com_google_gwt_lang_Cast_instanceOf__Ljava_lang_Object_2I(src, dstId){
  return src != null && com_google_gwt_lang_Cast_canCast__II(src.java_lang_Object_typeId$, dstId);
}

var com_google_gwt_lang_Cast_typeIdArray = [{}, {}, {1:1, 7:1, 8:1, 9:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {3:1, 7:1}, {2:1, 3:1, 7:1}, {8:1}, {3:1, 7:1}, {4:1}, {4:1}, {4:1}, {5:1, 7:1}, {6:1, 7:1}, {3:1, 7:1}, {10:1}];
function com_google_gwt_lang_Exceptions_caught__Ljava_lang_Object_2(e){
  if (e != null && com_google_gwt_lang_Cast_canCast__II(e.java_lang_Object_typeId$, 3)) {
    return e;
  }
  return com_google_gwt_core_client_JavaScriptException_$JavaScriptException__Lcom_google_gwt_core_client_JavaScriptException_2Ljava_lang_Object_2(new com_google_gwt_core_client_JavaScriptException(), e);
}

function java_lang_ArrayStoreException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1ArrayStoreException_12_1classLit;
}

function java_lang_ArrayStoreException(){
}

_ = java_lang_ArrayStoreException.prototype = new java_lang_RuntimeException();
_.getClass__$ = java_lang_ArrayStoreException_getClass__;
_.java_lang_Object_typeId$ = 8;
function java_lang_Character_digit__CI(c, radix){
  if (radix < 2 || radix > 36) {
    return -1;
  }
  if (c >= 48 && c < 48 + (radix < 10?radix:10)) {
    return c - 48;
  }
  if (c >= 97 && c < radix + 97 - 10) {
    return c - 97 + 10;
  }
  if (c >= 65 && c < radix + 65 - 10) {
    return c - 65 + 10;
  }
  return -1;
}

function java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2(packageName, className){
  var clazz;
  clazz = new java_lang_Class();
  clazz.java_lang_Class_typeName = packageName + className;
  clazz.java_lang_Class_modifiers = 4;
  return clazz;
}

function java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2(packageName, className){
  var clazz;
  clazz = new java_lang_Class();
  clazz.java_lang_Class_typeName = packageName + className;
  return clazz;
}

function java_lang_Class_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Class_12_1classLit;
}

function java_lang_Class_toString__(){
  return ((this.java_lang_Class_modifiers & 2) != 0?$intern_19:(this.java_lang_Class_modifiers & 1) != 0?$intern_7:$intern_20) + this.java_lang_Class_typeName;
}

function java_lang_Class(){
}

_ = java_lang_Class.prototype = new java_lang_Object();
_.getClass__$ = java_lang_Class_getClass__;
_.toString__$ = java_lang_Class_toString__;
_.java_lang_Object_typeId$ = 0;
_.java_lang_Class_modifiers = 0;
_.java_lang_Class_typeName = null;
function java_lang_ClassCastException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1ClassCastException_12_1classLit;
}

function java_lang_ClassCastException(){
}

_ = java_lang_ClassCastException.prototype = new java_lang_RuntimeException();
_.getClass__$ = java_lang_ClassCastException_getClass__;
_.java_lang_Object_typeId$ = 11;
function java_lang_IllegalArgumentException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1IllegalArgumentException_12_1classLit;
}

function java_lang_IllegalArgumentException(){
}

_ = java_lang_IllegalArgumentException.prototype = new java_lang_RuntimeException();
_.getClass__$ = java_lang_IllegalArgumentException_getClass__;
_.java_lang_Object_typeId$ = 12;
function java_lang_IndexOutOfBoundsException_$IndexOutOfBoundsException__Ljava_lang_IndexOutOfBoundsException_2Ljava_lang_String_2(this$static, message){
  this$static.java_lang_Throwable_detailMessage = message;
  return this$static;
}

function java_lang_IndexOutOfBoundsException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1IndexOutOfBoundsException_12_1classLit;
}

function java_lang_IndexOutOfBoundsException(){
}

_ = java_lang_IndexOutOfBoundsException.prototype = new java_lang_RuntimeException();
_.getClass__$ = java_lang_IndexOutOfBoundsException_getClass__;
_.java_lang_Object_typeId$ = 13;
function java_lang_Number__1_1parseAndValidateInt__Ljava_lang_String_2III(s, radix, lowerBound, upperBound){
  var i, length, startIndex, toReturn;
  if (s == null) {
    throw java_lang_NumberFormatException_$NumberFormatException__Ljava_lang_NumberFormatException_2Ljava_lang_String_2(new java_lang_NumberFormatException(), $intern_12);
  }
  if (radix < 2 || radix > 36) {
    throw java_lang_NumberFormatException_$NumberFormatException__Ljava_lang_NumberFormatException_2Ljava_lang_String_2(new java_lang_NumberFormatException(), $intern_21 + radix + $intern_22);
  }
  length = s.length;
  startIndex = length > 0 && s.charCodeAt(0) == 45?1:0;
  for (i = startIndex; i < length; ++i) {
    if (java_lang_Character_digit__CI(s.charCodeAt(i), radix) == -1) {
      throw java_lang_NumberFormatException_$NumberFormatException__Ljava_lang_NumberFormatException_2Ljava_lang_String_2(new java_lang_NumberFormatException(), $intern_23 + s + $intern_24);
    }
  }
  toReturn = parseInt(s, radix);
  if (isNaN(toReturn)) {
    throw java_lang_NumberFormatException_$NumberFormatException__Ljava_lang_NumberFormatException_2Ljava_lang_String_2(new java_lang_NumberFormatException(), $intern_23 + s + $intern_24);
  }
   else if (toReturn < lowerBound || toReturn > upperBound) {
    throw java_lang_NumberFormatException_$NumberFormatException__Ljava_lang_NumberFormatException_2Ljava_lang_String_2(new java_lang_NumberFormatException(), $intern_23 + s + $intern_24);
  }
  return toReturn;
}

function java_lang_Integer_toPowerOfTwoString__II(value, shift){
  var bitMask, buf, bufSize, digits, pos;
  bufSize = ~~(32 / shift);
  bitMask = (1 << shift) - 1;
  buf = com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(com_google_gwt_lang_ClassLiteralHolder__13C_1classLit, 0, -1, bufSize, 1);
  digits = (java_lang_Number$_1_1Digits_$clinit__() , java_lang_Number$_1_1Digits_digits);
  pos = bufSize - 1;
  if (value >= 0) {
    while (value > bitMask) {
      buf[pos--] = digits[value & bitMask];
      value >>= shift;
    }
  }
   else {
    while (pos > 0) {
      buf[pos--] = digits[value & bitMask];
      value >>= shift;
    }
  }
  buf[pos] = digits[value & bitMask];
  return java_lang_String__1_1valueOf___3CII(buf, pos, bufSize);
}

function java_lang_Number$_1_1Digits_$clinit__(){
  java_lang_Number$_1_1Digits_$clinit__ = nullMethod;
  java_lang_Number$_1_1Digits_digits = com_google_gwt_lang_Array_initValues__Ljava_lang_Class_2IILcom_google_gwt_lang_Array_2(com_google_gwt_lang_ClassLiteralHolder__13C_1classLit, 0, -1, [48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122]);
}

var java_lang_Number$_1_1Digits_digits;
function java_lang_NumberFormatException_$NumberFormatException__Ljava_lang_NumberFormatException_2Ljava_lang_String_2(this$static, message){
  this$static.java_lang_Throwable_detailMessage = message;
  return this$static;
}

function java_lang_NumberFormatException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1NumberFormatException_12_1classLit;
}

function java_lang_NumberFormatException(){
}

_ = java_lang_NumberFormatException.prototype = new java_lang_IllegalArgumentException();
_.getClass__$ = java_lang_NumberFormatException_getClass__;
_.java_lang_Object_typeId$ = 14;
function java_lang_String_$split__Ljava_lang_String_2Ljava_lang_String_2I(this$static, regex, maxMatch){
  var compiled = new RegExp(regex, $intern_3);
  var out = [];
  var count = 0;
  var trail = this$static;
  var lastTrail = null;
  while (true) {
    var matchObj = compiled.exec(trail);
    if (matchObj == null || (trail == $intern_7 || count == maxMatch - 1 && maxMatch > 0)) {
      out[count] = trail;
      break;
    }
     else {
      out[count] = trail.substring(0, matchObj.index);
      trail = trail.substring(matchObj.index + matchObj[0].length, trail.length);
      compiled.lastIndex = 0;
      if (lastTrail == trail) {
        out[count] = trail.substring(0, 1);
        trail = trail.substring(1);
      }
      lastTrail = trail;
      count++;
    }
  }
  if (maxMatch == 0) {
    var lastNonEmpty = out.length;
    while (lastNonEmpty > 0 && out[lastNonEmpty - 1] == $intern_7) {
      --lastNonEmpty;
    }
    if (lastNonEmpty < out.length) {
      out.splice(lastNonEmpty, out.length - lastNonEmpty);
    }
  }
  var jr = com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1String_12_1classLit, 0, 1, out.length, 0);
  for (var i = 0; i < out.length; ++i) {
    jr[i] = out[i];
  }
  return jr;
}

function java_lang_String__1_1valueOf___3CII(x, start, end){
  x = x.slice(start, end);
  return String.fromCharCode.apply(null, x);
}

function java_lang_String_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1String_12_1classLit;
}

function java_lang_String_hashCode__(){
  return java_lang_String$HashCache_getHashCode__Ljava_lang_String_2(this);
}

function java_lang_String_toString__(){
  return this;
}

_ = String.prototype;
_.getClass__$ = java_lang_String_getClass__;
_.hashCode__$ = java_lang_String_hashCode__;
_.toString__$ = java_lang_String_toString__;
_.java_lang_Object_typeId$ = 2;
function java_lang_String$HashCache_$clinit__(){
  java_lang_String$HashCache_$clinit__ = nullMethod;
  java_lang_String$HashCache_back = {};
  java_lang_String$HashCache_front = {};
}

function java_lang_String$HashCache_compute__Ljava_lang_String_2(str){
  var hashCode, i, inc, n;
  n = str.length;
  inc = n < 64?1:~~(n / 32);
  hashCode = 0;
  for (i = 0; i < n; i += inc) {
    hashCode <<= 1;
    hashCode += str.charCodeAt(i);
  }
  hashCode |= 0;
  return hashCode;
}

function java_lang_String$HashCache_getHashCode__Ljava_lang_String_2(str){
  java_lang_String$HashCache_$clinit__();
  var key = $intern_25 + str;
  var result = java_lang_String$HashCache_front[key];
  if (result != null) {
    return result;
  }
  result = java_lang_String$HashCache_back[key];
  if (result == null) {
    result = java_lang_String$HashCache_compute__Ljava_lang_String_2(str);
  }
  java_lang_String$HashCache_increment__();
  return java_lang_String$HashCache_front[key] = result;
}

function java_lang_String$HashCache_increment__(){
  if (java_lang_String$HashCache_count == 256) {
    java_lang_String$HashCache_back = java_lang_String$HashCache_front;
    java_lang_String$HashCache_front = {};
    java_lang_String$HashCache_count = 0;
  }
  ++java_lang_String$HashCache_count;
}

var java_lang_String$HashCache_back, java_lang_String$HashCache_count = 0, java_lang_String$HashCache_front;
function java_lang_StringBuffer_$StringBuffer__Ljava_lang_StringBuffer_2(this$static){
  this$static.java_lang_StringBuffer_impl = new com_google_gwt_core_client_impl_StringBufferImplAppend();
  return this$static;
}

function java_lang_StringBuffer_$append__Ljava_lang_StringBuffer_2Ljava_lang_String_2(this$static, x){
  this$static.java_lang_StringBuffer_impl.com_google_gwt_core_client_impl_StringBufferImplAppend_string += x;
  return this$static;
}

function java_lang_StringBuffer_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1StringBuffer_12_1classLit;
}

function java_lang_StringBuffer_toString__(){
  return this.java_lang_StringBuffer_impl.com_google_gwt_core_client_impl_StringBufferImplAppend_string;
}

function java_lang_StringBuffer(){
}

_ = java_lang_StringBuffer.prototype = new java_lang_Object();
_.getClass__$ = java_lang_StringBuffer_getClass__;
_.toString__$ = java_lang_StringBuffer_toString__;
_.java_lang_Object_typeId$ = 15;
function java_lang_UnsupportedOperationException_$UnsupportedOperationException__Ljava_lang_UnsupportedOperationException_2Ljava_lang_String_2(this$static, message){
  this$static.java_lang_Throwable_detailMessage = message;
  return this$static;
}

function java_lang_UnsupportedOperationException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1UnsupportedOperationException_12_1classLit;
}

function java_lang_UnsupportedOperationException(){
}

_ = java_lang_UnsupportedOperationException.prototype = new java_lang_RuntimeException();
_.getClass__$ = java_lang_UnsupportedOperationException_getClass__;
_.java_lang_Object_typeId$ = 16;
function java_util_AbstractCollection_add__Ljava_lang_Object_2(o){
  throw java_lang_UnsupportedOperationException_$UnsupportedOperationException__Ljava_lang_UnsupportedOperationException_2Ljava_lang_String_2(new java_lang_UnsupportedOperationException(), $intern_26);
}

function java_util_AbstractCollection_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractCollection_12_1classLit;
}

function java_util_AbstractCollection_toString__(){
  var comma, iter, sb;
  sb = java_lang_StringBuffer_$StringBuffer__Ljava_lang_StringBuffer_2(new java_lang_StringBuffer());
  comma = null;
  sb.java_lang_StringBuffer_impl.com_google_gwt_core_client_impl_StringBufferImplAppend_string += $intern_27;
  iter = this.iterator__();
  while (iter.hasNext__()) {
    if (comma != null) {
      sb.java_lang_StringBuffer_impl.com_google_gwt_core_client_impl_StringBufferImplAppend_string += comma;
    }
     else {
      comma = $intern_28;
    }
    java_lang_StringBuffer_$append__Ljava_lang_StringBuffer_2Ljava_lang_String_2(sb, $intern_7 + iter.next__());
  }
  sb.java_lang_StringBuffer_impl.com_google_gwt_core_client_impl_StringBufferImplAppend_string += $intern_29;
  return sb.java_lang_StringBuffer_impl.com_google_gwt_core_client_impl_StringBufferImplAppend_string;
}

function java_util_AbstractCollection(){
}

_ = java_util_AbstractCollection.prototype = new java_lang_Object();
_.add__Ljava_lang_Object_2 = java_util_AbstractCollection_add__Ljava_lang_Object_2;
_.getClass__$ = java_util_AbstractCollection_getClass__;
_.toString__$ = java_util_AbstractCollection_toString__;
_.java_lang_Object_typeId$ = 0;
function java_util_AbstractMap_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractMap_12_1classLit;
}

function java_util_AbstractMap_hashCode__(){
  var entry, entry$iterator, hashCode;
  hashCode = 0;
  for (entry$iterator = java_util_AbstractHashMap$EntrySetIterator_$AbstractHashMap$EntrySetIterator__Ljava_util_AbstractHashMap$EntrySetIterator_2(new java_util_AbstractHashMap$EntrySetIterator(), java_util_AbstractHashMap$EntrySet_$AbstractHashMap$EntrySet__Ljava_util_AbstractHashMap$EntrySet_2(new java_util_AbstractHashMap$EntrySet(), com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(this, 6)).java_util_AbstractHashMap$EntrySet_this$0); java_util_AbstractList$IteratorImpl_$hasNext__Ljava_util_AbstractList$IteratorImpl_2(entry$iterator.java_util_AbstractHashMap$EntrySetIterator_iter);) {
    entry = com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(java_util_AbstractList$IteratorImpl_$next__Ljava_util_AbstractList$IteratorImpl_2(entry$iterator.java_util_AbstractHashMap$EntrySetIterator_iter), 4);
    hashCode += entry.hashCode__$();
    hashCode = ~~hashCode;
  }
  return hashCode;
}

function java_util_AbstractMap_toString__(){
  var comma, entry, iter, s;
  s = $intern_30;
  comma = false;
  for (iter = java_util_AbstractHashMap$EntrySetIterator_$AbstractHashMap$EntrySetIterator__Ljava_util_AbstractHashMap$EntrySetIterator_2(new java_util_AbstractHashMap$EntrySetIterator(), java_util_AbstractHashMap$EntrySet_$AbstractHashMap$EntrySet__Ljava_util_AbstractHashMap$EntrySet_2(new java_util_AbstractHashMap$EntrySet(), com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(this, 6)).java_util_AbstractHashMap$EntrySet_this$0); java_util_AbstractList$IteratorImpl_$hasNext__Ljava_util_AbstractList$IteratorImpl_2(iter.java_util_AbstractHashMap$EntrySetIterator_iter);) {
    entry = com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(java_util_AbstractList$IteratorImpl_$next__Ljava_util_AbstractList$IteratorImpl_2(iter.java_util_AbstractHashMap$EntrySetIterator_iter), 4);
    if (comma) {
      s += $intern_28;
    }
     else {
      comma = true;
    }
    s += $intern_7 + entry.getKey__();
    s += $intern_31;
    s += $intern_7 + entry.getValue__();
  }
  return s + $intern_32;
}

function java_util_AbstractMap(){
}

_ = java_util_AbstractMap.prototype = new java_lang_Object();
_.getClass__$ = java_util_AbstractMap_getClass__;
_.hashCode__$ = java_util_AbstractMap_hashCode__;
_.toString__$ = java_util_AbstractMap_toString__;
_.java_lang_Object_typeId$ = 0;
function java_util_AbstractHashMap_$addAllHashEntries__Ljava_util_AbstractHashMap_2Ljava_util_Collection_2(this$static, dest){
  var hashCodeMap = this$static.java_util_AbstractHashMap_hashCodeMap;
  for (var hashCode in hashCodeMap) {
    if (hashCode == parseInt(hashCode)) {
      var array = hashCodeMap[hashCode];
      for (var i = 0, c = array.length; i < c; ++i) {
        dest.add__Ljava_lang_Object_2(array[i]);
      }
    }
  }
}

function java_util_AbstractHashMap_$addAllStringEntries__Ljava_util_AbstractHashMap_2Ljava_util_Collection_2(this$static, dest){
  var stringMap = this$static.java_util_AbstractHashMap_stringMap;
  for (var key in stringMap) {
    if (key.charCodeAt(0) == 58) {
      var entry = java_util_AbstractHashMap$MapEntryString_new__Ljava_util_AbstractHashMap_2Ljava_lang_String_2(this$static, key.substring(1));
      dest.add__Ljava_lang_Object_2(entry);
    }
  }
}

function java_util_AbstractHashMap_$clearImpl__Ljava_util_AbstractHashMap_2(this$static){
  this$static.java_util_AbstractHashMap_hashCodeMap = [];
  this$static.java_util_AbstractHashMap_stringMap = {};
  this$static.java_util_AbstractHashMap_nullSlotLive = false;
  this$static.java_util_AbstractHashMap_nullSlot = null;
  this$static.java_util_AbstractHashMap_size = 0;
}

function java_util_AbstractHashMap_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap_12_1classLit;
}

function java_util_AbstractHashMap(){
}

_ = java_util_AbstractHashMap.prototype = new java_util_AbstractMap();
_.getClass__$ = java_util_AbstractHashMap_getClass__;
_.java_lang_Object_typeId$ = 0;
_.java_util_AbstractHashMap_hashCodeMap = null;
_.java_util_AbstractHashMap_nullSlot = null;
_.java_util_AbstractHashMap_nullSlotLive = false;
_.java_util_AbstractHashMap_size = 0;
_.java_util_AbstractHashMap_stringMap = null;
function java_util_AbstractSet_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractSet_12_1classLit;
}

function java_util_AbstractSet_hashCode__(){
  var hashCode, iter, next;
  hashCode = 0;
  for (iter = this.iterator__(); iter.hasNext__();) {
    next = iter.next__();
    if (next != null) {
      hashCode += com_google_gwt_core_client_JavaScriptObject_hashCode_1_1devirtual$__Ljava_lang_Object_2(next);
      hashCode = ~~hashCode;
    }
  }
  return hashCode;
}

function java_util_AbstractSet(){
}

_ = java_util_AbstractSet.prototype = new java_util_AbstractCollection();
_.getClass__$ = java_util_AbstractSet_getClass__;
_.hashCode__$ = java_util_AbstractSet_hashCode__;
_.java_lang_Object_typeId$ = 0;
function java_util_AbstractHashMap$EntrySet_$AbstractHashMap$EntrySet__Ljava_util_AbstractHashMap$EntrySet_2(this$static, this$0){
  this$static.java_util_AbstractHashMap$EntrySet_this$0 = this$0;
  return this$static;
}

function java_util_AbstractHashMap$EntrySet_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$EntrySet_12_1classLit;
}

function java_util_AbstractHashMap$EntrySet_iterator__(){
  return java_util_AbstractHashMap$EntrySetIterator_$AbstractHashMap$EntrySetIterator__Ljava_util_AbstractHashMap$EntrySetIterator_2(new java_util_AbstractHashMap$EntrySetIterator(), this.java_util_AbstractHashMap$EntrySet_this$0);
}

function java_util_AbstractHashMap$EntrySet_size__(){
  return this.java_util_AbstractHashMap$EntrySet_this$0.java_util_AbstractHashMap_size;
}

function java_util_AbstractHashMap$EntrySet(){
}

_ = java_util_AbstractHashMap$EntrySet.prototype = new java_util_AbstractSet();
_.getClass__$ = java_util_AbstractHashMap$EntrySet_getClass__;
_.iterator__ = java_util_AbstractHashMap$EntrySet_iterator__;
_.size__ = java_util_AbstractHashMap$EntrySet_size__;
_.java_lang_Object_typeId$ = 0;
_.java_util_AbstractHashMap$EntrySet_this$0 = null;
function java_util_AbstractHashMap$EntrySetIterator_$AbstractHashMap$EntrySetIterator__Ljava_util_AbstractHashMap$EntrySetIterator_2(this$static, this$0){
  var list;
  this$static.java_util_AbstractHashMap$EntrySetIterator_this$0 = this$0;
  list = java_util_ArrayList_$ArrayList__Ljava_util_ArrayList_2(new java_util_ArrayList());
  if (this$static.java_util_AbstractHashMap$EntrySetIterator_this$0.java_util_AbstractHashMap_nullSlotLive) {
    java_util_ArrayList_$add__Ljava_util_ArrayList_2Ljava_lang_Object_2(list, java_util_AbstractHashMap$MapEntryNull_$AbstractHashMap$MapEntryNull__Ljava_util_AbstractHashMap$MapEntryNull_2(new java_util_AbstractHashMap$MapEntryNull(), this$static.java_util_AbstractHashMap$EntrySetIterator_this$0));
  }
  java_util_AbstractHashMap_$addAllStringEntries__Ljava_util_AbstractHashMap_2Ljava_util_Collection_2(this$static.java_util_AbstractHashMap$EntrySetIterator_this$0, list);
  java_util_AbstractHashMap_$addAllHashEntries__Ljava_util_AbstractHashMap_2Ljava_util_Collection_2(this$static.java_util_AbstractHashMap$EntrySetIterator_this$0, list);
  this$static.java_util_AbstractHashMap$EntrySetIterator_iter = java_util_AbstractList$IteratorImpl_$AbstractList$IteratorImpl__Ljava_util_AbstractList$IteratorImpl_2(new java_util_AbstractList$IteratorImpl(), list);
  return this$static;
}

function java_util_AbstractHashMap$EntrySetIterator_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$EntrySetIterator_12_1classLit;
}

function java_util_AbstractHashMap$EntrySetIterator_hasNext__(){
  return java_util_AbstractList$IteratorImpl_$hasNext__Ljava_util_AbstractList$IteratorImpl_2(this.java_util_AbstractHashMap$EntrySetIterator_iter);
}

function java_util_AbstractHashMap$EntrySetIterator_next__(){
  return com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(java_util_AbstractList$IteratorImpl_$next__Ljava_util_AbstractList$IteratorImpl_2(this.java_util_AbstractHashMap$EntrySetIterator_iter), 4);
}

function java_util_AbstractHashMap$EntrySetIterator(){
}

_ = java_util_AbstractHashMap$EntrySetIterator.prototype = new java_lang_Object();
_.getClass__$ = java_util_AbstractHashMap$EntrySetIterator_getClass__;
_.hasNext__ = java_util_AbstractHashMap$EntrySetIterator_hasNext__;
_.next__ = java_util_AbstractHashMap$EntrySetIterator_next__;
_.java_lang_Object_typeId$ = 0;
_.java_util_AbstractHashMap$EntrySetIterator_iter = null;
_.java_util_AbstractHashMap$EntrySetIterator_this$0 = null;
function java_util_AbstractMapEntry_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractMapEntry_12_1classLit;
}

function java_util_AbstractMapEntry_hashCode__(){
  var keyHash, valueHash;
  keyHash = 0;
  valueHash = 0;
  if (this.getKey__() != null) {
    keyHash = java_lang_String$HashCache_getHashCode__Ljava_lang_String_2(this.getKey__());
  }
  if (this.getValue__() != null) {
    valueHash = com_google_gwt_core_client_JavaScriptObject_hashCode_1_1devirtual$__Ljava_lang_Object_2(this.getValue__());
  }
  return keyHash ^ valueHash;
}

function java_util_AbstractMapEntry_toString__(){
  return this.getKey__() + $intern_31 + this.getValue__();
}

function java_util_AbstractMapEntry(){
}

_ = java_util_AbstractMapEntry.prototype = new java_lang_Object();
_.getClass__$ = java_util_AbstractMapEntry_getClass__;
_.hashCode__$ = java_util_AbstractMapEntry_hashCode__;
_.toString__$ = java_util_AbstractMapEntry_toString__;
_.java_lang_Object_typeId$ = 17;
function java_util_AbstractHashMap$MapEntryNull_$AbstractHashMap$MapEntryNull__Ljava_util_AbstractHashMap$MapEntryNull_2(this$static, this$0){
  this$static.java_util_AbstractHashMap$MapEntryNull_this$0 = this$0;
  return this$static;
}

function java_util_AbstractHashMap$MapEntryNull_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$MapEntryNull_12_1classLit;
}

function java_util_AbstractHashMap$MapEntryNull_getKey__(){
  return null;
}

function java_util_AbstractHashMap$MapEntryNull_getValue__(){
  return this.java_util_AbstractHashMap$MapEntryNull_this$0.java_util_AbstractHashMap_nullSlot;
}

function java_util_AbstractHashMap$MapEntryNull(){
}

_ = java_util_AbstractHashMap$MapEntryNull.prototype = new java_util_AbstractMapEntry();
_.getClass__$ = java_util_AbstractHashMap$MapEntryNull_getClass__;
_.getKey__ = java_util_AbstractHashMap$MapEntryNull_getKey__;
_.getValue__ = java_util_AbstractHashMap$MapEntryNull_getValue__;
_.java_lang_Object_typeId$ = 18;
_.java_util_AbstractHashMap$MapEntryNull_this$0 = null;
function java_util_AbstractHashMap$MapEntryString_$AbstractHashMap$MapEntryString__Ljava_util_AbstractHashMap$MapEntryString_2Ljava_lang_String_2(this$static, key, this$0){
  this$static.java_util_AbstractHashMap$MapEntryString_this$0 = this$0;
  this$static.java_util_AbstractHashMap$MapEntryString_key = key;
  return this$static;
}

function java_util_AbstractHashMap$MapEntryString_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$MapEntryString_12_1classLit;
}

function java_util_AbstractHashMap$MapEntryString_getKey__(){
  return this.java_util_AbstractHashMap$MapEntryString_key;
}

function java_util_AbstractHashMap$MapEntryString_getValue__(){
  return this.java_util_AbstractHashMap$MapEntryString_this$0.java_util_AbstractHashMap_stringMap[$intern_25 + this.java_util_AbstractHashMap$MapEntryString_key];
}

function java_util_AbstractHashMap$MapEntryString_new__Ljava_util_AbstractHashMap_2Ljava_lang_String_2(this$outer, key){
  return java_util_AbstractHashMap$MapEntryString_$AbstractHashMap$MapEntryString__Ljava_util_AbstractHashMap$MapEntryString_2Ljava_lang_String_2(new java_util_AbstractHashMap$MapEntryString(), key, this$outer);
}

function java_util_AbstractHashMap$MapEntryString(){
}

_ = java_util_AbstractHashMap$MapEntryString.prototype = new java_util_AbstractMapEntry();
_.getClass__$ = java_util_AbstractHashMap$MapEntryString_getClass__;
_.getKey__ = java_util_AbstractHashMap$MapEntryString_getKey__;
_.getValue__ = java_util_AbstractHashMap$MapEntryString_getValue__;
_.java_lang_Object_typeId$ = 19;
_.java_util_AbstractHashMap$MapEntryString_key = null;
_.java_util_AbstractHashMap$MapEntryString_this$0 = null;
function java_util_AbstractList_add__Ljava_lang_Object_2(obj){
  java_util_ArrayList_$add__Ljava_util_ArrayList_2ILjava_lang_Object_2(this, this.size__(), obj);
  return true;
}

function java_util_AbstractList_checkIndex__II(index, size){
  if (index < 0 || index >= size) {
    java_util_AbstractList_indexOutOfBounds__II(index, size);
  }
}

function java_util_AbstractList_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractList_12_1classLit;
}

function java_util_AbstractList_hashCode__(){
  var iter, k, obj;
  k = 1;
  iter = java_util_AbstractList$IteratorImpl_$AbstractList$IteratorImpl__Ljava_util_AbstractList$IteratorImpl_2(new java_util_AbstractList$IteratorImpl(), com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(this, 5));
  while (iter.java_util_AbstractList$IteratorImpl_i < iter.java_util_AbstractList$IteratorImpl_this$0.java_util_ArrayList_size) {
    obj = java_util_AbstractList$IteratorImpl_$next__Ljava_util_AbstractList$IteratorImpl_2(iter);
    k = 31 * k + (obj == null?0:com_google_gwt_core_client_JavaScriptObject_hashCode_1_1devirtual$__Ljava_lang_Object_2(obj));
    k = ~~k;
  }
  return k;
}

function java_util_AbstractList_indexOutOfBounds__II(index, size){
  throw java_lang_IndexOutOfBoundsException_$IndexOutOfBoundsException__Ljava_lang_IndexOutOfBoundsException_2Ljava_lang_String_2(new java_lang_IndexOutOfBoundsException(), $intern_33 + index + $intern_34 + size);
}

function java_util_AbstractList_iterator__(){
  return java_util_AbstractList$IteratorImpl_$AbstractList$IteratorImpl__Ljava_util_AbstractList$IteratorImpl_2(new java_util_AbstractList$IteratorImpl(), com_google_gwt_lang_Cast_dynamicCast__Ljava_lang_Object_2I(this, 5));
}

function java_util_AbstractList(){
}

_ = java_util_AbstractList.prototype = new java_util_AbstractCollection();
_.add__Ljava_lang_Object_2 = java_util_AbstractList_add__Ljava_lang_Object_2;
_.getClass__$ = java_util_AbstractList_getClass__;
_.hashCode__$ = java_util_AbstractList_hashCode__;
_.iterator__ = java_util_AbstractList_iterator__;
_.java_lang_Object_typeId$ = 0;
function java_util_AbstractList$IteratorImpl_$AbstractList$IteratorImpl__Ljava_util_AbstractList$IteratorImpl_2(this$static, this$0){
  this$static.java_util_AbstractList$IteratorImpl_this$0 = this$0;
  return this$static;
}

function java_util_AbstractList$IteratorImpl_$hasNext__Ljava_util_AbstractList$IteratorImpl_2(this$static){
  return this$static.java_util_AbstractList$IteratorImpl_i < this$static.java_util_AbstractList$IteratorImpl_this$0.java_util_ArrayList_size;
}

function java_util_AbstractList$IteratorImpl_$next__Ljava_util_AbstractList$IteratorImpl_2(this$static){
  if (this$static.java_util_AbstractList$IteratorImpl_i >= this$static.java_util_AbstractList$IteratorImpl_this$0.java_util_ArrayList_size) {
    throw new java_util_NoSuchElementException();
  }
  return java_util_ArrayList_$get__Ljava_util_ArrayList_2I(this$static.java_util_AbstractList$IteratorImpl_this$0, this$static.java_util_AbstractList$IteratorImpl_i++);
}

function java_util_AbstractList$IteratorImpl_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractList$IteratorImpl_12_1classLit;
}

function java_util_AbstractList$IteratorImpl_hasNext__(){
  return this.java_util_AbstractList$IteratorImpl_i < this.java_util_AbstractList$IteratorImpl_this$0.java_util_ArrayList_size;
}

function java_util_AbstractList$IteratorImpl_next__(){
  return java_util_AbstractList$IteratorImpl_$next__Ljava_util_AbstractList$IteratorImpl_2(this);
}

function java_util_AbstractList$IteratorImpl(){
}

_ = java_util_AbstractList$IteratorImpl.prototype = new java_lang_Object();
_.getClass__$ = java_util_AbstractList$IteratorImpl_getClass__;
_.hasNext__ = java_util_AbstractList$IteratorImpl_hasNext__;
_.next__ = java_util_AbstractList$IteratorImpl_next__;
_.java_lang_Object_typeId$ = 0;
_.java_util_AbstractList$IteratorImpl_i = 0;
_.java_util_AbstractList$IteratorImpl_this$0 = null;
function java_util_ArrayList_$ArrayList__Ljava_util_ArrayList_2(this$static){
  this$static.java_util_ArrayList_array = com_google_gwt_lang_Array_initDim__Ljava_lang_Class_2IIII(com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1Object_12_1classLit, 0, 0, 0, 0);
  this$static.java_util_ArrayList_size = 0;
  return this$static;
}

function java_util_ArrayList_$add__Ljava_util_ArrayList_2Ljava_lang_Object_2(this$static, o){
  com_google_gwt_lang_Array_setCheck__Lcom_google_gwt_lang_Array_2ILjava_lang_Object_2(this$static.java_util_ArrayList_array, this$static.java_util_ArrayList_size++, o);
  return true;
}

function java_util_ArrayList_$add__Ljava_util_ArrayList_2ILjava_lang_Object_2(this$static, index, o){
  if (index < 0 || index > this$static.java_util_ArrayList_size) {
    java_util_AbstractList_indexOutOfBounds__II(index, this$static.java_util_ArrayList_size);
  }
  this$static.java_util_ArrayList_array.splice(index, 0, o);
  ++this$static.java_util_ArrayList_size;
}

function java_util_ArrayList_$get__Ljava_util_ArrayList_2I(this$static, index){
  java_util_AbstractList_checkIndex__II(index, this$static.java_util_ArrayList_size);
  return this$static.java_util_ArrayList_array[index];
}

function java_util_ArrayList_add__Ljava_lang_Object_2(o){
  return com_google_gwt_lang_Array_setCheck__Lcom_google_gwt_lang_Array_2ILjava_lang_Object_2(this.java_util_ArrayList_array, this.java_util_ArrayList_size++, o) , true;
}

function java_util_ArrayList_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1ArrayList_12_1classLit;
}

function java_util_ArrayList_size__(){
  return this.java_util_ArrayList_size;
}

function java_util_ArrayList(){
}

_ = java_util_ArrayList.prototype = new java_util_AbstractList();
_.add__Ljava_lang_Object_2 = java_util_ArrayList_add__Ljava_lang_Object_2;
_.getClass__$ = java_util_ArrayList_getClass__;
_.size__ = java_util_ArrayList_size__;
_.java_lang_Object_typeId$ = 20;
_.java_util_ArrayList_array = null;
_.java_util_ArrayList_size = 0;
function java_util_HashMap_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1HashMap_12_1classLit;
}

function java_util_HashMap(){
}

_ = java_util_HashMap.prototype = new java_util_AbstractHashMap();
_.getClass__$ = java_util_HashMap_getClass__;
_.java_lang_Object_typeId$ = 21;
function java_util_NoSuchElementException_getClass__(){
  return com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1NoSuchElementException_12_1classLit;
}

function java_util_NoSuchElementException(){
}

_ = java_util_NoSuchElementException.prototype = new java_lang_RuntimeException();
_.getClass__$ = java_util_NoSuchElementException_getClass__;
_.java_lang_Object_typeId$ = 22;
function init(){
  !!$stats && $stats({moduleName:$moduleName, subSystem:$intern_35, evtGroup:$intern_36, millis:(new Date()).getTime(), type:$intern_37, className:$intern_38});
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

var com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1String_12_1classLit = java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2($intern_39, $intern_40), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Object_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_42), com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1gadgets_1client_1Gadget_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_43, $intern_44), com_google_gwt_lang_ClassLiteralHolder_Lcom_1example_1friends_1client_1Friends_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_45, $intern_46), com_google_gwt_lang_ClassLiteralHolder_Lcom_1example_1friends_1client_1FriendsGadgetImpl_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_45, $intern_47), com_google_gwt_lang_ClassLiteralHolder_Lcom_1example_1friends_1client_1OpenSocialFeature_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_45, $intern_48), com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1impl_1StringBufferImpl_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_49, $intern_50), com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1impl_1StringBufferImplAppend_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_49, $intern_51), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Throwable_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_52), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Exception_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_53), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1RuntimeException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_54), com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1JavaScriptException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_55, $intern_56), com_google_gwt_lang_ClassLiteralHolder_Lcom_1google_1gwt_1core_1client_1JavaScriptObject_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_55, $intern_57), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1IndexOutOfBoundsException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_58), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1ArrayStoreException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_59), com_google_gwt_lang_ClassLiteralHolder__13C_1classLit = java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2($intern_7, $intern_60), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1Class_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_61), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1ClassCastException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_62), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1IllegalArgumentException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_63), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1NumberFormatException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_64), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1String_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_13), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1StringBuffer_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_65), com_google_gwt_lang_ClassLiteralHolder_Ljava_1lang_1UnsupportedOperationException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_41, $intern_66), com_google_gwt_lang_ClassLiteralHolder__13Ljava_1lang_1Object_12_1classLit = java_lang_Class_createForArray__Ljava_lang_String_2Ljava_lang_String_2($intern_39, $intern_67), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractCollection_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_69), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractMap_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_70), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_71), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractSet_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_72), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$EntrySet_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_73), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$EntrySetIterator_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_74), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractMapEntry_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_75), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$MapEntryNull_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_76), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractHashMap$MapEntryString_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_77), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractList_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_78), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1AbstractList$IteratorImpl_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_79), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1ArrayList_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_80), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1HashMap_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_81), com_google_gwt_lang_ClassLiteralHolder_Ljava_1util_1NoSuchElementException_12_1classLit = java_lang_Class_createForClass__Ljava_lang_String_2Ljava_lang_String_2Ljava_lang_Class_2($intern_68, $intern_82);
$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});
if (friends) friends.onScriptLoad(gwtOnLoad);
})();

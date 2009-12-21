
//
// This is a Greasemonkey user script.  To install it, you need
// Greasemonkey 0.3 or later: http://greasemonkey.mozdev.org/
// Then restart Firefox and revisit this script.
// Under Tools, there will be a new menu item to "Install User Script".
// Accept the default configuration and install.
//
// To uninstall, go to Tools/Manage User Scripts,
// select "Birthdayplus", and click Uninstall.
//
// --------------------------------------------------------------------
//
// ==UserScript==
// @name          Birthdayplus
// @namespace  
// @description   script to add items to your Birthdayplus wishlist from zap// @include http://www.zap.co.il/model.aspx?modelid=*      // @exclude   
// ==/UserScript==



navbar = document.getElementById('minPrice');


if(navbar){
var link = document.createElement("div");
link.innerHTML= '<a href="javascript:void(0);"style="text-decoration: none;">birthdayplus</a>';
navbar.parentNode.insertBefore(link, navbar.nextSibling);
link.addEventListener("click",addItem,true);
}

function addItem(){
window.open("http://testrpcplus.appspot.com/birthdayplus/addWishlistItem?href="+window.location.href ,"_blank","width=800,height=500");

}
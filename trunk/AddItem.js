function()
{
    function I(u){
        var t=u.split('.'),e=t[t.length-1].toLowerCase();
         return {gif:1,jpg:1,jpeg:1,png:1,mng:1}[e]
     }
var q,h,i;
for(i=0;q=document.links[i];++i){
     h=q.href;
     if(h&&I(h))
        {break;}
     else h=null;
}

void(open('http://birthdayplus.appspot.com/birthdayplus/addWishlistItem?autoclose=yes&link='+encodeURIComponent(location.href)+'&wish='+encodeURIComponent(document.title)+'&thumbnail='+encodeURIComponent(h),'Birthday+','scrollbars=yes,menubars=no,toolbars=no,resizable=yes,width=800,height=500'))
}()
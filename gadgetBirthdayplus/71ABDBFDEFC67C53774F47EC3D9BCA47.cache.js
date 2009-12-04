(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'sadna',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var F='',kb=', Size: ',ib=':',B='DOMMouseScroll',r='Hello World!',jb='Index: ',ub='Object;',pb='SimpleGadget',rb='Widget;',qb='[Lcom.google.gwt.user.client.ui.',tb='[Ljava.lang.',C='blur',cb='button',hb='change',db='className',q='click',ob='com.client.Sadna',D='contextmenu',sb='dblclick',z='error',vb='focus',eb='gwt-Button',wb='keydown',xb='keypress',yb='keyup',E='left',zb='load',s='losecapture',mb='moduleStartup',t='mousedown',u='mousemove',v='mouseout',w='mouseover',x='mouseup',A='mousewheel',nb='onModuleLoadStart',bb='position',y='scroll',lb='startup',fb='submit',ab='top',gb='type';var _;function nn(a){return this===(a==null?null:a)}
function on(){return this.$H||(this.$H=++oc)}
function ln(){}
_=ln.prototype={};_.eQ=nn;_.hC=on;_.tM=ns;_.tI=1;function gc(b,a){return b.tM==ns||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function ic(a){return a.tM==ns||a.tI==2?a.hC():a.$H||(a.$H=++oc)}
var oc=0;function xc(){xc=ns;sc();new qc()}
function Ac(a){var b=a.parentNode;if(b==null){return null}if(b.nodeType!=1)b=null;return b}
function pc(){}
_=pc.prototype=new ln();_.tI=0;function vc(){vc=ns;xc()}
function uc(){}
_=uc.prototype=new pc();_.tI=0;function sc(){sc=ns;vc()}
function tc(b,a){while(a){if(b==a){return true}a=a.parentNode;if(a&&a.nodeType!=1){a=null}}return false}
function qc(){}
_=qc.prototype=new uc();_.tI=0;function bf(){}
_=bf.prototype=new ln();_.tI=0;_.c=false;_.d=null;function Ad(d,c,e){var a,b,f;if(Bd){f=yg(Bd.a[(xc(),d).type],3);if(f){a=f.a.a;b=f.a.b;f.a.a=d;f.a.b=e;wl(c,f.a);f.a.a=a;f.a.b=b}}}
function td(){}
_=td.prototype=new bf();_.tI=0;_.a=null;_.b=null;var Bd=null;function od(){od=ns;pd=vd(new ud(),q,(od(),new md()))}
function qd(a){yg(this.d,2);$wnd.alert(r)}
function rd(){return pd}
function md(){}
_=md.prototype=new td();_.i=qd;_.m=rd;_.tI=0;var pd;function df(a){a.c=++gf;return a}
function ff(){return this.c}
function cf(){}
_=cf.prototype=new ln();_.hC=ff;_.tI=0;_.c=0;var gf=0;function vd(c,a,b){c.c=++gf;c.a=b;if(!Bd){Bd=re(new me())}Bd.a[a]=c;c.b=a;return c}
function ud(){}
_=ud.prototype=new cf();_.tI=7;_.a=null;_.b=null;function re(a){a.a={};return a}
function me(){}
_=me.prototype=new ln();_.tI=0;_.a=null;function ye(a){zk()}
function ze(b){var a;if(xe){a=new ve();Ef(b,a)}}
function Ae(){return xe}
function ve(){}
_=ve.prototype=new bf();_.i=ye;_.m=Ae;_.tI=0;var xe=null;function Ee(){}
_=Ee.prototype=new ln();_.tI=0;function yf(b,a){b.d=rf(new pf());b.e=a;b.c=false;return b}
function Af(b,c,a){if(b.b>0){Cf(b,mf(new lf(),b,c,a))}else{sf(b.d,c,a)}return new Ee()}
function Cf(b,a){if(!b.a){b.a=cr(new br())}er(b.a,a)}
function Ef(c,a){var b;if(a.c){a.c=false;a.d=null}b=a.d;a.d=c.e;try{++c.b;uf(c.d,a,c.c)}finally{--c.b;if(c.b==0){Ff(c)}}if(b==null){a.c=true;a.d=null}else{a.d=b}}
function Ff(c){var a,b;if(c.a){try{for(b=Ap(new yp(),c.a);b.a<b.b.b;){a=yg(Dp(b),4);sf(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function kf(){}
_=kf.prototype=new ln();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function mf(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function lf(){}
_=lf.prototype=new ln();_.tI=8;_.a=null;_.b=null;_.c=null;function rf(a){a.a=or(new nr());return a}
function sf(c,d,a){var b;b=yg(mp(c.a,d),5);if(!b){b=cr(new br());sp(c.a,d,b)}sg(b.a,b.b++,a)}
function uf(i,e,h){var d,f,g,j,a,b,c;j=e.m();d=(a=yg(mp(i.a,j),5),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=yg(mp(i.a,j),5),yg((cq(g,b.b),b.a[g]),10));e.i(f)}}else{for(g=0;g<d;++g){f=(c=yg(mp(i.a,j),5),yg((cq(g,c.b),c.a[g]),10));e.i(f)}}}
function pf(){}
_=pf.prototype=new ln();_.tI=0;function pg(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function qg(a,f,c,b,e){var d;d=pg(e,b);gg();lg(d,hg,ig);d.tI=f;d.qI=c;return d}
function sg(a,b,c){if(c!=null){if(a.qI>0&&!wg(c.tI,a.qI)){throw new um()}if(a.qI<0&&(c.tM==ns||c.tI==2)){throw new um()}}return a[b]=c}
function eg(){}
_=eg.prototype=new ln();_.tI=0;_.length=0;_.qI=0;function gg(){gg=ns;hg=[];ig=[];jg(new eg(),hg,ig)}
function jg(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function lg(a,c,d){gg();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var hg,ig;function xg(b,a){return b&&!!dh[b][a]}
function wg(b,a){return b&&dh[b][a]}
function yg(b,a){if(b!=null&&!wg(b.tI,a)){throw new ym()}return b}
var dh=[{},{},{1:1},{11:1},{11:1},{11:1},{11:1},{3:1},{4:1},{10:1},{6:1},{8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{10:1},{10:1},{2:1,6:1,7:1,8:1,12:1},{10:1},{2:1,6:1,7:1,8:1,12:1},{11:1},{11:1},{11:1},{11:1},{11:1},{11:1},{11:1},{11:1},{15:1},{15:1},{13:1},{13:1},{13:1},{15:1},{5:1},{14:1},{15:1},{13:1},{11:1},{9:1}];function vh(){}
_=vh.prototype=new ln();_.tI=9;function Ah(b,a,c){var d;if(a==Dh){if(bj((xc(),b).type)==8192){Dh=null}}d=zh;zh=b;try{c.s(b)}finally{zh=d}}
function Eh(a,b){dj();Bi(a,b)}
var zh=null,Dh=null;function li(a){ui();return mi(xe?xe:(xe=df(new cf())),a)}
function mi(b,a){return Af(si(),b,a)}
function pi(){if(oi){ze(si())}}
function qi(){var a;if(oi){a=(di(),new bi());ri(a);return null}return null}
function ri(a){if(ti){Ef(ti,a)}}
function si(){if(!ti){ti=ii(new hi())}return ti}
function ui(){if(!oi){jj();oi=true}}
var oi=false,ti=null;function di(){di=ns;ei=df(new cf())}
function fi(a){null.z()}
function gi(){return ei}
function bi(){}
_=bi.prototype=new bf();_.i=fi;_.m=gi;_.tI=0;var ei;function ii(a){a.d=rf(new pf());a.e=null;a.c=false;return a}
function hi(){}
_=hi.prototype=new kf();_.tI=10;function bj(a){switch(a){case C:return 4096;case hb:return 1024;case q:return 1;case sb:return 2;case vb:return 2048;case wb:return 128;case xb:return 256;case yb:return 512;case zb:return 32768;case s:return 8192;case t:return 4;case u:return 64;case v:return 32;case w:return 16;case x:return 8;case y:return 16384;case z:return 65536;case A:return 131072;case B:return 131072;case D:return 262144;}}
function dj(){if(!fj){Ai();fj=true}}
function gj(a){return !(a!=null&&(a.tM!=ns&&a.tI!=2))&&(a!=null&&xg(a.tI,7))}
var fj=false;function Ai(){Ei=function(b){if(Di(b)){var a=Ci;if(a&&a.__listener){if(gj(a.__listener)){Ah(b,a,a.__listener);b.stopPropagation()}}}};Di=function(a){return true};Fi=function(b){var c,a=this;while(a&&!(c=a.__listener)){a=a.parentNode}if(a&&a.nodeType!=1){a=null}if(c){if(gj(c)){Ah(b,a,c)}}};$wnd.addEventListener(q,Ei,true);$wnd.addEventListener(sb,Ei,true);$wnd.addEventListener(t,Ei,true);$wnd.addEventListener(x,Ei,true);$wnd.addEventListener(u,Ei,true);$wnd.addEventListener(w,Ei,true);$wnd.addEventListener(v,Ei,true);$wnd.addEventListener(A,Ei,true);$wnd.addEventListener(wb,Di,true);$wnd.addEventListener(yb,Di,true);$wnd.addEventListener(xb,Di,true)}
function Bi(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?Fi:null;if(b&2)c.ondblclick=a&2?Fi:null;if(b&4)c.onmousedown=a&4?Fi:null;if(b&8)c.onmouseup=a&8?Fi:null;if(b&16)c.onmouseover=a&16?Fi:null;if(b&32)c.onmouseout=a&32?Fi:null;if(b&64)c.onmousemove=a&64?Fi:null;if(b&128)c.onkeydown=a&128?Fi:null;if(b&256)c.onkeypress=a&256?Fi:null;if(b&512)c.onkeyup=a&512?Fi:null;if(b&1024)c.onchange=a&1024?Fi:null;if(b&2048)c.onfocus=a&2048?Fi:null;if(b&4096)c.onblur=a&4096?Fi:null;if(b&8192)c.onlosecapture=a&8192?Fi:null;if(b&16384)c.onscroll=a&16384?Fi:null;if(b&32768)c.onload=a&32768?Fi:null;if(b&65536)c.onerror=a&65536?Fi:null;if(b&131072)c.onmousewheel=a&131072?Fi:null;if(b&262144)c.oncontextmenu=a&262144?Fi:null}
var Ci=null,Di=null,Ei=null,Fi=null;function jj(){var d=$wnd.onbeforeunload;var e=$wnd.onunload;$wnd.onbeforeunload=function(a){var c,b;try{c=qi()}finally{b=d&&d(a)}if(c!=null){return c}if(b!=null){return b}};$wnd.onunload=function(a){try{pi()}finally{e&&e(a);$wnd.onresize=null;$wnd.onscroll=null;$wnd.onbeforeunload=null;$wnd.onunload=null}}}
function cl(){}
_=cl.prototype=new ln();_.tI=11;_.f=null;function ul(b,a,c){Bl(b,bj(c.b));return Af(!b.d?(b.d=yf(new kf(),b)):b.d,c,a)}
function wl(b,a){if(b.d){Ef(b.d,a)}}
function xl(b){var a;if(b.b){throw new dn()}b.b=true;b.f.__listener=b;a=b.c;b.c=-1;if(a>0){Bl(b,a)}b.j();b.t()}
function yl(a){if(!a.b){throw new dn()}try{a.u()}finally{a.k();a.f.__listener=null;a.b=false}}
function zl(a){if(!a.e){yk();if(jp(Dk.a,a)){yl(a);vp(Dk.a,a)!=null}}else if(a.e){nj(a.e,a)}else if(a.e){throw new dn()}}
function Al(c,b){var a;a=c.e;if(!b){if(!!a&&a.b){yl(c)}c.e=null}else{if(a){throw new dn()}c.e=b;if(b.b){xl(c)}}}
function Bl(b,a){if(b.c==-1){Eh(b.f,a|(b.f.__eventBits||0))}else{b.c|=a}}
function Cl(){}
function Dl(){}
function El(a){var b;switch(bj((xc(),a).type)){case 16:case 32:b=a.relatedTarget;if(!!b&&tc(this.f,b)){return}}Ad(a,this,this.f)}
function Fl(){}
function am(){}
function el(){}
_=el.prototype=new cl();_.j=Cl;_.k=Dl;_.s=El;_.t=Fl;_.u=am;_.tI=12;_.b=false;_.c=0;_.d=null;_.e=null;function nk(){var a,b;for(b=this.q();b.a<b.b.b-1;){a=kl(b);xl(a)}}
function ok(){var a,b;for(b=this.q();b.a<b.b.b-1;){a=kl(b);yl(a)}}
function pk(){}
function qk(){}
function lk(){}
_=lk.prototype=new el();_.j=nk;_.k=ok;_.t=pk;_.u=qk;_.tI=13;function zj(c,a,b){zl(a);ol(c.a,a);b.appendChild(a.f);Al(a,c)}
function Bj(b,c){var a;if(c.e!=b){return false}Al(c,null);a=c.f;Ac((xc(),a)).removeChild(a);tl(b.a,c);return true}
function Cj(){return il(new gl(),this.a)}
function xj(){}
_=xj.prototype=new lk();_.q=Cj;_.tI=14;function lj(a,b){zj(a,b,a.f)}
function nj(b,c){var a;a=Bj(b,c);if(a){oj(c.f)}return a}
function oj(a){a.style[E]=F;a.style[ab]=F;a.style[bb]=F}
function kj(){}
_=kj.prototype=new xj();_.tI=15;function Ej(){Ej=ns;Fj=(om(),qm)}
function Dj(){}
_=Dj.prototype=new el();_.tI=16;var Fj;function sj(){sj=ns;Ej()}
function rj(b,a){sj();b.f=a;Fj.v(b.f,0);return b}
function qj(){}
_=qj.prototype=new Dj();_.tI=17;function vj(){vj=ns;sj()}
function uj(b,a){vj();rj(b,(xc(),$doc).createElement(cb));wj(b.f);b.f[db]=eb;b.f.innerHTML=a||F;return b}
function wj(b){if(b.type==fb){try{b.setAttribute(gb,cb)}catch(a){}}}
function pj(){}
_=pj.prototype=new qj();_.tI=18;function gk(){}
_=gk.prototype=new vh();_.tI=19;function hk(){}
_=hk.prototype=new gk();_.tI=20;function yk(){yk=ns;Ck=or(new nr());Dk=sr(new rr())}
function xk(b,a){yk();b.a=nl(new fl());b.f=a;xl(b);return b}
function zk(){var b,a;yk();var c,d;for(d=(b=oo(new no(),Aq(Dk.a).b.a),kq(new jq(),b));Cp(d.a.a);){c=yg((a=yg(Dp(d.a.a),13),a.n()),2);if(c.b){yl(c)}}hp(Dk.a);hp(Ck)}
function Bk(a){yk();var b;b=yg(mp(Ck,a),12);if(b){return b}if(Ck.d==0){li(new sk())}b=vk(new uk());sp(Ck,a,b);tr(Dk,b);return b}
function rk(){}
_=rk.prototype=new kj();_.tI=21;var Ck,Dk;function sk(){}
_=sk.prototype=new ln();_.tI=22;function wk(){wk=ns;yk()}
function vk(a){wk();xk(a,$doc.body);return a}
function uk(){}
_=uk.prototype=new rk();_.tI=23;function nl(a){a.a=qg(gh,0,2,4,0);return a}
function ol(a,b){rl(a,b,a.b)}
function ql(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function rl(d,e,a){var b,c;if(a<0||a>d.b){throw new gn()}if(d.b==d.a.length){c=qg(gh,0,2,d.a.length*2,0);for(b=0;b<d.a.length;++b){sg(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){sg(d.a,b,d.a[b-1])}sg(d.a,a,e)}
function sl(c,b){var a;if(b<0||b>=c.b){throw new gn()}--c.b;for(a=b;a<c.b;++a){sg(c.a,a,c.a[a+1])}sg(c.a,c.b,null)}
function tl(b,c){var a;a=ql(b,c);if(a==-1){throw new gs()}sl(b,a)}
function fl(){}
_=fl.prototype=new ln();_.tI=0;_.a=null;_.b=0;function il(b,a){b.b=a;return b}
function kl(a){if(a.a>=a.b.b){throw new gs()}return a.b.a[++a.a]}
function ll(){return this.a<this.b.b-1}
function ml(){return kl(this)}
function gl(){}
_=gl.prototype=new ln();_.p=ll;_.r=ml;_.tI=0;_.a=-1;_.b=null;function om(){om=ns;pm=km(new jm());qm=pm?(om(),new bm()):pm}
function rm(a,b){a.tabIndex=b}
function bm(){}
_=bm.prototype=new ln();_.v=rm;_.tI=0;var pm,qm;function fm(){fm=ns;om()}
function gm(){return function(a){if(this.parentNode.onblur){this.parentNode.onblur(a)}}}
function hm(){return function(a){if(this.parentNode.onfocus){this.parentNode.onfocus(a)}}}
function im(a,b){a.firstChild.tabIndex=b}
function cm(){}
_=cm.prototype=new bm();_.v=im;_.tI=0;function lm(){lm=ns;fm()}
function km(a){lm();gm();hm();mm();return a}
function mm(){return function(){var a=this.firstChild;$wnd.setTimeout(function(){a.focus()},0)}}
function jm(){}
_=jm.prototype=new cm();_.tI=0;function ao(){}
_=ao.prototype=new ln();_.tI=3;function bn(){}
_=bn.prototype=new ao();_.tI=4;function pn(){}
_=pn.prototype=new bn();_.tI=5;function um(){}
_=um.prototype=new pn();_.tI=25;function Bm(c,a){var b;b=new xm();return b}
function xm(){}
_=xm.prototype=new ln();_.tI=0;function ym(){}
_=ym.prototype=new pn();_.tI=28;function dn(){}
_=dn.prototype=new pn();_.tI=29;function hn(b,a){return b}
function gn(){}
_=gn.prototype=new pn();_.tI=30;function En(a){if(!(a!=null&&xg(a.tI,1))){return false}return String(this)==a}
function Fn(){return yn(this)}
_=String.prototype;_.eQ=En;_.hC=Fn;_.tI=2;function tn(){tn=ns;un={};xn={}}
function vn(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function yn(c){tn();var a=ib+c;var b=xn[a];if(b!=null){return b}b=un[a];if(b==null){b=vn(c)}zn();return xn[a]=b}
function zn(){if(wn==256){un=xn;xn={};wn=0}++wn}
var un,wn=0,xn;function co(){}
_=co.prototype=new pn();_.tI=31;function ho(a,b){var c;while(a.p()){c=a.r();if(b==null?c==null:gc(b,c)){return a}}return null}
function jo(a){throw new co()}
function ko(b){var a;a=ho(this.q(),b);return !!a}
function go(){}
_=go.prototype=new ln();_.g=jo;_.h=ko;_.tI=0;function Aq(b){var a;a=so(new mo(),b);return pq(new iq(),b,a)}
function Bq(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&xg(c.tI,14))){return false}e=yg(c,14);if(yg(this,14).d!=e.d){return false}for(b=oo(new no(),so(new mo(),e).a);Cp(b.a);){a=yg(Dp(b.a),13);d=a.n();f=a.o();if(!(d==null?yg(this,14).c:d!=null&&xg(d.tI,1)?op(yg(this,14),yg(d,1)):np(yg(this,14),d,~~ic(d)))){return false}if(!ms(f,d==null?yg(this,14).b:d!=null&&xg(d.tI,1)?yg(this,14).e[ib+yg(d,1)]:kp(yg(this,14),d,~~ic(d)))){return false}}return true}
function Cq(){var a,b,c;c=0;for(b=oo(new no(),so(new mo(),yg(this,14)).a);Cp(b.a);){a=yg(Dp(b.a),13);c+=a.hC();c=~~c}return c}
function hq(){}
_=hq.prototype=new ln();_.eQ=Bq;_.hC=Cq;_.tI=0;function fp(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.g(a[f])}}}}
function gp(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=dp(e,c.substring(1));a.g(b)}}}
function hp(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function jp(b,a){return a==null?b.c:a!=null&&xg(a.tI,1)?op(b,yg(a,1)):np(b,a,~~ic(a))}
function mp(b,a){return a==null?b.b:a!=null&&xg(a.tI,1)?b.e[ib+yg(a,1)]:kp(b,a,~~ic(a))}
function kp(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(h.l(g,d)){return c.o()}}}return null}
function np(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(h.l(g,d)){return true}}}return false}
function op(b,a){return ib+a in b.e}
function sp(b,a,c){return a==null?qp(b,c):a!=null&&xg(a.tI,1)?rp(b,yg(a,1),c):pp(b,a,c,~~ic(a))}
function pp(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(i.l(g,d)){var h=c.o();c.w(j);return h}}}else{a=i.a[e]=[]}var c=Fr(new Er(),g,j);a.push(c);++i.d;return null}
function qp(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function rp(d,a,e){var b,c=d.e;a=ib+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function vp(b,a){return !a?up(b):tp(b,a,~~(a.$H||(a.$H=++oc)))}
function tp(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(h.l(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.o()}}}return null}
function up(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function wp(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&gc(a,b)}
function lo(){}
_=lo.prototype=new hq();_.l=wp;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function Fq(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&xg(b.tI,15))){return false}c=yg(b,15);if(c.x()!=this.x()){return false}for(a=c.q();a.p();){d=a.r();if(!this.h(d)){return false}}return true}
function ar(){var a,b,c;a=0;for(b=this.q();b.p();){c=b.r();if(c!=null){a+=ic(c);a=~~a}}return a}
function Dq(){}
_=Dq.prototype=new go();_.eQ=Fq;_.hC=ar;_.tI=32;function so(b,a){b.a=a;return b}
function uo(d,c){var a,b,e;if(c!=null&&xg(c.tI,13)){a=yg(c,13);b=a.n();if(jp(d.a,b)){e=mp(d.a,b);return qr(a.o(),e)}}return false}
function vo(a){return uo(this,a)}
function wo(){return oo(new no(),this.a)}
function xo(){return this.a.d}
function mo(){}
_=mo.prototype=new Dq();_.h=vo;_.q=wo;_.x=xo;_.tI=33;_.a=null;function oo(c,b){var a;c.b=b;a=cr(new br());if(c.b.c){er(a,zo(new yo(),c.b))}gp(c.b,a);fp(c.b,a);c.a=Ap(new yp(),a);return c}
function qo(){return Cp(this.a)}
function ro(){return yg(Dp(this.a),13)}
function no(){}
_=no.prototype=new ln();_.p=qo;_.r=ro;_.tI=0;_.a=null;_.b=null;function xq(b){var a;if(b!=null&&xg(b.tI,13)){a=yg(b,13);if(ms(this.n(),a.n())&&ms(this.o(),a.o())){return true}}return false}
function yq(){var a,b;a=0;b=0;if(this.n()!=null){a=ic(this.n())}if(this.o()!=null){b=ic(this.o())}return a^b}
function vq(){}
_=vq.prototype=new ln();_.eQ=xq;_.hC=yq;_.tI=34;function zo(b,a){b.a=a;return b}
function Bo(){return null}
function Co(){return this.a.b}
function Do(a){return qp(this.a,a)}
function yo(){}
_=yo.prototype=new vq();_.n=Bo;_.o=Co;_.w=Do;_.tI=35;_.a=null;function Fo(c,a,b){c.b=b;c.a=a;return c}
function bp(){return this.a}
function cp(){return this.b.e[ib+this.a]}
function dp(b,a){return Fo(new Eo(),a,b)}
function ep(a){return rp(this.b,this.a,a)}
function Eo(){}
_=Eo.prototype=new vq();_.n=bp;_.o=cp;_.w=ep;_.tI=36;_.a=null;_.b=null;function bq(a){dr(this,this.x(),a);return true}
function cq(a,b){if(a<0||a>=b){fq(a,b)}}
function dq(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&xg(e.tI,5))){return false}f=yg(e,5);if(this.x()!=f.b){return false}c=Ap(new yp(),yg(this,5));d=Ap(new yp(),f);while(c.a<c.b.b){a=Dp(c);b=Dp(d);if(!(a==null?b==null:gc(a,b))){return false}}return true}
function eq(){var a,b,c;b=1;a=Ap(new yp(),yg(this,5));while(a.a<a.b.b){c=Dp(a);b=31*b+(c==null?0:ic(c));b=~~b}return b}
function fq(a,b){throw hn(new gn(),jb+a+kb+b)}
function gq(){return Ap(new yp(),yg(this,5))}
function xp(){}
_=xp.prototype=new go();_.g=bq;_.eQ=dq;_.hC=eq;_.q=gq;_.tI=0;function Ap(b,a){b.b=a;return b}
function Cp(a){return a.a<a.b.b}
function Dp(a){if(a.a>=a.b.b){throw new gs()}return gr(a.b,a.a++)}
function Ep(){return this.a<this.b.b}
function Fp(){return Dp(this)}
function yp(){}
_=yp.prototype=new ln();_.p=Ep;_.r=Fp;_.tI=0;_.a=0;_.b=null;function pq(b,a,c){b.a=a;b.b=c;return b}
function sq(a){return jp(this.a,a)}
function tq(){var a;return a=oo(new no(),this.b.a),kq(new jq(),a)}
function uq(){return this.b.a.d}
function iq(){}
_=iq.prototype=new Dq();_.h=sq;_.q=tq;_.x=uq;_.tI=37;_.a=null;_.b=null;function kq(a,b){a.a=b;return a}
function nq(){return Cp(this.a.a)}
function oq(){var a;return a=yg(Dp(this.a.a),13),a.n()}
function jq(){}
_=jq.prototype=new ln();_.p=nq;_.r=oq;_.tI=0;_.a=null;function cr(a){a.a=qg(hh,0,0,0,0);a.b=0;return a}
function er(b,a){sg(b.a,b.b++,a);return true}
function dr(c,a,b){if(a<0||a>c.b){fq(a,c.b)}c.a.splice(a,0,b);++c.b}
function gr(b,a){cq(a,b.b);return b.a[a]}
function hr(c,b,a){for(;a<c.b;++a){if(ms(b,c.a[a])){return a}}return -1}
function ir(a){return sg(this.a,this.b++,a),true}
function jr(a){return hr(this,a,0)!=-1}
function kr(){return this.b}
function br(){}
_=br.prototype=new xp();_.g=ir;_.h=jr;_.x=kr;_.tI=38;_.a=null;_.b=0;function or(a){hp(a);return a}
function qr(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&gc(a,b)}
function nr(){}
_=nr.prototype=new lo();_.tI=39;function sr(a){a.a=or(new nr());return a}
function tr(c,a){var b;b=sp(c.a,a,c);return b==null}
function xr(b){var a;return a=sp(this.a,b,this),a==null}
function yr(a){return jp(this.a,a)}
function zr(){var a;return a=oo(new no(),Aq(this.a).b.a),kq(new jq(),a)}
function Ar(){return this.a.d}
function rr(){}
_=rr.prototype=new Dq();_.g=xr;_.h=yr;_.q=zr;_.x=Ar;_.tI=40;_.a=null;function Fr(b,a,c){b.a=a;b.b=c;return b}
function bs(){return this.a}
function cs(){return this.b}
function es(b){var a;a=this.b;this.b=b;return a}
function Er(){}
_=Er.prototype=new vq();_.n=bs;_.o=cs;_.w=es;_.tI=41;_.a=null;_.b=null;function gs(){}
_=gs.prototype=new pn();_.tI=42;function ms(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&gc(a,b)}
function sm(){var b,a;!!$stats&&$stats({moduleName:$moduleName,subSystem:lb,evtGroup:mb,millis:(new Date()).getTime(),type:nb,className:ob});b=uj(new pj(),pb);a=new hk();ul(b,a,(od(),od(),pd));lj((yk(),Bk(null)),b)}
function gwtOnLoad(b,p,o){$moduleName=p;$moduleBase=o;if(b)try{sm()}catch(a){b(p)}else{sm()}}
function ns(){}
var gh=Bm(qb,rb),hh=Bm(tb,ub);$stats && $stats({moduleName:'sadna',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (sadna) sadna.onScriptLoad(gwtOnLoad);})();
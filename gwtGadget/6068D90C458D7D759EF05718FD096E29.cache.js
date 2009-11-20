(function(){var $gwt_version = "1.7.1";var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var $stats = $wnd.__gwtStatsEvent ? function(a) {return $wnd.__gwtStatsEvent(a);} : null;$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalStart'});var bb='',mb=', Size: ',kb=':',B='DOMMouseScroll',r='Hello World!',lb='Index: ',F='MouseEvents',wb='Object;',rb='SimpleGadget',ub='Widget;',tb='[Lcom.google.gwt.user.client.ui.',vb='[Ljava.lang.',C='blur',eb='button',hb='change',fb='className',q='click',qb='com.example.friends.client.Friends',D='contextmenu',sb='dblclick',z='error',xb='focus',gb='gwt-Button',E='html',yb='keydown',zb='keypress',Ab='keyup',ab='left',Bb='load',s='losecapture',ob='moduleStartup',t='mousedown',u='mousemove',v='mouseout',w='mouseover',x='mouseup',A='mousewheel',pb='onModuleLoadStart',db='position',y='scroll',nb='startup',ib='submit',cb='top',jb='type';var _;function tn(a){return this===(a==null?null:a)}
function un(){return this.$H||(this.$H=++qc)}
function rn(){}
_=rn.prototype={};_.eQ=tn;_.hC=un;_.tM=ts;_.tI=1;function ic(b,a){return b.tM==ts||b.tI==2?b.eQ(a):(b==null?null:b)===(a==null?null:a)}
function kc(a){return a.tM==ts||a.tI==2?a.hC():a.$H||(a.$H=++qc)}
var qc=0;function Cc(){Cc=ts;vc();new tc()}
function Fc(a){var b=a.parentNode;if(b==null){return null}if(b.nodeType!=1)b=null;return b}
function rc(){}
_=rc.prototype=new rn();_.tI=0;function Bc(){Bc=ts;Cc()}
function Ac(){}
_=Ac.prototype=new rc();_.tI=0;function xc(){xc=ts;Bc()}
function yc(b){var d=b.relatedTarget;try{var c=d.nodeName;return d}catch(a){return null}}
function zc(b,a){return b===a||!!(b.compareDocumentPosition(a)&16)}
function sc(){}
_=sc.prototype=new Ac();_.tI=0;function vc(){vc=ts;xc()}
function tc(){}
_=tc.prototype=new sc();_.tI=0;function gf(){}
_=gf.prototype=new rn();_.tI=0;_.c=false;_.d=null;function Fd(d,c,e){var a,b,f;if(ae){f=Dg(ae.a[(Cc(),d).type],3);if(f){a=f.a.a;b=f.a.b;f.a.a=d;f.a.b=e;Fl(c,f.a);f.a.a=a;f.a.b=b}}}
function yd(){}
_=yd.prototype=new gf();_.tI=0;_.a=null;_.b=null;var ae=null;function td(){td=ts;ud=Ad(new zd(),q,(td(),new rd()))}
function vd(a){Dg(this.d,2);$wnd.alert(r)}
function wd(){return ud}
function rd(){}
_=rd.prototype=new yd();_.i=vd;_.m=wd;_.tI=0;var ud;function jf(a){a.c=++mf;return a}
function lf(){return this.c}
function hf(){}
_=hf.prototype=new rn();_.hC=lf;_.tI=0;_.c=0;var mf=0;function Ad(c,a,b){c.c=++mf;c.a=b;if(!ae){ae=we(new re())}ae.a[a]=c;c.b=a;return c}
function zd(){}
_=zd.prototype=new hf();_.tI=7;_.a=null;_.b=null;function we(a){a.a={};return a}
function re(){}
_=re.prototype=new rn();_.tI=0;_.a=null;function De(a){cl()}
function Ee(b){var a;if(Ce){a=new Ae();dg(b,a)}}
function Fe(){return Ce}
function Ae(){}
_=Ae.prototype=new gf();_.i=De;_.m=Fe;_.tI=0;var Ce=null;function df(){}
_=df.prototype=new rn();_.tI=0;function Df(b,a){b.d=wf(new uf());b.e=a;b.c=false;return b}
function Ff(b,c,a){if(b.b>0){bg(b,rf(new qf(),b,c,a))}else{xf(b.d,c,a)}return new df()}
function bg(b,a){if(!b.a){b.a=ir(new hr())}kr(b.a,a)}
function dg(c,a){var b;if(a.c){a.c=false;a.d=null}b=a.d;a.d=c.e;try{++c.b;zf(c.d,a,c.c)}finally{--c.b;if(c.b==0){eg(c)}}if(b==null){a.c=true;a.d=null}else{a.d=b}}
function eg(c){var a,b;if(c.a){try{for(b=aq(new Ep(),c.a);b.a<b.b.b;){a=Dg(dq(b),4);xf(a.a.d,a.c,a.b)}}finally{c.a=null}}}
function pf(){}
_=pf.prototype=new rn();_.tI=0;_.a=null;_.b=0;_.c=false;_.d=null;_.e=null;function rf(b,a,d,c){b.a=a;b.c=d;b.b=c;return b}
function qf(){}
_=qf.prototype=new rn();_.tI=8;_.a=null;_.b=null;_.c=null;function wf(a){a.a=ur(new tr());return a}
function xf(c,d,a){var b;b=Dg(sp(c.a,d),5);if(!b){b=ir(new hr());yp(c.a,d,b)}xg(b.a,b.b++,a)}
function zf(i,e,h){var d,f,g,j,a,b,c;j=e.m();d=(a=Dg(sp(i.a,j),5),!a?0:a.b);if(h){for(g=d-1;g>=0;--g){f=(b=Dg(sp(i.a,j),5),Dg((iq(g,b.b),b.a[g]),10));e.i(f)}}else{for(g=0;g<d;++g){f=(c=Dg(sp(i.a,j),5),Dg((iq(g,c.b),c.a[g]),10));e.i(f)}}}
function uf(){}
_=uf.prototype=new rn();_.tI=0;function ug(d,c){var a=new Array(c);if(d>0){var e=[null,0,false,[0,0]][d];for(var b=0;b<c;++b){a[b]=e}}return a}
function vg(a,f,c,b,e){var d;d=ug(e,b);lg();qg(d,mg,ng);d.tI=f;d.qI=c;return d}
function xg(a,b,c){if(c!=null){if(a.qI>0&&!Bg(c.tI,a.qI)){throw new Am()}if(a.qI<0&&(c.tM==ts||c.tI==2)){throw new Am()}}return a[b]=c}
function jg(){}
_=jg.prototype=new rn();_.tI=0;_.length=0;_.qI=0;function lg(){lg=ts;mg=[];ng=[];og(new jg(),mg,ng)}
function og(e,a,b){var c=0,f;for(var d in e){if(f=e[d]){a[c]=d;b[c]=f;++c}}}
function qg(a,c,d){lg();for(var e=0,b=c.length;e<b;++e){a[c[e]]=d[e]}}
var mg,ng;function Cg(b,a){return b&&!!ih[b][a]}
function Bg(b,a){return b&&ih[b][a]}
function Dg(b,a){if(b!=null&&!Bg(b.tI,a)){throw new Em()}return b}
var ih=[{},{},{1:1},{11:1},{11:1},{11:1},{11:1},{3:1},{4:1},{10:1},{6:1},{8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{2:1,6:1,7:1,8:1},{10:1},{10:1},{2:1,6:1,7:1,8:1,12:1},{10:1},{2:1,6:1,7:1,8:1,12:1},{11:1},{11:1},{11:1},{11:1},{11:1},{11:1},{11:1},{11:1},{15:1},{15:1},{13:1},{13:1},{13:1},{15:1},{5:1},{14:1},{15:1},{13:1},{11:1},{9:1}];function Ah(){}
_=Ah.prototype=new rn();_.tI=9;function Fh(b,a,c){var d;if(a==ci){if(kj((Cc(),b).type)==8192){ci=null}}d=Eh;Eh=b;try{c.s(b)}finally{Eh=d}}
var Eh=null,ci=null;function pi(a){yi();return qi(Ce?Ce:(Ce=jf(new hf())),a)}
function qi(b,a){return Ff(wi(),b,a)}
function ti(){if(si){Ee(wi())}}
function ui(){var a;if(si){a=(hi(),new fi());vi(a);return null}return null}
function vi(a){if(xi){dg(xi,a)}}
function wi(){if(!xi){xi=mi(new li())}return xi}
function yi(){if(!si){sj();si=true}}
var si=false,xi=null;function hi(){hi=ts;ii=jf(new hf())}
function ji(a){null.z()}
function ki(){return ii}
function fi(){}
_=fi.prototype=new gf();_.i=ji;_.m=ki;_.tI=0;var ii;function mi(a){a.d=wf(new uf());a.e=null;a.c=false;return a}
function li(){}
_=li.prototype=new pf();_.tI=10;function kj(a){switch(a){case C:return 4096;case hb:return 1024;case q:return 1;case sb:return 2;case xb:return 2048;case yb:return 128;case zb:return 256;case Ab:return 512;case Bb:return 32768;case s:return 8192;case t:return 4;case u:return 64;case v:return 32;case w:return 16;case x:return 8;case y:return 16384;case z:return 65536;case A:return 131072;case B:return 131072;case D:return 262144;}}
function mj(){if(!oj){dj();Ei();oj=true}}
function pj(a){return !(a!=null&&(a.tM!=ts&&a.tI!=2))&&(a!=null&&Cg(a.tI,7))}
var oj=false;function dj(){hj=function(b){if(gj(b)){var a=fj;if(a&&a.__listener){if(pj(a.__listener)){Fh(b,a,a.__listener);b.stopPropagation()}}}};gj=function(a){return true};ij=function(b){var c,a=this;while(a&&!(c=a.__listener)){a=a.parentNode}if(a&&a.nodeType!=1){a=null}if(c){if(pj(c)){Fh(b,a,c)}}};$wnd.addEventListener(q,hj,true);$wnd.addEventListener(sb,hj,true);$wnd.addEventListener(t,hj,true);$wnd.addEventListener(x,hj,true);$wnd.addEventListener(u,hj,true);$wnd.addEventListener(w,hj,true);$wnd.addEventListener(v,hj,true);$wnd.addEventListener(A,hj,true);$wnd.addEventListener(yb,gj,true);$wnd.addEventListener(Ab,gj,true);$wnd.addEventListener(zb,gj,true)}
function ej(c,a){var b=(c.__eventBits||0)^a;c.__eventBits=a;if(!b)return;if(b&1)c.onclick=a&1?ij:null;if(b&2)c.ondblclick=a&2?ij:null;if(b&4)c.onmousedown=a&4?ij:null;if(b&8)c.onmouseup=a&8?ij:null;if(b&16)c.onmouseover=a&16?ij:null;if(b&32)c.onmouseout=a&32?ij:null;if(b&64)c.onmousemove=a&64?ij:null;if(b&128)c.onkeydown=a&128?ij:null;if(b&256)c.onkeypress=a&256?ij:null;if(b&512)c.onkeyup=a&512?ij:null;if(b&1024)c.onchange=a&1024?ij:null;if(b&2048)c.onfocus=a&2048?ij:null;if(b&4096)c.onblur=a&4096?ij:null;if(b&8192)c.onlosecapture=a&8192?ij:null;if(b&16384)c.onscroll=a&16384?ij:null;if(b&32768)c.onload=a&32768?ij:null;if(b&65536)c.onerror=a&65536?ij:null;if(b&131072)c.onmousewheel=a&131072?ij:null;if(b&262144)c.oncontextmenu=a&262144?ij:null}
var fj=null,gj=null,hj=null,ij=null;function Ei(){$wnd.addEventListener(v,function(b){var a=$wnd.__captureElem;if(a&&!b.relatedTarget){if(E==b.target.tagName.toLowerCase()){var c=$doc.createEvent(F);c.initMouseEvent(x,true,true,$wnd,0,b.screenX,b.screenY,b.clientX,b.clientY,b.ctrlKey,b.altKey,b.shiftKey,b.metaKey,b.button,null);a.dispatchEvent(c)}}},true);$wnd.addEventListener(B,hj,true)}
function aj(b,a){mj();ej(b,a);Fi(b,a)}
function Fi(b,a){if(a&131072){b.addEventListener(B,ij,false)}}
function sj(){var d=$wnd.onbeforeunload;var e=$wnd.onunload;$wnd.onbeforeunload=function(a){var c,b;try{c=ui()}finally{b=d&&d(a)}if(c!=null){return c}if(b!=null){return b}};$wnd.onunload=function(a){try{ti()}finally{e&&e(a);$wnd.onresize=null;$wnd.onscroll=null;$wnd.onbeforeunload=null;$wnd.onunload=null}}}
function ll(){}
_=ll.prototype=new rn();_.tI=11;_.f=null;function Dl(b,a,c){em(b,kj(c.b));return Ff(!b.d?(b.d=Df(new pf(),b)):b.d,c,a)}
function Fl(b,a){if(b.d){dg(b.d,a)}}
function am(b){var a;if(b.b){throw new kn()}b.b=true;b.f.__listener=b;a=b.c;b.c=-1;if(a>0){em(b,a)}b.j();b.t()}
function bm(a){if(!a.b){throw new kn()}try{a.u()}finally{a.k();a.f.__listener=null;a.b=false}}
function cm(a){if(!a.e){bl();if(pp(gl.a,a)){bm(a);Bp(gl.a,a)!=null}}else if(a.e){wj(a.e,a)}else if(a.e){throw new kn()}}
function dm(c,b){var a;a=c.e;if(!b){if(!!a&&a.b){bm(c)}c.e=null}else{if(a){throw new kn()}c.e=b;if(b.b){am(c)}}}
function em(b,a){if(b.c==-1){aj(b.f,a|(b.f.__eventBits||0))}else{b.c|=a}}
function fm(){}
function gm(){}
function hm(a){var b;switch(kj((Cc(),a).type)){case 16:case 32:b=yc(a);if(!!b&&zc(this.f,b)){return}}Fd(a,this,this.f)}
function im(){}
function jm(){}
function nl(){}
_=nl.prototype=new ll();_.j=fm;_.k=gm;_.s=hm;_.t=im;_.u=jm;_.tI=12;_.b=false;_.c=0;_.d=null;_.e=null;function wk(){var a,b;for(b=this.q();b.a<b.b.b-1;){a=tl(b);am(a)}}
function xk(){var a,b;for(b=this.q();b.a<b.b.b-1;){a=tl(b);bm(a)}}
function yk(){}
function zk(){}
function uk(){}
_=uk.prototype=new nl();_.j=wk;_.k=xk;_.t=yk;_.u=zk;_.tI=13;function ck(c,a,b){cm(a);xl(c.a,a);b.appendChild(a.f);dm(a,c)}
function ek(b,c){var a;if(c.e!=b){return false}dm(c,null);a=c.f;Fc((Cc(),a)).removeChild(a);Cl(b.a,c);return true}
function fk(){return rl(new pl(),this.a)}
function ak(){}
_=ak.prototype=new uk();_.q=fk;_.tI=14;function uj(a,b){ck(a,b,a.f)}
function wj(b,c){var a;a=ek(b,c);if(a){xj(c.f)}return a}
function xj(a){a.style[ab]=bb;a.style[cb]=bb;a.style[db]=bb}
function tj(){}
_=tj.prototype=new ak();_.tI=15;function hk(){hk=ts;ik=(um(),wm)}
function gk(){}
_=gk.prototype=new nl();_.tI=16;var ik;function Bj(){Bj=ts;hk()}
function Aj(b,a){Bj();b.f=a;ik.v(b.f,0);return b}
function zj(){}
_=zj.prototype=new gk();_.tI=17;function Ej(){Ej=ts;Bj()}
function Dj(b,a){Ej();Aj(b,(Cc(),$doc).createElement(eb));Fj(b.f);b.f[fb]=gb;b.f.innerHTML=a||bb;return b}
function Fj(b){if(b.type==ib){try{b.setAttribute(jb,eb)}catch(a){}}}
function yj(){}
_=yj.prototype=new zj();_.tI=18;function pk(){}
_=pk.prototype=new Ah();_.tI=19;function qk(){}
_=qk.prototype=new pk();_.tI=20;function bl(){bl=ts;fl=ur(new tr());gl=yr(new xr())}
function al(b,a){bl();b.a=wl(new ol());b.f=a;am(b);return b}
function cl(){var b,a;bl();var c,d;for(d=(b=uo(new to(),ar(gl.a).b.a),qq(new pq(),b));cq(d.a.a);){c=Dg((a=Dg(dq(d.a.a),13),a.n()),2);if(c.b){bm(c)}}np(gl.a);np(fl)}
function el(a){bl();var b;b=Dg(sp(fl,a),12);if(b){return b}if(fl.d==0){pi(new Bk())}b=Ek(new Dk());yp(fl,a,b);zr(gl,b);return b}
function Ak(){}
_=Ak.prototype=new tj();_.tI=21;var fl,gl;function Bk(){}
_=Bk.prototype=new rn();_.tI=22;function Fk(){Fk=ts;bl()}
function Ek(a){Fk();al(a,$doc.body);return a}
function Dk(){}
_=Dk.prototype=new Ak();_.tI=23;function wl(a){a.a=vg(lh,0,2,4,0);return a}
function xl(a,b){Al(a,b,a.b)}
function zl(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]==c){return a}}return -1}
function Al(d,e,a){var b,c;if(a<0||a>d.b){throw new nn()}if(d.b==d.a.length){c=vg(lh,0,2,d.a.length*2,0);for(b=0;b<d.a.length;++b){xg(c,b,d.a[b])}d.a=c}++d.b;for(b=d.b-1;b>a;--b){xg(d.a,b,d.a[b-1])}xg(d.a,a,e)}
function Bl(c,b){var a;if(b<0||b>=c.b){throw new nn()}--c.b;for(a=b;a<c.b;++a){xg(c.a,a,c.a[a+1])}xg(c.a,c.b,null)}
function Cl(b,c){var a;a=zl(b,c);if(a==-1){throw new ms()}Bl(b,a)}
function ol(){}
_=ol.prototype=new rn();_.tI=0;_.a=null;_.b=0;function rl(b,a){b.b=a;return b}
function tl(a){if(a.a>=a.b.b){throw new ms()}return a.b.a[++a.a]}
function ul(){return this.a<this.b.b-1}
function vl(){return tl(this)}
function pl(){}
_=pl.prototype=new rn();_.p=ul;_.r=vl;_.tI=0;_.a=-1;_.b=null;function um(){um=ts;vm=nm(new lm());wm=vm?(um(),new km()):vm}
function xm(a,b){a.tabIndex=b}
function km(){}
_=km.prototype=new rn();_.v=xm;_.tI=0;var vm,wm;function om(){om=ts;um()}
function nm(a){om();pm();qm();rm();return a}
function pm(){return function(a){if(this.parentNode.onblur){this.parentNode.onblur(a)}}}
function qm(){return function(a){if(this.parentNode.onfocus){this.parentNode.onfocus(a)}}}
function rm(){return function(){this.firstChild.focus()}}
function sm(a,b){a.firstChild.tabIndex=b}
function lm(){}
_=lm.prototype=new km();_.v=sm;_.tI=0;function ho(){}
_=ho.prototype=new rn();_.tI=3;function hn(){}
_=hn.prototype=new ho();_.tI=4;function vn(){}
_=vn.prototype=new hn();_.tI=5;function Am(){}
_=Am.prototype=new vn();_.tI=25;function bn(c,a){var b;b=new Dm();return b}
function Dm(){}
_=Dm.prototype=new rn();_.tI=0;function Em(){}
_=Em.prototype=new vn();_.tI=28;function kn(){}
_=kn.prototype=new vn();_.tI=29;function on(b,a){return b}
function nn(){}
_=nn.prototype=new vn();_.tI=30;function fo(a){if(!(a!=null&&Cg(a.tI,1))){return false}return String(this)==a}
function go(){return En(this)}
_=String.prototype;_.eQ=fo;_.hC=go;_.tI=2;function zn(){zn=ts;An={};Dn={}}
function Bn(e){var a,b,c,d;d=e.length;c=d<64?1:~~(d/32);a=0;for(b=0;b<d;b+=c){a<<=1;a+=e.charCodeAt(b)}a|=0;return a}
function En(c){zn();var a=kb+c;var b=Dn[a];if(b!=null){return b}b=An[a];if(b==null){b=Bn(c)}Fn();return Dn[a]=b}
function Fn(){if(Cn==256){An=Dn;Dn={};Cn=0}++Cn}
var An,Cn=0,Dn;function jo(){}
_=jo.prototype=new vn();_.tI=31;function no(a,b){var c;while(a.p()){c=a.r();if(b==null?c==null:ic(b,c)){return a}}return null}
function po(a){throw new jo()}
function qo(b){var a;a=no(this.q(),b);return !!a}
function mo(){}
_=mo.prototype=new rn();_.g=po;_.h=qo;_.tI=0;function ar(b){var a;a=yo(new so(),b);return vq(new oq(),b,a)}
function br(c){var a,b,d,e,f;if((c==null?null:c)===this){return true}if(!(c!=null&&Cg(c.tI,14))){return false}e=Dg(c,14);if(Dg(this,14).d!=e.d){return false}for(b=uo(new to(),yo(new so(),e).a);cq(b.a);){a=Dg(dq(b.a),13);d=a.n();f=a.o();if(!(d==null?Dg(this,14).c:d!=null&&Cg(d.tI,1)?up(Dg(this,14),Dg(d,1)):tp(Dg(this,14),d,~~kc(d)))){return false}if(!ss(f,d==null?Dg(this,14).b:d!=null&&Cg(d.tI,1)?Dg(this,14).e[kb+Dg(d,1)]:qp(Dg(this,14),d,~~kc(d)))){return false}}return true}
function cr(){var a,b,c;c=0;for(b=uo(new to(),yo(new so(),Dg(this,14)).a);cq(b.a);){a=Dg(dq(b.a),13);c+=a.hC();c=~~c}return c}
function nq(){}
_=nq.prototype=new rn();_.eQ=br;_.hC=cr;_.tI=0;function lp(g,c){var e=g.a;for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.g(a[f])}}}}
function mp(e,a){var d=e.e;for(var c in d){if(c.charCodeAt(0)==58){var b=jp(e,c.substring(1));a.g(b)}}}
function np(a){a.a=[];a.e={};a.c=false;a.b=null;a.d=0}
function pp(b,a){return a==null?b.c:a!=null&&Cg(a.tI,1)?up(b,Dg(a,1)):tp(b,a,~~kc(a))}
function sp(b,a){return a==null?b.b:a!=null&&Cg(a.tI,1)?b.e[kb+Dg(a,1)]:qp(b,a,~~kc(a))}
function qp(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(h.l(g,d)){return c.o()}}}return null}
function tp(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(h.l(g,d)){return true}}}return false}
function up(b,a){return kb+a in b.e}
function yp(b,a,c){return a==null?wp(b,c):a!=null&&Cg(a.tI,1)?xp(b,Dg(a,1),c):vp(b,a,c,~~kc(a))}
function vp(i,g,j,e){var a=i.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(i.l(g,d)){var h=c.o();c.w(j);return h}}}else{a=i.a[e]=[]}var c=fs(new es(),g,j);a.push(c);++i.d;return null}
function wp(b,c){var a;a=b.b;b.b=c;if(!b.c){b.c=true;++b.d}return a}
function xp(d,a,e){var b,c=d.e;a=kb+a;if(a in c){b=c[a]}else{++d.d}c[a]=e;return b}
function Bp(b,a){return !a?Ap(b):zp(b,a,~~(a.$H||(a.$H=++qc)))}
function zp(h,g,e){var a=h.a[e];if(a){for(var f=0,b=a.length;f<b;++f){var c=a[f];var d=c.n();if(h.l(g,d)){if(a.length==1){delete h.a[e]}else{a.splice(f,1)}--h.d;return c.o()}}}return null}
function Ap(b){var a;a=b.b;b.b=null;if(b.c){b.c=false;--b.d}return a}
function Cp(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&ic(a,b)}
function ro(){}
_=ro.prototype=new nq();_.l=Cp;_.tI=0;_.a=null;_.b=null;_.c=false;_.d=0;_.e=null;function fr(b){var a,c,d;if((b==null?null:b)===this){return true}if(!(b!=null&&Cg(b.tI,15))){return false}c=Dg(b,15);if(c.x()!=this.x()){return false}for(a=c.q();a.p();){d=a.r();if(!this.h(d)){return false}}return true}
function gr(){var a,b,c;a=0;for(b=this.q();b.p();){c=b.r();if(c!=null){a+=kc(c);a=~~a}}return a}
function dr(){}
_=dr.prototype=new mo();_.eQ=fr;_.hC=gr;_.tI=32;function yo(b,a){b.a=a;return b}
function Ao(d,c){var a,b,e;if(c!=null&&Cg(c.tI,13)){a=Dg(c,13);b=a.n();if(pp(d.a,b)){e=sp(d.a,b);return wr(a.o(),e)}}return false}
function Bo(a){return Ao(this,a)}
function Co(){return uo(new to(),this.a)}
function Do(){return this.a.d}
function so(){}
_=so.prototype=new dr();_.h=Bo;_.q=Co;_.x=Do;_.tI=33;_.a=null;function uo(c,b){var a;c.b=b;a=ir(new hr());if(c.b.c){kr(a,Fo(new Eo(),c.b))}mp(c.b,a);lp(c.b,a);c.a=aq(new Ep(),a);return c}
function wo(){return cq(this.a)}
function xo(){return Dg(dq(this.a),13)}
function to(){}
_=to.prototype=new rn();_.p=wo;_.r=xo;_.tI=0;_.a=null;_.b=null;function Dq(b){var a;if(b!=null&&Cg(b.tI,13)){a=Dg(b,13);if(ss(this.n(),a.n())&&ss(this.o(),a.o())){return true}}return false}
function Eq(){var a,b;a=0;b=0;if(this.n()!=null){a=kc(this.n())}if(this.o()!=null){b=kc(this.o())}return a^b}
function Bq(){}
_=Bq.prototype=new rn();_.eQ=Dq;_.hC=Eq;_.tI=34;function Fo(b,a){b.a=a;return b}
function bp(){return null}
function cp(){return this.a.b}
function dp(a){return wp(this.a,a)}
function Eo(){}
_=Eo.prototype=new Bq();_.n=bp;_.o=cp;_.w=dp;_.tI=35;_.a=null;function fp(c,a,b){c.b=b;c.a=a;return c}
function hp(){return this.a}
function ip(){return this.b.e[kb+this.a]}
function jp(b,a){return fp(new ep(),a,b)}
function kp(a){return xp(this.b,this.a,a)}
function ep(){}
_=ep.prototype=new Bq();_.n=hp;_.o=ip;_.w=kp;_.tI=36;_.a=null;_.b=null;function hq(a){jr(this,this.x(),a);return true}
function iq(a,b){if(a<0||a>=b){lq(a,b)}}
function jq(e){var a,b,c,d,f;if((e==null?null:e)===this){return true}if(!(e!=null&&Cg(e.tI,5))){return false}f=Dg(e,5);if(this.x()!=f.b){return false}c=aq(new Ep(),Dg(this,5));d=aq(new Ep(),f);while(c.a<c.b.b){a=dq(c);b=dq(d);if(!(a==null?b==null:ic(a,b))){return false}}return true}
function kq(){var a,b,c;b=1;a=aq(new Ep(),Dg(this,5));while(a.a<a.b.b){c=dq(a);b=31*b+(c==null?0:kc(c));b=~~b}return b}
function lq(a,b){throw on(new nn(),lb+a+mb+b)}
function mq(){return aq(new Ep(),Dg(this,5))}
function Dp(){}
_=Dp.prototype=new mo();_.g=hq;_.eQ=jq;_.hC=kq;_.q=mq;_.tI=0;function aq(b,a){b.b=a;return b}
function cq(a){return a.a<a.b.b}
function dq(a){if(a.a>=a.b.b){throw new ms()}return mr(a.b,a.a++)}
function eq(){return this.a<this.b.b}
function fq(){return dq(this)}
function Ep(){}
_=Ep.prototype=new rn();_.p=eq;_.r=fq;_.tI=0;_.a=0;_.b=null;function vq(b,a,c){b.a=a;b.b=c;return b}
function yq(a){return pp(this.a,a)}
function zq(){var a;return a=uo(new to(),this.b.a),qq(new pq(),a)}
function Aq(){return this.b.a.d}
function oq(){}
_=oq.prototype=new dr();_.h=yq;_.q=zq;_.x=Aq;_.tI=37;_.a=null;_.b=null;function qq(a,b){a.a=b;return a}
function tq(){return cq(this.a.a)}
function uq(){var a;return a=Dg(dq(this.a.a),13),a.n()}
function pq(){}
_=pq.prototype=new rn();_.p=tq;_.r=uq;_.tI=0;_.a=null;function ir(a){a.a=vg(mh,0,0,0,0);a.b=0;return a}
function kr(b,a){xg(b.a,b.b++,a);return true}
function jr(c,a,b){if(a<0||a>c.b){lq(a,c.b)}c.a.splice(a,0,b);++c.b}
function mr(b,a){iq(a,b.b);return b.a[a]}
function nr(c,b,a){for(;a<c.b;++a){if(ss(b,c.a[a])){return a}}return -1}
function or(a){return xg(this.a,this.b++,a),true}
function pr(a){return nr(this,a,0)!=-1}
function qr(){return this.b}
function hr(){}
_=hr.prototype=new Dp();_.g=or;_.h=pr;_.x=qr;_.tI=38;_.a=null;_.b=0;function ur(a){np(a);return a}
function wr(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&ic(a,b)}
function tr(){}
_=tr.prototype=new ro();_.tI=39;function yr(a){a.a=ur(new tr());return a}
function zr(c,a){var b;b=yp(c.a,a,c);return b==null}
function Dr(b){var a;return a=yp(this.a,b,this),a==null}
function Er(a){return pp(this.a,a)}
function Fr(){var a;return a=uo(new to(),ar(this.a).b.a),qq(new pq(),a)}
function as(){return this.a.d}
function xr(){}
_=xr.prototype=new dr();_.g=Dr;_.h=Er;_.q=Fr;_.x=as;_.tI=40;_.a=null;function fs(b,a,c){b.a=a;b.b=c;return b}
function hs(){return this.a}
function is(){return this.b}
function ks(b){var a;a=this.b;this.b=b;return a}
function es(){}
_=es.prototype=new Bq();_.n=hs;_.o=is;_.w=ks;_.tI=41;_.a=null;_.b=null;function ms(){}
_=ms.prototype=new vn();_.tI=42;function ss(a,b){return (a==null?null:a)===(b==null?null:b)||a!=null&&ic(a,b)}
function ym(){var b,a;!!$stats&&$stats({moduleName:$moduleName,subSystem:nb,evtGroup:ob,millis:(new Date()).getTime(),type:pb,className:qb});b=Dj(new yj(),rb);a=new qk();Dl(b,a,(td(),td(),ud));uj((bl(),el(null)),b)}
function gwtOnLoad(b,p,o){$moduleName=p;$moduleBase=o;if(b)try{ym()}catch(a){b(p)}else{ym()}}
function ts(){}
var lh=bn(tb,ub),mh=bn(vb,wb);$stats && $stats({moduleName:'friends',subSystem:'startup',evtGroup:'moduleStartup',millis:(new Date()).getTime(),type:'moduleEvalEnd'});if (friends) friends.onScriptLoad(gwtOnLoad);})();
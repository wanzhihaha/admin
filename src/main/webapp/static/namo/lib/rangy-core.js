!function(e,t){"function"==typeof define&&define.amd?define(e):"undefined"!=typeof module&&"object"==typeof exports?module.exports=e():t.rangy=e()}(function(){function e(e,t){var n=typeof e[t];return n==N||!(n!=C||!e[t])||"unknown"==n}function t(e,t){return!(typeof e[t]!=C||!e[t])}function n(e,t){return typeof e[t]!=E}function r(e){return function(t,n){for(var r=n.length;r--;)if(!e(t,n[r]))return!1;return!0}}function o(e){return e&&O(e,T)&&D(e,w)}function i(e){return t(e,"body")?e.body:e.getElementsByTagName("body")[0]}function a(t){typeof console!=E&&e(console,"log")&&console.log(t)}function s(e,t){b&&t?alert(e):a(e)}function c(e){I.initialized=!0,I.supported=!1,s("Rangy is not supported in this environment. Reason: "+e,I.config.alertOnFail)}function d(e){s("Rangy warning: "+e,I.config.alertOnWarn)}function f(e){return e.message||e.description||String(e)}function u(){if(b&&!I.initialized){var t,n=!1,r=!1;e(document,"createRange")&&(t=document.createRange(),O(t,y)&&D(t,S)&&(n=!0));var s=i(document);if(!s||"body"!=s.nodeName.toLowerCase())return void c("No body element found");if(s&&e(s,"createTextRange")&&(t=s.createTextRange(),o(t)&&(r=!0)),!n&&!r)return void c("Neither Range nor TextRange are available");I.initialized=!0,I.features={implementsDomRange:n,implementsTextRange:r};var d,u;for(var l in x)(d=x[l])instanceof p&&d.init(d,I);for(var h=0,g=M.length;g>h;++h)try{M[h](I)}catch(m){u="Rangy init listener threw an exception. Continuing. Detail: "+f(m),a(u)}}}function l(e,t,n){n&&(e+=" in module "+n.name),I.warn("DEPRECATED: "+e+" is deprecated. Please use "+t+" instead.")}function h(e,t,n,r){e[t]=function(){return l(t,n,r),e[n].apply(e,P.toArray(arguments))}}function g(e){e=e||window,u();for(var t=0,n=k.length;n>t;++t)k[t](e)}function p(e,t,n){this.name=e,this.dependencies=t,this.initialized=!1,this.supported=!1,this.initializer=n}function m(e,t,n){var r=new p(e,t,function(t){if(!t.initialized){t.initialized=!0;try{n(I,t),t.supported=!0}catch(r){var o="Module '"+e+"' failed to load: "+f(r);a(o),r.stack&&a(r.stack)}}});return x[e]=r,r}function R(){}function v(){}var C="object",N="function",E="undefined",S=["startContainer","startOffset","endContainer","endOffset","collapsed","commonAncestorContainer"],y=["setStart","setStartBefore","setStartAfter","setEnd","setEndBefore","setEndAfter","collapse","selectNode","selectNodeContents","compareBoundaryPoints","deleteContents","extractContents","cloneContents","insertNode","surroundContents","cloneRange","toString","detach"],w=["boundingHeight","boundingLeft","boundingTop","boundingWidth","htmlText","text"],T=["collapse","compareEndPoints","duplicate","moveToElementText","parentElement","select","setEndPoint","getBoundingClientRect"],O=r(e),_=r(t),D=r(n),A=[].forEach?function(e,t){e.forEach(t)}:function(e,t){for(var n=0,r=e.length;r>n;++n)t(e[n],n)},x={},b=typeof window!=E&&typeof document!=E,P={isHostMethod:e,isHostObject:t,isHostProperty:n,areHostMethods:O,areHostObjects:_,areHostProperties:D,isTextRange:o,getBody:i,forEach:A},I={version:"1.3.0",initialized:!1,isBrowser:b,supported:!0,util:P,features:{},modules:x,config:{alertOnFail:!1,alertOnWarn:!1,preferTextRange:!1,autoInitialize:typeof rangyAutoInitialize==E?!0:rangyAutoInitialize}};I.fail=c,I.warn=d;var B;({}).hasOwnProperty?(P.extend=B=function(e,t,n){var r,o;for(var i in t)t.hasOwnProperty(i)&&(r=e[i],o=t[i],n&&null!==r&&"object"==typeof r&&null!==o&&"object"==typeof o&&B(r,o,!0),e[i]=o);return t.hasOwnProperty("toString")&&(e.toString=t.toString),e},P.createOptions=function(e,t){var n={};return B(n,t),e&&B(n,e),n}):c("hasOwnProperty not supported"),b||c("Rangy can only run in a browser"),function(){var e;if(b){var t=document.createElement("div");t.appendChild(document.createElement("span"));var n=[].slice;try{1==n.call(t.childNodes,0)[0].nodeType&&(e=function(e){return n.call(e,0)})}catch(r){}}e||(e=function(e){for(var t=[],n=0,r=e.length;r>n;++n)t[n]=e[n];return t}),P.toArray=e}();var H;b&&(e(document,"addEventListener")?H=function(e,t,n){e.addEventListener(t,n,!1)}:e(document,"attachEvent")?H=function(e,t,n){e.attachEvent("on"+t,n)}:c("Document does not have required addEventListener or attachEvent method"),P.addListener=H);var M=[];P.deprecationNotice=l,P.createAliasForDeprecatedMethod=h,I.init=u,I.addInitListener=function(e){I.initialized?e(I):M.push(e)};var k=[];I.addShimListener=function(e){k.push(e)},b&&(I.shim=I.createMissingNativeApi=g,h(I,"createMissingNativeApi","shim")),p.prototype={init:function(){for(var e,t,n=this.dependencies||[],r=0,o=n.length;o>r;++r){if(t=n[r],e=x[t],!(e&&e instanceof p))throw new Error("required module '"+t+"' not found");if(e.init(),!e.supported)throw new Error("required module '"+t+"' not supported")}this.initializer(this)},fail:function(e){throw this.initialized=!0,this.supported=!1,new Error(e)},warn:function(e){I.warn("Module "+this.name+": "+e)},deprecationNotice:function(e,t){I.warn("DEPRECATED: "+e+" in module "+this.name+" is deprecated. Please use "+t+" instead")},createError:function(e){return new Error("Error in Rangy "+this.name+" module: "+e)}},I.createModule=function(e){var t,n;2==arguments.length?(t=arguments[1],n=[]):(t=arguments[2],n=arguments[1]);var r=m(e,n,t);I.initialized&&I.supported&&r.init()},I.createCoreModule=function(e,t,n){m(e,t,n)},I.RangePrototype=R,I.rangePrototype=new R,I.selectionPrototype=new v,I.createCoreModule("DomUtil",[],function(e,t){function n(e){var t;return typeof e.namespaceURI==b||null===(t=e.namespaceURI)||"http://www.w3.org/1999/xhtml"==t}function r(e){var t=e.parentNode;return 1==t.nodeType?t:null}function o(e){for(var t=0;e=e.previousSibling;)++t;return t}function i(e){switch(e.nodeType){case 7:case 10:return 0;case 3:case 8:return e.length;default:return e.childNodes.length}}function a(e,t){var n,r=[];for(n=e;n;n=n.parentNode)r.push(n);for(n=t;n;n=n.parentNode)if(M(r,n))return n;return null}function s(e,t,n){for(var r=n?t:t.parentNode;r;){if(r===e)return!0;r=r.parentNode}return!1}function c(e,t){return s(e,t,!0)}function d(e,t,n){for(var r,o=n?e:e.parentNode;o;){if(r=o.parentNode,r===t)return o;o=r}return null}function f(e){var t=e.nodeType;return 3==t||4==t||8==t}function u(e){if(!e)return!1;var t=e.nodeType;return 3==t||8==t}function l(e,t){var n=t.nextSibling,r=t.parentNode;return n?r.insertBefore(e,n):r.appendChild(e),e}function h(e,t,n){var r=e.cloneNode(!1);if(r.deleteData(0,t),e.deleteData(t,e.length-t),l(r,e),n)for(var i,a=0;i=n[a++];)i.node==e&&i.offset>t?(i.node=r,i.offset-=t):i.node==e.parentNode&&i.offset>o(e)&&++i.offset;return r}function g(e){if(9==e.nodeType)return e;if(typeof e.ownerDocument!=b)return e.ownerDocument;if(typeof e.document!=b)return e.document;if(e.parentNode)return g(e.parentNode);throw t.createError("getDocument: no document found for node")}function p(e){var n=g(e);if(typeof n.defaultView!=b)return n.defaultView;if(typeof n.parentWindow!=b)return n.parentWindow;throw t.createError("Cannot get a window object for node")}function m(e){if(typeof e.contentDocument!=b)return e.contentDocument;if(typeof e.contentWindow!=b)return e.contentWindow.document;throw t.createError("getIframeDocument: No Document object found for iframe element")}function R(e){if(typeof e.contentWindow!=b)return e.contentWindow;if(typeof e.contentDocument!=b)return e.contentDocument.defaultView;throw t.createError("getIframeWindow: No Window object found for iframe element")}function v(e){return e&&P.isHostMethod(e,"setTimeout")&&P.isHostObject(e,"document")}function C(e,t,n){var r;if(e?P.isHostProperty(e,"nodeType")?r=1==e.nodeType&&"iframe"==e.tagName.toLowerCase()?m(e):g(e):v(e)&&(r=e.document):r=document,!r)throw t.createError(n+"(): Parameter must be a Window object or DOM node");return r}function N(e){for(var t;t=e.parentNode;)e=t;return e}function E(e,n,r,i){var s,c,f,u,l;if(e==r)return n===i?0:i>n?-1:1;if(s=d(r,e,!0))return n<=o(s)?-1:1;if(s=d(e,r,!0))return o(s)<i?-1:1;if(c=a(e,r),!c)throw new Error("comparePoints error: nodes have no common ancestor");if(f=e===c?c:d(e,c,!0),u=r===c?c:d(r,c,!0),f===u)throw t.createError("comparePoints got to case 4 and childA and childB are the same!");for(l=c.firstChild;l;){if(l===f)return-1;if(l===u)return 1;l=l.nextSibling}}function S(e){var t;try{return t=e.parentNode,!1}catch(n){return!0}}function y(e){if(!e)return"[No node]";if(k&&S(e))return"[Broken node]";if(f(e))return'"'+e.data+'"';if(1==e.nodeType){var t=e.id?' id="'+e.id+'"':"";return"<"+e.nodeName+t+">[index:"+o(e)+",length:"+e.childNodes.length+"]["+(e.innerHTML||"[innerHTML not supported]").slice(0,25)+"]"}return e.nodeName}function w(e){for(var t,n=g(e).createDocumentFragment();t=e.firstChild;)n.appendChild(t);return n}function T(e,t,n){var r=I(e),o=e.createElement("div");o.contentEditable=""+!!n,t&&(o.innerHTML=t);var i=r.firstChild;return i?r.insertBefore(o,i):r.appendChild(o),o}function O(e){return e.parentNode.removeChild(e)}function _(e){this.root=e,this._next=e}function D(e){return new _(e)}function A(e,t){this.node=e,this.offset=t}function x(e){this.code=this[e],this.codeName=e,this.message="DOMException: "+this.codeName}var b="undefined",P=e.util,I=P.getBody;P.areHostMethods(document,["createDocumentFragment","createElement","createTextNode"])||t.fail("document missing a Node creation method"),P.isHostMethod(document,"getElementsByTagName")||t.fail("document missing getElementsByTagName method");var B=document.createElement("div");P.areHostMethods(B,["insertBefore","appendChild","cloneNode"]||!P.areHostObjects(B,["previousSibling","nextSibling","childNodes","parentNode"]))||t.fail("Incomplete Element implementation"),P.isHostProperty(B,"innerHTML")||t.fail("Element is missing innerHTML property");var H=document.createTextNode("test");P.areHostMethods(H,["splitText","deleteData","insertData","appendData","cloneNode"]||!P.areHostObjects(B,["previousSibling","nextSibling","childNodes","parentNode"])||!P.areHostProperties(H,["data"]))||t.fail("Incomplete Text Node implementation");var M=function(e,t){for(var n=e.length;n--;)if(e[n]===t)return!0;return!1},k=!1;!function(){var t=document.createElement("b");t.innerHTML="1";var n=t.firstChild;t.innerHTML="<br />",k=S(n),e.features.crashyTextNodes=k}();var L;typeof window.getComputedStyle!=b?L=function(e,t){return p(e).getComputedStyle(e,null)[t]}:typeof document.documentElement.currentStyle!=b?L=function(e,t){return e.currentStyle?e.currentStyle[t]:""}:t.fail("No means of obtaining computed style properties found"),_.prototype={_current:null,hasNext:function(){return!!this._next},next:function(){var e,t,n=this._current=this._next;if(this._current)if(e=n.firstChild)this._next=e;else{for(t=null;n!==this.root&&!(t=n.nextSibling);)n=n.parentNode;this._next=t}return this._current},detach:function(){this._current=this._next=this.root=null}},A.prototype={equals:function(e){return!!e&&this.node===e.node&&this.offset==e.offset},inspect:function(){return"[DomPosition("+y(this.node)+":"+this.offset+")]"},toString:function(){return this.inspect()}},x.prototype={INDEX_SIZE_ERR:1,HIERARCHY_REQUEST_ERR:3,WRONG_DOCUMENT_ERR:4,NO_MODIFICATION_ALLOWED_ERR:7,NOT_FOUND_ERR:8,NOT_SUPPORTED_ERR:9,INVALID_STATE_ERR:11,INVALID_NODE_TYPE_ERR:24},x.prototype.toString=function(){return this.message},e.dom={arrayContains:M,isHtmlNamespace:n,parentElement:r,getNodeIndex:o,getNodeLength:i,getCommonAncestor:a,isAncestorOf:s,isOrIsAncestorOf:c,getClosestAncestorIn:d,isCharacterDataNode:f,isTextOrCommentNode:u,insertAfter:l,splitDataNode:h,getDocument:g,getWindow:p,getIframeWindow:R,getIframeDocument:m,getBody:I,isWindow:v,getContentDocument:C,getRootContainer:N,comparePoints:E,isBrokenNode:S,inspectNode:y,getComputedStyleProperty:L,createTestElement:T,removeNode:O,fragmentFromNodeChildren:w,createIterator:D,DomPosition:A},e.DOMException=x}),I.createCoreModule("DomRange",["DomUtil"],function(e){function t(e,t){return 3!=e.nodeType&&(F(e,t.startContainer)||F(e,t.endContainer))}function n(e){return e.document||j(e.startContainer)}function r(e){return Q(e.startContainer)}function o(e){return new M(e.parentNode,W(e))}function i(e){return new M(e.parentNode,W(e)+1)}function a(e,t,n){var r=11==e.nodeType?e.firstChild:e;return L(t)?n==t.length?B.insertAfter(e,t):t.parentNode.insertBefore(e,0==n?t:U(t,n)):n>=t.childNodes.length?t.appendChild(e):t.insertBefore(e,t.childNodes[n]),r}function s(e,t,r){if(w(e),w(t),n(t)!=n(e))throw new k("WRONG_DOCUMENT_ERR");var o=z(e.startContainer,e.startOffset,t.endContainer,t.endOffset),i=z(e.endContainer,e.endOffset,t.startContainer,t.startOffset);return r?0>=o&&i>=0:0>o&&i>0}function c(e){for(var t,r,o,i=n(e.range).createDocumentFragment();r=e.next();){if(t=e.isPartiallySelectedSubtree(),r=r.cloneNode(!t),t&&(o=e.getSubtreeIterator(),r.appendChild(c(o)),o.detach()),10==r.nodeType)throw new k("HIERARCHY_REQUEST_ERR");i.appendChild(r)}return i}function d(e,t,n){var r,o;n=n||{stop:!1};for(var i,a;i=e.next();)if(e.isPartiallySelectedSubtree()){if(t(i)===!1)return void(n.stop=!0);if(a=e.getSubtreeIterator(),d(a,t,n),a.detach(),n.stop)return}else for(r=B.createIterator(i);o=r.next();)if(t(o)===!1)return void(n.stop=!0)}function f(e){for(var t;e.next();)e.isPartiallySelectedSubtree()?(t=e.getSubtreeIterator(),f(t),t.detach()):e.remove()}function u(e){for(var t,r,o=n(e.range).createDocumentFragment();t=e.next();){if(e.isPartiallySelectedSubtree()?(t=t.cloneNode(!1),r=e.getSubtreeIterator(),t.appendChild(u(r)),r.detach()):e.remove(),10==t.nodeType)throw new k("HIERARCHY_REQUEST_ERR");o.appendChild(t)}return o}function l(e,t,n){var r,o=!(!t||!t.length),i=!!n;o&&(r=new RegExp("^("+t.join("|")+")$"));var a=[];return d(new g(e,!1),function(t){if(!(o&&!r.test(t.nodeType)||i&&!n(t))){var s=e.startContainer;if(t!=s||!L(s)||e.startOffset!=s.length){var c=e.endContainer;t==c&&L(c)&&0==e.endOffset||a.push(t)}}}),a}function h(e){var t="undefined"==typeof e.getName?"Range":e.getName();return"["+t+"("+B.inspectNode(e.startContainer)+":"+e.startOffset+", "+B.inspectNode(e.endContainer)+":"+e.endOffset+")]"}function g(e,t){if(this.range=e,this.clonePartiallySelectedTextNodes=t,!e.collapsed){this.sc=e.startContainer,this.so=e.startOffset,this.ec=e.endContainer,this.eo=e.endOffset;var n=e.commonAncestorContainer;this.sc===this.ec&&L(this.sc)?(this.isSingleCharacterDataNode=!0,this._first=this._last=this._next=this.sc):(this._first=this._next=this.sc!==n||L(this.sc)?V(this.sc,n,!0):this.sc.childNodes[this.so],this._last=this.ec!==n||L(this.ec)?V(this.ec,n,!0):this.ec.childNodes[this.eo-1])}}function p(e){return function(t,n){for(var r,o=n?t:t.parentNode;o;){if(r=o.nodeType,Y(e,r))return o;o=o.parentNode}return null}}function m(e,t){if(rt(e,t))throw new k("INVALID_NODE_TYPE_ERR")}function R(e,t){if(!Y(t,e.nodeType))throw new k("INVALID_NODE_TYPE_ERR")}function v(e,t){if(0>t||t>(L(e)?e.length:e.childNodes.length))throw new k("INDEX_SIZE_ERR")}function C(e,t){if(tt(e,!0)!==tt(t,!0))throw new k("WRONG_DOCUMENT_ERR")}function N(e){if(nt(e,!0))throw new k("NO_MODIFICATION_ALLOWED_ERR")}function E(e,t){if(!e)throw new k(t)}function S(e,t){return t<=(L(e)?e.length:e.childNodes.length)}function y(e){return!!e.startContainer&&!!e.endContainer&&!(G&&(B.isBrokenNode(e.startContainer)||B.isBrokenNode(e.endContainer)))&&Q(e.startContainer)==Q(e.endContainer)&&S(e.startContainer,e.startOffset)&&S(e.endContainer,e.endOffset)}function w(e){if(!y(e))throw new Error("Range error: Range is not valid. This usually happens after DOM mutation. Range: ("+e.inspect()+")")}function T(e,t){w(e);var n=e.startContainer,r=e.startOffset,o=e.endContainer,i=e.endOffset,a=n===o;L(o)&&i>0&&i<o.length&&U(o,i,t),L(n)&&r>0&&r<n.length&&(n=U(n,r,t),a?(i-=r,o=n):o==n.parentNode&&i>=W(n)&&i++,r=0),e.setStartAndEnd(n,r,o,i)}function O(e){w(e);var t=e.commonAncestorContainer.parentNode.cloneNode(!1);return t.appendChild(e.cloneContents()),t.innerHTML}function _(e){e.START_TO_START=dt,e.START_TO_END=ft,e.END_TO_END=ut,e.END_TO_START=lt,e.NODE_BEFORE=ht,e.NODE_AFTER=gt,e.NODE_BEFORE_AND_AFTER=pt,e.NODE_INSIDE=mt}function D(e){_(e),_(e.prototype)}function A(e,t){return function(){w(this);var n,r,o=this.startContainer,a=this.startOffset,s=this.commonAncestorContainer,c=new g(this,!0);o!==s&&(n=V(o,s,!0),r=i(n),o=r.node,a=r.offset),d(c,N),c.reset();var f=e(c);return c.detach(),t(this,o,a,o,a),f}}function x(n,r){function a(e,t){return function(n){R(n,Z),R(Q(n),$);var r=(e?o:i)(n);(t?s:c)(this,r.node,r.offset)}}function s(e,t,n){var o=e.endContainer,i=e.endOffset;(t!==e.startContainer||n!==e.startOffset)&&((Q(t)!=Q(o)||1==z(t,n,o,i))&&(o=t,i=n),r(e,t,n,o,i))}function c(e,t,n){var o=e.startContainer,i=e.startOffset;(t!==e.endContainer||n!==e.endOffset)&&((Q(t)!=Q(o)||-1==z(t,n,o,i))&&(o=t,i=n),r(e,o,i,t,n))}var d=function(){};d.prototype=e.rangePrototype,n.prototype=new d,H.extend(n.prototype,{setStart:function(e,t){m(e,!0),v(e,t),s(this,e,t)},setEnd:function(e,t){m(e,!0),v(e,t),c(this,e,t)},setStartAndEnd:function(){var e=arguments,t=e[0],n=e[1],o=t,i=n;switch(e.length){case 3:i=e[2];break;case 4:o=e[2],i=e[3]}r(this,t,n,o,i)},setBoundary:function(e,t,n){this["set"+(n?"Start":"End")](e,t)},setStartBefore:a(!0,!0),setStartAfter:a(!1,!0),setEndBefore:a(!0,!1),setEndAfter:a(!1,!1),collapse:function(e){w(this),e?r(this,this.startContainer,this.startOffset,this.startContainer,this.startOffset):r(this,this.endContainer,this.endOffset,this.endContainer,this.endOffset)},selectNodeContents:function(e){m(e,!0),r(this,e,0,e,q(e))},selectNode:function(e){m(e,!1),R(e,Z);var t=o(e),n=i(e);r(this,t.node,t.offset,n.node,n.offset)},extractContents:A(u,r),deleteContents:A(f,r),canSurroundContents:function(){w(this),N(this.startContainer),N(this.endContainer);var e=new g(this,!0),n=e._first&&t(e._first,this)||e._last&&t(e._last,this);return e.detach(),!n},splitBoundaries:function(){T(this)},splitBoundariesPreservingPositions:function(e){T(this,e)},normalizeBoundaries:function(){w(this);var e,t=this.startContainer,n=this.startOffset,o=this.endContainer,i=this.endOffset,a=function(e){var t=e.nextSibling;t&&t.nodeType==e.nodeType&&(o=e,i=e.length,e.appendData(t.data),X(t))},s=function(e){var r=e.previousSibling;if(r&&r.nodeType==e.nodeType){t=e;var a=e.length;if(n=r.length,e.insertData(0,r.data),X(r),t==o)i+=n,o=t;else if(o==e.parentNode){var s=W(e);i==s?(o=e,i=a):i>s&&i--}}},c=!0;if(L(o))i==o.length?a(o):0==i&&(e=o.previousSibling,e&&e.nodeType==o.nodeType&&(i=e.length,t==o&&(c=!1),e.appendData(o.data),X(o),o=e));else{if(i>0){var d=o.childNodes[i-1];d&&L(d)&&a(d)}c=!this.collapsed}if(c){if(L(t))0==n?s(t):n==t.length&&(e=t.nextSibling,e&&e.nodeType==t.nodeType&&(o==e&&(o=t,i+=t.length),t.appendData(e.data),X(e)));else if(n<t.childNodes.length){var f=t.childNodes[n];f&&L(f)&&s(f)}}else t=o,n=i;r(this,t,n,o,i)},collapseToPoint:function(e,t){m(e,!0),v(e,t),this.setStartAndEnd(e,t)}}),D(n)}function b(e){e.collapsed=e.startContainer===e.endContainer&&e.startOffset===e.endOffset,e.commonAncestorContainer=e.collapsed?e.startContainer:B.getCommonAncestor(e.startContainer,e.endContainer)}function P(e,t,n,r,o){e.startContainer=t,e.startOffset=n,e.endContainer=r,e.endOffset=o,e.document=B.getDocument(t),b(e)}function I(e){this.startContainer=e,this.startOffset=0,this.endContainer=e,this.endOffset=0,this.document=e,b(this)}var B=e.dom,H=e.util,M=B.DomPosition,k=e.DOMException,L=B.isCharacterDataNode,W=B.getNodeIndex,F=B.isOrIsAncestorOf,j=B.getDocument,z=B.comparePoints,U=B.splitDataNode,V=B.getClosestAncestorIn,q=B.getNodeLength,Y=B.arrayContains,Q=B.getRootContainer,G=e.features.crashyTextNodes,X=B.removeNode;g.prototype={_current:null,_next:null,_first:null,_last:null,isSingleCharacterDataNode:!1,reset:function(){this._current=null,this._next=this._first},hasNext:function(){return!!this._next},next:function(){var e=this._current=this._next;return e&&(this._next=e!==this._last?e.nextSibling:null,L(e)&&this.clonePartiallySelectedTextNodes&&(e===this.ec&&(e=e.cloneNode(!0)).deleteData(this.eo,e.length-this.eo),this._current===this.sc&&(e=e.cloneNode(!0)).deleteData(0,this.so))),e},remove:function(){var e,t,n=this._current;!L(n)||n!==this.sc&&n!==this.ec?n.parentNode&&X(n):(e=n===this.sc?this.so:0,t=n===this.ec?this.eo:n.length,e!=t&&n.deleteData(e,t-e))},isPartiallySelectedSubtree:function(){var e=this._current;return t(e,this.range)},getSubtreeIterator:function(){var e;if(this.isSingleCharacterDataNode)e=this.range.cloneRange(),e.collapse(!1);else{e=new I(n(this.range));var t=this._current,r=t,o=0,i=t,a=q(t);F(t,this.sc)&&(r=this.sc,o=this.so),F(t,this.ec)&&(i=this.ec,a=this.eo),P(e,r,o,i,a)}return new g(e,this.clonePartiallySelectedTextNodes)},detach:function(){this.range=this._current=this._next=this._first=this._last=this.sc=this.so=this.ec=this.eo=null}};var Z=[1,3,4,5,7,8,10],$=[2,9,11],J=[5,6,10,12],K=[1,3,4,5,7,8,10,11],et=[1,3,4,5,7,8],tt=p([9,11]),nt=p(J),rt=p([6,10,12]),ot=document.createElement("style"),it=!1;try{ot.innerHTML="<b>x</b>",it=3==ot.firstChild.nodeType}catch(at){}e.features.htmlParsingConforms=it;var st=it?function(e){var t=this.startContainer,n=j(t);if(!t)throw new k("INVALID_STATE_ERR");var r=null;return 1==t.nodeType?r=t:L(t)&&(r=B.parentElement(t)),r=null===r||"HTML"==r.nodeName&&B.isHtmlNamespace(j(r).documentElement)&&B.isHtmlNamespace(r)?n.createElement("body"):r.cloneNode(!1),r.innerHTML=e,B.fragmentFromNodeChildren(r)}:function(e){var t=n(this),r=t.createElement("body");return r.innerHTML=e,B.fragmentFromNodeChildren(r)},ct=["startContainer","startOffset","endContainer","endOffset","collapsed","commonAncestorContainer"],dt=0,ft=1,ut=2,lt=3,ht=0,gt=1,pt=2,mt=3;H.extend(e.rangePrototype,{compareBoundaryPoints:function(e,t){w(this),C(this.startContainer,t.startContainer);var n,r,o,i,a=e==lt||e==dt?"start":"end",s=e==ft||e==dt?"start":"end";return n=this[a+"Container"],r=this[a+"Offset"],o=t[s+"Container"],i=t[s+"Offset"],z(n,r,o,i)},insertNode:function(e){if(w(this),R(e,K),N(this.startContainer),F(e,this.startContainer))throw new k("HIERARCHY_REQUEST_ERR");var t=a(e,this.startContainer,this.startOffset);this.setStartBefore(t)},cloneContents:function(){w(this);var e,t;if(this.collapsed)return n(this).createDocumentFragment();if(this.startContainer===this.endContainer&&L(this.startContainer))return e=this.startContainer.cloneNode(!0),e.data=e.data.slice(this.startOffset,this.endOffset),t=n(this).createDocumentFragment(),t.appendChild(e),t;var r=new g(this,!0);return e=c(r),r.detach(),e},canSurroundContents:function(){w(this),N(this.startContainer),N(this.endContainer);var e=new g(this,!0),n=e._first&&t(e._first,this)||e._last&&t(e._last,this);return e.detach(),!n},surroundContents:function(e){if(R(e,et),!this.canSurroundContents())throw new k("INVALID_STATE_ERR");var t=this.extractContents();if(e.hasChildNodes())for(;e.lastChild;)e.removeChild(e.lastChild);a(e,this.startContainer,this.startOffset),e.appendChild(t),this.selectNode(e)},cloneRange:function(){w(this);for(var e,t=new I(n(this)),r=ct.length;r--;)e=ct[r],t[e]=this[e];return t},toString:function(){w(this);var e=this.startContainer;if(e===this.endContainer&&L(e))return 3==e.nodeType||4==e.nodeType?e.data.slice(this.startOffset,this.endOffset):"";var t=[],n=new g(this,!0);return d(n,function(e){(3==e.nodeType||4==e.nodeType)&&t.push(e.data)}),n.detach(),t.join("")},compareNode:function(e){w(this);var t=e.parentNode,n=W(e);if(!t)throw new k("NOT_FOUND_ERR");var r=this.comparePoint(t,n),o=this.comparePoint(t,n+1);return 0>r?o>0?pt:ht:o>0?gt:mt},comparePoint:function(e,t){return w(this),E(e,"HIERARCHY_REQUEST_ERR"),C(e,this.startContainer),z(e,t,this.startContainer,this.startOffset)<0?-1:z(e,t,this.endContainer,this.endOffset)>0?1:0},createContextualFragment:st,toHtml:function(){return O(this)},intersectsNode:function(e,t){if(w(this),Q(e)!=r(this))return!1;var n=e.parentNode,o=W(e);if(!n)return!0;var i=z(n,o,this.endContainer,this.endOffset),a=z(n,o+1,this.startContainer,this.startOffset);return t?0>=i&&a>=0:0>i&&a>0},isPointInRange:function(e,t){return w(this),E(e,"HIERARCHY_REQUEST_ERR"),C(e,this.startContainer),z(e,t,this.startContainer,this.startOffset)>=0&&z(e,t,this.endContainer,this.endOffset)<=0},intersectsRange:function(e){return s(this,e,!1)},intersectsOrTouchesRange:function(e){return s(this,e,!0)},intersection:function(e){if(this.intersectsRange(e)){var t=z(this.startContainer,this.startOffset,e.startContainer,e.startOffset),n=z(this.endContainer,this.endOffset,e.endContainer,e.endOffset),r=this.cloneRange();return-1==t&&r.setStart(e.startContainer,e.startOffset),1==n&&r.setEnd(e.endContainer,e.endOffset),r}return null},union:function(e){if(this.intersectsOrTouchesRange(e)){var t=this.cloneRange();return-1==z(e.startContainer,e.startOffset,this.startContainer,this.startOffset)&&t.setStart(e.startContainer,e.startOffset),1==z(e.endContainer,e.endOffset,this.endContainer,this.endOffset)&&t.setEnd(e.endContainer,e.endOffset),t}throw new k("Ranges do not intersect")},containsNode:function(e,t){return t?this.intersectsNode(e,!1):this.compareNode(e)==mt},containsNodeContents:function(e){return this.comparePoint(e,0)>=0&&this.comparePoint(e,q(e))<=0},containsRange:function(e){var t=this.intersection(e);return null!==t&&e.equals(t)},containsNodeText:function(e){var t=this.cloneRange();t.selectNode(e);var n=t.getNodes([3]);if(n.length>0){t.setStart(n[0],0);var r=n.pop();return t.setEnd(r,r.length),this.containsRange(t)}return this.containsNodeContents(e)},getNodes:function(e,t){return w(this),l(this,e,t)},getDocument:function(){return n(this)},collapseBefore:function(e){this.setEndBefore(e),this.collapse(!1)},collapseAfter:function(e){this.setStartAfter(e),this.collapse(!0)},getBookmark:function(t){var r=n(this),o=e.createRange(r);t=t||B.getBody(r),o.selectNodeContents(t);var i=this.intersection(o),a=0,s=0;return i&&(o.setEnd(i.startContainer,i.startOffset),a=o.toString().length,s=a+i.toString().length),{start:a,end:s,containerNode:t}},moveToBookmark:function(e){var t=e.containerNode,n=0;this.setStart(t,0),this.collapse(!0);for(var r,o,i,a,s=[t],c=!1,d=!1;!d&&(r=s.pop());)if(3==r.nodeType)o=n+r.length,!c&&e.start>=n&&e.start<=o&&(this.setStart(r,e.start-n),c=!0),c&&e.end>=n&&e.end<=o&&(this.setEnd(r,e.end-n),d=!0),n=o;else for(a=r.childNodes,i=a.length;i--;)s.push(a[i])},getName:function(){return"DomRange"},equals:function(e){return I.rangesEqual(this,e)},isValid:function(){return y(this)},inspect:function(){return h(this)},detach:function(){}}),x(I,P),H.extend(I,{rangeProperties:ct,RangeIterator:g,copyComparisonConstants:D,createPrototypeRange:x,inspect:h,toHtml:O,getRangeDocument:n,rangesEqual:function(e,t){return e.startContainer===t.startContainer&&e.startOffset===t.startOffset&&e.endContainer===t.endContainer&&e.endOffset===t.endOffset}}),e.DomRange=I}),I.createCoreModule("WrappedRange",["DomRange"],function(e,t){var n,r,o=e.dom,i=e.util,a=o.DomPosition,s=e.DomRange,c=o.getBody,d=o.getContentDocument,f=o.isCharacterDataNode;if(e.features.implementsDomRange&&!function(){function r(e){for(var t,n=l.length;n--;)t=l[n],e[t]=e.nativeRange[t];e.collapsed=e.startContainer===e.endContainer&&e.startOffset===e.endOffset}function a(e,t,n,r,o){var i=e.startContainer!==t||e.startOffset!=n,a=e.endContainer!==r||e.endOffset!=o,s=!e.equals(e.nativeRange);(i||a||s)&&(e.setEnd(r,o),e.setStart(t,n))}var f,u,l=s.rangeProperties;n=function(e){if(!e)throw t.createError("WrappedRange: Range must be specified");this.nativeRange=e,r(this)},s.createPrototypeRange(n,a),f=n.prototype,f.selectNode=function(e){this.nativeRange.selectNode(e),r(this)},f.cloneContents=function(){return this.nativeRange.cloneContents()},f.surroundContents=function(e){this.nativeRange.surroundContents(e),r(this)},f.collapse=function(e){this.nativeRange.collapse(e),r(this)},f.cloneRange=function(){return new n(this.nativeRange.cloneRange())},f.refresh=function(){r(this)},f.toString=function(){return this.nativeRange.toString()};var h=document.createTextNode("test");c(document).appendChild(h);var g=document.createRange();g.setStart(h,0),g.setEnd(h,0);try{g.setStart(h,1),f.setStart=function(e,t){this.nativeRange.setStart(e,t),r(this)},f.setEnd=function(e,t){this.nativeRange.setEnd(e,t),r(this)},u=function(e){return function(t){this.nativeRange[e](t),r(this)}}}catch(p){f.setStart=function(e,t){try{this.nativeRange.setStart(e,t)}catch(n){this.nativeRange.setEnd(e,t),this.nativeRange.setStart(e,t)}r(this)},f.setEnd=function(e,t){try{this.nativeRange.setEnd(e,t)}catch(n){this.nativeRange.setStart(e,t),this.nativeRange.setEnd(e,t)}r(this)},u=function(e,t){return function(n){try{this.nativeRange[e](n)}catch(o){this.nativeRange[t](n),this.nativeRange[e](n)}r(this)}}}f.setStartBefore=u("setStartBefore","setEndBefore"),f.setStartAfter=u("setStartAfter","setEndAfter"),f.setEndBefore=u("setEndBefore","setStartBefore"),f.setEndAfter=u("setEndAfter","setStartAfter"),f.selectNodeContents=function(e){this.setStartAndEnd(e,0,o.getNodeLength(e))},g.selectNodeContents(h),g.setEnd(h,3);var m=document.createRange();m.selectNodeContents(h),m.setEnd(h,4),m.setStart(h,2),f.compareBoundaryPoints=-1==g.compareBoundaryPoints(g.START_TO_END,m)&&1==g.compareBoundaryPoints(g.END_TO_START,m)?function(e,t){return t=t.nativeRange||t,e==t.START_TO_END?e=t.END_TO_START:e==t.END_TO_START&&(e=t.START_TO_END),this.nativeRange.compareBoundaryPoints(e,t)}:function(e,t){return this.nativeRange.compareBoundaryPoints(e,t.nativeRange||t)};var R=document.createElement("div");R.innerHTML="123";var v=R.firstChild,C=c(document);C.appendChild(R),g.setStart(v,1),g.setEnd(v,2),g.deleteContents(),"13"==v.data&&(f.deleteContents=function(){this.nativeRange.deleteContents(),r(this)},f.extractContents=function(){var e=this.nativeRange.extractContents();return r(this),e}),C.removeChild(R),C=null,i.isHostMethod(g,"createContextualFragment")&&(f.createContextualFragment=function(e){return this.nativeRange.createContextualFragment(e)}),c(document).removeChild(h),f.getName=function(){return"WrappedRange"},e.WrappedRange=n,e.createNativeRange=function(e){return e=d(e,t,"createNativeRange"),e.createRange()}}(),e.features.implementsTextRange){var u=function(e){var t=e.parentElement(),n=e.duplicate();n.collapse(!0);var r=n.parentElement();n=e.duplicate(),n.collapse(!1);var i=n.parentElement(),a=r==i?r:o.getCommonAncestor(r,i);return a==t?a:o.getCommonAncestor(t,a)},l=function(e){return 0==e.compareEndPoints("StartToEnd",e)},h=function(e,t,n,r,i){var s=e.duplicate();s.collapse(n);var c=s.parentElement();if(o.isOrIsAncestorOf(t,c)||(c=t),!c.canHaveHTML){var d=new a(c.parentNode,o.getNodeIndex(c));return{boundaryPosition:d,nodeInfo:{nodeIndex:d.offset,containerElement:d.node}}}var u=o.getDocument(c).createElement("span");u.parentNode&&o.removeNode(u);for(var l,h,g,p,m,R=n?"StartToStart":"StartToEnd",v=i&&i.containerElement==c?i.nodeIndex:0,C=c.childNodes.length,N=C,E=N;;){if(E==C?c.appendChild(u):c.insertBefore(u,c.childNodes[E]),s.moveToElementText(u),l=s.compareEndPoints(R,e),0==l||v==N)break;if(-1==l){if(N==v+1)break;v=E}else N=N==v+1?v:E;E=Math.floor((v+N)/2),c.removeChild(u)}if(m=u.nextSibling,-1==l&&m&&f(m)){s.setEndPoint(n?"EndToStart":"EndToEnd",e);var S;if(/[\r\n]/.test(m.data)){var y=s.duplicate(),w=y.text.replace(/\r\n/g,"\r").length;for(S=y.moveStart("character",w);-1==(l=y.compareEndPoints("StartToEnd",y));)S++,y.moveStart("character",1)}else S=s.text.length;p=new a(m,S)}else h=(r||!n)&&u.previousSibling,g=(r||n)&&u.nextSibling,p=g&&f(g)?new a(g,0):h&&f(h)?new a(h,h.data.length):new a(c,o.getNodeIndex(u));return o.removeNode(u),{boundaryPosition:p,nodeInfo:{nodeIndex:E,containerElement:c}}},g=function(e,t){var n,r,i,a,s=e.offset,d=o.getDocument(e.node),u=c(d).createTextRange(),l=f(e.node);return l?(n=e.node,r=n.parentNode):(a=e.node.childNodes,n=s<a.length?a[s]:null,r=e.node),i=d.createElement("span"),i.innerHTML="&#feff;",n?r.insertBefore(i,n):r.appendChild(i),u.moveToElementText(i),u.collapse(!t),r.removeChild(i),l&&u[t?"moveStart":"moveEnd"]("character",s),u};r=function(e){this.textRange=e,this.refresh()},r.prototype=new s(document),r.prototype.refresh=function(){var e,t,n,r=u(this.textRange);
l(this.textRange)?t=e=h(this.textRange,r,!0,!0).boundaryPosition:(n=h(this.textRange,r,!0,!1),e=n.boundaryPosition,t=h(this.textRange,r,!1,!1,n.nodeInfo).boundaryPosition),this.setStart(e.node,e.offset),this.setEnd(t.node,t.offset)},r.prototype.getName=function(){return"WrappedTextRange"},s.copyComparisonConstants(r);var p=function(e){if(e.collapsed)return g(new a(e.startContainer,e.startOffset),!0);var t=g(new a(e.startContainer,e.startOffset),!0),n=g(new a(e.endContainer,e.endOffset),!1),r=c(s.getRangeDocument(e)).createTextRange();return r.setEndPoint("StartToStart",t),r.setEndPoint("EndToEnd",n),r};if(r.rangeToTextRange=p,r.prototype.toTextRange=function(){return p(this)},e.WrappedTextRange=r,!e.features.implementsDomRange||e.config.preferTextRange){var m=function(e){return e("return this;")()}(Function);"undefined"==typeof m.Range&&(m.Range=r),e.createNativeRange=function(e){return e=d(e,t,"createNativeRange"),c(e).createTextRange()},e.WrappedRange=r}}e.createRange=function(n){return n=d(n,t,"createRange"),new e.WrappedRange(e.createNativeRange(n))},e.createRangyRange=function(e){return e=d(e,t,"createRangyRange"),new s(e)},i.createAliasForDeprecatedMethod(e,"createIframeRange","createRange"),i.createAliasForDeprecatedMethod(e,"createIframeRangyRange","createRangyRange"),e.addShimListener(function(t){var n=t.document;"undefined"==typeof n.createRange&&(n.createRange=function(){return e.createRange(n)}),n=t=null})}),I.createCoreModule("WrappedSelection",["DomRange","WrappedRange"],function(e,t){function n(e){return"string"==typeof e?/^backward(s)?$/i.test(e):!!e}function r(e,n){if(e){if(D.isWindow(e))return e;if(e instanceof R)return e.win;var r=D.getContentDocument(e,t,n);return D.getWindow(r)}return window}function o(e){return r(e,"getWinSelection").getSelection()}function i(e){return r(e,"getDocSelection").document.selection}function a(e){var t=!1;return e.anchorNode&&(t=1==D.comparePoints(e.anchorNode,e.anchorOffset,e.focusNode,e.focusOffset)),t}function s(e,t,n){var r=n?"end":"start",o=n?"start":"end";e.anchorNode=t[r+"Container"],e.anchorOffset=t[r+"Offset"],e.focusNode=t[o+"Container"],e.focusOffset=t[o+"Offset"]}function c(e){var t=e.nativeSelection;e.anchorNode=t.anchorNode,e.anchorOffset=t.anchorOffset,e.focusNode=t.focusNode,e.focusOffset=t.focusOffset}function d(e){e.anchorNode=e.focusNode=null,e.anchorOffset=e.focusOffset=0,e.rangeCount=0,e.isCollapsed=!0,e._ranges.length=0}function f(t){var n;return t instanceof b?(n=e.createNativeRange(t.getDocument()),n.setEnd(t.endContainer,t.endOffset),n.setStart(t.startContainer,t.startOffset)):t instanceof P?n=t.nativeRange:H.implementsDomRange&&t instanceof D.getWindow(t.startContainer).Range&&(n=t),n}function u(e){if(!e.length||1!=e[0].nodeType)return!1;for(var t=1,n=e.length;n>t;++t)if(!D.isAncestorOf(e[0],e[t]))return!1;return!0}function l(e){var n=e.getNodes();if(!u(n))throw t.createError("getSingleElementFromRange: range "+e.inspect()+" did not consist of a single element");return n[0]}function h(e){return!!e&&"undefined"!=typeof e.text}function g(e,t){var n=new P(t);e._ranges=[n],s(e,n,!1),e.rangeCount=1,e.isCollapsed=n.collapsed}function p(t){if(t._ranges.length=0,"None"==t.docSelection.type)d(t);else{var n=t.docSelection.createRange();if(h(n))g(t,n);else{t.rangeCount=n.length;for(var r,o=k(n.item(0)),i=0;i<t.rangeCount;++i)r=e.createRange(o),r.selectNode(n.item(i)),t._ranges.push(r);t.isCollapsed=1==t.rangeCount&&t._ranges[0].collapsed,s(t,t._ranges[t.rangeCount-1],!1)}}}function m(e,n){for(var r=e.docSelection.createRange(),o=l(n),i=k(r.item(0)),a=L(i).createControlRange(),s=0,c=r.length;c>s;++s)a.add(r.item(s));try{a.add(o)}catch(d){throw t.createError("addRange(): Element within the specified Range could not be added to control selection (does it have layout?)")}a.select(),p(e)}function R(e,t,n){this.nativeSelection=e,this.docSelection=t,this._ranges=[],this.win=n,this.refresh()}function v(e){e.win=e.anchorNode=e.focusNode=e._ranges=null,e.rangeCount=e.anchorOffset=e.focusOffset=0,e.detached=!0}function C(e,t){for(var n,r,o=tt.length;o--;)if(n=tt[o],r=n.selection,"deleteAll"==t)v(r);else if(n.win==e)return"delete"==t?(tt.splice(o,1),!0):r;return"deleteAll"==t&&(tt.length=0),null}function N(e,n){for(var r,o=k(n[0].startContainer),i=L(o).createControlRange(),a=0,s=n.length;s>a;++a){r=l(n[a]);try{i.add(r)}catch(c){throw t.createError("setRanges(): Element within one of the specified Ranges could not be added to control selection (does it have layout?)")}}i.select(),p(e)}function E(e,t){if(e.win.document!=k(t))throw new I("WRONG_DOCUMENT_ERR")}function S(t){return function(n,r){var o;this.rangeCount?(o=this.getRangeAt(0),o["set"+(t?"Start":"End")](n,r)):(o=e.createRange(this.win.document),o.setStartAndEnd(n,r)),this.setSingleRange(o,this.isBackward())}}function y(e){var t=[],n=new B(e.anchorNode,e.anchorOffset),r=new B(e.focusNode,e.focusOffset),o="function"==typeof e.getName?e.getName():"Selection";if("undefined"!=typeof e.rangeCount)for(var i=0,a=e.rangeCount;a>i;++i)t[i]=b.inspect(e.getRangeAt(i));return"["+o+"(Ranges: "+t.join(", ")+")(anchor: "+n.inspect()+", focus: "+r.inspect()+"]"}e.config.checkSelectionRanges=!0;var w,T,O="boolean",_="number",D=e.dom,A=e.util,x=A.isHostMethod,b=e.DomRange,P=e.WrappedRange,I=e.DOMException,B=D.DomPosition,H=e.features,M="Control",k=D.getDocument,L=D.getBody,W=b.rangesEqual,F=x(window,"getSelection"),j=A.isHostObject(document,"selection");H.implementsWinGetSelection=F,H.implementsDocSelection=j;var z=j&&(!F||e.config.preferTextRange);if(z)w=i,e.isSelectionValid=function(e){var t=r(e,"isSelectionValid").document,n=t.selection;return"None"!=n.type||k(n.createRange().parentElement())==t};else{if(!F)return t.fail("Neither document.selection or window.getSelection() detected."),!1;w=o,e.isSelectionValid=function(){return!0}}e.getNativeSelection=w;var U=w();if(!U)return t.fail("Native selection was null (possibly issue 138?)"),!1;var V=e.createNativeRange(document),q=L(document),Y=A.areHostProperties(U,["anchorNode","focusNode","anchorOffset","focusOffset"]);H.selectionHasAnchorAndFocus=Y;var Q=x(U,"extend");H.selectionHasExtend=Q;var G=typeof U.rangeCount==_;H.selectionHasRangeCount=G;var X=!1,Z=!0,$=Q?function(t,n){var r=b.getRangeDocument(n),o=e.createRange(r);o.collapseToPoint(n.endContainer,n.endOffset),t.addRange(f(o)),t.extend(n.startContainer,n.startOffset)}:null;A.areHostMethods(U,["addRange","getRangeAt","removeAllRanges"])&&typeof U.rangeCount==_&&H.implementsDomRange&&!function(){var t=window.getSelection();if(t){for(var n=t.rangeCount,r=n>1,o=[],i=a(t),s=0;n>s;++s)o[s]=t.getRangeAt(s);var c=D.createTestElement(document,"",!1),d=c.appendChild(document.createTextNode("   ")),f=document.createRange();if(f.setStart(d,1),f.collapse(!0),t.removeAllRanges(),t.addRange(f),Z=1==t.rangeCount,t.removeAllRanges(),!r){var u=window.navigator.appVersion.match(/Chrome\/(.*?) /);
/*
if(u&&parseInt(u[1])>=36)
	X=!1;
else{
	var l=f.cloneRange();f.setStart(d,0),l.setEnd(d,3),l.setStart(d,2),t.addRange(f),t.addRange(l),X=2==t.rangeCount
}
*/
	}for(D.removeNode(c),t.removeAllRanges(),s=0;n>s;++s)0==s&&i?$?$(t,o[s]):(e.warn("Rangy initialization: original selection was backwards but selection has been restored forwards because the browser does not support Selection.extend"),t.addRange(o[s])):t.addRange(o[s])}}(),H.selectionSupportsMultipleRanges=X,H.collapsedNonEditableSelectionsSupported=Z;var J,K=!1;q&&x(q,"createControlRange")&&(J=q.createControlRange(),A.areHostProperties(J,["item","add"])&&(K=!0)),H.implementsControlRange=K,T=Y?function(e){return e.anchorNode===e.focusNode&&e.anchorOffset===e.focusOffset}:function(e){return e.rangeCount?e.getRangeAt(e.rangeCount-1).collapsed:!1};var et;x(U,"getRangeAt")?et=function(e,t){try{return e.getRangeAt(t)}catch(n){return null}}:Y&&(et=function(t){var n=k(t.anchorNode),r=e.createRange(n);return r.setStartAndEnd(t.anchorNode,t.anchorOffset,t.focusNode,t.focusOffset),r.collapsed!==this.isCollapsed&&r.setStartAndEnd(t.focusNode,t.focusOffset,t.anchorNode,t.anchorOffset),r}),R.prototype=e.selectionPrototype;var tt=[],nt=function(e){if(e&&e instanceof R)return e.refresh(),e;e=r(e,"getNativeSelection");var t=C(e),n=w(e),o=j?i(e):null;return t?(t.nativeSelection=n,t.docSelection=o,t.refresh()):(t=new R(n,o,e),tt.push({win:e,selection:t})),t};e.getSelection=nt,A.createAliasForDeprecatedMethod(e,"getIframeSelection","getSelection");var rt=R.prototype;if(!z&&Y&&A.areHostMethods(U,["removeAllRanges","addRange"])){rt.removeAllRanges=function(){this.nativeSelection.removeAllRanges(),d(this)};var ot=function(e,t){$(e.nativeSelection,t),e.refresh()};rt.addRange=G?function(t,r){if(K&&j&&this.docSelection.type==M)m(this,t);else if(n(r)&&Q)ot(this,t);else{var o;X?o=this.rangeCount:(this.removeAllRanges(),o=0);var i=f(t).cloneRange();try{this.nativeSelection.addRange(i)}catch(a){}if(this.rangeCount=this.nativeSelection.rangeCount,this.rangeCount==o+1){if(e.config.checkSelectionRanges){var c=et(this.nativeSelection,this.rangeCount-1);c&&!W(c,t)&&(t=new P(c))}this._ranges[this.rangeCount-1]=t,s(this,t,st(this.nativeSelection)),this.isCollapsed=T(this)}else this.refresh()}}:function(e,t){n(t)&&Q?ot(this,e):(this.nativeSelection.addRange(f(e)),this.refresh())},rt.setRanges=function(e){if(K&&j&&e.length>1)N(this,e);else{this.removeAllRanges();for(var t=0,n=e.length;n>t;++t)this.addRange(e[t])}}}else{if(!(x(U,"empty")&&x(V,"select")&&K&&z))return t.fail("No means of selecting a Range or TextRange was found"),!1;rt.removeAllRanges=function(){try{if(this.docSelection.empty(),"None"!=this.docSelection.type){var e;if(this.anchorNode)e=k(this.anchorNode);else if(this.docSelection.type==M){var t=this.docSelection.createRange();t.length&&(e=k(t.item(0)))}if(e){var n=L(e).createTextRange();n.select(),this.docSelection.empty()}}}catch(r){}d(this)},rt.addRange=function(t){this.docSelection.type==M?m(this,t):(e.WrappedTextRange.rangeToTextRange(t).select(),this._ranges[0]=t,this.rangeCount=1,this.isCollapsed=this._ranges[0].collapsed,s(this,t,!1))},rt.setRanges=function(e){this.removeAllRanges();var t=e.length;t>1?N(this,e):t&&this.addRange(e[0])}}rt.getRangeAt=function(e){if(0>e||e>=this.rangeCount)throw new I("INDEX_SIZE_ERR");return this._ranges[e].cloneRange()};var it;if(z)it=function(t){var n;e.isSelectionValid(t.win)?n=t.docSelection.createRange():(n=L(t.win.document).createTextRange(),n.collapse(!0)),t.docSelection.type==M?p(t):h(n)?g(t,n):d(t)};else if(x(U,"getRangeAt")&&typeof U.rangeCount==_)it=function(t){if(K&&j&&t.docSelection.type==M)p(t);else if(t._ranges.length=t.rangeCount=t.nativeSelection.rangeCount,t.rangeCount){for(var n=0,r=t.rangeCount;r>n;++n)t._ranges[n]=new e.WrappedRange(t.nativeSelection.getRangeAt(n));s(t,t._ranges[t.rangeCount-1],st(t.nativeSelection)),t.isCollapsed=T(t)}else d(t)};else{if(!Y||typeof U.isCollapsed!=O||typeof V.collapsed!=O||!H.implementsDomRange)return t.fail("No means of obtaining a Range or TextRange from the user's selection was found"),!1;it=function(e){var t,n=e.nativeSelection;n.anchorNode?(t=et(n,0),e._ranges=[t],e.rangeCount=1,c(e),e.isCollapsed=T(e)):d(e)}}rt.refresh=function(e){var t=e?this._ranges.slice(0):null,n=this.anchorNode,r=this.anchorOffset;if(it(this),e){var o=t.length;if(o!=this._ranges.length)return!0;if(this.anchorNode!=n||this.anchorOffset!=r)return!0;for(;o--;)if(!W(t[o],this._ranges[o]))return!0;return!1}};var at=function(e,t){var n=e.getAllRanges();e.removeAllRanges();for(var r=0,o=n.length;o>r;++r)W(t,n[r])||e.addRange(n[r]);e.rangeCount||d(e)};rt.removeRange=K&&j?function(e){if(this.docSelection.type==M){for(var t,n=this.docSelection.createRange(),r=l(e),o=k(n.item(0)),i=L(o).createControlRange(),a=!1,s=0,c=n.length;c>s;++s)t=n.item(s),t!==r||a?i.add(n.item(s)):a=!0;i.select(),p(this)}else at(this,e)}:function(e){at(this,e)};var st;!z&&Y&&H.implementsDomRange?(st=a,rt.isBackward=function(){return st(this)}):st=rt.isBackward=function(){return!1},rt.isBackwards=rt.isBackward,rt.toString=function(){for(var e=[],t=0,n=this.rangeCount;n>t;++t)e[t]=""+this._ranges[t];return e.join("")},rt.collapse=function(t,n){E(this,t);var r=e.createRange(t);r.collapseToPoint(t,n),this.setSingleRange(r),this.isCollapsed=!0},rt.collapseToStart=function(){if(!this.rangeCount)throw new I("INVALID_STATE_ERR");var e=this._ranges[0];this.collapse(e.startContainer,e.startOffset)},rt.collapseToEnd=function(){if(!this.rangeCount)throw new I("INVALID_STATE_ERR");var e=this._ranges[this.rangeCount-1];this.collapse(e.endContainer,e.endOffset)},rt.selectAllChildren=function(t){E(this,t);var n=e.createRange(t);n.selectNodeContents(t),this.setSingleRange(n)},rt.deleteFromDocument=function(){if(K&&j&&this.docSelection.type==M){for(var e,t=this.docSelection.createRange();t.length;)e=t.item(0),t.remove(e),D.removeNode(e);this.refresh()}else if(this.rangeCount){var n=this.getAllRanges();if(n.length){this.removeAllRanges();for(var r=0,o=n.length;o>r;++r)n[r].deleteContents();this.addRange(n[o-1])}}},rt.eachRange=function(e,t){for(var n=0,r=this._ranges.length;r>n;++n)if(e(this.getRangeAt(n)))return t},rt.getAllRanges=function(){var e=[];return this.eachRange(function(t){e.push(t)}),e},rt.setSingleRange=function(e,t){this.removeAllRanges(),this.addRange(e,t)},rt.callMethodOnEachRange=function(e,t){var n=[];return this.eachRange(function(r){n.push(r[e].apply(r,t||[]))}),n},rt.setStart=S(!0),rt.setEnd=S(!1),e.rangePrototype.select=function(e){nt(this.getDocument()).setSingleRange(this,e)},rt.changeEachRange=function(e){var t=[],n=this.isBackward();this.eachRange(function(n){e(n),t.push(n)}),this.removeAllRanges(),n&&1==t.length?this.addRange(t[0],"backward"):this.setRanges(t)},rt.containsNode=function(e,t){return this.eachRange(function(n){return n.containsNode(e,t)},!0)||!1},rt.getBookmark=function(e){return{backward:this.isBackward(),rangeBookmarks:this.callMethodOnEachRange("getBookmark",[e])}},rt.moveToBookmark=function(t){for(var n,r,o=[],i=0;n=t.rangeBookmarks[i++];)r=e.createRange(this.win),r.moveToBookmark(n),o.push(r);t.backward?this.setSingleRange(o[0],"backward"):this.setRanges(o)},rt.saveRanges=function(){return{backward:this.isBackward(),ranges:this.callMethodOnEachRange("cloneRange")}},rt.restoreRanges=function(e){this.removeAllRanges();for(var t,n=0;t=e.ranges[n];++n)this.addRange(t,e.backward&&0==n)},rt.toHtml=function(){var e=[];return this.eachRange(function(t){e.push(b.toHtml(t))}),e.join("")},H.implementsTextRange&&(rt.getNativeTextRange=function(){var n;if(n=this.docSelection){var r=n.createRange();if(h(r))return r;throw t.createError("getNativeTextRange: selection is a control selection")}if(this.rangeCount>0)return e.WrappedTextRange.rangeToTextRange(this.getRangeAt(0));throw t.createError("getNativeTextRange: selection contains no range")}),rt.getName=function(){return"WrappedSelection"},rt.inspect=function(){return y(this)},rt.detach=function(){C(this.win,"delete"),v(this)},R.detachAll=function(){C(null,"deleteAll")},R.inspect=y,R.isDirectionBackward=n,e.Selection=R,e.selectionPrototype=rt,e.addShimListener(function(e){"undefined"==typeof e.getSelection&&(e.getSelection=function(){return nt(e)}),e=null})});var L=!1,W=function(){L||(L=!0,!I.initialized&&I.config.autoInitialize&&u())};return b&&("complete"==document.readyState?W():(e(document,"addEventListener")&&document.addEventListener("DOMContentLoaded",W,!1),H(window,"load",W))),I},this);
!function(e,t){"function"==typeof define&&define.amd?define(["./rangy-core"],e):"undefined"!=typeof module&&"object"==typeof exports?module.exports=e(require("rangy")):e(t.rangy)}(function(e){return e.createModule("ClassApplier",["WrappedSelection"],function(e,t){function n(e,t){for(var n in e)if(e.hasOwnProperty(n)&&t(n,e[n])===!1)return!1;return!0}function s(e){return e.replace(/^\s\s*/,"").replace(/\s\s*$/,"")}function r(e,t){return!!e&&new RegExp("(?:^|\\s)"+t+"(?:\\s|$)").test(e)}function o(e,t){if("object"==typeof e.classList)return e.classList.contains(t);var n="string"==typeof e.className,s=n?e.className:e.getAttribute("class");return r(s,t)}function a(e,t){if("object"==typeof e.classList)e.classList.add(t);else{var n="string"==typeof e.className,s=n?e.className:e.getAttribute("class");s?r(s,t)||(s+=" "+t):s=t,n?e.className=s:e.setAttribute("class",s)}}function l(e){var t="string"==typeof e.className;return t?e.className:e.getAttribute("class")}function u(e){return e&&e.split(/\s+/).sort().join(" ")}function f(e){return u(l(e))}function c(e,t){return f(e)==f(t)}function p(e,t){for(var n=t.split(/\s+/),r=0,i=n.length;i>r;++r)if(!o(e,s(n[r])))return!1;return!0}function d(e){var t=e.parentNode;return t&&1==t.nodeType&&!/^(textarea|style|script|select|iframe)$/i.test(t.nodeName)}function h(e,t,n,s,r){var i=e.node,o=e.offset,a=i,l=o;i==s&&o>r&&++l,i!=t||o!=n&&o!=n+1||(a=s,l+=r-n),i==t&&o>n+1&&--l,e.node=a,e.offset=l}function m(e,t,n){e.node==t&&e.offset>n&&--e.offset}function g(e,t,n,s){-1==n&&(n=t.childNodes.length);var r=e.parentNode,i=$.getNodeIndex(e);U(s,function(e){h(e,r,i,t,n)}),t.childNodes.length==n?t.appendChild(e):t.insertBefore(e,t.childNodes[n])}function N(e,t){var n=e.parentNode,s=$.getNodeIndex(e);U(t,function(e){m(e,n,s)}),$.removeNode(e)}function v(e,t,n,s,r){for(var i,o=[];i=e.firstChild;)g(i,t,n++,r),o.push(i);return s&&N(e,r),o}function y(e,t){return v(e,e.parentNode,$.getNodeIndex(e),!0,t)}function C(e,t){var n=e.cloneRange();n.selectNodeContents(t);var s=n.intersection(e),r=s?s.toString():"";return""!=r}function T(e){for(var t,n=e.getNodes([3]),s=0;(t=n[s])&&!C(e,t);)++s;for(var r=n.length-1;(t=n[r])&&!C(e,t);)--r;return n.slice(s,r+1)}function E(e,t){if(e.attributes.length!=t.attributes.length)return!1;for(var n,s,r,i=0,o=e.attributes.length;o>i;++i)if(n=e.attributes[i],r=n.name,"class"!=r){if(s=t.attributes.getNamedItem(r),null===n!=(null===s))return!1;if(n.specified!=s.specified)return!1;if(n.specified&&n.nodeValue!==s.nodeValue)return!1}return!0}function b(e,t){for(var n,s=0,r=e.attributes.length;r>s;++s)if(n=e.attributes[s].name,(!t||!B(t,n))&&e.attributes[s].specified&&"class"!=n)return!0;return!1}function A(e){var t;return e&&1==e.nodeType&&((t=e.parentNode)&&9==t.nodeType&&"on"==t.designMode||G(e)&&!G(e.parentNode))}function S(e){return(G(e)||1!=e.nodeType&&G(e.parentNode))&&!A(e)}function x(e){return e&&1==e.nodeType&&!J.test(F(e,"display"))}function R(e){if(0==e.data.length)return!0;if(K.test(e.data))return!1;var t=F(e.parentNode,"whiteSpace");switch(t){case"pre":case"pre-wrap":case"-moz-pre-wrap":return!1;case"pre-line":if(/[\r\n]/.test(e.data))return!1}return x(e.previousSibling)||x(e.nextSibling)}function P(e){var t,n,s=[];for(t=0;n=e[t++];)s.push(new z(n.startContainer,n.startOffset),new z(n.endContainer,n.endOffset));return s}function w(e,t){for(var n,s,r,i=0,o=e.length;o>i;++i)n=e[i],s=t[2*i],r=t[2*i+1],n.setStartAndEnd(s.node,s.offset,r.node,r.offset)}function O(e,t){return $.isCharacterDataNode(e)?0==t?!!e.previousSibling:t==e.length?!!e.nextSibling:!0:t>0&&t<e.childNodes.length}function I(e,n,s,r){var i,o,a=0==s;if($.isAncestorOf(n,e))return e;if($.isCharacterDataNode(n)){var l=$.getNodeIndex(n);if(0==s)s=l;else{if(s!=n.length)throw t.createError("splitNodeAt() should not be called with offset in the middle of a data node ("+s+" in "+n.data);s=l+1}n=n.parentNode}if(O(n,s)){i=n.cloneNode(!1),o=n.parentNode,i.id&&i.removeAttribute("id");for(var u,f=0;u=n.childNodes[s];)g(u,i,f++,r);return g(i,o,$.getNodeIndex(n)+1,r),n==e?i:I(e,o,$.getNodeIndex(i),r)}if(e!=n){i=n.parentNode;var c=$.getNodeIndex(n);return a||c++,I(e,i,c,r)}return e}function W(e,t){return e.namespaceURI==t.namespaceURI&&e.tagName.toLowerCase()==t.tagName.toLowerCase()&&c(e,t)&&E(e,t)&&"inline"==F(e,"display")&&"inline"==F(t,"display")}function L(e){var t=e?"nextSibling":"previousSibling";return function(n,s){var r=n.parentNode,i=n[t];if(i){if(i&&3==i.nodeType)return i}else if(s&&(i=r[t],i&&1==i.nodeType&&W(r,i))){var o=i[e?"firstChild":"lastChild"];if(o&&3==o.nodeType)return o}return null}}function M(e){this.isElementMerge=1==e.nodeType,this.textNodes=[];var t=this.isElementMerge?e.lastChild:e;t&&(this.textNodes[0]=t)}function H(e,t,r){var i,o,a,l,f=this;f.cssClass=f.className=e;var c=null,p={};if("object"==typeof t&&null!==t){for("undefined"!=typeof t.elementTagName&&(t.elementTagName=t.elementTagName.toLowerCase()),r=t.tagNames,c=t.elementProperties,p=t.elementAttributes,o=0;l=Y[o++];)t.hasOwnProperty(l)&&(f[l]=t[l]);i=t.normalize}else i=t;f.normalize="undefined"==typeof i?!0:i,f.attrExceptions=[];var d=document.createElement(f.elementTagName);f.elementProperties=f.copyPropertiesToElement(c,d,!0),n(p,function(e,t){f.attrExceptions.push(e),p[e]=""+t}),f.elementAttributes=p,f.elementSortedClassName=f.elementProperties.hasOwnProperty("className")?u(f.elementProperties.className+" "+e):e,f.applyToAnyTagName=!1;var h=typeof r;if("string"==h)"*"==r?f.applyToAnyTagName=!0:f.tagNames=s(r.toLowerCase()).split(/\s*,\s*/);else if("object"==h&&"number"==typeof r.length)for(f.tagNames=[],o=0,a=r.length;a>o;++o)"*"==r[o]?f.applyToAnyTagName=!0:f.tagNames.push(r[o].toLowerCase());else f.tagNames=[f.elementTagName]}function j(e,t,n){return new H(e,t,n)}var $=e.dom,z=$.DomPosition,B=$.arrayContains,D=e.util,U=D.forEach,V="span",k=D.isHostMethod(document,"createElementNS"),q=function(){function e(e,t,n){return t&&n?" ":""}return function(t,n){if("object"==typeof t.classList)t.classList.remove(n);else{var s="string"==typeof t.className,r=s?t.className:t.getAttribute("class");r=r.replace(new RegExp("(^|\\s)"+n+"(\\s|$)"),e),s?t.className=r:t.setAttribute("class",r)}}}(),F=$.getComputedStyleProperty,G=function(){var e=document.createElement("div");return"boolean"==typeof e.isContentEditable?function(e){return e&&1==e.nodeType&&e.isContentEditable}:function(e){return e&&1==e.nodeType&&"false"!=e.contentEditable?"true"==e.contentEditable||G(e.parentNode):!1}}(),J=/^inline(-block|-table)?$/i,K=/[^\r\n\t\f \u200B]/,Q=L(!1),X=L(!0);M.prototype={doMerge:function(e){var t=this.textNodes,n=t[0];if(t.length>1){var s,r=$.getNodeIndex(n),i=[],o=0;U(t,function(t,a){s=t.parentNode,a>0&&(s.removeChild(t),s.hasChildNodes()||$.removeNode(s),e&&U(e,function(e){e.node==t&&(e.node=n,e.offset+=o),e.node==s&&e.offset>r&&(--e.offset,e.offset==r+1&&len-1>a&&(e.node=n,e.offset=o))})),i[a]=t.data,o+=t.data.length}),n.data=i.join("")}return n.data},getLength:function(){for(var e=this.textNodes.length,t=0;e--;)t+=this.textNodes[e].length;return t},toString:function(){var e=[];return U(this.textNodes,function(t,n){e[n]="'"+t.data+"'"}),"[Merge("+e.join(",")+")]"}};var Y=["elementTagName","ignoreWhiteSpace","applyToEditableOnly","useExistingElements","removeEmptyElements","onElementCreate"],Z={};H.prototype={elementTagName:V,elementProperties:{},elementAttributes:{},ignoreWhiteSpace:!0,applyToEditableOnly:!1,useExistingElements:!0,removeEmptyElements:!0,onElementCreate:null,copyPropertiesToElement:function(e,t,n){var s,r,i,o,l,f,c={};for(var p in e)if(e.hasOwnProperty(p))if(o=e[p],l=t[p],"className"==p)a(t,o),a(t,this.className),t[p]=u(t[p]),n&&(c[p]=o);else if("style"==p){r=l,n&&(c[p]=i={});for(s in e[p])e[p].hasOwnProperty(s)&&(r[s]=o[s],n&&(i[s]=r[s]));this.attrExceptions.push(p)}else t[p]=o,n&&(c[p]=t[p],f=Z.hasOwnProperty(p)?Z[p]:p,this.attrExceptions.push(f));return n?c:""},copyAttributesToElement:function(e,t){for(var n in e)e.hasOwnProperty(n)&&!/^class(?:Name)?$/i.test(n)&&t.setAttribute(n,e[n])},appliesToElement:function(e){return B(this.tagNames,e.tagName.toLowerCase())},getEmptyElements:function(e){var t=this;return e.getNodes([1],function(e){return t.appliesToElement(e)&&!e.hasChildNodes()})},hasClass:function(e){return 1==e.nodeType&&(this.applyToAnyTagName||this.appliesToElement(e))&&o(e,this.className)},getSelfOrAncestorWithClass:function(e){for(;e;){if(this.hasClass(e))return e;e=e.parentNode}return null},isModifiable:function(e){return!this.applyToEditableOnly||S(e)},isIgnorableWhiteSpaceNode:function(e){return this.ignoreWhiteSpace&&e&&3==e.nodeType&&R(e)},postApply:function(e,t,n,s){var r,o,a=e[0],l=e[e.length-1],u=[],f=a,c=l,p=0,d=l.length;U(e,function(e){o=Q(e,!s),o?(r||(r=new M(o),u.push(r)),r.textNodes.push(e),e===a&&(f=r.textNodes[0],p=f.length),e===l&&(c=r.textNodes[0],d=r.getLength())):r=null});var h=X(l,!s);if(h&&(r||(r=new M(l),u.push(r)),r.textNodes.push(h)),u.length){for(i=0,len=u.length;len>i;++i)u[i].doMerge(n);t.setStartAndEnd(f,p,c,d)}},createContainer:function(e){var t,n=$.getDocument(e),s=k&&!$.isHtmlNamespace(e)&&(t=e.namespaceURI)?n.createElementNS(e.namespaceURI,this.elementTagName):n.createElement(this.elementTagName);return this.copyPropertiesToElement(this.elementProperties,s,!1),this.copyAttributesToElement(this.elementAttributes,s),a(s,this.className),this.onElementCreate&&this.onElementCreate(s,this),s},elementHasProperties:function(e,t){var s=this;return n(t,function(t,n){if("className"==t)return p(e,n);if("object"==typeof n){if(!s.elementHasProperties(e[t],n))return!1}else if(e[t]!==n)return!1})},elementHasAttributes:function(e,t){return n(t,function(t,n){return e.getAttribute(t)!==n?!1:void 0})},applyToTextNode:function(e){if(d(e)){var t=e.parentNode;if(1==t.childNodes.length&&this.useExistingElements&&this.appliesToElement(t)&&this.elementHasProperties(t,this.elementProperties)&&this.elementHasAttributes(t,this.elementAttributes))a(t,this.className);else{var n=e.parentNode,s=this.createContainer(n);n.insertBefore(s,e),s.appendChild(e)}}},isRemovable:function(e){return e.tagName.toLowerCase()==this.elementTagName&&f(e)==this.elementSortedClassName&&this.elementHasProperties(e,this.elementProperties)&&!b(e,this.attrExceptions)&&this.elementHasAttributes(e,this.elementAttributes)&&this.isModifiable(e)},isEmptyContainer:function(e){var t=e.childNodes.length;return 1==e.nodeType&&this.isRemovable(e)&&(0==t||1==t&&this.isEmptyContainer(e.firstChild))},removeEmptyContainers:function(e){var t=this,n=e.getNodes([1],function(e){return t.isEmptyContainer(e)}),s=[e],r=P(s);U(n,function(e){N(e,r)}),w(s,r)},undoToTextNode:function(e,t,n,s){if(!t.containsNode(n)){var r=t.cloneRange();r.selectNode(n),r.isPointInRange(t.endContainer,t.endOffset)&&(I(n,t.endContainer,t.endOffset,s),t.setEndAfter(n)),r.isPointInRange(t.startContainer,t.startOffset)&&(n=I(n,t.startContainer,t.startOffset,s))}this.isRemovable(n)?y(n,s):q(n,this.className)},splitAncestorWithClass:function(e,t,n){var s=this.getSelfOrAncestorWithClass(e);s&&I(s,e,t,n)},undoToAncestor:function(e,t){this.isRemovable(e)?y(e,t):q(e,this.className)},applyToRange:function(e,t){var n=this;t=t||[];var s=P(t||[]);e.splitBoundariesPreservingPositions(s),n.removeEmptyElements&&n.removeEmptyContainers(e);var r=T(e);if(r.length){U(r,function(e){n.isIgnorableWhiteSpaceNode(e)||n.getSelfOrAncestorWithClass(e)||!n.isModifiable(e)||n.applyToTextNode(e,s)});var i=r[r.length-1];e.setStartAndEnd(r[0],0,i,i.length),n.normalize&&n.postApply(r,e,s,!1),w(t,s)}var o=n.getEmptyElements(e);U(o,function(e){a(e,n.className)})},applyToRanges:function(e){for(var t=e.length;t--;)this.applyToRange(e[t],e);return e},applyToSelection:function(t){var n=e.getSelection(t);n.setRanges(this.applyToRanges(n.getAllRanges()))},undoToRange:function(e,t){var n=this;t=t||[];var s=P(t);e.splitBoundariesPreservingPositions(s),n.removeEmptyElements&&n.removeEmptyContainers(e,s);var r,i,o=T(e),a=o[o.length-1];if(o.length){n.splitAncestorWithClass(e.endContainer,e.endOffset,s),n.splitAncestorWithClass(e.startContainer,e.startOffset,s);for(var l=0,u=o.length;u>l;++l)r=o[l],i=n.getSelfOrAncestorWithClass(r),i&&n.isModifiable(r)&&n.undoToAncestor(i,s);e.setStartAndEnd(o[0],0,a,a.length),n.normalize&&n.postApply(o,e,s,!0),w(t,s)}var f=n.getEmptyElements(e);U(f,function(e){q(e,n.className)})},undoToRanges:function(e){for(var t=e.length;t--;)this.undoToRange(e[t],e);return e},undoToSelection:function(t){var n=e.getSelection(t),s=e.getSelection(t).getAllRanges();this.undoToRanges(s),n.setRanges(s)},isAppliedToRange:function(e){if(e.collapsed||""==e.toString())return!!this.getSelfOrAncestorWithClass(e.commonAncestorContainer);var t=e.getNodes([3]);if(t.length)for(var n,s=0;n=t[s++];)if(!this.isIgnorableWhiteSpaceNode(n)&&C(e,n)&&this.isModifiable(n)&&!this.getSelfOrAncestorWithClass(n))return!1;return!0},isAppliedToRanges:function(e){var t=e.length;if(0==t)return!1;for(;t--;)if(!this.isAppliedToRange(e[t]))return!1;return!0},isAppliedToSelection:function(t){var n=e.getSelection(t);return this.isAppliedToRanges(n.getAllRanges())},toggleRange:function(e){this.isAppliedToRange(e)?this.undoToRange(e):this.applyToRange(e)},toggleSelection:function(e){this.isAppliedToSelection(e)?this.undoToSelection(e):this.applyToSelection(e)},getElementsWithClassIntersectingRange:function(e){var t=[],n=this;return e.getNodes([3],function(e){var s=n.getSelfOrAncestorWithClass(e);s&&!B(t,s)&&t.push(s)}),t},detach:function(){}},H.util={hasClass:o,addClass:a,removeClass:q,getClass:l,hasSameClasses:c,hasAllClasses:p,replaceWithOwnChildren:y,elementsHaveSameNonClassAttributes:E,elementHasNonClassAttributes:b,splitNodeAt:I,isEditableElement:G,isEditingHost:A,isEditable:S},e.CssClassApplier=e.ClassApplier=H,e.createClassApplier=j,D.createAliasForDeprecatedMethod(e,"createCssClassApplier","createClassApplier",t)}),e},this);

rangy.createModule("Util", ["WrappedSelection"], function(api, module) {
    var rangeProto = api.rangePrototype;
    var selProto = api.selectionPrototype;

    selProto.pasteText = function(text) {
        this.deleteFromDocument();
        var range = this.getRangeAt(0);
        var textNode = range.getDocument().createTextNode(text);
        range.insertNode(textNode);
        this.setSingleRange(range);
    };

    rangeProto.pasteText = function(text) {
        this.deleteContents();
        var textNode = this.getDocument().createTextNode(text);
        this.insertNode(textNode);
    };

    selProto.pasteHtml = function(html) {
        this.deleteFromDocument();
        var range = this.getRangeAt(0);
        var frag = this.createContextualFragment(html);
        var lastNode = frag.lastChild;
        range.insertNode(frag);
        if (lastNode) {
            range.setStartAfter(lastNode)
        }
        this.setSingleRange(range);
    };

    rangeProto.pasteHtml = function(html) {
        this.deleteContents();
        var frag = this.createContextualFragment(html);
        this.insertNode(frag);
    };

    selProto.selectNodeContents = function(node) {
        var range = api.createRange(this.win);
        range.selectNodeContents(node);
        this.setSingleRange(range);
    };

    api.createRangeFromNode = function(node) {
        var range = api.createRange(node);
        range.selectNode(node);
        return range;
    };

    api.createRangeFromNodeContents = function(node) {
        var range = api.createRange(node);
        range.selectNodeContents(node);
        return range;
    };

    api.selectNodeContents = function(node) {
        api.getSelection().selectNodeContents(node);
    };

    rangeProto.selectSelectedTextElements = (function() {
        function isInlineElement(node) {
            return node.nodeType == 1 && api.dom.getComputedStyleProperty(node, "display") == "inline";
        }

        function getOutermostNodeContainingText(range, node) {
            var outerNode = null;
            var nodeRange = range.cloneRange();
            nodeRange.selectNode(node);
            if (nodeRange.toString() !== "") {
                while ( (node = node.parentNode) && isInlineElement(node) && range.containsNodeText(node) ) {
                    outerNode = node;
                }
            }
            return outerNode;
        }

        return function() {
            var startNode = getOutermostNodeContainingText(this, this.startContainer);
            if (startNode) {
                this.setStartBefore(startNode);
            }

            var endNode = getOutermostNodeContainingText(this, this.endContainer);
            if (endNode) {
                this.setEndAfter(endNode);
            }
        };
    })();

    // TODO: simple selection save/restore
});

/**
 * Commands module for Rangy.
 * Provides replacements for many document.execCommand() commands, applicable to Ranges and Selections.
 *
 * Part of Rangy, a cross-browser JavaScript range and selection library
 * https://github.com/timdown/rangy
 *
 * Depends on Rangy core.
 *
 * Algorithm is based on Aryeh Gregor's HTML Editing Commands specification
 * http://aryeh.name/gitweb.cgi?p=editcommands;a=blob_plain;f=editcommands.html;hb=HEAD
 *
 * Parts of this code are based on Aryeh Gregor's implementation of his algorithm
 * http://aryeh.name/spec/editcommands/autoimplementation.html
 *
 * Copyright %%build:year%%, Tim Down
 * Licensed under the MIT license.
 * Version: %%build:version%%
 * Build date: %%build:date%%
 */
rangy.createModule("Commands", ["WrappedSelection"], function(api, module) {
    /*
    http://aryeh.name/spec/editcommands/autoimplementation.html
    https://bitbucket.org/ms2ger/dom-range/src/tip/test/
    http://aryeh.name/gitweb.cgi?p=editcommands;a=blob_plain;f=editcommands.html;hb=HEAD
     */

    var dom = api.dom;
    var BOOLEAN = "boolean", UNDEF = "undefined";
    var getRootContainer = dom.getRootContainer;

    var globalOptions = {
        applyToEditableOnly: false
    };

    var getComputedStyleProperty;

    if (typeof window.getComputedStyle != UNDEF) {
        getComputedStyleProperty = function(el, propName) {
            return dom.getWindow(el).getComputedStyle(el, null)[propName];
        };
    } else if (typeof document.documentElement.currentStyle != UNDEF) {
        getComputedStyleProperty = function(el, propName) {
            return el.currentStyle[propName];
        };
    } else {
        module.fail("No means of obtaining computed style properties found");
    }

    function isBefore(nodeA, nodeB) {
        var parentA = nodeA.parentNode, parentB = nodeB.parentNode;
        if (parentA && parentB) {
            return dom.comparePoints(nodeA.parentNode, dom.getNodeIndex(nodeA), nodeB.parentNode, dom.getNodeIndex(nodeB)) == -1;
        } else {
            return !parentA;
        }
    }

    var toArray = api.util.toArray;

    /**
     * Returns the furthest ancestor of a Node as defined by DOM Range.
     */
    function getFurthestAncestor(node) {
        var root = node;
        while (root.parentNode != null) {
            root = root.parentNode;
        }
        return root;
    }

    function isEditableElement(node) {
        return node && node.nodeType == 1 && node.isContentEditable;
    }

    // The spec says "An editing host is a node that is either an Element with a contenteditable
    // attribute set to the true state, or a Document whose designMode is enabled."
    // Because Safari returns "true" for the contentEditable property of an element that actually inherits its
    // editability from its parent, we use a different definition:

    // "An editing host is a node that is either an Element whose isContentEditable property returns true but whose
    // parent node is not an element or whose isContentEditable property returns false, or a Document whose designMode
    // is enabled."
    function isEditingHost(node) {
        return node &&
            ((node.nodeType == 9 && node.designMode == "on") ||
            (isEditableElement(node) && !isEditableElement(node.parentNode)));
    }

    // The spec says "Something is editable if it is a node which is not an editing host, does
    // not have a contenteditable attribute set to the false state, and whose
    // parent is an editing host or editable."

    // We're not making any distinction, unless the applyToEditableOnly global option is set to true. Rangy commands can
    // run on non-editable content. The revised definition:

    // "A node is editable if it is not an editing host and is or is the child of an Element whose isContentEditable
    // property returns true."
    function isEditable(node, options) {
        // This is slightly a lie, because we're excluding non-HTML elements with
        // contentEditable attributes.
        return !options || !options.applyToEditableOnly
            || ( (isEditableElement(node) || isEditableElement(node.parentNode)) && !isEditingHost(node) );
    }

    /**
     * "contained" as defined by DOM Range: "A Node node is contained in a range
     * range if node's furthest ancestor is the same as range's root, and (node, 0)
     * is after range's start, and (node, length of node) is before range's end."
     */
    function isContained(node, range) {
        var pos1 = dom.comparePoints(node, 0, range.startContainer, range.startOffset);
        var pos2 = dom.comparePoints(node, dom.getNodeLength(node), range.endContainer, range.endOffset);

        return getRootContainer(node) == getRootContainer(range.startContainer)
            && pos1 == 1
            && pos2 == -1;
    }

    /**
     * "A Node is effectively contained in a Range if either it is contained in the
     * Range; or it is the Range's start node, it is a Text node, and its length is
     * different from the Range's start offset; or it is the Range's end node, it
     * is a Text node, and the Range's end offset is not 0; or it has at least one
     * child, and all its children are effectively contained in the Range."
     */
    function isEffectivelyContained(node, range) {
        if (isContained(node, range)) {
            return true;
        }
        var isCharData = dom.isCharacterDataNode(node);
        if (node == range.startContainer && isCharData && dom.getNodeLength(node) != range.startOffset) {
            return true;
        }
        if (node == range.endContainer && isCharData && range.endOffset != 0) {
            return true;
        }
        var children = node.childNodes, childCount = children.length;
        if (childCount != 0) {
            for (var i = 0; i < childCount; ++i) {
                if (!isEffectivelyContained(children[i], range)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // Opera 11 puts HTML elements in the null namespace, it seems, and IE 7 has undefined namespaceURI
    function isHtmlNode(node) {
        var ns;
        return typeof (ns = node.namespaceURI) == UNDEF || (ns === null || ns == "http://www.w3.org/1999/xhtml");
    }


    var unwrappableTagNamesRegex = /^(h[1-6]|p|hr|pre|blockquote|ol|ul|li|dl|dt|dd|div|table|caption|colgroup|col|tbody|thead|tfoot|tr|th|td|address)$/i;
    var inlineDisplayRegex = /^inline(-block|-table)?$/i;

    /**
     * "An inline node is either a Text node, or an Element whose 'display'
     * property computes to 'inline', 'inline-block', or 'inline-table'."
     */
    function isInlineNode(node) {
        return dom.isCharacterDataNode(node) ||
                (node.nodeType == 1 && inlineDisplayRegex.test(getComputedStyleProperty(node, "display")));
    }

    function isNullOrInlineNode(node) {
        return !node || isInlineNode(node);
    }

    function isNonBrInlineNode(node) {
        return isInlineNode(node) && node.nodeName.toLowerCase() != "br";
    }

    function isHtmlElement(node, tagNames) {
        if (!node || node.nodeType != 1 || !isHtmlNode(node)) {
            return false;
        }
        switch (typeof tagNames) {
            case "string":
                return node.tagName.toLowerCase() == tagNames.toLowerCase();
            case "object":
                return new RegExp("^(" + tagNames.join(",") + ")$", "i").test(node.tagName);
            default:
                return true;
        }
    }

    /**
     * "An unwrappable node is an HTML element which may not be used where only
     * phrasing content is expected (not counting unknown or obsolete elements,
     * which cannot be used at all); or any Element whose display property computes
     * to something other than 'inline', 'inline-block', or 'inline-table'; or any
     * node whose parent is not editable."
     */
    function isUnwrappable(node, options) {
        if (!isHtmlElement(node)) {
            return false;
        }

        if (!isInlineNode(node)) {
            return true;
        }

        if (!isEditable(node, options)) {
            return true;
        }

/*
        if (node.nodeType == 3 && options.ignoreWhiteSpace && !/[^\r\n\t ]/.test(node.data)
                && (isUnwrappable(node.previousSibling, options) || isUnwrappable(node.nextSibling, options))) {
            return true;
        }
*/

        return unwrappableTagNamesRegex.test(node.tagName);
    }


/*
    function isWhitespaceNode(node) {
        return node.nodeType == 3 && !/[^\r\n\t ]/.test(node.data);
    }
*/

    function isIgnoredNode(node, options) {
        // Ignore comment nodes
        if (node.nodeType == 8) {
            return true;
        } else if (node.nodeType == 3) {
            // Ignore text nodes that are within <script> and <style> elements
            if (node.parentNode && /^(script|style)$/i.test(node.parentNode.tagName)) {
                return true;
            }

            // Ignore whitespace nodes that are next to an unwrappable element
            if (options.ignoreWhiteSpace && !/[^\r\n\t ]/.test(node.data)
                    && (isUnwrappable(node.previousSibling, options) || isUnwrappable(node.nextSibling, options))) {
                return true;
            }
        }
        return false;
    }

    function blockExtend(range) {
        // "Let start node, start offset, end node, and end offset be the start
        // and end nodes and offsets of the range."
        var startNode = range.startContainer,
            startOffset = range.startOffset,
            endNode = range.endContainer,
            endOffset = range.endOffset,
            startChildNode,
            endChildNode;

        // "Repeat the following steps:"
        while (true) {
            // "If start node is a Text or Comment node or start offset is 0,
            // set start offset to the index of start node and then set start
            // node to its parent."
            if (dom.isCharacterDataNode(startNode) || startOffset == 0) {
                startOffset = dom.getNodeIndex(startNode);
                startNode = startNode.parentNode;

            // "Otherwise, if start offset is equal to the length of start
            // node, set start offset to one plus the index of start node and
            // then set start node to its parent."
            } else if (startOffset == dom.getNodeLength(startNode)) {
                startOffset = 1 + dom.getNodeIndex(startNode);
                startNode = startNode.parentNode;

            // "Otherwise, if the child of start node with index start offset and
            // its previousSibling are both inline nodes and neither is a br,
            // subtract one from start offset."
            } else if ( (startChildNode = startNode.childNodes[startOffset])
                    && isNonBrInlineNode(startChildNode)
                    && isNonBrInlineNode(startChildNode.previousSibling)) {

                startOffset--;

            // "Otherwise, break from this loop."
            } else {
                break;
            }
        }

        // "Repeat the following steps:"
        while (true) {
            // "If end offset is 0, set end offset to the index of end node and
            // then set end node to its parent."
            if (endOffset == 0) {
                endOffset = dom.getNodeIndex(endNode);
                endNode = endNode.parentNode;

            // "Otherwise, if end node is a Text or Comment node or end offset
            // is equal to the length of end node, set end offset to one plus
            // the index of end node and then set end node to its parent."
            } else if (dom.isCharacterDataNode(endNode) || endOffset == dom.getNodeLength(endNode)) {
                endOffset = 1 + dom.getNodeIndex(endNode);
                endNode = endNode.parentNode;

            // "Otherwise, if the child of end node with index end offset and its
            // nextSibling are both inline nodes and neither is a br, add one
            // to end offset."
            } else if ( (endChildNode = endNode.childNodes[endOffset])
                    && isNonBrInlineNode(endChildNode)
                    && isNonBrInlineNode(endChildNode.previousSibling)) {

                endOffset++;

            // "Otherwise, break from this loop."
            } else {
                break;
            }
        }

        // "Let new range be a new range whose start and end nodes and offsets
        // are start node, start offset, end node, and end offset."
        var newRange = range.cloneRange();
        newRange.setStart(startNode, startOffset);
        newRange.setEnd(endNode, endOffset);

        // "Return new range."
        return newRange;
    }

    function elementOnlyHasAttributes(el, attrs) {
        for (var i = 0, len = el.attributes.length, attrName; i < len; ++i) {
            attrName = el.attributes[i].name;
            if (el.attributes[i].specified && (!attrs || !dom.arrayContains(attrs, attrName))) {
                return false;
            }
        }
        return true;
    }

    // "A modifiable element is a b, em, i, s, span, strong, sub, sup, or u element
    // with no attributes except possibly style; or a font element with no
    // attributes except possibly style, color, face, and/or size; or an a element
    // with no attributes except possibly style and/or href."
    var modifiableElements = "b|em|i|s|span|strike|strong|sub|sup|u";
    var modifiableElementRegex = new RegExp("^(" + modifiableElements + ")$");

    function isModifiableElement(node, context) {
        if (!isHtmlElement(node)) {
            return false;
        }
        if (context && context.command.isModifiableElement) {
            return context.command.isModifiableElement(el, context);
        }
        var tagName = node.tagName.toLowerCase(), allowedAttributes;

        if (modifiableElementRegex.test(tagName)) {
            allowedAttributes = ["style", "class"];
        } else if (tagName == "a") {
            allowedAttributes = ["style", "class", "href"];
        } else if (tagName == "font") {
            allowedAttributes = ["style", "class", "color", "face", "size"];
        } else {
            return false;
        }
        return elementOnlyHasAttributes(node, allowedAttributes);
    }

    var simpleModifiableElements = modifiableElements + "|a|font";
    var simpleModifiableElementRegex = new RegExp("^(" + simpleModifiableElements + ")$");

    function isSimpleModifiableElement(el, context) {
        // "A simple modifiable element is an HTML element for which at least one
        // of the following holds:"
        if (!isHtmlElement(el)) {
            return false;
        }

        if (context && context.command.isSimpleModifiableElement) {
            return context.command.isSimpleModifiableElement(el, context);
        }

        // Only these elements can possibly be a simple modifiable element.
        var tagName = el.tagName.toLowerCase();
        if (!simpleModifiableElementRegex.test(tagName)) {
            return false;
        }

        // Extract attributes once and quit if more than one is found
        var attrName, attrValue, hasAnyAttrs = false;
        for (var i = 0, len = el.attributes.length; i < len; ++i) {
            if (el.attributes[i].specified) {
                // If it's got more than one attribute, everything after this fails.
                if (hasAnyAttrs) {
                    return false;
                } else {
                    attrName = el.attributes[i].name;
                    attrValue = el.getAttribute(attrName);
                    hasAnyAttrs = true;
                }
            }
        }

        // "It is an a, b, em, font, i, s, span, strike, strong, sub, sup, or u
        // element with no attributes."
        if (!hasAnyAttrs) {
            return true;
        }

        // "It is an a, b, em, font, i, s, span, strike, strong, sub, sup, or u
        // element with exactly one attribute, which is style, which sets no CSS
        // properties (including invalid or unrecognized properties)."
        if (attrName == "style" && el.style.cssText.length == 0) {
            return true;
        }

        // "It is an a element with exactly one attribute, which is href."
        if (tagName == "a" && attrName == "href") {
            return true;
        }

        // "It is a font element with exactly one attribute, which is either color,
        // face, or size."
        if (tagName == "font" && /^(color|face|size)$/.test(attrName)) {
            return true;
        }

        // Check style attribute and bail out if it has more than one property
        if ( attrName != "style" || (typeof el.style.length == "number" && el.style.length > 1) ||
                !/^[a-z\-]+:[^;]+;?\s?$/i.test(el.style.cssText)) {
            return false;
        }

        // "It is a b or strong element with exactly one attribute, which is style,
        // and the style attribute sets exactly one CSS property (including invalid
        // or unrecognized properties), which is "font-weight"."

        if ((tagName == "b" || tagName == "strong") && el.style.fontWeight != "") {
            return true;
        }

        // "It is an i or em element with exactly one attribute, which is style,
        // and the style attribute sets exactly one CSS property (including invalid
        // or unrecognized properties), which is "font-style"."
        if ((tagName == "i" || tagName == "em") && el.style.fontStyle != "") {
            return true;
        }

        // "It is a sub or sub element with exactly one attribute, which is style,
        // and the style attribute sets exactly one CSS property (including invalid
        // or unrecognized properties), which is "vertical-align"."
        if ((tagName == "sub" || tagName == "sup") && el.style.verticalAlign != "") {
            return true;
        }

        // "It is an a, font, or span element with exactly one attribute, which is
        // style, and the style attribute sets exactly one CSS property (including
        // invalid or unrecognized properties), and that property is not
        // "text-decoration"."
        if ((tagName == "a" || tagName == "font" || tagName == "span") && el.style.textDecoration == "") {
            return true;
        }

        // "It is an a, font, s, span, strike, or u element with exactly one
        // attribute, which is style, and the style attribute sets exactly one CSS
        // property (including invalid or unrecognized properties), which is
        // "text-decoration", which is set to "line-through" or "underline" or
        // "overline" or "none"."
        if (/^(a|font|s|span|strike|u)$/.test(tagName) && /^(line-through|underline|overline|none)$/.test(el.style.textDecoration)) {
            return true;
        }

        return false;
    }

    function addRangeMove(rangeMoves, range, oldParent, oldIndex, newParent, newIndex) {
        var sc = range.startContainer, so = range.startOffset,
            ec = range.endContainer, eo = range.endOffset;

        var newSc = sc, newSo = so, newEc = ec, newEo = eo;

        // "If a boundary point's node is the same as or a descendant of node,
        // leave it unchanged, so it moves to the new location."
        //
        // No modifications necessary.

        // "If a boundary point's node is new parent and its offset is greater than
        // new index, add one to its offset."
        if (sc == newParent && so > newIndex) {
            newSo++;
        }
        if (ec == newParent && eo > newIndex) {
            newEo++;
        }

        // "If a boundary point's node is old parent and its offset is old index or
        // old index + 1, set its node to new parent and add new index  old index
        // to its offset."
        if (sc == oldParent && (so == oldIndex  || so == oldIndex + 1)) {
            newSc = newParent;
            newSo += newIndex - oldIndex;
        }
        if (ec == oldParent && (eo == oldIndex || eo == oldIndex + 1)) {
            newEc = newParent;
            newEo += newIndex - oldIndex;
        }

        // "If a boundary point's node is old parent and its offset is greater than
        // old index + 1, subtract one from its offset."
        if (sc == oldParent && so > oldIndex + 1) {
            newSo--;
        }
        if (ec == oldParent && eo > oldIndex + 1) {
            newEo--;
        }

        if (newSc == sc && newSo == so && newEc == ec && newEo == eo) {
            rangeMoves.push([range, newSc, newSo, newEc, newEo]);
        }
    }

    function movePreservingRanges(node, newParent, newIndex, rangesToPreserve) {
        // For convenience, allow newIndex to be -1 to mean "insert at the end".
        if (newIndex == -1) {
            newIndex = newParent.childNodes.length;
        }

        // "When the user agent is to move a Node to a new location, preserving
        // ranges, it must remove the Node from its original parent (if any), then insert it
        // in the new location. In doing so, however, it must ignore the regular
        // range mutation rules, and instead follow these rules:"

        // "Let node be the moved Node, old parent and old index be the old parent
        // and index, and new parent and new index be the new parent and index."
        var oldParent = node.parentNode;
        var oldIndex = dom.getNodeIndex(node);

        var rangeMoves = [];

        for (var i = 0, len = rangesToPreserve.length; i < len; ++i) {
            addRangeMove(rangeMoves, rangesToPreserve[i], oldParent, oldIndex, newParent, newIndex);
        }

        // Now actually move the node.
        if (newParent.childNodes.length == newIndex) {
            newParent.appendChild(node);
        } else {
            newParent.insertBefore(node, newParent.childNodes[newIndex]);
        }

        // Set the new range boundaries
        for (var j = 0, rangeMove; rangeMove = rangeMoves[j++]; ) {
            rangeMove[0].setStart(rangeMove[1], rangeMove[2]);
            rangeMove[0].setEnd(rangeMove[3], rangeMove[4]);
        }
    }

    function decomposeSubtree(rangeIterator, nodes) {
        nodes = nodes || [];
        for (var node, subRangeIterator; node = rangeIterator.next(); ) {
            if (rangeIterator.isPartiallySelectedSubtree()) {
                // The node is partially selected by the Range, so we can use a new RangeIterator on the portion of the
                // node selected by the Range.
                subRangeIterator = rangeIterator.getSubtreeIterator();
                decomposeSubtree(subRangeIterator, nodes);
                subRangeIterator.detach();
            } else {
                nodes.push(node);
            }
        }
        return nodes;
    }

    function decomposeRange(range, rangesToPreserve) {
        // "If range's start and end are the same, return an empty list."
        if (range.startContainer == range.endContainer && range.startOffset == range.endOffset) {
            return [];
        }

        range.splitBoundaries(rangesToPreserve);

        // "Let cloned range be the result of calling cloneRange() on range."
        var clonedRange = range.cloneRange();

        // "While the start offset of cloned range is 0, and the parent of cloned
        // range's start node is not null, set the start of cloned range to (parent
        // of start node, index of start node)."
        while (clonedRange.startOffset == 0 && clonedRange.startContainer.parentNode) {
            clonedRange.setStart(clonedRange.startContainer.parentNode, dom.getNodeIndex(clonedRange.startContainer));
        }

        // "While the end offset of cloned range equals the length of its end node,
        // and the parent of clone range's end node is not null, set the end of
        // cloned range to (parent of end node, 1 + index of end node)."
        while (clonedRange.endOffset == dom.getNodeLength(clonedRange.endContainer) && clonedRange.endContainer.parentNode) {
            clonedRange.setEnd(clonedRange.endContainer.parentNode, 1 + dom.getNodeIndex(clonedRange.endContainer));
        }

        // "Return a list consisting of every Node contained in cloned range in
        // tree order, omitting any whose parent is also contained in cloned
        // range."

        var iterator = new rangy.DomRange.RangeIterator(clonedRange, false);
        var nodes = decomposeSubtree(iterator);
        iterator.detach();
        return nodes;
    }

    function moveChildrenPreservingRanges(node, newParent, newIndex, removeNode, rangesToPreserve) {
        var child, children = [];
        while ( (child = node.firstChild) ) {
            movePreservingRanges(child, newParent, newIndex++, rangesToPreserve);
            children.push(child);
        }
        if (removeNode) {
            node.parentNode.removeChild(node);
        }
        return children;
    }

    function replaceWithOwnChildren(element, rangesToPreserve) {
        return moveChildrenPreservingRanges(element, element.parentNode, dom.getNodeIndex(element), true, rangesToPreserve);
    }

    function copyAttributes(fromElement, toElement) {
        var attrs = fromElement.attributes;

        for (var i = 0, len = attrs.length; i < len; ++i) {
            if (attrs[i].specified) {
                // For IE, which doesn't allow copying of the entire style object using get/setAttribute
                if (attrs[i].name == "style") {
                    toElement.style.cssText = toElement.style.cssText;
                } else {
                    toElement.setAttribute(attrs[i].name, attrs[i].value);
                }
            }
        }
    }

    function clearValue(element, context) {
        var command = context.command, rangesToPreserve = context.rangesToPreserve;

        // "If element's specified value for command is null, return the empty
        // list."
        if (command.getSpecifiedValue(element, context) === null) {
            return [];
        }

        // "If element is a simple modifiable element:"
        if (isSimpleModifiableElement(element, context)) {
            var p = element.parentNode;
            return replaceWithOwnChildren(element, rangesToPreserve);
        }

        // Command-specific special cases
        if (command.clearValue) {
            command.clearValue(element, context);
        }

        // "If the relevant CSS property for command is not null, unset the CSS
        // property property of element."
        if (command.relevantCssProperty !== null) {
            element.style[command.relevantCssProperty] = "";
            if (element.style.cssText == "") {
                element.removeAttribute("style");
            }
        }

        // "If element's specified value for command is null, return the empty
        // list."
        if (command.getSpecifiedValue(element, context) === null) {
            return [];
        }

        // "Let new element be a new HTML element with name "span", with the
        // same attributes and ownerDocument as element."
        var newElement = dom.getDocument(element).createElement("span");
        copyAttributes(element, newElement);

        // "Insert new element into the parent of element immediately before it."
        element.parentNode.insertBefore(newElement, element);

        // "While element has children, append its first child as the last child of
        // new element, preserving ranges."
        // "Remove element from its parent."
        moveChildrenPreservingRanges(element, newElement, 0, true, rangesToPreserve);

        // "Return the one-Node list consisting of new element."
        return [newElement];
    }

    // This entire function is a massive hack to work around browser
    // incompatibility.  It wouldn't work in real life, but it's good enough for a
    // test implementation.  It's not clear how all this should actually be specced
    // in practice, since CSS defines no notion of equality, does it?
    function valuesEqual(command, val1, val2) {
        if (val1 === null || val2 === null) {
            return val1 === val2;
        }

        return command.valuesEqual(val1, val2);
    }

    /**
     * "effective value" per edit command spec
     */
    function getEffectiveValue(node, context) {
        var isElement = (node.nodeType == 1);

        // "If neither node nor its parent is an Element, return null."
        if (!isElement && (!node.parentNode || node.parentNode.nodeType != 1)) {
            return null;
        }

        // "If node is not an Element, return the effective value of its parent for
        // command."
        if (!isElement) {
            return getEffectiveValue(node.parentNode, context);
        }

        return context.command.getEffectiveValue(node, context);
    }

    function removeExtraneousLineBreaksBefore(node) {
        // "If node is not an Element, or it is an inline node, do nothing and
        // abort these steps."
        if (!node || node.nodeType != 1 || isInlineNode(node)) {
            return;
        }

        // "If the previousSibling of node is a br, and the previousSibling of the
        // previousSibling of node is an inline node that is not a br, remove the
        // previousSibling of node from its parent."
        var previousSibling = node.previousSibling, previousSiblingPreviousSibling;
        if (isHtmlElement(previousSibling, "br")
                && isInlineNode( (previousSiblingPreviousSibling = node.previousSibling.previousSibling) )
                && !isHtmlElement(previousSiblingPreviousSibling, "br")) {
            node.parentNode.removeChild(previousSibling);
        }
    }

    function removeExtraneousLineBreaksAtTheEndOf(node) {
        // "If node is not an Element, or it is an inline node, do nothing and
        // abort these steps."
        if (!node || node.nodeType != 1 || isInlineNode(node)) {
            return;
        }

        // "If node has at least two children, and its last child is a br, and its
        // second-to-last child is an inline node that is not a br, remove the last
        // child of node from node."
        var lastChild = node.lastChild, lastChildPreviousSibling;
        if (node.childNodes.length >= 2 && isHtmlElement(node.lastChild, "br")
                && isInlineNode( (lastChildPreviousSibling = node.lastChild.previousSibling) )
                && !isHtmlElement(lastChildPreviousSibling, "br")) {
            node.removeChild(lastChild);
        }
    }

    // "To remove extraneous line breaks from a node, first remove extraneous line
    // breaks before it, then remove extraneous line breaks at the end of it."
    function removeExtraneousLineBreaksFrom(node) {
        removeExtraneousLineBreaksBefore(node);
        removeExtraneousLineBreaksAtTheEndOf(node);
    }

    function wrap(nodeList, siblingCriteria, newParentInstructions, context) {
        var firstNode = nodeList[0];
        var options = context.options, rangesToPreserve = context.rangesToPreserve;
        var i, len, range;

        // "If node list is empty, or the first member of node list is not
        // editable, return null and abort these steps."
        if (!nodeList.length || !isEditable(firstNode, options)) {
            return null;
        }

        var lastNode = nodeList[nodeList.length - 1];

        // "If node list's last member is an inline node that's not a br, and node
        // list's last member's nextSibling is a br, append that br to node list."
        if (isInlineNode(lastNode) && !isHtmlElement(lastNode, "br") && isHtmlElement(lastNode.nextSibling, "br")) {
            nodeList.push(lastNode.nextSibling);
        }

        // "If the previousSibling of the first member of node list is editable and
        // meets the sibling criteria, let new parent be the previousSibling of the
        // first member of node list."
        var newParent, nodePriorToFirstNode = firstNode.previousSibling, nodeAfterLastNode = lastNode.nextSibling;

        if (isEditable(nodePriorToFirstNode, options) && siblingCriteria(nodePriorToFirstNode)) {
            newParent = nodePriorToFirstNode;

        // "Otherwise, if the nextSibling of the last member of node list is
        // editable and meets the sibling criteria, let new parent be the
        // nextSibling of the last member of node list."
        } else if (isEditable(nodeAfterLastNode, options) && siblingCriteria(nodeAfterLastNode)) {
            newParent = nodeAfterLastNode;

        // "Otherwise, run the new parent instructions, and let new parent be the
        // result."
        } else {
            newParent = newParentInstructions();
        }

        // "If new parent is null, abort these steps and return null."
        if (!newParent) {
            return null;
        }

        var doc = dom.getDocument(newParent);
        var newParentParent = newParent.parentNode, firstNodeParent = firstNode.parentNode;

        // "If new parent's parent is null:"
        if (!newParentParent) {
            // "Insert new parent into the parent of the first member of node list
            // immediately before the first member of node list."
            firstNodeParent.insertBefore(newParent, firstNode);

            // "If any range has a boundary point with node equal to the parent of
            // new parent and offset equal to the index of new parent, add one to
            // that boundary point's offset."

            // Preserve only the ranges passed in
            for (i = 0; range = rangesToPreserve[i++]; ) {
                if (range.startContainer == newParentParent && range.startOffset == dom.getNodeIndex(newParent)) {
                    range.setStart(range.startContainer, range.startOffset + 1);
                }
                if (range.endContainer == newParentParent && range.endOffset == dom.getNodeIndex(newParent)) {
                    range.setEnd(range.endContainer, range.endOffset + 1);
                }
            }
        }

        // "Let original parent be the parent of the first member of node list."

        // "If new parent is before the first member of node list in tree order:"
        if (isBefore(newParent, firstNode)) {
            // "If new parent is not an inline node, but the last child of new
            // parent and the first member of node list are both inline nodes, and
            // the last child of new parent is not a br, call createElement("br")
            // on the ownerDocument of new parent and append the result as the last
            // child of new parent."
            if (!isInlineNode(newParent) && isInlineNode(newParent.lastChild) && isInlineNode(firstNode) && !isHtmlElement(newParent.lastChild, "br")) {
                newParent.appendChild(doc.createElement("br"));
            }

            // "For each node in node list, append node as the last child of new
            // parent, preserving ranges."
            for (i = 0, len = nodeList.length; i < len; ++i) {
                movePreservingRanges(nodeList[i], newParent, -1, rangesToPreserve);
            }

        // "Otherwise:"
        } else {
            // "If new parent is not an inline node, but the first child of new
            // parent and the last member of node list are both inline nodes, and
            // the last member of node list is not a br, call createElement("br")
            // on the ownerDocument of new parent and insert the result as the
            // first child of new parent."
            if (!isInlineNode(newParent) && isInlineNode(newParent.firstChild) && isInlineNode(lastNode) && !isHtmlElement(lastNode, "br")) {
                newParent.insertBefore(doc.createElement("br"), newParent.firstChild);
            }

            // "For each node in node list, in reverse order, insert node as the
            // first child of new parent, preserving ranges."
            for (i = nodeList.length - 1; i >= 0; i--) {
                movePreservingRanges(nodeList[i], newParent, 0, rangesToPreserve);
            }
        }

        // "If original parent is editable and has no children, remove it from its
        // parent."
        if (isEditable(firstNodeParent, options) && !firstNodeParent.hasChildNodes()) {
            firstNodeParent.parentNode.removeChild(firstNodeParent);
        }

        // "If new parent's nextSibling is editable and meets the sibling
        // criteria:"
        var newParentNextSibling = newParent.nextSibling;
        if (isEditable(newParentNextSibling, options) && siblingCriteria(newParentNextSibling)) {
            // "If new parent is not an inline node, but new parent's last child
            // and new parent's nextSibling's first child are both inline nodes,
            // and new parent's last child is not a br, call createElement("br") on
            // the ownerDocument of new parent and append the result as the last
            // child of new parent."
            if (!isInlineNode(newParent) && isInlineNode(newParent.lastChild) &&
                    isInlineNode(newParentNextSibling.firstChild) && !isHtmlElement(newParent.lastChild, "br")) {
                newParent.appendChild(doc.createElement("br"));
            }

            // "While new parent's nextSibling has children, append its first child
            // as the last child of new parent, preserving ranges."
            while (newParentNextSibling.hasChildNodes()) {
                movePreservingRanges(newParentNextSibling.firstChild, newParent, -1, rangesToPreserve);
            }

            // "Remove new parent's nextSibling from its parent."
            newParent.parentNode.removeChild(newParentNextSibling);
        }

        // "Remove extraneous line breaks from new parent."
        removeExtraneousLineBreaksFrom(newParent);

        // "Return new parent."
        return newParent;
    }

    function reorderModifiableDescendants(node, command, newValue, context, siblingPropName) {
        var candidate = node[siblingPropName], rangesToPreserve = context.rangesToPreserve;

        // "While candidate is a modifiable element, and candidate has exactly one
        // child, and that child is also a modifiable element, and candidate is
        // not a simple modifiable element or candidate's specified value for
        // command is not new value, set candidate to its child."
        while (isModifiableElement(candidate, context)
                && candidate.childNodes.length == 1
                && isModifiableElement(candidate.firstChild, context)
                && (!isSimpleModifiableElement(candidate, context)
                    || !valuesEqual(command, command.getSpecifiedValue(candidate, context), newValue))) {
            candidate = candidate.firstChild;
        }

        // "If candidate is a simple modifiable element whose specified value and
        // effective value for command are both new value, and candidate is
        // not the previousSibling/nextSibling of node:"
        if (isSimpleModifiableElement(candidate, context)
                && valuesEqual(command, command.getSpecifiedValue(candidate, context), newValue)
                && valuesEqual(command, getEffectiveValue(candidate, context), newValue)
                && candidate != node[siblingPropName]) {

            // "While candidate has children, insert the first child of
            // candidate into candidate's parent immediately before candidate,
            // preserving ranges."
            moveChildrenPreservingRanges(candidate, candidate.parentNode, dom.getNodeIndex(candidate), false, rangesToPreserve);

            // "Insert candidate into node's parent before node's
            // previousSibling."
            node.parentNode.insertBefore(candidate, node[siblingPropName]);

            // "Append the nextSibling of candidate as the last child of
            // candidate, preserving ranges."
            movePreservingRanges(candidate.nextSibling, candidate, candidate.childNodes.length, rangesToPreserve);
        }
    }

    function forceValue(node, newValue, context) {
        var child, i, len, children, nodeType = node.nodeType;
        var command = context.command, rangesToPreserve = context.rangesToPreserve, options = context.options;

        // "If node's parent is null, abort this algorithm."
        if (!node.parentNode) {
            return;
        }

        // "If new value is null, abort this algorithm."
        if (newValue === null) {
            return;
        }

        // "If node is an Element, Text, Comment, or ProcessingInstruction node,
        // and is not an unwrappable node:"
        if (/^(1|3|4|7)$/.test("" + nodeType) && !isUnwrappable(node, options)) {
		    // "Reorder modifiable descendants of node's previousSibling."
            reorderModifiableDescendants(node, command, newValue, context, "previousSibling");

    		// "Reorder modifiable descendants of node's nextSibling."
            reorderModifiableDescendants(node, command, newValue, context, "nextSibling");

            // "Wrap the one-node list consisting of node, with sibling criteria
            // matching a simple modifiable element whose specified value and
            // effective value for command are both new value, and with new parent
            // instructions returning null."
            wrap([node],
                function(node) {
                    return isSimpleModifiableElement(node, context)
                        && valuesEqual(command, command.getSpecifiedValue(node, context), newValue)
                        && valuesEqual(command, getEffectiveValue(node, context), newValue);
                },
                function() { return null; },
                context
            );
        }

        // "If the effective value of command is new value on node, abort this
        // algorithm."
        if (valuesEqual(command, getEffectiveValue(node, context), newValue)) {
            return;
        }

        // "If node is an unwrappable node:"
        if (isUnwrappable(node, options)) {
            // "Let children be all children of node, omitting any that are
            // Elements whose specified value for command is neither null nor
            // equal to new value."
            children = [];

			if(node && node.childNodes.length == 0 && node.innerHTML == "<br>"){
				var newBr = node.ownerDocument.createElement("br");
				node.appendChild(newBr);
			}

            for (i = 0, len = node.childNodes.length, specifiedValue; i < len; ++i) {
                child = node.childNodes[i];
                if (child.nodeType == 1) {
                    specifiedValue = command.getSpecifiedValue(child, context);
                    if (specifiedValue !== null && !valuesEqual(command, newValue, specifiedValue)) {
                        continue;
                    }
                }
                children.push(child);
            }

            // "Force the value of each Node in children, with command and new
            // value as in this invocation of the algorithm."
            for (i = 0; child = children[i++]; ) {
                forceValue(child, newValue, context);
            }

            // "Abort this algorithm."
            return;
        }

        // "If node is a Comment or ProcessingInstruction, abort this algorithm."
        if (nodeType == 4 || nodeType == 7) {
            return;
        }

        // "If the effective value of command is new value on node, abort this
        // algorithm."
        if (valuesEqual(command, getEffectiveValue(node, context), newValue)) {
            return;
        }

        // "Let new parent be null."
        var newParent = null;

        // "If the CSS styling flag is false:"
        if (!options.styleWithCss && command.createNonCssElement) {
            newParent = command.createNonCssElement(node, newValue, context);
        }

        // "If new parent is null, let new parent be the result of calling
        // createElement("span") on the ownerDocument of node."
        if (!newParent) {
            if (command.createCssElement) {
                newParent = command.createCssElement(dom.getDocument(node), context);
            } else {
                newParent = dom.getDocument(node).createElement("span");
            }
        }

        // "Insert new parent in node's parent before node."
        node.parentNode.insertBefore(newParent, node);

        // "If the effective value of command for new parent is not new value, and
        // the relevant CSS property for command is not null, set that CSS property
        // of new parent to new value (if the new value would be valid)."
        var property = command.relevantCssProperty;
        if (property !== null && !valuesEqual(command, getEffectiveValue(newParent, context), newValue)) {
            newParent.style[property] = newValue;
        }

        // Perform additional styling (for commands such as strikethrough and underline)
        if (command.styleCssElement) {
            command.styleCssElement(newParent, newValue);
        }

        // "Append node to new parent as its last child, preserving ranges."
        movePreservingRanges(node, newParent, newParent.childNodes.length, rangesToPreserve);

        // "If node is an Element and the effective value of command for node is
        // not new value:"
        if (nodeType == 1 && !valuesEqual(command, getEffectiveValue(node, context), newValue)) {
            // "Insert node into the parent of new parent before new parent,
            // preserving ranges."
            movePreservingRanges(node, newParent.parentNode, dom.getNodeIndex(newParent), rangesToPreserve);

            // "Remove new parent from its parent."
            newParent.parentNode.removeChild(newParent);

            // "If new parent is a span, and either a) command is "underline" or
            // "strikethrough", or b) command is "fontSize" and new value is not
            // "xxx-large", or c) command is not "fontSize" and the relevant CSS
            // property for command is not null:"
            if (newParent.tagName.toLowerCase() == "span"
                    && ((command.hasSpecialSpanStyling && command.hasSpecialSpanStyling(newValue))
                    || property !== null)) {

                // "If the relevant CSS property for command is not null, set that
                // CSS property of node to new value."
                if (property !== null) {
                    node.style[property] = newValue;
                }

                if (command.styleSpanChildElement) {
                    command.styleSpanChildElement(node, newValue);
                }

            // "Otherwise:"
            } else {
                // "Let children be all children of node, omitting any that are
                // Elements whose specified value for command is neither null nor
                // equal to new value."
                children = [];
                var specifiedValue;
                for (i = 0, len = node.childNodes.length; i < len; ++i) {
                    child = node.childNodes[i];
                    if (child.nodeType == 1) {
                        specifiedValue = command.getSpecifiedValue(child, context);

                        if (specifiedValue !== null && !valuesEqual(command, newValue, specifiedValue)) {
                            continue;
                        }
                    }
                    children.push(child);
                }

                // "Force the value of each Node in children, with command and new
                // value as in this invocation of the algorithm."
                for (i = 0, len = children.length; i < len; ++i) {
                    forceValue(children[i], newValue, context);
                }
            }
        }
    }

    function pushDownValues(node, context) {
        var command = context.command,
            newValue = context.value,
            options = context.options,
            parent = node.parentNode;

        // "If node's parent is not an Element, abort this algorithm."
        if (!parent || parent.nodeType != 1) {
            return;
        }

        // "If the effective value of command is new value on node, abort this
        // algorithm."
        if (valuesEqual(command, getEffectiveValue(node, context), newValue)) {
            return;
        }

        // "Let current ancestor be node's parent."
        var currentAncestor = parent;

        // "Let ancestor list be a list of Nodes, initially empty."
        var ancestorList = [];

        // "While current ancestor is an editable Element and the effective value
        // of command is not new value on it, append current ancestor to ancestor
        // list, then set current ancestor to its parent."
        while (isEditable(currentAncestor, options) && currentAncestor.nodeType == 1
                && !valuesEqual(command, getEffectiveValue(currentAncestor, context), newValue)) {
            ancestorList.push(currentAncestor);
            currentAncestor = currentAncestor.parentNode;
        }

        // "If ancestor list is empty, abort this algorithm."
        if (ancestorList.length == 0) {
            return;
        }

        // "Let propagated value be the specified value of command on the last
        // member of ancestor list."
        var lastAncestor = ancestorList[ancestorList.length - 1],
            propagatedValue = command.getSpecifiedValue(lastAncestor, context);

        // "If propagated value is null and is not equal to new value, abort this
        // algorithm."
        if (propagatedValue === null && propagatedValue != newValue) {
            return;
        }

        // "If the effective value of command is not new value on the parent of
        // the last member of ancestor list, and new value is not null, abort this
        // algorithm."
        if (newValue !== null && !valuesEqual(command, getEffectiveValue(lastAncestor.parentNode, context), newValue)) {
            return;
        }

        // "While ancestor list is not empty:"
        while (ancestorList.length) {
            // "Let current ancestor be the last member of ancestor list."
            // "Remove the last member from ancestor list."
            currentAncestor = ancestorList.pop();

            // "If the specified value of current ancestor for command is not null,
            // set propagated value to that value."
            if (command.getSpecifiedValue(currentAncestor, context) !== null) {
                propagatedValue = command.getSpecifiedValue(currentAncestor, context);
            }

            // "Let children be the children of current ancestor."
            var children = toArray(currentAncestor.childNodes);

            // "If the specified value of current ancestor for command is not null,
            // clear the value of current ancestor."
            if (command.getSpecifiedValue(currentAncestor, context) !== null) {
                clearValue(currentAncestor, context);
            }

            // "For every child in children:"
            for (var i = 0, child; child = children[i++]; ) {
                // "If child is node, continue with the next child."
                if (child == node) {
                    continue;
                }

                // "If child is an Element whose specified value for command
                // is neither null nor equal to propagated value, continue with the
                // next child."
                if (child.nodeType == 1
                        && command.getSpecifiedValue(child, context) !== null
                        && !valuesEqual(command, propagatedValue, command.getSpecifiedValue(child, context))) {
                    continue;
                }

                // "If child is the last member of ancestor list, continue with the
                // next child."
                if (child == lastAncestor) {
                    continue;
                }

                // "Force the value of child, with command as in this algorithm
                // and new value equal to propagated value."
                forceValue(child, propagatedValue, context);
            }
        }
    }

    function setChildrenNodeValue(node, context) {
        var children = toArray(node.childNodes);
        for (var i = 0, len = children.length; i < len; ++i) {
            setNodeValue(children[i], context);
        }
    }

    function outerHtml(node) {
        if (node === null) {
            return "null";
        } else if (node.nodeType == 3) {
            return node.data;
        } else {
            var div = dom.getDocument(node).createElement("div");
            div.appendChild(node.cloneNode(true));
            return div.innerHTML;
        }
    }

    function getRangeContainerHtml(range) {
        return outerHtml(range.commonAncestorContainer);
    }

    function setNodeValue(node, context) {
        var i, len, child, nodeType = node.nodeType;

        // "If node is a Document, set the value of its Element child (if it has
        // one) and abort this algorithm."
        if (nodeType == 9) {
            for (i = 0; i < node.childNodes.length; ++i) {
                child = node.childNodes[i];
                if (child.nodeType == 1) {
                    setNodeValue(child, context);
                    break;
                }
            }
            return;
        }

        // "If node is a DocumentFragment, let children be a list of its children.
        // Set the value of each member of children, then abort this algorithm."
        if (nodeType == 11) {
            setChildrenNodeValue(node, context);
            return;
        }

        // "If node's parent is null, or if node is a DocumentType, abort this
        // algorithm."
        if (!node.parentNode || nodeType == 10) {
            return;
        }

        // If node is a ignorable text node, abort
        if (isIgnoredNode(node, context.options)) {
            return;
        }

        // "If node is not editable, let children be the children of node. Set the value of each member of children.
        // Abort this algorithm."
        if (!isEditable(node, context.options)) {
            setChildrenNodeValue(node, context);
            return;
        }

        // "If node is an Element:"
        if (nodeType == 1) {
            // "Clear the value of node, and let new nodes be the result."
            var newNodes = clearValue(node, context);

            // "For each new node in new nodes, set the value of new node, with the
            // same inputs as this invocation of the algorithm."
            for (i = 0, len = newNodes.length; i < len; ++i) {
                setNodeValue(newNodes[i], context);
            }

            // "If node's parent is null, abort this algorithm."
            if (!node.parentNode) {
                return;
            }
        }

        // "Push down values on node."
        pushDownValues(node, context);

        // "Force the value of node."
        forceValue(node, context.value, context);

        // "Let children be the children of node. Set the value of each member of children."
        setChildrenNodeValue(node, context);
    }

    // TODO: Add something about whitespace text nodes (option?)
    function getEffectiveTextNodes(range, context) {
        return range.getNodes([3], function(node) {
            return isEffectivelyContained(node, range) && !isIgnoredNode(node, context.options);
        });
    }

    function Command() {}

    Command.prototype = {
        relevantCssProperty: null,

        getSpecifiedValue: function() {
            //throw new module.createError("Command '" + this.name + "' does not implement getSpecifiedValue()");
            return null;
        },

        clearValue: function(/*element*/) {},

        getEffectiveValue: function(element, context) {
            return getComputedStyleProperty(element, this.relevantCssProperty);
        },

        createCssElement: null,

        createNonCssElement: null,

        styleCssElement: null,

        hasSpecialSpanStyling: null,

        styleSpanChildElement: null,

        isSimpleModifiableElement: null,

        defaultOptions: null,

        addDefaultOptions: null,

        valuesEqual: function(val1, val2) {
            return val1 === val2;
        },

        createContext: function(value, rangesToPreserve, options) {
            var defaultOptions = this.defaultOptions;
            if (defaultOptions) {
                for (var i in defaultOptions) {
                    if (defaultOptions.hasOwnProperty(i) && !options.hasOwnProperty(i)) {
                        options[i] = defaultOptions[i];
                    }
                }
            }
            return {
                command: this,
                value: value,
                rangesToPreserve: rangesToPreserve || [],
                options: options || {}
            };
        },

        getSelectionValue: function(/*sel, context*/) {
            return null;
        },

        getNewRangeValue: function(/*range, context*/) {
            return null;
        },

        getNewSelectionValue: function(/*sel, context*/) {
            return null;
        },

        applyToRange: function(doc, value, options, range) {
            var context = this.createContext(value, [range], options);
            context.value = this.getNewRangeValue(range, context);
            this.applyValueToRange(range, context);
        },

        applyToSelection: function(doc, value, options) {
            var win = dom.getWindow(doc);
            var sel = api.getSelection(win);
            var selRanges = sel.getAllRanges();
            var context = this.createContext(value, selRanges, options);
            context.value = this.getNewSelectionValue(sel, context);

            for (var i = 0, len = selRanges.length; i < len; ++i) {
                this.applyValueToRange(selRanges[i], context);
            }

            sel.setRanges(selRanges);
        }
    };

    Command.util = {
        getComputedStyleProperty: getComputedStyleProperty,
        getFurthestAncestor: getFurthestAncestor,
        isContained: isContained,
        isEffectivelyContained: isEffectivelyContained,
        isHtmlNode: isHtmlNode,
        isHtmlElement: isHtmlElement,
        isInlineNode: isInlineNode,
        isUnwrappable: isUnwrappable,
        blockExtend: blockExtend,
        isModifiableElement: isModifiableElement,
        isSimpleModifiableElement: isSimpleModifiableElement,
        moveChildrenPreservingRanges: moveChildrenPreservingRanges,
        replaceWithOwnChildren: replaceWithOwnChildren,
        copyAttributes: copyAttributes,
        getEffectiveValue: getEffectiveValue,
        getEffectiveTextNodes: getEffectiveTextNodes,
        setNodeValue: setNodeValue,
        wrap: wrap,
        decomposeRange: decomposeRange,

        setGlobalOption: function(name, value) {
            globalOptions[name] = value;
        }
    };

    Command.create = function(commandConstructor, properties) {
        var proto = new Command();
        commandConstructor.prototype = proto;

        if (typeof properties == "object") {
            for (var i in properties) {
                if (properties.hasOwnProperty(i)) {
                    proto[i] = properties[i];
                }
            }
        }
    };

    var commandsByName = {};

    api.registerCommand = function(name, command) {
        if (!(command instanceof Command)) {
            throw module.createError("registerCommand(): Object supplied is not a Command");
        }
        commandsByName[name.toLowerCase()] = command;
    };

    function getCommand(name) {
        var lowerName = name.toLowerCase();
        if (commandsByName.hasOwnProperty(lowerName)) {
            return commandsByName[lowerName];
        } else {
            throw module.createError("getCommand(): No command registered with the name '" + name + "'");
        }
    }

    api.execCommand = function(name, options, range) {
        options = options || {};
        var doc = options.hasOwnProperty("document") ? options.document : document;
        var value = options.hasOwnProperty("value") ? options.value : null;
        var command = getCommand(name);
        if (range) {
            command.applyToRange(doc, value, options, range);
        } else {
            command.applyToSelection(doc, value, options);
        }
    };

    api.queryCommandValue = function(name, options, range) {
        options = options || {};
        var win = options.hasOwnProperty("document") ? dom.getWindow(options.document) : window;
        var value = options.hasOwnProperty("value") ? options.value : null;
        var command = getCommand(name);
        var sel = api.getSelection(win);
        var context = command.createContext(value, null, options);

        return command.getSelectionValue(sel, context);
    };

    api.getCommand = getCommand;
    api.Command = Command;

});

rangy.createModule("BoldCommand",  ["Commands"], function(api, module) {
    // api.requireModules( ["Commands"] );

    var dom = api.dom, commandUtil = api.Command.util;

    function BoldCommand() {

    }

    api.Command.create(BoldCommand, {
        relevantCssProperty: "fontWeight",

        defaultOptions: {
            tagName: "b",
            ignoreWhiteSpace: true
        },

        getSpecifiedValue: function(element) {
            return element.style.fontWeight || (/^(strong|b)$/i.test(element.tagName) ? "bold" : null);
        },

        valuesEqual: function(val1, val2) {
            val1 = ("" + val1).toLowerCase();
            val2 = ("" + val2).toLowerCase();
            return val1 == val2
                || (val1 == "bold" && val2 == "700")
                || (val2 == "bold" && val1 == "700")
                || (val1 == "normal" && val2 == "400")
                || (val2 == "normal" && val1 == "400");
        },

        createNonCssElement: function(node, value, context) {
            if (value == "bold" || value == "700") {
                return dom.getDocument(node).createElement(context.options.tagName);
            }

            return null;
        },

        isBoldCssValue: function(value) {
            return /^(bold|700|800|900)$/.test(value);
        },

        getRangeValue: function(range, context) {
            var textNodes = commandUtil.getEffectiveTextNodes(range, context), i = textNodes.length, value;

            if (textNodes.length == 0) {
                return this.isBoldCssValue(commandUtil.getEffectiveValue(range.commonAncestorContainer, context))
            } else {
                while (i--) {
                    value = commandUtil.getEffectiveValue(textNodes[i], context);
                    if (!this.isBoldCssValue(value)) {
                        return false;
                    }
                }
                return true;
            }
        },

        getSelectionValue: function(sel, context) {
            var selRanges = sel.getAllRanges();
            for (var i = 0, len = selRanges.length; i < len; ++i) {
                if (!this.getRangeValue(selRanges[i], context)) {
                    return false;
                }
            }
            return len > 0;
        },

        getNewRangeValue: function(range, context) {
            return this.getRangeValue(range, context) ? "normal" : "bold";
        },

        getNewSelectionValue: function(sel, context) {
            return this.getSelectionValue(sel, context) ? "normal" : "bold";
        },

        applyValueToRange: function(range, context) {
            var decomposed = commandUtil.decomposeRange(range, context.rangesToPreserve);

            for (var i = 0, len = decomposed.length; i < len; ++i) {
                commandUtil.setNodeValue(decomposed[i], context);
            }
        }
    });

    api.registerCommand("bold", new BoldCommand());

});
rangy.createModule("ItalicCommand", ["Commands"], function(api, module) {
    // api.requireModules( ["Commands"] );

    var dom = api.dom, commandUtil = api.Command.util;

    function ItalicCommand() {

    }

    api.Command.create(ItalicCommand, {
        relevantCssProperty: "fontStyle",

        defaultOptions: {
            ignoreWhiteSpace: true
        },

        getSpecifiedValue: function(element) {
            return element.style.fontStyle || (/^(em|i)$/i.test(element.tagName) ? "italic" : null);
        },

        createNonCssElement: function(node, value) {
            return (value == "italic") ? dom.getDocument(node).createElement("em") : null;
        },

        getRangeValue: function(range, context) {
            var textNodes = commandUtil.getEffectiveTextNodes(range, context), i = textNodes.length, value;
            if (textNodes.length == 0) {
                return commandUtil.getEffectiveValue(range.commonAncestorContainer, context) == "italic";
            } else {
                while (i--) {
                    value = commandUtil.getEffectiveValue(textNodes[i], context);
                    if (value != "italic") {
                        return false;
                    }
                }
                return true;
            }
        },

        getSelectionValue: function(sel, context) {
            var selRanges = sel.getAllRanges();
            for (var i = 0, len = selRanges.length; i < len; ++i) {
                if (!this.getRangeValue(selRanges[i], context)) {
                    return false;
                }
            }
            return len > 0;
        },

        getNewRangeValue: function(range, context) {
            return this.getRangeValue(range, context) ? "normal" : "italic";
        },

        getNewSelectionValue: function(sel, context) {
            return this.getSelectionValue(sel, context) ? "normal" : "italic";
        },

        applyValueToRange: function(range, context) {
            var decomposed = commandUtil.decomposeRange(range, context.rangesToPreserve);

            for (var i = 0, len = decomposed.length; i < len; ++i) {
                commandUtil.setNodeValue(decomposed[i], context);
            }
        }
    });

    api.registerCommand("italic", new ItalicCommand());

});

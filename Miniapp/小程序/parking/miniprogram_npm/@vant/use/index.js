module.exports = (function() {
var __MODS__ = {};
var __DEFINE__ = function(modId, func, req) { var m = { exports: {}, _tempexports: {} }; __MODS__[modId] = { status: 0, func: func, req: req, m: m }; };
var __REQUIRE__ = function(modId, source) { if(!__MODS__[modId]) return require(source); if(!__MODS__[modId].status) { var m = __MODS__[modId].m; m._exports = m._tempexports; var desp = Object.getOwnPropertyDescriptor(m, "exports"); if (desp && desp.configurable) Object.defineProperty(m, "exports", { set: function (val) { if(typeof val === "object" && val !== m._exports) { m._exports.__proto__ = val.__proto__; Object.keys(val).forEach(function (k) { m._exports[k] = val[k]; }); } m._tempexports = val }, get: function () { return m._tempexports; } }); __MODS__[modId].status = 1; __MODS__[modId].func(__MODS__[modId].req, m, m.exports); } return __MODS__[modId].m.exports; };
var __REQUIRE_WILDCARD__ = function(obj) { if(obj && obj.__esModule) { return obj; } else { var newObj = {}; if(obj != null) { for(var k in obj) { if (Object.prototype.hasOwnProperty.call(obj, k)) newObj[k] = obj[k]; } } newObj.default = obj; return newObj; } };
var __REQUIRE_DEFAULT__ = function(obj) { return obj && obj.__esModule ? obj.default : obj; };
__DEFINE__(1630394574266, function(require, module, exports) {


exports.__esModule = true;

var _utils = require("./utils");

Object.keys(_utils).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _utils[key]) return;
  exports[key] = _utils[key];
});

var _useRect = require("./useRect");

Object.keys(_useRect).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useRect[key]) return;
  exports[key] = _useRect[key];
});

var _useToggle = require("./useToggle");

Object.keys(_useToggle).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useToggle[key]) return;
  exports[key] = _useToggle[key];
});

var _useRelation = require("./useRelation");

Object.keys(_useRelation).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useRelation[key]) return;
  exports[key] = _useRelation[key];
});

var _useCountDown = require("./useCountDown");

Object.keys(_useCountDown).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useCountDown[key]) return;
  exports[key] = _useCountDown[key];
});

var _useClickAway = require("./useClickAway");

Object.keys(_useClickAway).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useClickAway[key]) return;
  exports[key] = _useClickAway[key];
});

var _useWindowSize = require("./useWindowSize");

Object.keys(_useWindowSize).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useWindowSize[key]) return;
  exports[key] = _useWindowSize[key];
});

var _useScrollParent = require("./useScrollParent");

Object.keys(_useScrollParent).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useScrollParent[key]) return;
  exports[key] = _useScrollParent[key];
});

var _useEventListener = require("./useEventListener");

Object.keys(_useEventListener).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useEventListener[key]) return;
  exports[key] = _useEventListener[key];
});

var _usePageVisibility = require("./usePageVisibility");

Object.keys(_usePageVisibility).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _usePageVisibility[key]) return;
  exports[key] = _usePageVisibility[key];
});

var _onMountedOrActivated = require("./onMountedOrActivated");

Object.keys(_onMountedOrActivated).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _onMountedOrActivated[key]) return;
  exports[key] = _onMountedOrActivated[key];
});
}, function(modId) {var map = {"./utils":1630394574267,"./useRect":1630394574268,"./useToggle":1630394574269,"./useRelation":1630394574270,"./useCountDown":1630394574273,"./useClickAway":1630394574274,"./useWindowSize":1630394574277,"./useScrollParent":1630394574278,"./useEventListener":1630394574275,"./usePageVisibility":1630394574279,"./onMountedOrActivated":1630394574276}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574267, function(require, module, exports) {


exports.__esModule = true;
exports.raf = raf;
exports.cancelRaf = cancelRaf;
exports.doubleRaf = doubleRaf;
exports.inBrowser = void 0;
var inBrowser = typeof window !== 'undefined';
exports.inBrowser = inBrowser;
var root = inBrowser ? window : global;
var prev = Date.now();

function rafPolyfill(fn) {
  var curr = Date.now();
  var ms = Math.max(0, 16 - (curr - prev));
  var id = setTimeout(fn, ms);
  prev = curr + ms;
  return id;
}

function raf(fn) {
  var requestAnimationFrame = root.requestAnimationFrame || rafPolyfill;
  return requestAnimationFrame.call(root, fn);
}

function cancelRaf(id) {
  var cancelAnimationFrame = root.cancelAnimationFrame || root.clearTimeout;
  cancelAnimationFrame.call(root, id);
} // double raf for animation


function doubleRaf(fn) {
  raf(() => raf(fn));
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574268, function(require, module, exports) {


exports.__esModule = true;
exports.useRect = void 0;

var _vue = require("vue");

function isWindow(val) {
  return val === window;
}

function makeDOMRect(width, height) {
  return {
    top: 0,
    left: 0,
    right: width,
    bottom: height,
    width,
    height
  };
}

var useRect = elementOrRef => {
  var element = (0, _vue.unref)(elementOrRef);

  if (isWindow(element)) {
    var width = element.innerWidth;
    var height = element.innerHeight;
    return makeDOMRect(width, height);
  }

  if (element && element.getBoundingClientRect) {
    return element.getBoundingClientRect();
  }

  return makeDOMRect(0, 0);
};

exports.useRect = useRect;
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574269, function(require, module, exports) {


exports.__esModule = true;
exports.useToggle = useToggle;

var _vue = require("vue");

function useToggle(defaultValue = false) {
  var state = (0, _vue.ref)(defaultValue);

  var toggle = (value = !state.value) => {
    state.value = value;
  };

  return [state, toggle];
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574270, function(require, module, exports) {


exports.__esModule = true;

var _useParent = require("./useParent");

Object.keys(_useParent).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useParent[key]) return;
  exports[key] = _useParent[key];
});

var _useChildren = require("./useChildren");

Object.keys(_useChildren).forEach(function (key) {
  if (key === "default" || key === "__esModule") return;
  if (key in exports && exports[key] === _useChildren[key]) return;
  exports[key] = _useChildren[key];
});
}, function(modId) { var map = {"./useParent":1630394574271,"./useChildren":1630394574272}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574271, function(require, module, exports) {


exports.__esModule = true;
exports.useParent = useParent;

var _vue = require("vue");

function useParent(key) {
  var parent = (0, _vue.inject)(key, null);

  if (parent) {
    var instance = (0, _vue.getCurrentInstance)();
    var {
      link: _link,
      unlink: _unlink,
      internalChildren
    } = parent;

    _link(instance);

    (0, _vue.onUnmounted)(() => _unlink(instance));
    var index = (0, _vue.computed)(() => internalChildren.indexOf(instance));
    return {
      parent,
      index
    };
  }

  return {
    parent: null,
    index: (0, _vue.ref)(-1)
  };
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574272, function(require, module, exports) {


exports.__esModule = true;
exports.flattenVNodes = flattenVNodes;
exports.sortChildren = sortChildren;
exports.useChildren = useChildren;

var _vue = require("vue");

function flattenVNodes(children) {
  var result = [];

  var traverse = children => {
    if (Array.isArray(children)) {
      children.forEach(child => {
        if ((0, _vue.isVNode)(child)) {
          var _child$component;

          result.push(child);

          if ((_child$component = child.component) != null && _child$component.subTree) {
            traverse(child.component.subTree.children);
          }

          if (child.children) {
            traverse(child.children);
          }
        }
      });
    }
  };

  traverse(children);
  return result;
} // sort children instances by vnodes order


function sortChildren(parent, publicChildren, internalChildren) {
  var vnodes = flattenVNodes(parent.subTree.children);
  internalChildren.sort((a, b) => vnodes.indexOf(a.vnode) - vnodes.indexOf(b.vnode));
  var orderedPublicChildren = internalChildren.map(item => item.proxy);
  publicChildren.sort((a, b) => {
    var indexA = orderedPublicChildren.indexOf(a);
    var indexB = orderedPublicChildren.indexOf(b);
    return indexA - indexB;
  });
}

function useChildren(key) {
  var publicChildren = (0, _vue.reactive)([]);
  var internalChildren = (0, _vue.reactive)([]);
  var parent = (0, _vue.getCurrentInstance)();

  var linkChildren = value => {
    var link = child => {
      if (child.proxy) {
        internalChildren.push(child);
        publicChildren.push(child.proxy);
        sortChildren(parent, publicChildren, internalChildren);
      }
    };

    var unlink = child => {
      var index = internalChildren.indexOf(child);
      publicChildren.splice(index, 1);
      internalChildren.splice(index, 1);
    };

    (0, _vue.provide)(key, Object.assign({
      link,
      unlink,
      children: publicChildren,
      internalChildren
    }, value));
  };

  return {
    children: publicChildren,
    linkChildren
  };
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574273, function(require, module, exports) {


exports.__esModule = true;
exports.useCountDown = useCountDown;

var _vue = require("vue");

var _utils = require("../utils");

var SECOND = 1000;
var MINUTE = 60 * SECOND;
var HOUR = 60 * MINUTE;
var DAY = 24 * HOUR;

function parseTime(time) {
  var days = Math.floor(time / DAY);
  var hours = Math.floor(time % DAY / HOUR);
  var minutes = Math.floor(time % HOUR / MINUTE);
  var seconds = Math.floor(time % MINUTE / SECOND);
  var milliseconds = Math.floor(time % SECOND);
  return {
    total: time,
    days,
    hours,
    minutes,
    seconds,
    milliseconds
  };
}

function isSameSecond(time1, time2) {
  return Math.floor(time1 / 1000) === Math.floor(time2 / 1000);
}

function useCountDown(options) {
  var rafId;
  var endTime;
  var counting;
  var deactivated;
  var remain = (0, _vue.ref)(options.time);
  var current = (0, _vue.computed)(() => parseTime(remain.value));

  var pause = () => {
    counting = false;
    (0, _utils.cancelRaf)(rafId);
  };

  var getCurrentRemain = () => Math.max(endTime - Date.now(), 0);

  var setRemain = value => {
    remain.value = value;
    options.onChange == null ? void 0 : options.onChange(current.value);

    if (value === 0) {
      pause();
      options.onFinish == null ? void 0 : options.onFinish();
    }
  };

  var microTick = () => {
    rafId = (0, _utils.raf)(() => {
      // in case of call reset immediately after finish
      if (counting) {
        setRemain(getCurrentRemain());

        if (remain.value > 0) {
          microTick();
        }
      }
    });
  };

  var macroTick = () => {
    rafId = (0, _utils.raf)(() => {
      // in case of call reset immediately after finish
      if (counting) {
        var remainRemain = getCurrentRemain();

        if (!isSameSecond(remainRemain, remain.value) || remainRemain === 0) {
          setRemain(remainRemain);
        }

        if (remain.value > 0) {
          macroTick();
        }
      }
    });
  };

  var tick = () => {
    // should not start counting in server
    // see: https://github.com/youzan/vant/issues/7807
    if (!_utils.inBrowser) {
      return;
    }

    if (options.millisecond) {
      microTick();
    } else {
      macroTick();
    }
  };

  var start = () => {
    if (!counting) {
      endTime = Date.now() + remain.value;
      counting = true;
      tick();
    }
  };

  var reset = (totalTime = options.time) => {
    pause();
    remain.value = totalTime;
  };

  (0, _vue.onBeforeUnmount)(pause);
  (0, _vue.onActivated)(() => {
    if (deactivated) {
      counting = true;
      deactivated = false;
      tick();
    }
  });
  (0, _vue.onDeactivated)(() => {
    if (counting) {
      pause();
      deactivated = true;
    }
  });
  return {
    start,
    pause,
    reset,
    current
  };
}
}, function(modId) { var map = {"../utils":1630394574267}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574274, function(require, module, exports) {


exports.__esModule = true;
exports.useClickAway = useClickAway;

var _vue = require("vue");

var _utils = require("../utils");

var _useEventListener = require("../useEventListener");

function useClickAway(target, listener, options = {}) {
  if (!_utils.inBrowser) {
    return;
  }

  var {
    eventName = 'click'
  } = options;

  var onClick = event => {
    var element = (0, _vue.unref)(target);

    if (element && !element.contains(event.target)) {
      listener(event);
    }
  };

  (0, _useEventListener.useEventListener)(eventName, onClick, {
    target: document
  });
}
}, function(modId) { var map = {"../utils":1630394574267,"../useEventListener":1630394574275}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574275, function(require, module, exports) {


exports.__esModule = true;
exports.useEventListener = useEventListener;
exports.supportsPassive = void 0;

var _vue = require("vue");

var _onMountedOrActivated = require("../onMountedOrActivated");

var _utils = require("../utils");

// eslint-disable-next-line
var supportsPassive = false;
exports.supportsPassive = supportsPassive;

if (_utils.inBrowser) {
  try {
    var opts = {};
    Object.defineProperty(opts, 'passive', {
      get() {
        exports.supportsPassive = supportsPassive = true;
      }

    });
    window.addEventListener('test-passive', null, opts); // eslint-disable-next-line no-empty
  } catch (e) {}
}

function useEventListener(type, listener, options = {}) {
  if (!_utils.inBrowser) {
    return;
  }

  var {
    target = window,
    passive = false,
    capture = false
  } = options;
  var attached;

  var add = target => {
    var element = (0, _vue.unref)(target);

    if (element && !attached) {
      element.addEventListener(type, listener, supportsPassive ? {
        capture,
        passive
      } : capture);
      attached = true;
    }
  };

  var remove = target => {
    var element = (0, _vue.unref)(target);

    if (element && attached) {
      element.removeEventListener(type, listener, capture);
      attached = false;
    }
  };

  (0, _vue.onUnmounted)(() => remove(target));
  (0, _vue.onDeactivated)(() => remove(target));
  (0, _onMountedOrActivated.onMountedOrActivated)(() => add(target));

  if ((0, _vue.isRef)(target)) {
    (0, _vue.watch)(target, (val, oldVal) => {
      remove(oldVal);
      add(val);
    });
  }
}
}, function(modId) { var map = {"../onMountedOrActivated":1630394574276,"../utils":1630394574267}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574276, function(require, module, exports) {


exports.__esModule = true;
exports.onMountedOrActivated = onMountedOrActivated;

var _vue = require("vue");

function onMountedOrActivated(hook) {
  var mounted;
  (0, _vue.onMounted)(() => {
    hook();
    (0, _vue.nextTick)(() => {
      mounted = true;
    });
  });
  (0, _vue.onActivated)(() => {
    if (mounted) {
      hook();
    }
  });
}
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574277, function(require, module, exports) {


exports.__esModule = true;
exports.useWindowSize = useWindowSize;

var _vue = require("vue");

var _utils = require("../utils");

var _useEventListener = require("../useEventListener");

function useWindowSize() {
  var width = (0, _vue.ref)(_utils.inBrowser ? window.innerWidth : 0);
  var height = (0, _vue.ref)(_utils.inBrowser ? window.innerHeight : 0);

  var onResize = () => {
    width.value = window.innerWidth;
    height.value = window.innerHeight;
  };

  (0, _useEventListener.useEventListener)('resize', onResize);
  (0, _useEventListener.useEventListener)('orientationchange', onResize);
  return {
    width,
    height
  };
}
}, function(modId) { var map = {"../utils":1630394574267,"../useEventListener":1630394574275}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574278, function(require, module, exports) {


exports.__esModule = true;
exports.getScrollParent = getScrollParent;
exports.useScrollParent = useScrollParent;

var _vue = require("vue");

var _utils = require("../utils");

var overflowScrollReg = /scroll|auto/i;
var defaultRoot = _utils.inBrowser ? window : undefined;

function isElement(node) {
  var ELEMENT_NODE_TYPE = 1;
  return node.tagName !== 'HTML' && node.tagName !== 'BODY' && node.nodeType === ELEMENT_NODE_TYPE;
} // https://github.com/youzan/vant/issues/3823


function getScrollParent(el, root = defaultRoot) {
  var node = el;

  while (node && node !== root && isElement(node)) {
    var {
      overflowY
    } = window.getComputedStyle(node);

    if (overflowScrollReg.test(overflowY)) {
      return node;
    }

    node = node.parentNode;
  }

  return root;
}

function useScrollParent(el, root = defaultRoot) {
  var scrollParent = (0, _vue.ref)();
  (0, _vue.onMounted)(() => {
    if (el.value) {
      scrollParent.value = getScrollParent(el.value, root);
    }
  });
  return scrollParent;
}
}, function(modId) { var map = {"../utils":1630394574267}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574279, function(require, module, exports) {


exports.__esModule = true;
exports.usePageVisibility = usePageVisibility;

var _vue = require("vue");

var _utils = require("../utils");

var _useEventListener = require("../useEventListener");

function usePageVisibility() {
  var visibility = (0, _vue.ref)('visible');

  var setVisibility = () => {
    if (_utils.inBrowser) {
      visibility.value = document.hidden ? 'hidden' : 'visible';
    }
  };

  setVisibility();
  (0, _useEventListener.useEventListener)('visibilitychange', setVisibility);
  return visibility;
}
}, function(modId) { var map = {"../utils":1630394574267,"../useEventListener":1630394574275}; return __REQUIRE__(map[modId], modId); })
return __REQUIRE__(1630394574266);
})()
//miniprogram-npm-outsideDeps=["vue"]
//# sourceMappingURL=index.js.map
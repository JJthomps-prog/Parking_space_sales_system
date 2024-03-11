module.exports = (function() {
var __MODS__ = {};
var __DEFINE__ = function(modId, func, req) { var m = { exports: {}, _tempexports: {} }; __MODS__[modId] = { status: 0, func: func, req: req, m: m }; };
var __REQUIRE__ = function(modId, source) { if(!__MODS__[modId]) return require(source); if(!__MODS__[modId].status) { var m = __MODS__[modId].m; m._exports = m._tempexports; var desp = Object.getOwnPropertyDescriptor(m, "exports"); if (desp && desp.configurable) Object.defineProperty(m, "exports", { set: function (val) { if(typeof val === "object" && val !== m._exports) { m._exports.__proto__ = val.__proto__; Object.keys(val).forEach(function (k) { m._exports[k] = val[k]; }); } m._tempexports = val }, get: function () { return m._tempexports; } }); __MODS__[modId].status = 1; __MODS__[modId].func(__MODS__[modId].req, m, m.exports); } return __MODS__[modId].m.exports; };
var __REQUIRE_WILDCARD__ = function(obj) { if(obj && obj.__esModule) { return obj; } else { var newObj = {}; if(obj != null) { for(var k in obj) { if (Object.prototype.hasOwnProperty.call(obj, k)) newObj[k] = obj[k]; } } newObj.default = obj; return newObj; } };
var __REQUIRE_DEFAULT__ = function(obj) { return obj && obj.__esModule ? obj.default : obj; };
__DEFINE__(1630394574258, function(require, module, exports) {


exports.__esModule = true;
exports.Lazyload = void 0;

var _lazy = _interopRequireDefault(require("./lazy"));

var _lazyComponent = _interopRequireDefault(require("./lazy-component"));

var _lazyContainer = _interopRequireDefault(require("./lazy-container"));

var _lazyImage = _interopRequireDefault(require("./lazy-image"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var Lazyload = {
  /*
   * install function
   * @param  {App} app
   * @param  {object} options lazyload options
   */
  install(app, options = {}) {
    var LazyClass = (0, _lazy.default)();
    var lazy = new LazyClass(options);
    var lazyContainer = new _lazyContainer.default({
      lazy
    });
    app.config.globalProperties.$Lazyload = lazy;

    if (options.lazyComponent) {
      app.component('LazyComponent', (0, _lazyComponent.default)(lazy));
    }

    if (options.lazyImage) {
      app.component('LazyImage', (0, _lazyImage.default)(lazy));
    }

    app.directive('lazy', {
      beforeMount: lazy.add.bind(lazy),
      updated: lazy.update.bind(lazy),
      unmounted: lazy.remove.bind(lazy)
    });
    app.directive('lazy-container', {
      beforeMount: lazyContainer.bind.bind(lazyContainer),
      updated: lazyContainer.update.bind(lazyContainer),
      unmounted: lazyContainer.unbind.bind(lazyContainer)
    });
  }

};
exports.Lazyload = Lazyload;
}, function(modId) {var map = {"./lazy":1630394574259,"./lazy-component":1630394574262,"./lazy-container":1630394574263,"./lazy-image":1630394574264}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574259, function(require, module, exports) {


exports.__esModule = true;
exports.default = _default;

var _vue = require("vue");

var _util = require("./util");

var _listener = _interopRequireDefault(require("./listener"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

var DEFAULT_URL = 'data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7';
var DEFAULT_EVENTS = ['scroll', 'wheel', 'mousewheel', 'resize', 'animationend', 'transitionend', 'touchmove'];
var DEFAULT_OBSERVER_OPTIONS = {
  rootMargin: '0px',
  threshold: 0
};

function _default() {
  return class Lazy {
    constructor({
      preLoad,
      error,
      throttleWait,
      preLoadTop,
      dispatchEvent,
      loading,
      attempt,
      silent = true,
      scale,
      listenEvents,
      filter,
      adapter,
      observer,
      observerOptions
    }) {
      this.mode = _util.modeType.event;
      this.ListenerQueue = [];
      this.TargetIndex = 0;
      this.TargetQueue = [];
      this.options = {
        silent,
        dispatchEvent: !!dispatchEvent,
        throttleWait: throttleWait || 200,
        preLoad: preLoad || 1.3,
        preLoadTop: preLoadTop || 0,
        error: error || DEFAULT_URL,
        loading: loading || DEFAULT_URL,
        attempt: attempt || 3,
        scale: scale || (0, _util.getDPR)(scale),
        ListenEvents: listenEvents || DEFAULT_EVENTS,
        hasbind: false,
        supportWebp: (0, _util.supportWebp)(),
        filter: filter || {},
        adapter: adapter || {},
        observer: !!observer,
        observerOptions: observerOptions || DEFAULT_OBSERVER_OPTIONS
      };

      this._initEvent();

      this._imageCache = new _util.ImageCache({
        max: 200
      });
      this.lazyLoadHandler = (0, _util.throttle)(this._lazyLoadHandler.bind(this), this.options.throttleWait);
      this.setMode(this.options.observer ? _util.modeType.observer : _util.modeType.event);
    }
    /**
     * update config
     * @param  {Object} config params
     * @return
     */


    config(options = {}) {
      Object.assign(this.options, options);
    }
    /**
     * output listener's load performance
     * @return {Array}
     */


    performance() {
      return this.ListenerQueue.map(item => item.performance());
    }
    /*
     * add lazy component to queue
     * @param  {Vue} vm lazy component instance
     * @return
     */


    addLazyBox(vm) {
      this.ListenerQueue.push(vm);

      if (_util.inBrowser) {
        this._addListenerTarget(window);

        this._observer && this._observer.observe(vm.el);

        if (vm.$el && vm.$el.parentNode) {
          this._addListenerTarget(vm.$el.parentNode);
        }
      }
    }
    /*
     * add image listener to queue
     * @param  {DOM} el
     * @param  {object} binding vue directive binding
     * @param  {vnode} vnode vue directive vnode
     * @return
     */


    add(el, binding, vnode) {
      if (this.ListenerQueue.some(item => item.el === el)) {
        this.update(el, binding);
        return (0, _vue.nextTick)(this.lazyLoadHandler);
      }

      var value = this._valueFormatter(binding.value);

      var {
        src
      } = value;
      (0, _vue.nextTick)(() => {
        src = (0, _util.getBestSelectionFromSrcset)(el, this.options.scale) || src;
        this._observer && this._observer.observe(el);
        var container = Object.keys(binding.modifiers)[0];
        var $parent;

        if (container) {
          $parent = vnode.context.$refs[container]; // if there is container passed in, try ref first, then fallback to getElementById to support the original usage

          $parent = $parent ? $parent.$el || $parent : document.getElementById(container);
        }

        if (!$parent) {
          $parent = (0, _util.scrollParent)(el);
        }

        var newListener = new _listener.default({
          bindType: binding.arg,
          $parent,
          el,
          src,
          loading: value.loading,
          error: value.error,
          cors: value.cors,
          elRenderer: this._elRenderer.bind(this),
          options: this.options,
          imageCache: this._imageCache
        });
        this.ListenerQueue.push(newListener);

        if (_util.inBrowser) {
          this._addListenerTarget(window);

          this._addListenerTarget($parent);
        }

        this.lazyLoadHandler();
        (0, _vue.nextTick)(() => this.lazyLoadHandler());
      });
    }
    /**
     * update image src
     * @param  {DOM} el
     * @param  {object} vue directive binding
     * @return
     */


    update(el, binding, vnode) {
      var value = this._valueFormatter(binding.value);

      var {
        src
      } = value;
      src = (0, _util.getBestSelectionFromSrcset)(el, this.options.scale) || src;
      var exist = (0, _util.find)(this.ListenerQueue, item => item.el === el);

      if (!exist) {
        this.add(el, binding, vnode);
      } else {
        exist.update({
          src,
          error: value.error,
          loading: value.loading
        });
      }

      if (this._observer) {
        this._observer.unobserve(el);

        this._observer.observe(el);
      }

      this.lazyLoadHandler();
      (0, _vue.nextTick)(() => this.lazyLoadHandler());
    }
    /**
     * remove listener form list
     * @param  {DOM} el
     * @return
     */


    remove(el) {
      if (!el) return;
      this._observer && this._observer.unobserve(el);
      var existItem = (0, _util.find)(this.ListenerQueue, item => item.el === el);

      if (existItem) {
        this._removeListenerTarget(existItem.$parent);

        this._removeListenerTarget(window);

        (0, _util.remove)(this.ListenerQueue, existItem);
        existItem.$destroy();
      }
    }
    /*
     * remove lazy components form list
     * @param  {Vue} vm Vue instance
     * @return
     */


    removeComponent(vm) {
      if (!vm) return;
      (0, _util.remove)(this.ListenerQueue, vm);
      this._observer && this._observer.unobserve(vm.el);

      if (vm.$parent && vm.$el.parentNode) {
        this._removeListenerTarget(vm.$el.parentNode);
      }

      this._removeListenerTarget(window);
    }

    setMode(mode) {
      if (!_util.hasIntersectionObserver && mode === _util.modeType.observer) {
        mode = _util.modeType.event;
      }

      this.mode = mode; // event or observer

      if (mode === _util.modeType.event) {
        if (this._observer) {
          this.ListenerQueue.forEach(listener => {
            this._observer.unobserve(listener.el);
          });
          this._observer = null;
        }

        this.TargetQueue.forEach(target => {
          this._initListen(target.el, true);
        });
      } else {
        this.TargetQueue.forEach(target => {
          this._initListen(target.el, false);
        });

        this._initIntersectionObserver();
      }
    }
    /*
     *** Private functions ***
     */

    /*
     * add listener target
     * @param  {DOM} el listener target
     * @return
     */


    _addListenerTarget(el) {
      if (!el) return;
      var target = (0, _util.find)(this.TargetQueue, target => target.el === el);

      if (!target) {
        target = {
          el,
          id: ++this.TargetIndex,
          childrenCount: 1,
          listened: true
        };
        this.mode === _util.modeType.event && this._initListen(target.el, true);
        this.TargetQueue.push(target);
      } else {
        target.childrenCount++;
      }

      return this.TargetIndex;
    }
    /*
     * remove listener target or reduce target childrenCount
     * @param  {DOM} el or window
     * @return
     */


    _removeListenerTarget(el) {
      this.TargetQueue.forEach((target, index) => {
        if (target.el === el) {
          target.childrenCount--;

          if (!target.childrenCount) {
            this._initListen(target.el, false);

            this.TargetQueue.splice(index, 1);
            target = null;
          }
        }
      });
    }
    /*
     * add or remove eventlistener
     * @param  {DOM} el DOM or Window
     * @param  {boolean} start flag
     * @return
     */


    _initListen(el, start) {
      this.options.ListenEvents.forEach(evt => _util._[start ? 'on' : 'off'](el, evt, this.lazyLoadHandler));
    }

    _initEvent() {
      this.Event = {
        listeners: {
          loading: [],
          loaded: [],
          error: []
        }
      };

      this.$on = (event, func) => {
        if (!this.Event.listeners[event]) this.Event.listeners[event] = [];
        this.Event.listeners[event].push(func);
      };

      this.$once = (event, func) => {
        var on = (...args) => {
          this.$off(event, on);
          func.apply(this, args);
        };

        this.$on(event, on);
      };

      this.$off = (event, func) => {
        if (!func) {
          if (!this.Event.listeners[event]) return;
          this.Event.listeners[event].length = 0;
          return;
        }

        (0, _util.remove)(this.Event.listeners[event], func);
      };

      this.$emit = (event, context, inCache) => {
        if (!this.Event.listeners[event]) return;
        this.Event.listeners[event].forEach(func => func(context, inCache));
      };
    }
    /**
     * find nodes which in viewport and trigger load
     * @return
     */


    _lazyLoadHandler() {
      var freeList = [];
      this.ListenerQueue.forEach(listener => {
        if (!listener.el || !listener.el.parentNode) {
          freeList.push(listener);
        }

        var catIn = listener.checkInView();
        if (!catIn) return;
        listener.load();
      });
      freeList.forEach(item => {
        (0, _util.remove)(this.ListenerQueue, item);
        item.$destroy();
      });
    }
    /**
     * init IntersectionObserver
     * set mode to observer
     * @return
     */


    _initIntersectionObserver() {
      if (!_util.hasIntersectionObserver) {
        return;
      }

      this._observer = new IntersectionObserver(this._observerHandler.bind(this), this.options.observerOptions);

      if (this.ListenerQueue.length) {
        this.ListenerQueue.forEach(listener => {
          this._observer.observe(listener.el);
        });
      }
    }
    /**
     * init IntersectionObserver
     * @return
     */


    _observerHandler(entries) {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          this.ListenerQueue.forEach(listener => {
            if (listener.el === entry.target) {
              if (listener.state.loaded) return this._observer.unobserve(listener.el);
              listener.load();
            }
          });
        }
      });
    }
    /**
     * set element attribute with image'url and state
     * @param  {object} lazyload listener object
     * @param  {string} state will be rendered
     * @param  {bool} inCache  is rendered from cache
     * @return
     */


    _elRenderer(listener, state, cache) {
      if (!listener.el) return;
      var {
        el,
        bindType
      } = listener;
      var src;

      switch (state) {
        case 'loading':
          src = listener.loading;
          break;

        case 'error':
          src = listener.error;
          break;

        default:
          ({
            src
          } = listener);
          break;
      }

      if (bindType) {
        el.style[bindType] = 'url("' + src + '")';
      } else if (el.getAttribute('src') !== src) {
        el.setAttribute('src', src);
      }

      el.setAttribute('lazy', state);
      this.$emit(state, listener, cache);
      this.options.adapter[state] && this.options.adapter[state](listener, this.options);

      if (this.options.dispatchEvent) {
        var event = new _util.CustomEvent(state, {
          detail: listener
        });
        el.dispatchEvent(event);
      }
    }
    /**
     * generate loading loaded error image url
     * @param {string} image's src
     * @return {object} image's loading, loaded, error url
     */


    _valueFormatter(value) {
      var src = value;
      var {
        loading,
        error
      } = this.options; // value is object

      if ((0, _util.isObject)(value)) {
        if (!value.src && !this.options.silent) console.error('Vue Lazyload warning: miss src with ' + value);
        ({
          src
        } = value);
        loading = value.loading || this.options.loading;
        error = value.error || this.options.error;
      }

      return {
        src,
        loading,
        error
      };
    }

  };
}
}, function(modId) { var map = {"./util":1630394574260,"./listener":1630394574261}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574260, function(require, module, exports) {


exports.__esModule = true;
exports.remove = remove;
exports.find = find;
exports.noop = noop;
exports.isObject = isObject;
exports.throttle = throttle;
exports.supportWebp = supportWebp;
exports.getBestSelectionFromSrcset = getBestSelectionFromSrcset;
exports.loadImageAsync = exports.scrollParent = exports.getDPR = exports._ = exports.CustomEvent = exports.inBrowser = exports.ImageCache = exports.modeType = exports.hasIntersectionObserver = void 0;
var inBrowser = typeof window !== 'undefined' && window !== null;
exports.inBrowser = inBrowser;

function checkIntersectionObserver() {
  if (inBrowser && 'IntersectionObserver' in window && 'IntersectionObserverEntry' in window && 'intersectionRatio' in window.IntersectionObserverEntry.prototype) {
    // Minimal polyfill for Edge 15's lack of `isIntersecting`
    // See: https://github.com/w3c/IntersectionObserver/issues/211
    if (!('isIntersecting' in window.IntersectionObserverEntry.prototype)) {
      Object.defineProperty(window.IntersectionObserverEntry.prototype, 'isIntersecting', {
        get() {
          return this.intersectionRatio > 0;
        }

      });
    }

    return true;
  }

  return false;
}

var hasIntersectionObserver = checkIntersectionObserver();
exports.hasIntersectionObserver = hasIntersectionObserver;
var modeType = {
  event: 'event',
  observer: 'observer'
}; // CustomEvent polyfill

exports.modeType = modeType;

var CustomEvent = function () {
  if (!inBrowser) return;
  if (typeof window.CustomEvent === 'function') return window.CustomEvent;

  function CustomEvent(event, params) {
    params = params || {
      bubbles: false,
      cancelable: false,
      detail: undefined
    };
    var evt = document.createEvent('CustomEvent');
    evt.initCustomEvent(event, params.bubbles, params.cancelable, params.detail);
    return evt;
  }

  CustomEvent.prototype = window.Event.prototype;
  return CustomEvent;
}();

exports.CustomEvent = CustomEvent;

function remove(arr, item) {
  if (!arr.length) return;
  var index = arr.indexOf(item);
  if (index > -1) return arr.splice(index, 1);
}

function getBestSelectionFromSrcset(el, scale) {
  if (el.tagName !== 'IMG' || !el.getAttribute('data-srcset')) return;
  var options = el.getAttribute('data-srcset');
  var container = el.parentNode;
  var containerWidth = container.offsetWidth * scale;
  var spaceIndex;
  var tmpSrc;
  var tmpWidth;
  options = options.trim().split(',');
  var result = options.map(item => {
    item = item.trim();
    spaceIndex = item.lastIndexOf(' ');

    if (spaceIndex === -1) {
      tmpSrc = item;
      tmpWidth = 999998;
    } else {
      tmpSrc = item.substr(0, spaceIndex);
      tmpWidth = parseInt(item.substr(spaceIndex + 1, item.length - spaceIndex - 2), 10);
    }

    return [tmpWidth, tmpSrc];
  });
  result.sort(function (a, b) {
    if (a[0] < b[0]) {
      return 1;
    }

    if (a[0] > b[0]) {
      return -1;
    }

    if (a[0] === b[0]) {
      if (b[1].indexOf('.webp', b[1].length - 5) !== -1) {
        return 1;
      }

      if (a[1].indexOf('.webp', a[1].length - 5) !== -1) {
        return -1;
      }
    }

    return 0;
  });
  var bestSelectedSrc = '';
  var tmpOption;

  for (var i = 0; i < result.length; i++) {
    tmpOption = result[i];
    bestSelectedSrc = tmpOption[1];
    var next = result[i + 1];

    if (next && next[0] < containerWidth) {
      bestSelectedSrc = tmpOption[1];
      break;
    } else if (!next) {
      bestSelectedSrc = tmpOption[1];
      break;
    }
  }

  return bestSelectedSrc;
}

function find(arr, fn) {
  var item;

  for (var i = 0, len = arr.length; i < len; i++) {
    if (fn(arr[i])) {
      item = arr[i];
      break;
    }
  }

  return item;
}

var getDPR = (scale = 1) => inBrowser ? window.devicePixelRatio || scale : scale;

exports.getDPR = getDPR;

function supportWebp() {
  if (!inBrowser) return false;
  var support = true;

  try {
    var elem = document.createElement('canvas');

    if (elem.getContext && elem.getContext('2d')) {
      support = elem.toDataURL('image/webp').indexOf('data:image/webp') === 0;
    }
  } catch (err) {
    support = false;
  }

  return support;
}

function throttle(action, delay) {
  var timeout = null;
  var lastRun = 0;
  return function (...args) {
    if (timeout) {
      return;
    }

    var elapsed = Date.now() - lastRun;

    var runCallback = () => {
      lastRun = Date.now();
      timeout = false;
      action.apply(this, args);
    };

    if (elapsed >= delay) {
      runCallback();
    } else {
      timeout = setTimeout(runCallback, delay);
    }
  };
}

function testSupportsPassive() {
  if (!inBrowser) return;
  var support = false;

  try {
    var opts = Object.defineProperty({}, 'passive', {
      // eslint-disable-next-line getter-return
      get() {
        support = true;
      }

    });
    window.addEventListener('test', null, opts);
  } catch (e) {//
  }

  return support;
}

var supportsPassive = testSupportsPassive();
var _ = {
  on(el, type, func, capture = false) {
    if (supportsPassive) {
      el.addEventListener(type, func, {
        capture,
        passive: true
      });
    } else {
      el.addEventListener(type, func, capture);
    }
  },

  off(el, type, func, capture = false) {
    el.removeEventListener(type, func, capture);
  }

};
exports._ = _;

var loadImageAsync = (item, resolve, reject) => {
  var image = new Image();

  if (!item || !item.src) {
    var err = new Error('image src is required');
    return reject(err);
  }

  image.src = item.src;

  if (item.cors) {
    image.crossOrigin = item.cors;
  }

  image.onload = function () {
    resolve({
      naturalHeight: image.naturalHeight,
      naturalWidth: image.naturalWidth,
      src: image.src
    });
  };

  image.onerror = function (e) {
    reject(e);
  };
};

exports.loadImageAsync = loadImageAsync;

var style = (el, prop) => {
  return typeof getComputedStyle !== 'undefined' ? getComputedStyle(el, null).getPropertyValue(prop) : el.style[prop];
};

var overflow = el => {
  return style(el, 'overflow') + style(el, 'overflow-y') + style(el, 'overflow-x');
};

var scrollParent = el => {
  if (!inBrowser) return;

  if (!(el instanceof HTMLElement)) {
    return window;
  }

  var parent = el;

  while (parent) {
    if (parent === document.body || parent === document.documentElement) {
      break;
    }

    if (!parent.parentNode) {
      break;
    }

    if (/(scroll|auto)/.test(overflow(parent))) {
      return parent;
    }

    parent = parent.parentNode;
  }

  return window;
};

exports.scrollParent = scrollParent;

function isObject(obj) {
  return obj !== null && typeof obj === 'object';
}

function noop() {}

class ImageCache {
  constructor({
    max
  }) {
    this.options = {
      max: max || 100
    };
    this._caches = [];
  }

  has(key) {
    return this._caches.indexOf(key) > -1;
  }

  add(key) {
    if (this.has(key)) return;

    this._caches.push(key);

    if (this._caches.length > this.options.max) {
      this.free();
    }
  }

  free() {
    this._caches.shift();
  }

}

exports.ImageCache = ImageCache;
}, function(modId) { var map = {}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574261, function(require, module, exports) {


exports.__esModule = true;
exports.default = void 0;

var _util = require("./util");

class ReactiveListener {
  constructor({
    el,
    src,
    error,
    loading,
    bindType,
    $parent,
    options,
    cors,
    elRenderer,
    imageCache
  }) {
    this.el = el;
    this.src = src;
    this.error = error;
    this.loading = loading;
    this.bindType = bindType;
    this.attempt = 0;
    this.cors = cors;
    this.naturalHeight = 0;
    this.naturalWidth = 0;
    this.options = options;
    this.rect = null;
    this.$parent = $parent;
    this.elRenderer = elRenderer;
    this._imageCache = imageCache;
    this.performanceData = {
      init: Date.now(),
      loadStart: 0,
      loadEnd: 0
    };
    this.filter();
    this.initState();
    this.render('loading', false);
  }
  /*
   * init listener state
   * @return
   */


  initState() {
    if ('dataset' in this.el) {
      this.el.dataset.src = this.src;
    } else {
      this.el.setAttribute('data-src', this.src);
    }

    this.state = {
      loading: false,
      error: false,
      loaded: false,
      rendered: false
    };
  }
  /*
   * record performance
   * @return
   */


  record(event) {
    this.performanceData[event] = Date.now();
  }
  /*
   * update image listener data
   * @param  {String} image uri
   * @param  {String} loading image uri
   * @param  {String} error image uri
   * @return
   */


  update({
    src,
    loading,
    error
  }) {
    var oldSrc = this.src;
    this.src = src;
    this.loading = loading;
    this.error = error;
    this.filter();

    if (oldSrc !== this.src) {
      this.attempt = 0;
      this.initState();
    }
  }
  /*
   * get el node rect
   * @return
   */


  getRect() {
    this.rect = this.el.getBoundingClientRect();
  }
  /*
   *  check el is in view
   * @return {Boolean} el is in view
   */


  checkInView() {
    this.getRect();
    return this.rect.top < window.innerHeight * this.options.preLoad && this.rect.bottom > this.options.preLoadTop && this.rect.left < window.innerWidth * this.options.preLoad && this.rect.right > 0;
  }
  /*
   * listener filter
   */


  filter() {
    Object.keys(this.options.filter).forEach(key => {
      this.options.filter[key](this, this.options);
    });
  }
  /*
   * render loading first
   * @params cb:Function
   * @return
   */


  renderLoading(cb) {
    this.state.loading = true;
    (0, _util.loadImageAsync)({
      src: this.loading,
      cors: this.cors
    }, () => {
      this.render('loading', false);
      this.state.loading = false;
      cb();
    }, () => {
      // handler `loading image` load failed
      cb();
      this.state.loading = false;
      if (!this.options.silent) console.warn("VueLazyload log: load failed with loading image(" + this.loading + ")");
    });
  }
  /*
   * try load image and  render it
   * @return
   */


  load(onFinish = _util.noop) {
    if (this.attempt > this.options.attempt - 1 && this.state.error) {
      if (!this.options.silent) console.log("VueLazyload log: " + this.src + " tried too more than " + this.options.attempt + " times");
      onFinish();
      return;
    }

    if (this.state.rendered && this.state.loaded) return;

    if (this._imageCache.has(this.src)) {
      this.state.loaded = true;
      this.render('loaded', true);
      this.state.rendered = true;
      return onFinish();
    }

    this.renderLoading(() => {
      var _this$options$adapter, _this$options$adapter2;

      this.attempt++;
      (_this$options$adapter = (_this$options$adapter2 = this.options.adapter).beforeLoad) == null ? void 0 : _this$options$adapter.call(_this$options$adapter2, this, this.options);
      this.record('loadStart');
      (0, _util.loadImageAsync)({
        src: this.src,
        cors: this.cors
      }, data => {
        this.naturalHeight = data.naturalHeight;
        this.naturalWidth = data.naturalWidth;
        this.state.loaded = true;
        this.state.error = false;
        this.record('loadEnd');
        this.render('loaded', false);
        this.state.rendered = true;

        this._imageCache.add(this.src);

        onFinish();
      }, err => {
        !this.options.silent && console.error(err);
        this.state.error = true;
        this.state.loaded = false;
        this.render('error', false);
      });
    });
  }
  /*
   * render image
   * @param  {String} state to render // ['loading', 'src', 'error']
   * @param  {String} is form cache
   * @return
   */


  render(state, cache) {
    this.elRenderer(this, state, cache);
  }
  /*
   * output performance data
   * @return {Object} performance data
   */


  performance() {
    var state = 'loading';
    var time = 0;

    if (this.state.loaded) {
      state = 'loaded';
      time = (this.performanceData.loadEnd - this.performanceData.loadStart) / 1000;
    }

    if (this.state.error) state = 'error';
    return {
      src: this.src,
      state,
      time
    };
  }
  /*
   * $destroy
   * @return
   */


  $destroy() {
    this.el = null;
    this.src = null;
    this.error = null;
    this.loading = null;
    this.bindType = null;
    this.attempt = 0;
  }

}

exports.default = ReactiveListener;
}, function(modId) { var map = {"./util":1630394574260}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574262, function(require, module, exports) {


exports.__esModule = true;
exports.default = void 0;

var _vue = require("vue");

var _util = require("./util");

var _default = lazy => {
  return {
    props: {
      tag: {
        type: String,
        default: 'div'
      }
    },
    emits: ['show'],

    render() {
      return (0, _vue.h)(this.tag, this.show && this.$slots.default ? this.$slots.default() : null);
    },

    data() {
      return {
        el: null,
        state: {
          loaded: false
        },
        rect: {},
        show: false
      };
    },

    mounted() {
      this.el = this.$el;
      lazy.addLazyBox(this);
      lazy.lazyLoadHandler();
    },

    beforeUnmount() {
      lazy.removeComponent(this);
    },

    methods: {
      getRect() {
        this.rect = this.$el.getBoundingClientRect();
      },

      checkInView() {
        this.getRect();
        return _util.inBrowser && this.rect.top < window.innerHeight * lazy.options.preLoad && this.rect.bottom > 0 && this.rect.left < window.innerWidth * lazy.options.preLoad && this.rect.right > 0;
      },

      load() {
        this.show = true;
        this.state.loaded = true;
        this.$emit('show', this);
      },

      destroy() {
        return this.$destroy;
      }

    }
  };
};

exports.default = _default;
}, function(modId) { var map = {"./util":1630394574260}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574263, function(require, module, exports) {


exports.__esModule = true;
exports.default = void 0;

var _util = require("./util");

/* eslint-disable max-classes-per-file */
var defaultOptions = {
  selector: 'img'
};

class LazyContainer {
  constructor({
    el,
    binding,
    vnode,
    lazy
  }) {
    this.el = null;
    this.vnode = vnode;
    this.binding = binding;
    this.options = {};
    this.lazy = lazy;
    this._queue = [];
    this.update({
      el,
      binding
    });
  }

  update({
    el,
    binding
  }) {
    this.el = el;
    this.options = Object.assign({}, defaultOptions, binding.value);
    var imgs = this.getImgs();
    imgs.forEach(el => {
      this.lazy.add(el, Object.assign({}, this.binding, {
        value: {
          src: 'dataset' in el ? el.dataset.src : el.getAttribute('data-src'),
          error: ('dataset' in el ? el.dataset.error : el.getAttribute('data-error')) || this.options.error,
          loading: ('dataset' in el ? el.dataset.loading : el.getAttribute('data-loading')) || this.options.loading
        }
      }), this.vnode);
    });
  }

  getImgs() {
    return Array.from(this.el.querySelectorAll(this.options.selector));
  }

  clear() {
    var imgs = this.getImgs();
    imgs.forEach(el => this.lazy.remove(el));
    this.vnode = null;
    this.binding = null;
    this.lazy = null;
  }

}

class LazyContainerManager {
  constructor({
    lazy
  }) {
    this.lazy = lazy;
    this._queue = [];
  }

  bind(el, binding, vnode) {
    var container = new LazyContainer({
      el,
      binding,
      vnode,
      lazy: this.lazy
    });

    this._queue.push(container);
  }

  update(el, binding, vnode) {
    var container = (0, _util.find)(this._queue, item => item.el === el);
    if (!container) return;
    container.update({
      el,
      binding,
      vnode
    });
  }

  unbind(el) {
    var container = (0, _util.find)(this._queue, item => item.el === el);
    if (!container) return;
    container.clear();
    (0, _util.remove)(this._queue, container);
  }

}

exports.default = LazyContainerManager;
}, function(modId) { var map = {"./util":1630394574260}; return __REQUIRE__(map[modId], modId); })
__DEFINE__(1630394574264, function(require, module, exports) {


exports.__esModule = true;
exports.default = void 0;

var _util = require("./util");

var _default = lazyManager => {
  return {
    props: {
      src: [String, Object],
      tag: {
        type: String,
        default: 'img'
      }
    },

    render(h) {
      return h(this.tag, {
        attrs: {
          src: this.renderSrc
        }
      }, this.$slots.default);
    },

    data() {
      return {
        el: null,
        options: {
          src: '',
          error: '',
          loading: '',
          attempt: lazyManager.options.attempt
        },
        state: {
          loaded: false,
          error: false,
          attempt: 0
        },
        rect: {},
        renderSrc: ''
      };
    },

    watch: {
      src() {
        this.init();
        lazyManager.addLazyBox(this);
        lazyManager.lazyLoadHandler();
      }

    },

    created() {
      this.init();
      this.renderSrc = this.options.loading;
    },

    mounted() {
      this.el = this.$el;
      lazyManager.addLazyBox(this);
      lazyManager.lazyLoadHandler();
    },

    beforeUnmount() {
      lazyManager.removeComponent(this);
    },

    methods: {
      init() {
        var {
          src,
          loading,
          error
        } = lazyManager._valueFormatter(this.src);

        this.state.loaded = false;
        this.options.src = src;
        this.options.error = error;
        this.options.loading = loading;
        this.renderSrc = this.options.loading;
      },

      getRect() {
        this.rect = this.$el.getBoundingClientRect();
      },

      checkInView() {
        this.getRect();
        return _util.inBrowser && this.rect.top < window.innerHeight * lazyManager.options.preLoad && this.rect.bottom > 0 && this.rect.left < window.innerWidth * lazyManager.options.preLoad && this.rect.right > 0;
      },

      load(onFinish = _util.noop) {
        if (this.state.attempt > this.options.attempt - 1 && this.state.error) {
          if (!lazyManager.options.silent) console.log("VueLazyload log: " + this.options.src + " tried too more than " + this.options.attempt + " times");
          onFinish();
          return;
        }

        var {
          src
        } = this.options;
        (0, _util.loadImageAsync)({
          src
        }, ({
          src
        }) => {
          this.renderSrc = src;
          this.state.loaded = true;
        }, () => {
          this.state.attempt++;
          this.renderSrc = this.options.error;
          this.state.error = true;
        });
      }

    }
  };
};

exports.default = _default;
}, function(modId) { var map = {"./util":1630394574260}; return __REQUIRE__(map[modId], modId); })
return __REQUIRE__(1630394574258);
})()
//miniprogram-npm-outsideDeps=["vue"]
//# sourceMappingURL=index.js.map
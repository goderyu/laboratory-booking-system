"use strict";

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

// small circle radius
var r1 = 5;
var r2 = 10;
var r3 = 15;
var width = window.innerWidth;
var height = window.innerHeight;

var minWH = Math.min(width, height);

var maxSize = undefined;
if (minWH < 430) {
	maxSize = minWH - 30;
} else {
	maxSize = 400;
}

// utils
// deg to radian
var rad = function rad(a) {
	return Math.PI * (a - 90) / 180;
};

// relative polar coordinates
var rx = function rx(r, a, c) {
	return c + r * Math.cos(rad(a, c));
};

var ry = function ry(r, a, c) {
	return c + r * Math.sin(rad(a));
};

// get hours, minutes, and seconds
var HMS = function HMS(t) {
	return {
		h: t.getHours(),
		m: t.getMinutes(),
		s: t.getSeconds()
	};
};

var pathStringVars = function pathStringVars(c, r, time) {
	// center, radius and time = this.props		
	// const {c,r,time} = p

	var _HMS = HMS(time);

	var h = _HMS.h;
	var m = _HMS.m;
	var s = _HMS.s;

	// divide 360 deg by 12hrs, 60min, and 60s

	var hAngFact = 30;
	var mAngFact = 6;
	var sAngFact = 6;

	// calc relative coordinates 		
	var hx = rx(r - 30, hAngFact * h, c);
	var hy = ry(r - 30, hAngFact * h, c);
	var mx = rx(r - 30, mAngFact * m, c);
	var my = ry(r - 30, mAngFact * m, c);
	var sx = rx(r - 30, sAngFact * s, c);
	var sy = ry(r - 30, sAngFact * s, c);

	return { hx: hx, hy: hy, mx: mx, my: my, sx: sx, sy: sy };
};

var TextTime = function TextTime(_ref) {
	var x = _ref.x;
	var y = _ref.y;
	var time = _ref.time;

	var str = time.toTimeString().slice(0, 8).replace(/:/g, " : ");
	return React.createElement(
		"text",
		{ x: x, y: y, className: "textTime" },
		str
	);
};

// Circle component
var Circle = function Circle(_ref2) {
	var cx = _ref2.cx;
	var cy = _ref2.cy;
	var r = _ref2.r;
	var cl = _ref2.cl;
	return React.createElement("circle", {
		cx: cx,
		cy: cy,
		r: r,
		className: cl
	});
};

// Single spike
var Spike = function Spike(_ref3) {
	var x1 = _ref3.x1;
	var x2 = _ref3.x2;
	var y1 = _ref3.y1;
	var y2 = _ref3.y2;
	return React.createElement("line", {
		className: "spike",
		x1: x1,
		x2: x2,
		y1: y1,
		y2: y2
	});
};

var spikeNodes = function spikeNodes(c, r) {
	var increment = 30;
	var nodes = [];

	for (var i = 1; i < 13; i++) {
		var ang = i * increment;

		var temp = React.createElement(Spike, {
			x1: rx(r - 5, ang, c),
			x2: rx(r - 10, ang, c),
			y1: ry(r - 5, ang, c),
			y2: ry(r - 10, ang, c),
			key: i
		});
		nodes.push(temp);
	}
	return nodes;
};

// populate Spikes
var Spikes = function Spikes(_ref4) {
	var c = _ref4.c;
	var r = _ref4.r;
	return React.createElement(
		"g",
		null,
		spikeNodes(c, r)
	);
};

// triangle component
var Triangle = function Triangle(_ref5) {
	var c = _ref5.c;
	var r = _ref5.r;
	var time = _ref5.time;

	var _pathStringVars = pathStringVars(c, r, time);

	var hx = _pathStringVars.hx;
	var hy = _pathStringVars.hy;
	var mx = _pathStringVars.mx;
	var my = _pathStringVars.my;
	var sx = _pathStringVars.sx;
	var sy = _pathStringVars.sy;

	var path = "M" + hx + "," + hy + " L" + mx + "," + my + " L" + sx + "," + sy + " L" + hx + "," + hy;
	return React.createElement("path", {
		className: "triangle",
		d: path
	});
};

// Secondary circles
var SecCircle = function SecCircle(_ref6) {
	var c = _ref6.c;
	var r = _ref6.r;
	var time = _ref6.time;

	var _pathStringVars2 = pathStringVars(c, r, time);

	var hx = _pathStringVars2.hx;
	var hy = _pathStringVars2.hy;
	var mx = _pathStringVars2.mx;
	var my = _pathStringVars2.my;
	var sx = _pathStringVars2.sx;
	var sy = _pathStringVars2.sy;

	return React.createElement(
		"g",
		null,
		React.createElement(Circle, { cl: "secCircle", cx: hx, cy: hy, r: r3 }),
		React.createElement(Circle, { cl: "secCircle", cx: mx, cy: my, r: r2 }),
		React.createElement(Circle, { cl: "secCircle", cx: sx, cy: sy, r: r1 })
	);
};

// main container

var Clock = function (_React$Component) {
	_inherits(Clock, _React$Component);

	function Clock() {
		_classCallCheck(this, Clock);

		var _this = _possibleConstructorReturn(this, _React$Component.call(this));

		_this.state = {
			time: new Date()
		};
		return _this;
	}

	Clock.prototype.render = function render() {
		var _this2 = this;

		var size = maxSize;

		var viewBox = "0 0 " + size + " " + size;

		var mid = size / 2;

		var paddedRadius = (size - 30) / 2;

		window.setTimeout(function () {
			_this2.setState({
				time: new Date()
			});
		}, 1000);

		return React.createElement(
			"svg",
			{ xmlns: "http://www.w3.org/svg/2000",
				viewBox: viewBox,
				width: size,
				height: size
			},
			React.createElement(Circle, {
				cl: "outerRing",
				cx: mid,
				cy: mid,
				r: mid - 5
			}),
			React.createElement(Circle, {
				cl: "primCircle",
				cx: mid,
				cy: mid,
				r: mid - 15
			}),
			React.createElement(Spikes, { c: mid, r: paddedRadius }),
			React.createElement(Triangle, { c: mid, r: paddedRadius, time: this.state.time }),
			React.createElement(SecCircle, { c: mid, r: paddedRadius, time: this.state.time }),
			React.createElement(TextTime, {
				time: this.state.time,
				x: mid,
				y: mid
			})
		);
	};

	return Clock;
}(React.Component);

ReactDOM.render(React.createElement(Clock, null), document.querySelector('.clock'));
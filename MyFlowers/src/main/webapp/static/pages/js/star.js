// JavaScript Document
var sArray = new Object;
sArray[0] = new Image;
sArray[0].src = "static/pages/images/evaluate/icon_star_1.gif";
for (var i = 1; i < 6; i++) {
	sArray[i] = new Image();
	sArray[i].src = "static/pages/images/evaluate/icon_star_2.gif";
}
var starTimer;
var pro;
var rate;
function initStar() {
	try {
		setProfix("star_");
		setStars(document.getElementById("service").value, 'service');
		setProfix("logistics_");
		setStars(document.getElementById("logistics").value, 'logistics');
		setProfix("commodity_");
		setStars(document.getElementById("commodity").value, 'commodity');
	} catch (e) {}
}
function showStars(starNum, rate) {
	try {
		clearStarTimer();
		greyStars();
		colorStars(starNum);
	} catch (e) {}
//	setStars(starNum, rate);
}
function setProfix(profix) {
	pro = profix ;
}
function colorStars(starNum) {
	try {
		for (var i = 1; i <= starNum; i++) {
			var tmpStar = document.getElementById(pro + i);
			tmpStar.src = sArray[starNum].src;
		}
	} catch (e) {}
}
function greyStars() {
	try {
		for (var i = 1; i < 6; i++) {
			var tmpStar = document.getElementById(pro + i);
			tmpStar.src = sArray[0].src;
		}
	} catch (e) {}
}
function greyAll(curpro, currate) {
	try {
		document.getElementById(currate).value = "";
		for (var i = 1; i < 6; i++) {
			var tmpStar = document.getElementById(curpro + i);
			tmpStar.src = sArray[0].src;
		}
	} catch (e) {}
}
function setStars(starNum, rate) {
	rate = rate ;
	try {
		clearStarTimer();
		var rating = document.getElementById(rate);
		rating.value = starNum;
		showStars(starNum);
	} catch (e) {}
}
function clearStars(currate) {
	rate = currate ;
	try {
		var rating = document.getElementById(rate);
		if (rating.value != '') {
			setStars(rating.value, rate);
		} else {
			greyStars();
		}
	} catch (e) {}
}
function resetStars() {
	try {
		clearStarTimer();
		var rating = document.getElementById(rate);
		if (rating.value != '') {
			setStars(rating.value, rate);
		} else {
			greyStars();
		}
	} catch (e) {}
}
function clearStarTimer() {
	if (starTimer) {
		clearTimeout(starTimer);
		starTimer = null;
	}
}
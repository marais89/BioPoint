var flag = true;
var cls = false;
var url = "/pages/profile.xhtml";
var postion = 0;
function unsize() {
	var t = document.getElementById('form:listsize');
	var modif = document.getElementById('form:modif');
	var ajout = document.getElementById('form:ajout');
	var supp = document.getElementById('form:supprim');
	if ((t.value) == 0) {
		modif.style.display = 'none';
		supp.style.display = 'none';
	}

	else if ((t.value) == 1) {
		modif.style.display = 'inline';
		supp.style.display = 'inline';
	} else if (t.value > 1) {
		supp.style.display = 'inline';
		modif.style.display = 'none';
	}
}
function undisplaymodif() {
	var supp = document.getElementById('form:supprim');
	var modif = document.getElementById('form:modif');
	if(modif != null || supp != null){
	if (modif.style.display != 'none') {
		modif.style.display = 'none';
		supp.style.display = 'none';
	}}
}

function displaymodif() {
	var supp = document.getElementById('form:supprim');
	var modif = document.getElementById('form:modif');
	if(modif != null || supp != null){
	if (modif.style.display != 'inline') {
		modif.style.display = 'inline';
		supp.style.display = 'inline';
	}}
}
function getsize() {
	var t = document.getElementById('form:listsize');
	var modif = document.getElementById('form:modif');
	var ajout = document.getElementById('form:ajout');
	var supp = document.getElementById('form:supprim');
	if(modif!= null){
	if ((t.value) == 0) {
		modif.style.display = 'none';
		supp.style.display = 'none';
	}

	else if ((t.value) == 1) {
		modif.style.display = 'inline';
		supp.style.display = 'inline';
	} else if (t.value > 1) {
		supp.style.display = 'inline';
		modif.style.display = 'none';
	}
}}
function displayconsole() {
	var bt = document.getElementsByClassName('consolebt');
	for (i = 0; i < bt.length; i++) {
		bt[i].style.display = 'inline';

	}
}
function undisplayconsole() {
	var bt = document.getElementsByClassName('consolebt');
	for (i = 0; i < bt.length; i++) {
		bt[i].style.display = 'none';

	}

}
function cachecol(vart) {
	if (vart == true) {
		document.getElementById("cache").style.visibility = 'hidden';
		document.getElementById("cache2").style.visibility = 'hidden';

	} else {
		document.getElementById("cache").style.visibility = 'visible';
		document.getElementById("cache2").style.visibility = 'visible';
	}

}

function tmmp() {
	setTimeout(mtaille(), 3000);
}

function mtaille() {

	tab = document.getElementById("formpersonnel:personnels");
	col = tab.getElementsByTagName("tr");
	longeur = col.length;

	border = document.getElementById("cache");
	border.style.height = (longeur - 1) * 2.9 + "%";

}

$(document).ready(function() {
	var t = document.getElementsByClassName('leftside');
	for (i = 0; i < t.length; i++) {
		if (t[i].href == window.location.href) {
			t[i].className += ' ui-widget-header selectedmenu';
			t[i].style.color = 'black';
			t[i].style.font = '16px';
		}
	}
});

function gotonext() {
	var t = document.getElementsByClassName('leftside');
	var l = t.length;
	t[postion].className = 'ui-menuitem-link leftside ui-corner-all';
	if (postion == l) {
		postion = 0;
	} else {
		postion++;
	}
	t[postion].className += ' ui-widget-header first leftside selectedmenu';
}

function getfirst() {

	var t = document.getElementsByClassName('leftside');
	t[0].className += ' ui-widget-header first selectedmenu';
	t[0].style.font = '16px';
	url = t[0].href;
	console.log(url);
	var input = document.getElementById('formwest:globalFilter');
	if (input.value.length == 0) {
		var t = document.getElementsByClassName('leftside');
		for (i = 0; i < t.length; i++) {
			if (t[i].href == window.location.href) {
				t[i].className += ' ui-widget-header';
				t[i].style.color = 'black';
				t[i].style.font = '16px';
			} else {

				t[i].className = 'ui-menuitem-link leftside ui-corner-all';
			}
		}
	}
}

function switchImg() {
	var t = document.getElementById('formheader:syncp');
	t.style.display = "none";
	var t = document.getElementById('formheader:syncp2');
	t.style.display = "inline";

}

function switchnotImg() {
	var t = document.getElementById('formheader:syncp');
	t.style.display = "inline";
	var t = document.getElementById('formheader:syncp2');
	t.style.display = "none";
}
function gotofirst(key) {
	if (key == 13) {
		document.location.href = url;
	}
}

function expandmenu() {
	$(".ui-panelmenu-content").css("display", "block"); // shows the menuitems
	$(".ui-panelmenu-header").addClass("ui-state-active"); // sets the submenu
															// header to active
															// state
	$(".ui-icon-triangle-1-e").removeClass("ui-icon-triangle-1-e").addClass(
			"ui-icon-triangle-1-s"); // sets the triangle icon to "expaned"
										// version
}

$(document).ready(
		function() {

			// cl.className+=' ui-widget-header';
			$(".ui-panelmenu-content").css("display", "block"); // shows the
																// menuitems
			$(".ui-panelmenu-header").addClass("ui-state-active"); // sets the
																	// submenu
																	// header to
																	// active
																	// state
			$(".ui-icon-triangle-1-e").removeClass("ui-icon-triangle-1-e")
					.addClass("ui-icon-triangle-1-s"); // sets the triangle
														// icon to "expaned"
														// version
		});

function colorSelect(index) {
	var t = document.getElementsByClassName('pane5');
	var i;
	index = index + 1;
	for (i = 0; i < t.length; i++) {
		if (i == index) {
			t[index].style.background = '#48D1CC';
		} else {
			t[i].style.backgroundColor = '#F5F5F5';
		}
	}
}

function selectall(str,str2)
{
	var m =document.getElementsByClassName('selectbt');	
	var t;
	var x="";
	var i;
	for(i=0;i<m.length;i++)
		{
		t=m[i].children;
		if (t.length==3)
		{x += str+",";
			for(var j=0;j<t.length;j++)
				{
				if(j==str){$(t[j]).addClass("ui-state-active");}
				else{$(t[j]).removeClass("ui-state-active");}
				}
		}
		if (t.length==2)
		{if(str==0){x += str+",";}else{x += (str-1)+",";}
			for(var j=0;j<t.length;j++)
			{
				if(str==0){if(j==0){$(t[j]).addClass("ui-state-active");}else{$(t[j]).removeClass("ui-state-active");}}
				else{
			if(j==str-1){$(t[j]).addClass("ui-state-active");}
			else{$(t[j]).removeClass("ui-state-active");}
			}}
		}
		
		}
	localStorage.setItem(str2, x);
}

function out()
{
alert("test reussie");	
}
function clearStorage()
{
	localStorage.clear();
}

function makeUpdate()
{  //alert("makeUpdate data !");
	var i;
	var a = document.getElementById('form:notvisible');
	var str = a.getAttribute('title');
	var tab = str.split("*");
	for(i=0;i<tab.length;i++)
		{
		var elm=tab[i].split(";");
		if(elm[0]=="" || elm[0]==null){}
		else{
		localStorage.setItem(elm[0],elm[1]);}
		}
}

function storageData(str1,str2)
{
	//alert("start store data");
	var m =document.getElementsByClassName('selectbt'); 
	var i,j,k;	
	var n;
	for(i=0;i<m.length;i++)
	{
	n= m[i].children;
	for(j=0;j<n.length;j++)
		{
		$(n[j]).removeClass("ui-widget");			
		}
	}
	var tab=[];
	for(i=0;i<m.length;i++)
		{
		n= m[i].children;
		for(j=0;j<n.length;j++)
			{
			if($(n[j]).hasClass( "ui-state-active" )){tab.push(j);}				
			}
		}
//	alert(str2+""+tab);
	localStorage.setItem(str2,tab);	
	
//	var a = document.getElementById('form:notvisible');
//	a.setAttribute('value', "hello");
}

function resetData()
{
	// alert("reset data !");
	var m =document.getElementsByClassName('selectbt');
	var n=m[0].children;
	var str,i,j;
		str = n[0].getAttribute('title');
	var tab=[];
	var tab2=[];
	
	tab= localStorage.getItem(str);
	if(tab==null || tab == "" || tab== " ")
		{ 
		for(i=0;i<m.length;i++)
		{
		n= m[i].children;
		for(j=0;j<n.length;j++)
			{
			if(j==n.length-1){$(n[j]).addClass("ui-state-active");}
			else{$(n[j]).removeClass("ui-state-active");}		
			}
		}
		}
	else{
	for(i=0;i<tab.length;i++)
		{if(tab[i]==","){;}
		else {tab2.push(tab[i]);}
		}
	for(i=0;i<m.length;i++)
	{
	n= m[i].children;
	for(j=0;j<n.length;j++)
		{
	var	x=tab2[i];
		if(j==x){$(n[j]).addClass("ui-state-active");}
		else{$(n[j]).removeClass("ui-state-active");}		
		}
	}}
	
	
	
}

function clearstorage()
{
	localStorage.clear();	
}

function activeb1()
{
	$('.b1').addClass("ui-state-active");
	$('.b2').removeClass("ui-state-active");
	$('.b3').removeClass("ui-state-active");
	$('.b3').removeClass("ui-state-hover");
	
}
function activeb2()
{
	$('.b2').addClass("ui-state-active");
	$('.b1').removeClass("ui-state-active");
	$('.b3').removeClass("ui-state-active");

}
function activeb3()
{
	$('.b3').addClass("ui-state-active");
	$('.b2').removeClass("ui-state-active");
	$('.b1').removeClass("ui-state-active");

}


function setActive(v1,v2) {
	
	var m =document.getElementsByClassName('formopajout:roles_data');	
	var t= m[v1];
	var n=t[1];
	var b;
	var i;
	for (i = 0; i < 3; i++) 
	{
		if(i==v2)
			{b=n[v2];$("b").addClass("ui-state-active");}
		else{b=n[i];$("b").removeClass("ui-state-active");
		}
		
	}
	
}

function clickElement()
{
	$(this).click(function(){$(this).addClass("ui-state-active");});
}

function afficheBoule(str) {
	var m = document.getElementsByClassName('westMenuUl');
	var t;
	var i;
	for (i = 0; i < m.length; i++) 
	{
		
		t=m[i].getAttribute('title');
		if(t==str)
			{ 
			m[i].style.visibility="visible";}
		else
			{m[i].style.visibility="hidden";}
	}
}

function initColorSelect() {
	var t = document.getElementsByClassName('pane5');
	var i;
	for (i = 0; i < t.length; i++) {
		t[i].style.background = '#F5F5F5';
	}

}

function colorCol() {
	var a = document.getElementById('form:paner');
	var b = a.children;
	var c = b[0];
	var d = c.children;
	var e = d[0];
	var f = e.children;
	f[3].style.background = '#48D1CC';
}

function findClass()
{
	var a = document.getElementById('form:paner');
	if ($(this).hasClass("your_Class")) 
	    alert("positive");            
	else              
	    alert("Negative");
}

function test(s)
{
	var t= document.getElementById("form:codex");
	var m= t.getAttribute('title');
	//alert(s); 
	//var win= window.open("../Report"+s+".pdf","nom_popup","menubar=no, status=no, scrollbars=no, menubar=no, width=1400, height=900");
	var titre="Report"+s+m+".pdf";
	var uri_enc = encodeURIComponent(titre);
	var win= window.open(uri_enc,"maPage","directories='no',location='no'");
 }
function init(){

	var modif = document.getElementById('form:modif');
	var supp = document.getElementById('form:supprim');
	if(modif!=null)
	{modif.style.display='none';
	supp.style.display='none';}
	};
	window.onload = init();
	

	

var code;
function createCode() 
{
 code="";
 var checkCode = document.getElementById("checkCode");
 var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
      'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
 for(var i=0;i<4;i++) 
 {
  var charNum = Math.floor(Math.random() * 36);
  code += codeChars[charNum];
 }
 if(checkCode) 
 {
  checkCode.className = "code";
  checkCode.innerHTML = code;
 }
}

function show()  
{
   var hideobj=document.getElementById("hidebg");
   hidebg.style.display="block"; 
   hidebg.style.height=document.body.clientHeight+260+"px";  
   document.getElementById("hidebox").style.display="block";
   document.getElementById("agreementtitle").style.display="block";
   
   document.getElementById("agreement").style.display="block";
   document.getElementById("hidebutton").style.display="block";
}
function hide()  
{
	document.getElementById("hidebox").style.display="none";
	document.getElementById("agreementtitle").style.display="none";
   
   document.getElementById("hidebg").style.display="none";
   document.getElementById("agreement").style.display="none";
   document.getElementById("hidebutton").style.display="none";
}
function doCheckCode()
{
	 var oTellphone=document.getElementById("tellphone").value;
    var partten = /^1[3,5,7,8]\d{9}$/;
    if(partten.test(oTellphone))
    {
    }
    else
    {
		oTellphone="";
       alert('电话号码格式不正确!');
	   return false;
    }
	var input=document.getElementById("verification").value;
		if(input==""){
			alert("验证码不能为空");
			return false;
		}
		else if(input.toLowerCase()!=code.toLowerCase()){
			alert("验证码不正确");
			return false;
		}
}
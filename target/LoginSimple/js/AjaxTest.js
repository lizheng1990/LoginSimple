/**
 * 
 */
function logincheck() {
	var AjaxURL = "./Login";
    var a=document.getElementById("username").value;
    var b=document.getElementById("password").value;
    var c=document.getElementsByClassName("remember-me_text").checked;
    if (a != null && b != null) {
        if (a.length > 2 && a.length < 17 && b.length > 2 && b.length < 17) {
            $.ajax({
                type : "post",
                dataType : "json",
                url : AjaxURL,
                data : $("#loginForm").serialize(),
                success : function(result) {
                    // alert(result["status"]);
                    if (result["status"] == 200) {
                        window.location.href=("./userinfo.html");
                        // document.getElementById("info").innerText = result["message"];
                    } else {
                        document.getElementById("info").innerText = result["message"];
                    }
                },
                error : function(result) {
                    alert("登录调用POST接口失败，请检查后重试！")
                }
            })
        } else {
            document.getElementById("info").innerText = "用户名或密码长度错误!";
        }
    } else {
        document.getElementById("info").innerText = "用户名或密码为空!";
    }
}

function logout() {
	var AjaxURL = "./Logout";
	$.ajax({
		type : "post",
		dataType : "json",
		url : AjaxURL,
		success : function(result) {
			// alert(result["status"]);
			if (result["status"] == 200) {
				alert(result["message"]);
                console.log(result["message"]);
				window.location.href="./index.html";
			} else {
				alert(result["message"]);
                console.log(result["message"]);
				window.location.href="./index.html";
			}
		},
		error : function(result) {
			alert("注销调用POST接口失败，请检查后重试！")
		}
	})
}

function userinfo() {
	var AjaxURL = "./UserInfo";
	$.ajax({
		type : "get",
		dataType : "json",
		url : AjaxURL,
		success : function(result) {
		document.getElementById("id").innerHTML=result["uid"];
		document.getElementById("xb").innerHTML=result["sex"];
		document.getElementById("nl").innerHTML=result["age"];
		document.getElementById("cs").innerHTML=result["city"];
		document.getElementById("ah").innerHTML=result["hobby"];
		document.getElementById("xx").innerHTML=result["info"];
		},
		error : function(result) {
			alert("userinfo调用POST接口失败，请检查后重试！")
		}
	})
}
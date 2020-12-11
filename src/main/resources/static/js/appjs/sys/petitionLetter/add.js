$().ready(function() {
	validateRule();
});

// var deptIdex='';
// $(function() {
// 	var deptId = '';
// 	getTreeData();
// 	load(deptId);
// });


$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {

	value=document.getElementById("servicedept")
	$('#signupForm').servicedept=value;

	var a=$('#signupForm').serialize();
	console.log(a);
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/petitionLetter/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}

function choiceSour() {
	layer.open({
		type:2,
		title:"选择来源",
		area : [ '300px', '450px' ],
		content:"/sys/petitionLetter/treeView"
	})
}

function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}

var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:"/sys/petitionLetter/treeViews"
	})
}

// var a="";
// var b="";
function loadDept(serviceDept,deptName){


	$("#serviceDept").val(serviceDept);
	$("#deptName").val(deptName);

	console.log($("#servicedept"))
	/*$("#xzbm").appendChild("<input id=\"deptId\" name=\"deptId\"  type=\"checkbox\" class=\"hidden form-check-input>")*/
	// a=$("#deptId").val(deptId).toString();
	// b=$("#deptName").val(deptName).toString();
	// console.log(a);
	// console.log(b);
}

// $('#jstree').on("changed.jstree", function(e, data) {
// 	if (data.selected == -1) {
// 		var opt = {
// 			query : {
// 				deptId : '',
// 			}
// 		}
// 		deptIdex='';
// 		$('#exampleTable').bootstrapTable('refresh', opt);
// 	} else {
// 		var opt = {
// 			query : {
// 				deptId : data.selected[0],
// 			}
// 		}
// 		deptIdex=data.selected[0];
// 		$('#exampleTable').bootstrapTable('refresh',opt);
// 	}
//
// });
//
// function getTreeData() {
// 	$.ajax({
// 		type : "GET",
// 		url : "/sys/dept/tree",
// 		success : function(tree) {
// 			loadTree(tree);
// 		}
// 	});
// }


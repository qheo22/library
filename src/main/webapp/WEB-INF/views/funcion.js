//게시물 삭제 부분
function boarddelete(bno){
	if (confirm('삭제하시겠습니까?')) {
		   $.ajax({
	             type : "get",
	             url : "boarddelete.do",
	             data : "bno=" + bno,
	             dataType : "html"
	         }).done(function(data){
	        	 alert('삭제완료 되었습니다.')
	        	 opener.location.reload();
	        	 self.close();
	         }
	         );
	} else {
	}
}


//게시물 수정 부분
function boardupdateform(bno){
	 location.href = "boardupdateform.do?bno="+bno;
	}

//댓글 작성 부분
function comment(bno,user_id){
	alert(user_id);
	if(user_id==null){
		 alert('로그인후 댓글을 작성할수 있습니다')}
	else if (confirm('작성하시겠습니까?')) {
		var comment = $("#comment").val();
		   $.ajax({
	             type : "get",
	             url : "boardcomment.do",
	             data : "board_Comment=" + comment + "&bno=" + bno + "&user_id=" +user_id,
	             dataType : "html"
	         }).done(function(data){
		        var num = data;
	        	alert('작성을 완료하였습니다.');
	        	$("#abcd").append(data);
           	  	$("#comment").val("");
	         }
	         );
	} else {
	}     
}



//댓글 삭제 부분
function commentdelete(comment_no){
	if (confirm('삭제하시겠습니까?')) {
		   $.ajax({
	             type : "get",
	             url : "commentdelete.do",
	             data : "comment_no=" + comment_no,
	             dataType : "html"
	         }).done(function(data){
		         console.log(comment_no);
	        	 alert('삭제완료 되었습니다.')
	        	 $("#table"+comment_no).remove();
	         }
	        );
	} else {
		
	}
}


//댓글 수정창 띄우는 부분
function commentupdateform(comment_no){
	jQuery('#formtextarea' + comment_no).css("display", "block");
	jQuery('#aa' + comment_no).css("display", "none");
	jQuery('#form'+ comment_no).css("display", "none");
	jQuery('#form1'+ comment_no).css("display", "block");
}



//댓글 수정중 취소
function commentupdatecancel(comment_no){
	var data = comment_no;
	jQuery('#formtextarea' + data).css("display", "none");
	jQuery('#aa' + data).css("display", "block");
	jQuery('#form1'+ data).css("display", "none");
	jQuery('#form'+ data).css("display", "block");
}





//댓글 수정 부분
function commentupdate(comment_no){
	var comment =$("#formtextarea"+comment_no).val();
	if (confirm('수정 하시겠습니까?')) {
		   $.ajax({
			   type : "get",
	             url : "commentupdate.do",
	             data : "comment_no=" + comment_no +"&board_comment=" + comment,
	             dataType : "json"
	         }).done(function(data){
	        	 alert('수정완료 되었습니다.')
	        	 jQuery('#form'+comment_no).css("display", "block");
	        	 jQuery('#form1'+comment_no).css("display", "none");
	        	 jQuery('#formtextarea' + comment_no).css("display", "none");
	   			 $("#aa"+comment_no).css("display", "block");
	        	 $("#aa"+comment_no).text("내용:"+comment);
	         }
	        );
	} else {
		
	}
}
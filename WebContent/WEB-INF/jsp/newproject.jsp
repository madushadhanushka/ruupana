<%@include file="header.jsp" %>

  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script>
$(document).ready(function(){
  $("#subbutton").click(function(){
          processFileUpload();
  });

  $("#loader1").on('change',prepareLoad);
  var files;
  function prepareLoad(event)
  {
      console.log(' event fired'+event.target.files[0].name);
      files=event.target.files;
  }
  function processFileUpload()
  {
      console.log("fileupload clicked");
      var oMyForm = new FormData();
      oMyForm.append("file", files[0]);
     $
        .ajax({dataType : 'json',
            url : "${pageContext.request.contextPath}/uploadMyFile",
            data : oMyForm,
            type : "POST",
            enctype: 'multipart/form-data',
            processData: false,
            contentType:false,
            success : function(result) {
            alert('success'+JSON.stringify(result));
            },
            error : function(result){
                alert('error'+JSON.stringify(result));
            }
        });
  }
});
</script>
</head>
<body>
<input type="file" name="loader1" id="loader1" />
<input type="button" id="subbutton" value="Upload"/>
 <br><a href="selcolumn">
 <button class="btn btn-info">
												<i class="ace-icon fa fa-check bigger-110"></i>
												Next
											</button></a>
<%@include file="footer.jsp" %>
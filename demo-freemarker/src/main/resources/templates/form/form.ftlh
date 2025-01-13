<html>
	<head>
		<title>Spring boot form submission</title>
	</head>
	<body>
		<h1>Spring boot form submission</h1>
		<#if user?? >
			dados enviados no formulario:<br/>
			Name: ${user.name}<br/>
			Message: ${user.message}<br/>
		<#else>	
			<form action="/form" method="post">
				<div>Name:</div>
				<input type="text" name="name" >
				<br/><br/>
				
				<div>Message:</div>
				<textarea rows="5" cols="5" name="message"></textarea>
				<br/><br/>
				
				<input type="submit" value="Submit" >
				<input type="reset" value="Reset" >
			</form>		
		</#if>
	</body>
</html>
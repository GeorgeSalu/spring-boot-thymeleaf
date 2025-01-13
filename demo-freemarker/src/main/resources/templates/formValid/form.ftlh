<#import "/spring.ftl" as spring />

<!DOCTYPE html>  
<html lang="en">  
    <head>
        <meta charset="UTF-8">
        <title>Form Data Binding and Validation</title>
        <style type="text/css">
        	body * {  
			  font-weight: 400;
			}
			
			form b {  
			  color: red;
			}
			        	
        </style>
    </head>
    <body>
        <h2>Form Data Binding and Validation</h2>

        <#if user?? && noErrors??>
            Your submitted data<br>
            First name: ${person.firstName}<br>
            Last name: ${person.lastName}<br>
            Email: ${person.email}<br>
        <#else>
            <form action="/valid/form" method="post">
                First name:<br>
                <@spring.formInput "person.firstName"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                Last name:<br>
                <@spring.formInput "person.lastName"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                Email:<br>
                <@spring.formInput "person.email"/>
                <@spring.showErrors "<br>"/>
                <br><br>
                <input type="submit" value="Submit">
            </form>
        </#if>

    </body>
</html>

# cybersecuritybase-project
Template for the first course project.

Same report that I have in https://cybersecuritybase.mooc.fi/project/

OWASP top ten list: https://www.owasp.org/images/7/72/OWASP_Top_10-2017_(en).pdf.pdf

### A1: Injection.
Application allows code injection. More specifically, you can inject malicious code into the /form/accountname site, which can be 
run when the page is loaded. The page consists of your account name, possibility to delete your account, signup form for the event, and 
a list of people who have signed up. The list shows the name people have entered to the signup form and it is fetched from database when loading
the screen. 
To test this, you must create an account and log in with it (create account button on login screen, successful creation redirects to login screen, then login with the username and password you
just created). This should redirect you to /form/youraccountname and post a fake entry. The code that can be injected
should be typed into the 'name' field of the form. For example, typing 

<script language="javascript">
    window.location.href = "http://google.com"
</script> 

as the name and submitting it would redirect anyone loading the page to the given address. 
With thymeleaf, this could be fixed simply by using th:text instead of th:utext when iterating through the list of people who
have registered to the event in the form.html file. If using th:text instead of th:utext, the malicious code that has been written into the "name" field will be shown as a string and it will not be executed. 
In addition to this, could also use some sort of process where the name and address fields only accept letters and numbers, and everything else would be removed.
This way, even though code would be sent to the application when signing up, it would be stripped of all else except letters and numbers, and when
the signup is loaded to the page, it would be just letters and numbers.




### A2: Application allows automated attacks. 
To test this you can add a bunch of different users/passwords to the database (click create account button in /login, and create accounts one by one),
and send post requests to /login with paramaters "username=yourselectedusername&password=yourselectedpasswords". In case of a successful login, you
should be redirected to /form/accountname page. If wrong login, you should be redirected to /login. There is no delays
between requests and no timeouts, no matter how many requests are made. 

Also, application permits weak passwords. There is no 
checks against weak or well-known passwords. There is not even a check for if there is a password at all, you can leave the password field empty when creating an account.

Weak passwords could be fixed by, for example, testing new and changed passwords against a list of top 10000
worst passwords, and/or requiring the password to be of a certain length and require numbers and upper/lowercase letters (and perhaps even 
special letters such as !, &, %, etc). 

Against automated attacks, could add delays between requests and timeouts after enough wrong attempts. Could make
it so that after an account has tried logging in a few times (for example 5 failed attempts), would have to fill a reCAPTCHA or account is locked and the user (if there was 
an email address required when creating account) is sent email. 


### A3: Since application stores passwords in plain text and they are not salted. 
I'm not sure if this belongs to A2 as well, since they seem to be quite similar in description. 
When creating an account it is sent to /create and it takes the username and password parameters 
and stores them to database as they are. When logging in the given username is used to find a Customer with that username in the database. It's username and password is compared to given username and password.
(also a null check if no customer found with given name). I've added a "print password" just after the comparison is made. So this can be
checked by creating an account and logging in with it. The password should be printed on the screen. 
Passwords should be salted. Should use some sort of password encoder.

### A5: broken access control. 
In the application it is possible to delete other users accounts. The deleting happens
by sending a delete request to the address /form/accountname. The application does not have any checks to ensure that 
the account requesting delete is the same as the account that is being deleted. In fact, one does not have to be logged in to delete an account.
You can try this by creating an account, for example an account with name "asd". Then you could send a delete request to /form/accountname (for example, in command line: curl http://localhost:8080/form/asd -X "DELETE"  ).
and try to login with the account. It should not exist anymore since you just deleted it.
As such, anyone can delete any other person's account as long as they know that persons account name. This could be fixed by adding a check to the delete method that
(for example) receives the id of the account requesting delete (through some secure way, so it cannot be forged), and checks it against the database. 


### A10: application does not log any event. As such, it is vulnerable to probing. If this were a real application, should
log atleast events such as logins, failed logins, creating/deleting account. Should also make sure that warnings and errors
generate clear, adequate log messages, and the logs should be stored somewhere (not locally).

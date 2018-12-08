# FamilySearch API README 

I hope that this will answer many questions relating to how to use the FamilySearch API in the application.

If this doesn't answer your questions, you can message me, or ask a question to the [FamilySearch Developer Network Google Group](https://groups.google.com/a/ldsmail.net/forum/#!forum/fsdn)

## The Postman Collection

The purpose of the Postman collection is to make it as EASY as possible for collaborators and contributors to test out the endpoints and know how to implement the in-app services we want. Postman makes it easy to see exactly what urls, methods, parameters, headers and authorizations are needed for the HTTP request to go through, and examples of what the response from the API should look like.

When you are looking at one of the example requests, you will see {{ ... }} in some of the fields, and those are where you need to input a token/secret/password which we don't want to put into a public git repository :) 

You will want to check all of the following types of fields for {{ ... }} that you need to fill in.
1. URL  (i.e. "/platform/tree/persons/{{person_id}}/parents")
1. Parameters (i.e @/ancestry)
1. Authorization (Token is stored in that tab under the "Bearer" authorization type)
    1. For all requests after the initial authentication, the token is required, so you will need to have an up-to-date token in the Authorization tab of all of your other Requests while you are testing them out. 
1. Body (Most of the initial authentication secrets are in the POST body of the /token POST request)


## Initial Authentication

You will want to create your own FamilySearch Developer account (if you don't have one) and create an app associated with this project, then use those developer secrets to fill in the blanks in the Postman Collection. 

There are a few possible methods for authenticating, but we chose to go with the basic password authentication. 
- Under "Password Authentication for Desktop and Mobile Apps" [in the Authentication Guide](https://www.familysearch.org/developers/docs/guides/authentication)

Basically, you just need to get the username and password from the user, send it to the /token endpoint and get a token back, which is necessary to use any of the other endpoints. While designing the app you'll want to remember that you need to have the user accept the FamilySearch terms of service [and a few other details](https://www.familysearch.org/developers/docs/certification/authentication#how2-AT01-authentication-native)

Try it out in the Postman collection

## Get the Current Person

Grabs the family tree person that is attached to the user who has logged into the app. 

This is important because we need to get the ID of the current person, so we can stick that ID in a parameter to the /ancestry endpoint. 

In the future, maybe we might not use this endpoint if the user wants to supply a person-ID that we use as the root of the traversal.

Try it out in the Postman collection

## Traverse the tree

We had thought about using Get-Person and Get-Parents to traverse through a tree, but then we found the /ancestry endpoint. The /ancestry endpoint gives you a whole tree in a level-order-traversal-ordered list. The endpoint makes use of the [Ahnen Number System](https://en.wikipedia.org/wiki/Ahnentafel) and calls that number the "Ascendency Number" in the response object.

As I was writing this README, I realized that the /ancestry endpoint doesn't necessarily give all the life-event information that we need to preview for the user, so we will need to iterate through the given list and do a GET /person request on the ID of each person to get all their life events, and then present them to the user.

Try it out in the Postman collection

## Read Person

I missed creating an example request in the Postman collection for this endpoint, but it should be easy to create after using the other three. You would model it especially after the /current-person endpoint request, but the endpoint that we would use here is .../person/{{ID_here}}. Here is a [link to the Example Request](https://www.familysearch.org/developers/docs/api/tree/Read_Person_usecase) for this endpoint. 

## Possible Features in the Future

We will probably expand the Postman collection if we start adding features. 

Here are some ideas/examples for using the FS API to implement future features.

1. If we wanted to make it easier to add life-events from great aunts and uncles, instead of just direct ancestors, then we would want to traverse the tree with /ancestry and then call an endpoint to get the family relationships of each direct ancestor and add those persons into the preview staging area. 
1. If we want to add ancestors that are not direct ancestors of the user (maybe direct ancestors of the spouse of the user), then we might want to change the root user (id) that we call /ancestry from. The user would then be supplying that and we would want to have some fairly robust error-handling there, and maybe some input-validation.

# FAQ

## Creating a FamilySearch Developer account
.. IDK . I just googled "FamilySearch developer account" and I made an account pretty easily.

## How do I log into my Integration (Sandbox) Account to add family members that I can test with?
1. Go to https://integration.familysearch.org
1. Log in with your sandbox username and password, which are given to you in the console of your FamilySearch developer account.

## Common Authentication Problems
- I came across a problem that was, apparently, kind of common and I just had to email devsupport@familysearch.org to have them fix it for me. It was a setting in my account, I guess. [Here is the Link](https://groups.google.com/a/ldsmail.net/forum/#!topic/fsdn/RQnZh8RopCk) to the Google Group Discussion about it. 

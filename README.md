# Cinema-Room-Rest-Service-Project
## About
A Spring REST service that helps manage a small movie theater. The service can show all available seats, refund and sell tickets, and display theater statistics. The theater has 9 rows with 9 seats, and information is returned in JSON format.
## Examples
### Seat Availability
The /seats endpoint handles a GET request and returns a JSON object that displays seat availability and pricing:
```  
{
    "total_rows":9,
    "total_columns":9,
    "available_seats":[
        {
             "row":1,
             "column":1,
             "price":10
        },
        {
             "row":1,
             "column":2,
             "price":10
        },
        
        ..............
        
        {
             "row":9,
             "column":8,
             "price":8
        },
        {
             "row":9,
             "column":9,
             "price":8
        }
    ]
}
```      
### Purchasing and Returning Tickets
The /purchase endpoint handles a POST request and marks a booked ticket as purchased. If the ticket is taken, the response will be a 400 (Bad Request) status code.
```
{
    "error": "The ticket has been already purchased!"
}
```
If the user passes a row or column that does not exist (i.e., the row or column is out of bounds), the response will be a 400 (Bad Request) status code.
```
{
    "error": "The number of a row or a column is out of bounds!"
}
```
The /return endpoint allows users to return tickets. Tokens are used to secure the refund process. The following is an example of a response to a successful purchase request.
```
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
         "row": 1,
         "column": 1,
         "price": 10
     }
 }
 ```
The following is an example of a response to a successful return request:
```
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```
In the event the token used to return the ticket is incorrect, the program responds with a 400 (Bad Request) status code:
```
{
    "error": "Wrong token!"
}
```
### Theater Statistics
The /stats endpoint handles a POST request. If the request contains the correct password, the theater's statistics are displayed as follows:
```
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```
If the password is incorrect, the program responds with a 401 (Unauthorized) status code.
```
{
    "error": "The password is wrong!"
}
```

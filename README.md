# StockViewer

It is a plain springboot application. 
Upon initialization it exposes some REST endpoints with sample stock data, open for viewing/creating/updating the stocks.

To view all the stocks available in the memory, use the URL below with a GET method type
http://localhost:8090/api/stocks/
{
   {
        "id": 1,
        "name": "Google",
        "price": 100,
        "updatedTime": "2019-03-20T23:34:25.578+0000"
    },
    {
        "id": 2,
        "name": "Microsoft",
        "price": 102,
        "updatedTime": "2019-03-20T23:34:25.672+0000"
    }
}

To view a particular stock by Id, use the below GET URL 
http://localhost:8090/api/stocks/4 -> 4 is the stockId
Sample response 
{
    "id": 4,
    "name": "SpaceX",
    "price": 95,
    "updatedTime": "2019-03-20T23:34:25.672+0000"
}

To create a new stock, use a POST request with the Stock object in the body 
http://localhost:8090/api/stocks/
Sample body to create new entry 
{
    "name": "Satyam",
    "price": 199,
    "updatedTime": "2018-03-20T21:13:58.148+0000"
}

To update an existing object use the given URL with PUT method type
http://localhost:8090/api/stocks/5 -> 5 is the stock id 
Sample body 
{
    "name": "Satyam29",
    "price": 205,
    "updatedTime": "2019-03-20T21:13:58.148+0000"
}

Each input request is validated and correspnding response is sent 

# Feed Consumer

This is a implementation of a feed consumer with two endpoints.
1. A load enpoint that allows providers to load content data to the system.
2. A total provider endpoint returning the total ammount of records the provider has loaded

The first endpoint contains 


## Disclaimer

Since documentation was very open, and many points weren´t settled I have taken the following considerations.

1. The second endpoint returns the "total" data of a provider, since it is not established what "total" it means, it will return the total amount of records the requested provider has submitted.

2. Since there was no specific information about the input data I had to do reverse engineering. It took me the longest.
   
3. I found a "feature" in the documentation, it says recordIndex is a int32 (int) I found out it was actually a int64 (long)

4. Since the business logic doesn´t need to have different behaviour than the incoming dto I didn´t map the entities in new objects, if it is required please let me know and I will add it to the development.

5. I have never worked with hexagonal architecture before, I have read about it online, so I tried to use it here, but I am not sure about have implemented it properly, if you see something to improve, please let me know, I would like to learn.

6. Due to the lack of test cases, and the reverse engineering of the development I couldn´t use TDD properly.

7. The data parse happens in the infrastructure layout, in MVC it would happen in the service layer, but since I am trying to do a hexagonal architecture I think as long as it is not in inner layers domain it should remain in the infrastructure layer.

